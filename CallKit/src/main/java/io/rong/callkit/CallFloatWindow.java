package io.rong.callkit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.rong.calllib.IRongCallListener;
import io.rong.calllib.RongCallClient;
import io.rong.calllib.RongCallCommon;
import io.rong.calllib.RongCallSession;
import io.rong.calllib.message.CallSTerminateMessage;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.utils.NotificationUtil;
import io.rong.imlib.model.Conversation;
import io.rong.message.InformationNotificationMessage;

/**
 * Created by chenjianrun on 2018/1/11.
 */

public class CallFloatWindow extends BaseFloatWindow implements IRongCallListener {
    private static final String TAG = "CallFloatWindow";
    private Context mContext;
    private View mRoot;
    private FrameLayout mFlCallView;
    private TextView mTvShowTime;
    private SurfaceView mLocalVideo;
    private long mTime;
    private ScheduledExecutorService timer;
    private RongCallSession mCallSession;
    private Bundle mBundle;
    private Boolean isShown = false;
    private RongCallCommon.CallMediaType mediaType;

    public CallFloatWindow(Context context) {
        super(context);
        this.mContext = context;
    }


    private void initView(Context context){
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        if (mediaType.equals(RongCallCommon.CallMediaType.AUDIO)) {
            mRoot = View.inflate(context, R.layout.rc_voip_float_box,null);
            mTvShowTime = (TextView) mRoot.findViewById(R.id.rc_time);
            if (mRoot.getParent() == null){
                addView(mRoot);
            }
        } else {
            mRoot = View.inflate(context, R.layout.layout_float_call_view,null);
            mFlCallView = (FrameLayout) mRoot.findViewById(R.id.fl_call_view);
            mTvShowTime = (TextView) mRoot.findViewById(R.id.rc_time);
            lp.width = dip2px(mContext, 100);
            lp.height = dip2px(mContext, 180);
            if (mRoot.getParent() == null){
                addView(mRoot, lp);
            }
        }

    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public void show(Bundle bundle, SurfaceView surfaceView){
        if (isShown) {
            return;
        }
        isShown = true;

        mediaType = RongCallCommon.CallMediaType.valueOf(bundle.getInt("mediaType"));
        initView(mContext);
        mLocalVideo = surfaceView;
        mCallSession = RongCallClient.getInstance().getCallSession();
        long activeTime = mCallSession != null ? mCallSession.getActiveTime() : 0;
        mTime = activeTime == 0 ? 0 : (System.currentTimeMillis() - activeTime) / 1000;
        mBundle = bundle;
        setupTime();


        if (mediaType.equals(RongCallCommon.CallMediaType.AUDIO)) {
            handleAudio();
        } else {
            handleVideo();
        }
        RongCallClient.getInstance().setVoIPCallListener(this);
        super.showView(this);
    }

    public void hide() {
        RongCallClient.getInstance().setVoIPCallListener(RongCallProxy.getInstance());
        if (isShown && null != mRoot) {
            mFlCallView.removeAllViews();
            removeView(mRoot);
            timer.shutdownNow();
            timer = null;
            isShown = false;
            mRoot = null;
            mTime = 0;
            mBundle = null;
            Log.d(TAG, "hide: removeAllViews");
        }


    }

    /**
     * 处理视频媒体
     */
    private void handleVideo(){
        if (mLocalVideo != null) {
            if (mLocalVideo.getParent() != null) {
                ((ViewGroup)mLocalVideo.getParent()).removeView(mLocalVideo);
            }
            mLocalVideo.setTag(mCallSession.getSelfUserId());
            mFlCallView.addView(mLocalVideo);
        }
    }

    /***
     * 处理音频媒体
     */
    private void handleAudio(){

    }

    public void dismiss() {
        super.hideView();
    }


    @Override
    public void onClickToResume() {
        //当快速双击悬浮窗时，第一次点击之后会把mBundle置为空，第二次点击的时候出现NPE
        if (mBundle == null) {
//            RLog.d(TAG, "onClickToResume mBundle is null");
            return;
        }
        RongCallClient.getInstance().setVoIPCallListener(RongCallProxy.getInstance());
        Intent intent = new Intent(mBundle.getString("action"));
        intent.putExtra("floatbox", mBundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("callAction", RongCallAction.ACTION_RESUME_CALL.getName());
        mContext.startActivity(intent);
        mBundle = null;
        super.hideView();
    }

    @Override
    public void onCallOutgoing(RongCallSession rongCallSession, SurfaceView surfaceView) {

    }

    @Override
    public void onCallConnected(RongCallSession rongCallSession, SurfaceView surfaceView) {

    }

    @Override
    public void onCallDisconnected(RongCallSession callProfile, RongCallCommon.CallDisconnectedReason reason) {
        String senderId;
        String extra = "";
        senderId = callProfile.getInviterUserId();
        switch (reason) {
            case HANGUP:
            case REMOTE_HANGUP:
                if (mTime >= 3600) {
                    extra = String.format("%d:%02d:%02d", mTime / 3600, (mTime % 3600) / 60, (mTime % 60));
                } else {
                    extra = String.format("%02d:%02d", (mTime % 3600) / 60, (mTime % 60));
                }
                setupTime();
                break;
            default:
                break;
        }

        if (!TextUtils.isEmpty(senderId)) {
            switch (callProfile.getConversationType()) {
                case PRIVATE:
                    CallSTerminateMessage callSTerminateMessage = new CallSTerminateMessage();
                    callSTerminateMessage.setReason(reason);
                    callSTerminateMessage.setMediaType(callProfile.getMediaType());
                    callSTerminateMessage.setExtra(extra);
                    if (senderId.equals(callProfile.getSelfUserId())) {
                        callSTerminateMessage.setDirection("MO");
                        RongIM.getInstance().insertOutgoingMessage(Conversation.ConversationType.PRIVATE, callProfile.getTargetId(),
                                io.rong.imlib.model.Message.SentStatus.SENT, callSTerminateMessage, null);
                    } else {
                        callSTerminateMessage.setDirection("MT");
                        io.rong.imlib.model.Message.ReceivedStatus receivedStatus = new io.rong.imlib.model.Message.ReceivedStatus(0);
                        RongIM.getInstance().insertIncomingMessage(Conversation.ConversationType.PRIVATE, callProfile.getTargetId(),
                                senderId, receivedStatus, callSTerminateMessage, null);
                    }
                    break;
                case GROUP:
                    InformationNotificationMessage informationNotificationMessage;
                    if (reason.equals(RongCallCommon.CallDisconnectedReason.NO_RESPONSE)) {
                        informationNotificationMessage = InformationNotificationMessage.obtain(RongContext.getInstance().getString(R.string.rc_voip_audio_no_response));
                    } else {
                        informationNotificationMessage = InformationNotificationMessage.obtain(RongContext.getInstance().getString(R.string.rc_voip_audio_ended));
                    }

                    if (senderId.equals(callProfile.getSelfUserId())) {
                        RongIM.getInstance().insertOutgoingMessage(Conversation.ConversationType.GROUP, callProfile.getTargetId(),
                                io.rong.imlib.model.Message.SentStatus.SENT, informationNotificationMessage, null);
                    } else {
                        io.rong.imlib.model.Message.ReceivedStatus receivedStatus = new io.rong.imlib.model.Message.ReceivedStatus(0);
                        RongIM.getInstance().insertIncomingMessage(Conversation.ConversationType.GROUP, callProfile.getTargetId(),
                                senderId, receivedStatus, informationNotificationMessage, null);
                    }
                    break;
                default:
                    break;
            }
        }
        Toast.makeText(mContext, mContext.getString(R.string.rc_voip_call_terminalted), Toast.LENGTH_SHORT).show();

        if ( mRoot != null) {
            removeView(mRoot);
            timer.shutdownNow();
            timer = null;
            mRoot = null;
            mTime = 0;
            isShown = false;
        }
        super.hideView();
        NotificationUtil.clearNotification(mContext, BaseCallActivity.CALL_NOTIFICATION_ID);
        RongCallClient.getInstance().setVoIPCallListener(RongCallProxy.getInstance());
    }

    @Override
    public void onRemoteUserRinging(String s) {

    }

    @Override
    public void onRemoteUserJoined(String s, RongCallCommon.CallMediaType callMediaType, SurfaceView surfaceView) {

    }

    @Override
    public void onRemoteUserInvited(String s, RongCallCommon.CallMediaType callMediaType) {

    }

    @Override
    public void onRemoteUserLeft(String s, RongCallCommon.CallDisconnectedReason callDisconnectedReason) {

    }

    @Override
    public void onMediaTypeChanged(String s, RongCallCommon.CallMediaType callMediaType, SurfaceView surfaceView) {

    }

    @Override
    public void onError(RongCallCommon.CallErrorCode callErrorCode) {

    }

    @Override
    public void onRemoteCameraDisabled(String s, boolean b) {

    }

    private void setupTime() {
        final Handler handler = new Handler(Looper.getMainLooper());
        timer = Executors.newScheduledThreadPool(1);
        timer.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTime++;
                        if (mTime >= 3600) {
                            mTvShowTime.setText(String.format("%d:%02d:%02d", mTime / 3600, (mTime % 3600) / 60, (mTime % 60)));
                        } else {
                            mTvShowTime.setText(String.format("%02d:%02d", (mTime % 3600) / 60, (mTime % 60)));
                        }
                    }
                });
            }
        },0,1, TimeUnit.SECONDS);

    }
}

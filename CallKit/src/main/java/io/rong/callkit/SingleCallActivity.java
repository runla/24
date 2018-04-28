package io.rong.callkit;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fansonlib.utils.MyPermissionHelper;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.recyclerview.AutoLoadRecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.rong.callkit.Activity.ImageShowActivity;
import io.rong.callkit.Constants.BroadcastTag;
import io.rong.callkit.adapter.MessageAdapter;
import io.rong.callkit.utils.SettingAlertPermission;
import io.rong.calllib.CallUserProfile;
import io.rong.calllib.RongCallClient;
import io.rong.calllib.RongCallCommon;
import io.rong.calllib.RongCallSession;
import io.rong.calllib.message.CallSTerminateMessage;
import io.rong.common.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.utilities.PermissionCheckUtil;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;
import io.rong.message.TextMessage;

public class SingleCallActivity extends BaseCallActivity implements Handler.Callback, RongIMClient.OnReceiveMessageListener, IRongCallback.ISendMessageCallback, MyInputDialog.OnTextSendListener, MessageAdapter.ClickMessageCallback,View.OnClickListener {
    public static final int REQUEST_CODE_MESSAGE = 888;
    private static final String TAG = "VoIPSingleActivity";
    private LayoutInflater inflater;
    private RongCallSession callSession;
    private RelativeLayout mRlBack;
    private FrameLayout mLPreviewContainer;
    private FrameLayout mSPreviewContainer;
    private FrameLayout mButtonContainer;
    private LinearLayout mUserInfoContainer;
    private Boolean isInformationShow = false;

    private boolean muted = false;
    private boolean handFree = false;
    private boolean startForCheckPermissions = false;
    private boolean isCloseCamera = false;
    private int EVENT_FULL_SCREEN = 1;

    private String targetId = null;
    private RongCallCommon.CallMediaType mediaType;
    private boolean localViewInSmall = true;    // 本地视频显示在小屏幕
    private AutoLoadRecyclerView mAutoLoadRecyclerView;
    private MessageAdapter mAdapter;
    private MyPermissionHelper mPermissionHelper;
    private boolean firstRequestPermission = true;
    private boolean firstConnected = true;

    @Override
    final public boolean handleMessage(Message msg) {
        if (msg.what == EVENT_FULL_SCREEN) {
            hideVideoCallInformation();
            return true;
        }
        return false;
    }

    @Override
    @TargetApi(23)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_call);

        Intent intent = getIntent();
        mLPreviewContainer = (FrameLayout) findViewById(R.id.rc_voip_call_large_preview);
        mSPreviewContainer = (FrameLayout) findViewById(R.id.rc_voip_call_small_preview);
        mButtonContainer = (FrameLayout) findViewById(R.id.rc_voip_btn);
        mUserInfoContainer = (LinearLayout) findViewById(R.id.rc_voip_user_info);
        mRlBack = (RelativeLayout) findViewById(R.id.rl_back);
        mRlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionForFloatBox();
            }
        });

        startForCheckPermissions = intent.getBooleanExtra("checkPermissions", false);
        RongCallAction callAction = RongCallAction.valueOf(intent.getStringExtra("callAction"));

        if (callAction.equals(RongCallAction.ACTION_OUTGOING_CALL)) {
            if (intent.getAction().equals(RongVoIPIntent.RONG_INTENT_ACTION_VOIP_SINGLEAUDIO)) {
                mediaType = RongCallCommon.CallMediaType.AUDIO;
            } else {
                mediaType = RongCallCommon.CallMediaType.VIDEO;
            }
        } else if (callAction.equals(RongCallAction.ACTION_INCOMING_CALL)) {
            callSession = intent.getParcelableExtra("callSession");
            mediaType = callSession.getMediaType();
        } else {
            callSession = RongCallClient.getInstance().getCallSession();
            if (callSession != null) {
                mediaType = callSession.getMediaType();
            }
        }
        if (mediaType != null) {
            inflater = LayoutInflater.from(this);
            initView(mediaType, callAction);

            /*if (!requestCallPermissions(mediaType, REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS)) {
                return;
            }*/
            checkPermission(true, null);
//            FloatWindowRequest.requestPermission(SingleCallActivity.this);
//            checkPermissionForFloatBox();
            setupIntent();
        } else {
            RLog.w(TAG, "恢复的瞬间，对方已挂断");
            setShouldShowFloat(false);
            getCallFloatView().hide();
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        RLog.d(TAG, "onNewIntent");
        firstConnected = false;
        startForCheckPermissions = intent.getBooleanExtra("checkPermissions", false);
        RongCallAction callAction = RongCallAction.valueOf(intent.getStringExtra("callAction"));
        if (callAction == null) {
            return;
        }
        if (callAction.equals(RongCallAction.ACTION_OUTGOING_CALL)) {
            if (intent.getAction().equals(RongVoIPIntent.RONG_INTENT_ACTION_VOIP_SINGLEAUDIO)) {
                mediaType = RongCallCommon.CallMediaType.AUDIO;
            } else {
                mediaType = RongCallCommon.CallMediaType.VIDEO;
            }
        } else if (callAction.equals(RongCallAction.ACTION_INCOMING_CALL)) {
            callSession = intent.getParcelableExtra("callSession");
            mediaType = callSession.getMediaType();
        } else {
            callSession = RongCallClient.getInstance().getCallSession();
            mediaType = callSession.getMediaType();
        }

        /*if (!requestCallPermissions(mediaType, REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS)) {
            return;
        }

        if (callSession != null) {
            setupIntent();
        }*/
        checkPermission(true, callSession);
//        FloatWindowRequest.requestPermission(SingleCallActivity.this);
        if (callSession != null) {
            setupIntent();
        }
        super.onNewIntent(intent);
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
                boolean permissionGranted;
                if (mediaType == RongCallCommon.CallMediaType.AUDIO) {
                    permissionGranted = PermissionCheckUtil.checkPermissions(this, AUDIO_CALL_PERMISSIONS);
                } else {
                    permissionGranted = PermissionCheckUtil.checkPermissions(this, VIDEO_CALL_PERMISSIONS);

                }
                if (permissionGranted) {
                    if (startForCheckPermissions) {
                        startForCheckPermissions = false;
                        RongCallClient.getInstance().onPermissionGranted();
                    } else {
                        setupIntent();
                    }
                } else {
                    if (startForCheckPermissions) {
                        startForCheckPermissions = false;
                        RongCallClient.getInstance().onPermissionDenied();
                    } else {
                        finish();
                    }
                }
                break;
            default:
                mPermissionHelper.handleRequestPermissionsResult(requestCode, permissions, grantResults);
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS) {

            String[] permissions;
            if (mediaType == RongCallCommon.CallMediaType.AUDIO) {
                permissions = AUDIO_CALL_PERMISSIONS;
            } else {
                permissions = VIDEO_CALL_PERMISSIONS;
            }
            if (PermissionCheckUtil.checkPermissions(this, permissions)) {
                if (startForCheckPermissions) {
                    RongCallClient.getInstance().onPermissionGranted();
                } else {
                    setupIntent();
                }
            } else {
                if (startForCheckPermissions) {
                    RongCallClient.getInstance().onPermissionDenied();
                } else {
                    finish();
                }
            }

        }else if (requestCode == REQUEST_CODE_MESSAGE){
            // 处理编辑消息的结果返回
            handlerSendMessage(resultCode,data);
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    private void initRecyclerview() {
        if (mAutoLoadRecyclerView == null) {
            mAutoLoadRecyclerView = (AutoLoadRecyclerView) findViewById(R.id.recyclerview);
        }
        mAutoLoadRecyclerView.setVisibility(View.VISIBLE);
        mAutoLoadRecyclerView.setHasFixedSize(true);
        mAutoLoadRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (mAdapter == null) {
            mAdapter = new MessageAdapter(this);
            mAdapter.setClickMessageCallback(this);
        }
        mAutoLoadRecyclerView.setAdapter(mAdapter);
    }

    private void setupIntent() {
        RLog.d(TAG, "setupIntent");
        RongCallCommon.CallMediaType mediaType;
        Intent intent = getIntent();
        RongCallAction callAction = RongCallAction.valueOf(intent.getStringExtra("callAction"));

//        if (callAction.equals(RongCallAction.ACTION_RESUME_CALL)) {
//            return;
//        }

        if (callAction.equals(RongCallAction.ACTION_INCOMING_CALL)) {
            callSession = intent.getParcelableExtra("callSession");
            mediaType = callSession.getMediaType();
            targetId = callSession.getInviterUserId();
        } else if (callAction.equals(RongCallAction.ACTION_OUTGOING_CALL)) {
            if (intent.getAction().equals(RongVoIPIntent.RONG_INTENT_ACTION_VOIP_SINGLEAUDIO)) {
                mediaType = RongCallCommon.CallMediaType.AUDIO;
            } else {
                mediaType = RongCallCommon.CallMediaType.VIDEO;
            }
            Conversation.ConversationType conversationType = Conversation.ConversationType.valueOf(intent.getStringExtra("conversationType").toUpperCase(Locale.US));
            targetId = intent.getStringExtra("targetId");

            List<String> userIds = new ArrayList<>();
            userIds.add(targetId);
            RongCallClient.getInstance().startCall(conversationType, targetId, userIds, mediaType, null);
        } else { // resume call
            callSession = RongCallClient.getInstance().getCallSession();
            mediaType = callSession.getMediaType();
        }

        if (mediaType.equals(RongCallCommon.CallMediaType.AUDIO)) {
            handFree = false;
        } else if (mediaType.equals(RongCallCommon.CallMediaType.VIDEO)) {
            handFree = true;
        }

        UserInfo userInfo = RongContext.getInstance().getUserInfoFromCache(targetId);
        if (userInfo != null) {
            TextView userName = (TextView) mUserInfoContainer.findViewById(R.id.tv_user_name);
            userName.setText(userInfo.getName());
            AsyncImageView userPortrait = (AsyncImageView) mUserInfoContainer.findViewById(R.id.iv_user_header);
            userPortrait.setCircle(true);
            if (userPortrait != null && userInfo.getPortraitUri() != null) {
                userPortrait.setResource(userInfo.getPortraitUri().toString(), R.drawable.rc_default_portrait);
                userPortrait.setTag(userInfo.getPortraitUri());
                userPortrait.setOnClickListener(this);
            }
        }

        createPowerManager();
        createPickupDetector();
        RongIM.setOnReceiveMessageListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pickupDetector != null && mediaType.equals(RongCallCommon.CallMediaType.AUDIO)) {
            pickupDetector.register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (pickupDetector != null) {
            pickupDetector.unRegister();
        }
    }

    private void initView(RongCallCommon.CallMediaType mediaType, RongCallAction callAction) {
        RLog.d(TAG, "initView");

        LinearLayout buttonLayout = (LinearLayout) inflater.inflate(R.layout.avchat_video_button_controller, null);
        RelativeLayout userInfoLayout;
        // 底部按钮布局
        if (callAction.equals(RongCallAction.ACTION_OUTGOING_CALL)) {
            buttonLayout.findViewById(R.id.linear_call_mute).setVisibility(View.GONE);
            buttonLayout.findViewById(R.id.linear_handfree).setVisibility(View.GONE);
        }

        // 添加来电用户信息布局
        userInfoLayout = (RelativeLayout) inflater.inflate(R.layout.layout_income_user_info, null);


        if (mediaType.equals(RongCallCommon.CallMediaType.AUDIO)) {
            mLPreviewContainer.setVisibility(View.GONE);
            mSPreviewContainer.setVisibility(View.GONE);
            findViewById(R.id.rc_voip_call_information).setBackground(ContextCompat.getDrawable(this, R.drawable.bg));

            if (callAction.equals(RongCallAction.ACTION_INCOMING_CALL)) {
                buttonLayout = (LinearLayout) inflater.inflate(R.layout.layout_incoming_button, null);
                TextView callInfo = (TextView) userInfoLayout.findViewById(R.id.tv_remind_info);
                callInfo.setText(R.string.rc_voip_audio_call_inviting);
                onIncomingCallRinging();
            }
        } else {
            if (callAction.equals(RongCallAction.ACTION_INCOMING_CALL)) {
                findViewById(R.id.rc_voip_call_information).setBackground(ContextCompat.getDrawable(this, R.drawable.bg));
                buttonLayout = (LinearLayout) inflater.inflate(R.layout.layout_incoming_button, null);
                TextView callInfo = (TextView) userInfoLayout.findViewById(R.id.tv_remind_info);
                callInfo.setText(R.string.rc_voip_video_call_inviting);
                onIncomingCallRinging();
            }
        }
        mButtonContainer.removeAllViews();
        mButtonContainer.addView(buttonLayout);
        mUserInfoContainer.removeAllViews();
        mUserInfoContainer.addView(userInfoLayout);
    }

    /**
     * 呼叫
     *
     * @param callSession
     * @param localVideo
     */
    @Override
    public void onCallOutgoing(RongCallSession callSession, SurfaceView localVideo) {
        super.onCallOutgoing(callSession, localVideo);
        this.callSession = callSession;
        if (callSession.getMediaType().equals(RongCallCommon.CallMediaType.VIDEO)) {
            mLPreviewContainer.setVisibility(View.VISIBLE);
            localVideo.setTag(callSession.getSelfUserId());
            mLPreviewContainer.addView(localVideo);
        }
        onOutgoingCallRinging();
    }

    /**
     * 通话已连接
     *
     * @param callSession
     * @param localVideo
     */
    @Override
    public void onCallConnected(RongCallSession callSession, SurfaceView localVideo) {
        super.onCallConnected(callSession, localVideo);
        this.callSession = callSession;

//        mButtonContainer.findViewById(R.id.rl_voip_call_hang_up).setVisibility(View.GONE);

        // 语音聊天
        if (callSession.getMediaType().equals(RongCallCommon.CallMediaType.AUDIO)) {
            LinearLayout btnLayout = (LinearLayout) inflater.inflate(R.layout.avchat_video_button_controller, null);
            btnLayout.findViewById(R.id.linear_ask).setVisibility(View.GONE);
            btnLayout.findViewById(R.id.linear_camera_close).setVisibility(View.GONE);
            btnLayout.findViewById(R.id.linear_camera_switch).setVisibility(View.GONE);
            mButtonContainer.removeAllViews();
            mButtonContainer.addView(btnLayout);
        } else {
            initRecyclerview();

            mLocalVideo = localVideo;
            mLocalVideo.setTag(callSession.getSelfUserId());

            // 添加来电用户信息布局
            RelativeLayout userInfoLayout = (RelativeLayout) inflater.inflate(R.layout.item_video_header_new, null);
            UserInfo userInfo = RongContext.getInstance().getUserInfoFromCache(targetId);
            if (userInfo != null) {
                TextView userName = (TextView) userInfoLayout.findViewById(R.id.tv_user_name);
                userName.setText(userInfo.getName());
                AsyncImageView userPortrait = (AsyncImageView) userInfoLayout.findViewById(R.id.iv_user_header);
                userPortrait.setCircle(true);
                if (userPortrait != null) {
                    userPortrait.setAvatar(userInfo.getPortraitUri().toString(), R.drawable.rc_default_portrait);
                    userPortrait.setTag(userInfo.getPortraitUri());
                    userPortrait.setOnClickListener(this);
                }
            } else {
                TextView userName = (TextView) userInfoLayout.findViewById(R.id.tv_user_name);
                userName.setText(targetId);
            }
            mUserInfoContainer.removeAllViews();
            mUserInfoContainer.addView(userInfoLayout);


            // 发送开始视频的广播
            Intent intent = new Intent();
            intent.setAction(BroadcastTag.VIDEO_START);
            intent.putExtra(BroadcastTag.PARAM_TARGET_ID, callSession.getInviterUserId());
            sendBroadcast(intent);

        }
        //设置倒计时
        TextView remindInfo = (TextView) mUserInfoContainer.findViewById(R.id.tv_remind_info);
        setupTime(remindInfo);

        // 静音
        RongCallClient.getInstance().setEnableLocalAudio(!muted);
        View muteV = mButtonContainer.findViewById(R.id.iv_mute);
        if (muteV != null) {
            muteV.setSelected(muted);
        }

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioManager.isWiredHeadsetOn()) {
            RongCallClient.getInstance().setEnableSpeakerphone(false);
        } else {
            RongCallClient.getInstance().setEnableSpeakerphone(handFree);
        }
        View handFreeV = mButtonContainer.findViewById(R.id.linear_handfree);
        if (handFreeV != null) {
            handFreeV.setSelected(handFree);
        }
        stopRing();
    }

    @Override
    protected void onDestroy() {
        stopRing();
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.setReferenceCounted(false);
            wakeLock.release();
        }
        RLog.d(TAG, "SingleCallActivity onDestroy");
        RongIM.setOnReceiveMessageListener(null);
        super.onDestroy();
    }

    @Override
    public void onRemoteUserJoined(final String userId, RongCallCommon.CallMediaType mediaType, SurfaceView remoteVideo) {
        super.onRemoteUserJoined(userId, mediaType, remoteVideo);
        if (mediaType.equals(RongCallCommon.CallMediaType.VIDEO)) {
            if (userId.startsWith("master") && firstConnected) {
                // 連線成功后的招呼語，對方不顯示
                TextMessage myTextMessage = TextMessage.obtain("已加入视频聊天");
                myTextMessage.setUserInfo(RongUserInfoManager.getInstance().getUserInfo(RongIM.getInstance().getCurrentUserId()));
                RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, myTextMessage, null, null, this);
            }

            findViewById(R.id.rc_voip_call_information).setBackgroundColor(getResources().getColor(android.R.color.transparent));

            mLPreviewContainer.setVisibility(View.VISIBLE);
            mLPreviewContainer.removeAllViews();
            remoteVideo.setTag(userId);
            mLPreviewContainer.addView(remoteVideo);
            // 点击大屏幕
            mLPreviewContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isInformationShow) {
                        hideVideoCallInformation();
                    } else {
                        showVideoCallInformation();
                        handler.sendEmptyMessageDelayed(EVENT_FULL_SCREEN, 5 * 1000);
                    }
                }
            });


            // 小屏幕
            mSPreviewContainer.setVisibility(View.VISIBLE);
            mSPreviewContainer.removeAllViews();
            if (mLocalVideo != null) {
                mLocalVideo.setZOrderMediaOverlay(true);
                mLocalVideo.setZOrderOnTop(true);
                mSPreviewContainer.addView(mLocalVideo);
            }

            // 点击小屏幕
            mSPreviewContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SurfaceView fromView = (SurfaceView) mSPreviewContainer.getChildAt(0);
                    SurfaceView toView = (SurfaceView) mLPreviewContainer.getChildAt(0);

                    mLPreviewContainer.removeAllViews();
                    mSPreviewContainer.removeAllViews();
                    fromView.setZOrderOnTop(false);
                    fromView.setZOrderMediaOverlay(false);
                    mLPreviewContainer.addView(fromView);
                    toView.setZOrderOnTop(true);
                    toView.setZOrderMediaOverlay(true);
                    mSPreviewContainer.addView(toView);
                    localViewInSmall = !localViewInSmall;
                }
            });


            mButtonContainer.setVisibility(View.GONE);
            mUserInfoContainer.setVisibility(View.GONE);

        }
    }


    @Override
    public void onMediaTypeChanged(String userId, RongCallCommon.CallMediaType mediaType, SurfaceView video) {
        if (callSession.getSelfUserId().equals(userId)) {
            showShortToast(getString(R.string.rc_voip_switched_to_audio));
        } else {
            if (callSession.getMediaType() != RongCallCommon.CallMediaType.AUDIO) {
                RongCallClient.getInstance().changeCallMediaType(RongCallCommon.CallMediaType.AUDIO);
                callSession.setMediaType(RongCallCommon.CallMediaType.AUDIO);
                showShortToast(getString(R.string.rc_voip_remote_switched_to_audio));
            }
        }
        initAudioCallView();
        handler.removeMessages(EVENT_FULL_SCREEN);
        mButtonContainer.findViewById(R.id.iv_mute).setSelected(muted);
    }

    private void initAudioCallView() {
        RLog.d(TAG, "initAudioCallView");
        mLPreviewContainer.removeAllViews();
        mLPreviewContainer.setVisibility(View.GONE);
        mSPreviewContainer.removeAllViews();
        mSPreviewContainer.setVisibility(View.GONE);

        findViewById(R.id.rc_voip_call_information).setBackgroundColor(getResources().getColor(R.color.rc_voip_background_color));
        findViewById(R.id.rc_voip_audio_chat).setVisibility(View.GONE);

        View userInfoView = inflater.inflate(R.layout.layout_income_user_info, null);
        TextView timeView = (TextView) userInfoView.findViewById(R.id.tv_remind_info);
        setupTime(timeView);

        mUserInfoContainer.removeAllViews();
        mUserInfoContainer.addView(userInfoView);
        UserInfo userInfo = RongContext.getInstance().getUserInfoFromCache(targetId);
        if (userInfo != null) {
            TextView userName = (TextView) mUserInfoContainer.findViewById(R.id.tv_user_name);
            userName.setText(userInfo.getName());
            AsyncImageView userPortrait = (AsyncImageView) mUserInfoContainer.findViewById(R.id.iv_user_header);
            userPortrait.setCircle(true);
            if (userPortrait != null) {
                userPortrait.setAvatar(userInfo.getPortraitUri().toString(), R.drawable.rc_default_portrait);
                userPortrait.setTag(userInfo.getPortraitUri());
                userPortrait.setOnClickListener(this);
            }

        }
        mUserInfoContainer.setVisibility(View.VISIBLE);

        View button = inflater.inflate(R.layout.avchat_video_button_controller, null);
        mButtonContainer.removeAllViews();
        mButtonContainer.addView(button);
        mButtonContainer.setVisibility(View.VISIBLE);
        View handFreeV = mButtonContainer.findViewById(R.id.linear_handfree);
        handFreeV.setSelected(handFree);

        if (pickupDetector != null) {
            pickupDetector.register(this);
        }
    }


    public void onReceiveBtnClick(View view) {
        RongCallSession session = RongCallClient.getInstance().getCallSession();
        if (session == null || isFinishing) {
            finish();
            return;
        }
        RongCallClient.getInstance().acceptCall(session.getCallId());
    }

    public void hideVideoCallInformation() {
        isInformationShow = false;
        mUserInfoContainer.setVisibility(View.GONE);
        mButtonContainer.setVisibility(View.GONE);
        mRlBack.setVisibility(View.GONE);
        findViewById(R.id.rc_voip_audio_chat).setVisibility(View.GONE);
        mAutoLoadRecyclerView.setVisibility(View.GONE);

    }

    public void showVideoCallInformation() {
        isInformationShow = true;
        mRlBack.setVisibility(View.VISIBLE);
        mUserInfoContainer.setVisibility(View.VISIBLE);
        mButtonContainer.setVisibility(View.VISIBLE);
        mAutoLoadRecyclerView.setVisibility(View.VISIBLE);
        LinearLayout btnLayout = (LinearLayout) inflater.inflate(R.layout.avchat_video_button_controller, null);
        btnLayout.findViewById(R.id.iv_mute).setSelected(muted);
        btnLayout.findViewById(R.id.iv_switch_camera).setSelected(isFrontCamera);
        btnLayout.findViewById(R.id.iv_close_camera).setSelected(closeCamera);
        btnLayout.findViewById(R.id.linear_handfree).setVisibility(View.GONE);
        btnLayout.findViewById(R.id.linear_ask).setVisibility(View.VISIBLE);
        btnLayout.findViewById(R.id.linear_camera_switch).setVisibility(View.VISIBLE);
        mButtonContainer.removeAllViews();
        mButtonContainer.addView(btnLayout);
        View view = findViewById(R.id.rc_voip_audio_chat);
        view.setVisibility(View.VISIBLE);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RongCallClient.getInstance().changeCallMediaType(RongCallCommon.CallMediaType.AUDIO);
                callSession.setMediaType(RongCallCommon.CallMediaType.AUDIO);
                initAudioCallView();
            }
        });
    }


    @Override
    public void onCallDisconnected(RongCallSession callSession, RongCallCommon.CallDisconnectedReason reason) {
        super.onCallDisconnected(callSession, reason);
        mRlBack.setVisibility(View.GONE);

        // 视频聊天
        if (callSession.getMediaType().equals(RongCallCommon.CallMediaType.VIDEO)) {
            // 发送结束视频的广播
            Intent intent = new Intent();
            intent.setAction(BroadcastTag.VIDEO_STOP);
            intent.putExtra(BroadcastTag.PARAM_TARGET_ID, callSession.getInviterUserId());
            sendBroadcast(intent);
        }

        String senderId;
        String extra = "";

        isFinishing = true;
        if (callSession == null) {
            RLog.e(TAG, "onCallDisconnected. callSession is null!");
            postRunnableDelay(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });
            return;
        }
        senderId = callSession.getInviterUserId();
        switch (reason) {
            case HANGUP:
            case REMOTE_HANGUP:
                long time = getTime();
                if (time >= 3600) {
                    extra = String.format("%d:%02d:%02d", time / 3600, (time % 3600) / 60, (time % 60));
                } else {
                    extra = String.format("%02d:%02d", (time % 3600) / 60, (time % 60));
                }
                break;
            case OTHER_DEVICE_HAD_ACCEPTED:
                showShortToast(getString(R.string.rc_voip_call_other));
                break;
        }

        if (!TextUtils.isEmpty(senderId)) {
            CallSTerminateMessage message = new CallSTerminateMessage();
            message.setReason(reason);
            message.setMediaType(callSession.getMediaType());
            message.setExtra(extra);
            if (senderId.equals(callSession.getSelfUserId())) {
                message.setDirection("MO");
                RongIM.getInstance().insertOutgoingMessage(Conversation.ConversationType.PRIVATE, callSession.getTargetId(), io.rong.imlib.model.Message.SentStatus.SENT, message, null);
            } else {
                message.setDirection("MT");
                io.rong.imlib.model.Message.ReceivedStatus receivedStatus = new io.rong.imlib.model.Message.ReceivedStatus(0);
                receivedStatus.setRead();
                RongIM.getInstance().insertIncomingMessage(Conversation.ConversationType.PRIVATE, callSession.getTargetId(), senderId, receivedStatus, message, null);
            }
        }
        postRunnableDelay(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
    }

    /**
     * 远端摄像头是否可用
     *
     * @param userId
     * @param disabled
     */
    @Override
    public void onRemoteCameraDisabled(String userId, boolean disabled) {
        /*if (disabled){
            if (localViewInSmall) {
                mLPreviewContainer.removeAllViews();
            }else{
                mSPreviewContainer.removeAllViews();
            }
        }else{
            if (localViewInSmall) {
                mLPreviewContainer.addView(rem);
            }else{
                mSPreviewContainer.removeAllViews();
            }
        }*/
    }

    /**
     * 重新获取浮动框的按钮状态
     *
     * @param bundle
     */
    @Override
    public void onRestoreFloatBox(Bundle bundle) {
        super.onRestoreFloatBox(bundle);
        if (bundle == null) {
            return;
        }
        firstConnected = false;
        muted = bundle.getBoolean("muted");
        handFree = bundle.getBoolean("handFree");
        isFrontCamera = bundle.getBoolean("isFrontCamera");
        closeCamera = bundle.getBoolean("closeCamera");
        ArrayList<io.rong.imlib.model.Message> chatRecordList = bundle.getParcelableArrayList("messageList");
        if (mAdapter == null) {
            initRecyclerview();
            mAdapter.fillList(chatRecordList);
            if (mAdapter.getItemCount() > 0) {
                mAutoLoadRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
            }
            mAutoLoadRecyclerView.setVisibility(View.VISIBLE);
        }
        setShouldShowFloat(true);

        callSession = RongCallClient.getInstance().getCallSession();
        RongCallCommon.CallMediaType mediaType = callSession.getMediaType();
        RongCallAction callAction = RongCallAction.valueOf(getIntent().getStringExtra("callAction"));
        inflater = LayoutInflater.from(this);
        initView(mediaType, callAction);
        targetId = callSession.getTargetId();
        UserInfo userInfo = RongContext.getInstance().getUserInfoFromCache(targetId);
        if (userInfo != null) {
            TextView userName = (TextView) mUserInfoContainer.findViewById(R.id.tv_user_name);
            userName.setText(userInfo.getName());
            AsyncImageView userPortrait = (AsyncImageView) mUserInfoContainer.findViewById(R.id.iv_user_header);
            userPortrait.setCircle(true);
            if (userPortrait != null) {
                userPortrait.setAvatar(userInfo.getPortraitUri().toString(), R.drawable.rc_default_portrait);
                userPortrait.setTag(userInfo.getPortraitUri());
                userPortrait.setOnClickListener(this);
            }

        }
        SurfaceView localVideo = null;
        SurfaceView remoteVideo = null;
        String remoteUserId = null;
        for (CallUserProfile profile : callSession.getParticipantProfileList()) {
            if (profile.getUserId().equals(RongIMClient.getInstance().getCurrentUserId())) {
                localVideo = profile.getVideoView();
            } else {
                remoteVideo = profile.getVideoView();
                remoteUserId = profile.getUserId();
            }
        }
        if (localVideo != null && localVideo.getParent() != null) {
            ((ViewGroup) localVideo.getParent()).removeView(localVideo);
        }
        onCallOutgoing(callSession, localVideo);
        onCallConnected(callSession, localVideo);
        if (remoteVideo != null && remoteVideo.getParent() != null) {
            ((ViewGroup) remoteVideo.getParent()).removeView(remoteVideo);
            onRemoteUserJoined(remoteUserId, mediaType, remoteVideo);
        }
    }

    /**
     * 保存浮动框的按钮状态
     *
     * @param bundle
     * @return
     */
    @Override
    public String onSaveFloatBoxState(Bundle bundle) {
        RLog.d(TAG, "onSaveFloatBoxState");

        super.onSaveFloatBoxState(bundle);
        callSession = RongCallClient.getInstance().getCallSession();
        bundle.putBoolean("muted", muted);
        bundle.putBoolean("handFree", handFree);
        bundle.putBoolean("isFrontCamera", isFrontCamera);
        bundle.putBoolean("closeCamera",closeCamera);
        bundle.putInt("mediaType", callSession.getMediaType().getValue());
        bundle.putParcelableArrayList("messageList", mAdapter.getDataList());
        return getIntent().getAction();
    }

    @Override
    public void onMinimizeClick(View view) {
        super.onMinimizeClick(view);
    }


    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }*/

    public void onEventMainThread(UserInfo userInfo) {
        if (targetId != null && targetId.equals(userInfo.getUserId())) {
            TextView userName = (TextView) mUserInfoContainer.findViewById(R.id.tv_user_name);
            if (userInfo.getName() != null) {
                userName.setText(userInfo.getName());
            }
            AsyncImageView userPortrait = (AsyncImageView) mUserInfoContainer.findViewById(R.id.iv_user_header);
            userPortrait.setCircle(true);
            if (userPortrait != null && userInfo.getPortraitUri() != null) {
                userPortrait.setResource(userInfo.getPortraitUri().toString(), R.drawable.rc_default_portrait);
                userPortrait.setTag(userInfo.getPortraitUri());
                userPortrait.setOnClickListener(this);
            }
        }
    }


    public void checkPermission(final boolean isCreate, final RongCallSession callSession) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        mPermissionHelper = new MyPermissionHelper(this);
        mPermissionHelper.requestPermissions(getString(R.string.request_video_permission), new MyPermissionHelper.PermissionListener() {
            @Override
            public void doAfterGrand(String... permission) {
                if (startForCheckPermissions) {
                    startForCheckPermissions = false;
                    RongCallClient.getInstance().onPermissionGranted();
                } else {
                    setupIntent();
                }

            }

            @Override
            public void doAfterDenied(String... permission) {
                if (startForCheckPermissions) {
                    startForCheckPermissions = false;
                    RongCallClient.getInstance().onPermissionDenied();
                } else {
                    finish();
                }
                ShowToast.Long(getString(R.string.request_video_no_permission));
            }
        }, VIDEO_CALL_PERMISSIONS);

    }


    //--------------------- start listen -----------------------------------------
    boolean isFrontCamera = false;

    /**
     * 切换摄像头
     *
     * @param view
     */
    public void onSwitchCameraClick(View view) {
        view.setSelected(!view.isSelected());
        RongCallClient.getInstance().switchCamera();
        isFrontCamera = view.isSelected();
    }

    /**
     * 免提
     *
     * @param view
     */
    public void onHandFreeButtonClick(View view) {
        RongCallClient.getInstance().setEnableSpeakerphone(!view.isSelected());
        view.setSelected(!view.isSelected());
        handFree = view.isSelected();
    }

    /**
     * 设置静音
     *
     * @param view
     */
    public void onMuteButtonClick(View view) {
        RongCallClient.getInstance().setEnableLocalAudio(view.isSelected());
        view.setSelected(!view.isSelected());
        muted = view.isSelected();
    }

    /**
     * 提问
     *
     * @param view
     */
    public void onAskButtonClick(View view) {
        /*MyInputDialog.newInstance().setmOnTextSendListener(this)
                .show(getSupportFragmentManager());*/
        startActivityForResult(new Intent(this,EditMessageActivity.class),REQUEST_CODE_MESSAGE);
    }

    private boolean closeCamera = false;

    /**
     * 关闭摄像头
     *
     * @param view
     */
    public void onCloseCameraClick(View view) {
        RongCallClient.getInstance().setEnableLocalVideo(view.isSelected());
        view.setSelected(!view.isSelected());
        closeCamera = view.isSelected();
    }

    /**
     * 结束通话
     *
     * @param view
     */
    public void onHangupBtnClick(View view) {
        RongCallSession session = RongCallClient.getInstance().getCallSession();
        if (session == null || isFinishing) {
            finish();
            return;
        }
        RongCallClient.getInstance().hangUpCall(session.getCallId());
        stopRing();
    }

    @Override
    public boolean onReceived(io.rong.imlib.model.Message message, int i) {
        MessageContent messageContent = message.getContent();
        if (messageContent instanceof TextMessage
                || messageContent instanceof ImageMessage) {
            // 更新内存中消息的已读状态
            message.getReceivedStatus().setRead();
            // 更新数据库中消息的状态
            RongIM.getInstance().setMessageReceivedStatus(message.getMessageId(), message.getReceivedStatus(), null);

            // 不顯示招呼語
            /*if (TextUtils.equals(((TextMessage)message.getContent()).getContent(),"first hello")) {
                return false;
            }*/
            Log.d(TAG, "onReceived: " + message.getObjectName());
            mAdapter.appendItem(message);
            mAutoLoadRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
            mAutoLoadRecyclerView.setVisibility(View.VISIBLE);
        }

        return false;
    }

    /**
     * 消息本地数据库存储成功的回调
     *
     * @param message
     */
    @Override
    public void onAttached(io.rong.imlib.model.Message message) {

    }

    /**
     * 消息通过网络发送成功的回调
     *
     * @param message
     */
    @Override
    public void onSuccess(io.rong.imlib.model.Message message) {
        // 不顯示招呼語
        if (message.getTargetId().startsWith("master") && ((TextMessage)message.getContent()).getContent().startsWith("已加入视频聊天")) {
            return;
        }
        mAdapter.appendItem(message);
        mAutoLoadRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
    }

    /**
     * 消息发送失败的回调
     *
     * @param message
     * @param errorCode
     */
    @Override
    public void onError(io.rong.imlib.model.Message message, RongIMClient.ErrorCode errorCode) {

    }

    /**
     * 处理编辑消息（图片或者文字），然后进行发送
     * @param resultCode
     */
    private void handlerSendMessage(int resultCode,Intent data) {
        // 处理文字，及发送
        if (resultCode == EditMessageActivity.RESULT_TEXT){
            String msgContent = data.getStringExtra("text");
            if (TextUtils.isEmpty(msgContent.trim())) {
                return;
            }
            TextMessage myTextMessage = TextMessage.obtain(msgContent);
            myTextMessage.setUserInfo(RongUserInfoManager.getInstance().getUserInfo(RongIM.getInstance().getCurrentUserId()));
            RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, myTextMessage, null, null, this);
            return;
        }

        // 处理图片，及发送
        if (resultCode == EditMessageActivity.RESULT_PIC){
            File file = new File(data.getStringExtra("picture"));
            Uri uri = Uri.fromFile(file);

            RongIM.getInstance().sendImageMessage(Conversation.ConversationType.PRIVATE, targetId, ImageMessage.obtain(uri, uri, true), null, null, new RongIMClient.SendImageMessageCallback() {
                @Override
                public void onAttached(io.rong.imlib.model.Message message) {

                }

                @Override
                public void onError(io.rong.imlib.model.Message message, RongIMClient.ErrorCode errorCode) {

                }

                @Override
                public void onSuccess(io.rong.imlib.model.Message message) {
                    mAdapter.appendItem(message);
                    mAutoLoadRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                }

                @Override
                public void onProgress(io.rong.imlib.model.Message message, int i) {

                }
            });

            return;
        }
    }

    /**
     * 输入对话框结果回调
     *
     * @param msg
     * @param askOpen 提问开关
     */
    @Override
    public void onTextSend(String msg, boolean askOpen) {
        if (TextUtils.isEmpty(msg.trim())) {
            return;
        }
        TextMessage myTextMessage = TextMessage.obtain(msg);
        myTextMessage.setUserInfo(RongUserInfoManager.getInstance().getUserInfo(RongIM.getInstance().getCurrentUserId()));
        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, myTextMessage, null, null, this);
    }



    @Override
    public void onBackPressed() {
        checkPermissionForFloatBox();

    }
    /**
     * 按下返回键时为显示浮窗检查权限
     */
    private void checkPermissionForFloatBox() {
        if (SettingAlertPermission.hasPermission(this)) {
            super.onBackPressed();
        }else{
            if (firstRequestPermission) {
                firstRequestPermission = false;
                SettingAlertPermission.checkSettingAlertPermission(this);
                return;
            }
            new AlertDialog.Builder(SingleCallActivity.this)
                    .setMessage(getString(R.string.no_float_permission))
                    .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            onHangupBtnClick(null);
                        }
                    }).show();
        }
    }

    @Override
    public void onClickImage(Uri uri) {
        ImageShowActivity.startActivity(this,uri);
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_user_header) {
            ImageShowActivity.startActivity(this, (Uri) v.getTag());
        }
    }


    //--------------------- end listen -----------------------------------------


//    @Override
//    public void showOnGoingNotification() {
//        Intent intent = new Intent(getIntent().getAction());
//        Bundle bundle = new Bundle();
//        onSaveFloatBoxState(bundle);
//        intent.putExtra("floatbox", bundle);
//        intent.putExtra("callAction", RongCallAction.ACTION_RESUME_CALL.getName());
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationUtil.showNotification(this, "todo", "coontent", pendingIntent, CALL_NOTIFICATION_ID);
//    }
}

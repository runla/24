package io.rong.callkit.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;

import java.util.ArrayList;

import io.rong.callkit.R;
import io.rong.callkit.widget.BubbleImageView;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ImageMessage;
import io.rong.message.TextMessage;

/**
 * Created by chenjianrun on 2017/11/10.
 * 描述：在视频聊天中聊天记录的 adapter
 */

public class MessageAdapter extends BaseDataAdapter<Message> {
    private ClickMessageCallback clickMessageCallback;
    public MessageAdapter(Context context) {
        super(context);
    }

    public ArrayList<Message> getDataList(){
        return (ArrayList<Message>) mDataList;
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.item_video_chat;
    }

    @Override
    public void bindCustomViewHolder(BaseHolder baseHolder, int i) {
        MessageContent messageContent = getItem(i).getContent();
        if (messageContent instanceof ImageMessage) {
            // 图片
            ImageMessage imageMessage = (ImageMessage) messageContent;
            final BubbleImageView imageView = baseHolder.getView(R.id.iv_pic);
            baseHolder.setVisible(R.id.tv_chat_content, false);
            baseHolder.setVisible(R.id.iv_pic, true);

            Uri uri;
            if (imageMessage.getLocalUri() != null) {
                uri = imageMessage.getLocalUri();
                imageView.setTag(imageMessage.getLocalUri());
            }else{
                uri = imageMessage.getMediaUrl();
                imageView.setTag(imageMessage.getMediaUrl());
            }

            Glide.with(context)
                    .load(uri)
                    .asBitmap()
                    .centerCrop()
                    .placeholder(R.drawable.rc_image_error)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            imageView.setImageBitmap(resource);
                        }
                    });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = (Uri) v.getTag();
                    if (clickMessageCallback != null) {
                        clickMessageCallback.onClickImage(uri);
                    }
                }
            });
        }else if (messageContent instanceof TextMessage){
            // 文字
            TextMessage textMessage = (TextMessage) messageContent;
            baseHolder.setText(R.id.tv_chat_content,textMessage.getContent());
            baseHolder.setVisible(R.id.tv_chat_content, true);
            baseHolder.setVisible(R.id.iv_pic, false);
        }

        if (TextUtils.isEmpty(getItem(i).getSenderUserId())) {
            baseHolder.setText(R.id.tv_chat_name,context.getString(R.string.me));
            return;
        }
        UserInfo userInfo = messageContent.getUserInfo();
        if (userInfo != null) {
            if (TextUtils.equals(userInfo.getUserId(), RongIM.getInstance().getCurrentUserId())){
                baseHolder.setText(R.id.tv_chat_name,context.getString(R.string.me));
            }else{
                baseHolder.setText(R.id.tv_chat_name,userInfo.getName());
            }
        }else{
            baseHolder.setText(R.id.tv_chat_name,getItem(i).getSenderUserId());
        }
    }

    public void setClickMessageCallback(ClickMessageCallback clickMessageCallback) {
        this.clickMessageCallback = clickMessageCallback;
    }

    public interface ClickMessageCallback{
        void onClickImage(Uri uri);
    }
}

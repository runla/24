package com.yinghai.a24divine_user.module.message;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.fansonlib.base.BaseDataAdapter;
import com.example.fansonlib.base.BaseHolder;
import com.google.gson.Gson;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.bean.SystemMessageBean;
import com.yinghai.a24divine_user.callback.OnAdapterListener;
import com.yinghai.a24divine_user.constant.ConSystemCode;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/30 17:50
 *         Describe：系统消息的适配器
 */

public class MessageAdapter extends BaseDataAdapter<Message>{

    private Context mContext;
    private OnAdapterListener mOnAdapterListener;
    public void setOnAdapterListener(OnAdapterListener listener){
        mOnAdapterListener = listener;
    }

    public MessageAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public int getLayoutRes(int i) {
        return R.layout.item_message;
    }

    @Override
    public void bindCustomViewHolder(final BaseHolder holder, final int position) {
        final MessageContent messageContent =getItem(position).getContent();
        if (messageContent instanceof TextMessage){
            if (getItem(position).getReceivedStatus().isRead()) {
                showHasReadMessage(holder);
            }else{
                showUnReadMessage(holder);
            }

            TextMessage textMessage = ((TextMessage) messageContent);
            String content = textMessage.getContent();
            final SystemMessageBean systemMessageBean = new Gson().fromJson(content,SystemMessageBean.class);

            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            holder.setText(R.id.tv_time,sdf.format(new Date(getItem(position).getReceivedTime())));
            handleMessage(holder,systemMessageBean);


            // 只有好友的验证信息才可以点击
            holder.setOnClickListener(R.id.rootView, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 打开消息详情
                    if (mOnAdapterListener != null && Integer.parseInt(systemMessageBean.getCode()) == ConSystemCode.ADD_FIREND) {
                        mOnAdapterListener.clickItem(systemMessageBean);
                    }
                    setMessageReaded(getItem(position));
                    showHasReadMessage(holder);
                }
            });


            holder.setOnClickListener(R.id.btn_confirm, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO 将消息设置为已读
                    setMessageReaded(getItem(position));
                    showHasReadMessage(holder);
                }
            });
        }

    }

    /**
     * 处理系统消息的显示
     * @param holder
     * @param systemMessageBean
     */
    private void handleMessage(BaseHolder holder,SystemMessageBean systemMessageBean){
        String nickName="";
        switch (Integer.parseInt(systemMessageBean.getCode())){
            case ConSystemCode.ORDER_PAY_SUCCESS:
                if (systemMessageBean.getData().getTfOrderTotal() == null) {
                    holder.setText(R.id.tv_message,String.format(mContext.getString(R.string.order_divine_pay_success),systemMessageBean.getData().getOrder().getOrderId()));
                }else{
                    holder.setText(R.id.tv_message,String.format(mContext.getString(R.string.order_shop_pay_success),systemMessageBean.getData().getTfOrderTotal().getTotalId()));
                }
                break;

            case ConSystemCode.ORDER_CANCEL_SUCCESS:
                if (systemMessageBean.getData().getTfOrderTotal() == null) {
                    holder.setText(R.id.tv_message,String.format(mContext.getString(R.string.order_divine_cancel_success),systemMessageBean.getData().getOrder().getOrderId()));
                }else{
                    holder.setText(R.id.tv_message,String.format(mContext.getString(R.string.order_shop_cancel_success),systemMessageBean.getData().getTfOrderTotal().getTotalId()));
                }
                break;

            case ConSystemCode.ORDER_HAS_CANCEL:
                if (systemMessageBean.getData().getTfOrderTotal() == null) {
                    holder.setText(R.id.tv_message,String.format(mContext.getString(R.string.order_divine_has_cancel),systemMessageBean.getData().getOrder().getOrderId()));
                }else{
                    holder.setText(R.id.tv_message,String.format(mContext.getString(R.string.order_shop_has_cancel),systemMessageBean.getData().getTfOrderTotal().getTotalId()));
                }
                break;

            case ConSystemCode.ORDER_START_SOON:
                if (systemMessageBean.getData().getTfOrderTotal() == null) {
                    holder.setText(R.id.tv_message,String.format(mContext.getString(R.string.order_divine_start_soon),systemMessageBean.getData().getOrder().getOrderId()));
                }else{
                    holder.setText(R.id.tv_message,String.format(mContext.getString(R.string.order_shop_start_soon),systemMessageBean.getData().getTfOrderTotal().getTotalId()));
                }
                break;

            case ConSystemCode.ADD_FIREND:
                if (systemMessageBean.getData().getUser() != null) {
                    nickName = systemMessageBean.getData().getUser().getUNick();
                }
                holder.setText(R.id.tv_message,String.format(mContext.getString(R.string.request_firend),nickName));
                break;

            case ConSystemCode.AGREE_FIREND:
                if (systemMessageBean.getData().getUser() != null) {
                    nickName = systemMessageBean.getData().getUser().getUNick();
                }
                holder.setText(R.id.tv_message,String.format(mContext.getString(R.string.agress_firend),nickName));
                break;

            case ConSystemCode.REJECT_FIREND:
                if (systemMessageBean.getData().getUser() != null) {
                    nickName = systemMessageBean.getData().getUser().getUNick();
                }
                holder.setText(R.id.tv_message,String.format(mContext.getString(R.string.reject_firend),nickName));
                break;

            default:
                break;
        }
    }

    /**
     * 设置已读的系统消息显示(颜色设置为灰色)
     * @param holder
     */
    private void showHasReadMessage(BaseHolder holder){
        holder.setTextColor(R.id.tv_message,ContextCompat.getColor(mContext,R.color.gray));
        holder.setTextColor(R.id.tv_time, ContextCompat.getColor(mContext,R.color.gray));
        holder.getView(R.id.btn_confirm).setEnabled(false);
        holder.getView(R.id.btn_confirm).setSelected(true);
        holder.setText(R.id.btn_confirm,mContext.getString(R.string.read_n));
    }
    /**
     * 设置未读的系统消息显示(颜色设置为黑色)
     * @param holder
     */
    private void showUnReadMessage(BaseHolder holder){
        holder.setTextColor(R.id.tv_message,ContextCompat.getColor(mContext,R.color.gray_71));
        holder.setTextColor(R.id.tv_time, ContextCompat.getColor(mContext,R.color.gray_71));
        holder.getView(R.id.btn_confirm).setEnabled(true);
        holder.getView(R.id.btn_confirm).setSelected(false);
        holder.setText(R.id.btn_confirm,mContext.getString(R.string.confirm_n));
    }

    /**
     * 将消息设置为已读
     * @param message
     */
    private void setMessageReaded(Message message){
        // 更新内存中消息的已读状态
        message.getReceivedStatus().setRead();
        // 更新数据库中消息的状态
        RongIM.getInstance().setMessageReceivedStatus(message.getMessageId(), message.getReceivedStatus(),null);

    }
}

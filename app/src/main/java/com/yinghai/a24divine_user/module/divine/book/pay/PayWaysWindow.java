package com.yinghai.a24divine_user.module.divine.book.pay;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.fansonlib.utils.NetWorkUtil;
import com.example.fansonlib.utils.ShowToast;
import com.example.fansonlib.widget.popupwindow.BasePopup;
import com.yinghai.a24divine_user.R;
import com.yinghai.a24divine_user.alipay.Alipay;
import com.yinghai.a24divine_user.bean.AliPayBean;
import com.yinghai.a24divine_user.bean.WechatPayBean;
import com.yinghai.a24divine_user.wxapi.pay.WechatPay;
import com.yinghai.a24divine_user.wxapi.pay.WechatPayCallback;


/**
 * @author Created by：fanson
 *         Created Time: 2017/11/16 16:10
 *         Describe：选择支付方式的Window
 */

public class PayWaysWindow extends BasePopup implements ContractPay.IPayView {

    private int payType = 0;//支付類型
    public static final int PAY_TYPE_ALIPAY = 1;//支付宝支付
    public static final int PAY_TYPE_WECHAT = 2;//微信支付
    private View popupView;
    private RadioButton radio_wechat, radio_alipay;
    private RadioGroup radioGroup;
    private Button btn_confirm_pay;
    private Button btn_cancel_pay;
    private Activity mActivity;
    private PayPresenter mPayPresenter;
    private PayCallback mIPayCallback;
    //订单号
    private String mOrderNo;
    //订单总价
    private int mPrice;
    //订单类型
    private int mOrderType;
    private static final int TYPE_DIVINE = 1;
    private static final int TYPE_PRODUCT = 2;

    /**
     * @param activity
     * @param orderNo      订单编号
     * @param orderType    订单类型（1在线占卜;2到访 3商品）
     * @param price        订单价格
     * @param iPayCallback
     */
    public PayWaysWindow(Activity activity, String orderNo, int orderType,int price, PayCallback iPayCallback) {
        super(activity);
        mOrderNo = orderNo;
        mOrderType = orderType;
        mPrice = price;
        this.mActivity = activity;
        this.mIPayCallback = iPayCallback;
        initView();
        initEvent();
    }

    @Override
    protected Animation initShowAnimation() {
        return getTranslateAnimation(250 * 2, 0, 300);
    }

    @Override
    public View getClickToDismissView() {
        return null;
//        return popupView.findViewById(R.id.root_view);
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.window_choose_pay, null);
        return popupView;
    }


    /**
     * 显示View
     */
    public void setX(int offsetX) {
        popupView.setTranslationX(offsetX);
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void initView() {
        if (popupView != null) {
            radioGroup = (RadioGroup) popupView.findViewById(R.id.radiogroup_pay);
            radio_wechat = (RadioButton) popupView.findViewById(R.id.radio_wechat);
            radio_alipay = (RadioButton) popupView.findViewById(R.id.radio_alipay);
            btn_confirm_pay = (Button) popupView.findViewById(R.id.btn_confirm_pay);
            btn_cancel_pay = (Button) popupView.findViewById(R.id.btn_cancel_pay);
            TextView tvTotalFee = (TextView) popupView.findViewById(R.id.tv_price);
            initButtonDrawableRightSize(radio_alipay, R.mipmap.ic_ali_pay, R.drawable.selector_pay);
            initButtonDrawableRightSize(radio_wechat, R.mipmap.ic_wechat_pay, R.drawable.selector_pay);
            tvTotalFee.setText(String.format(mActivity.getString(R.string.money_unit), mPrice/100));
        }
    }

    /**
     * 设置RadioButton中icon的大小
     */
    private void initButtonDrawableRightSize(Button button, int resourceIdLeft, int resourceIdRight) {
        final float scale = mActivity.getResources().getDisplayMetrics().density;// 屏幕密度
        int pd_right = 25;
        Drawable drawable_right = ContextCompat.getDrawable(mActivity, resourceIdRight);
        drawable_right.setBounds(0, 0, (int) (pd_right * scale + 0.5f), (int) (pd_right * scale + 0.5f));

        int pd_left = 35;
        Drawable drawable_left = ContextCompat.getDrawable(mActivity, resourceIdLeft);
        drawable_left.setBounds(0, 0, (int) (pd_left * scale + 0.5f), (int) (pd_left * scale + 0.5f));
        button.setCompoundDrawables(drawable_left, null, drawable_right, null);
    }

    private void initEvent() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_alipay:
                        payType = PAY_TYPE_ALIPAY;
                        break;

                    case R.id.radio_wechat:
                        payType = PAY_TYPE_WECHAT;
                        break;

                    default:
                        break;
                }
            }
        });

        //確認支付
        btn_confirm_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NetWorkUtil.isNetWordConnected(getContext())) {
                    ShowToast.Long(mActivity.getString(R.string.no_net));
                    return;
                }

                switch (payType) {
                    case PAY_TYPE_WECHAT:   //微信支付
                        showLoading();
                        mPayPresenter = new PayPresenter(PayWaysWindow.this);
                        mPayPresenter.wechatCreateOrder(mOrderNo, mOrderType, "192.168.0.111");
                        break;
                    case PAY_TYPE_ALIPAY:   //支付宝支付
                        showLoading();
                        mPayPresenter = new PayPresenter(PayWaysWindow.this);
                        mPayPresenter.aliPay(mOrderNo, mOrderType);
                        break;

                    default:
                        break;
                }
            }
        });

        /**
         * 取消支付
         */
        btn_cancel_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayWaysWindow.this.dismiss();
            }
        });
    }


    /**
     * 微信统一下单成功，调起 app 微信支付进行支付
     *
     * @param
     */
    @Override
    public void wechatCreateSuccess(WechatPayBean bean) {
        ShowToast.singleShort(mActivity.getString(R.string.submit_order_success));
        WechatPay.getInstance().startPay(mActivity, bean.getData().getData().getAppid(), bean.getData().getData(), new WechatPayCallback() {
            @Override
            public void showPayLoading() {
                if (mIPayCallback != null) {
                    mIPayCallback.showPayLoading();
                }
            }

            @Override
            public void paySuccess() {
                if (mIPayCallback != null) {
                    mIPayCallback.paySuccess();
                }
            }

            @Override
            public void payFailure(int errCode) {
                if (mIPayCallback != null) {
                    mIPayCallback.payFailure(errCode);
                }
            }
        });
        hideLoading();
    }

    /**
     * 统一下单失败
     */
    @Override
    public void wechatCreateFailure(String errorMsg) {
        ShowToast.singleShort(errorMsg);
        hideLoading();
    }

    @Override
    public void showAliPaySuccess(AliPayBean bean) {
        //TODO 调起支付宝
        ShowToast.singleShort(mActivity.getString(R.string.submit_order_success));
        Alipay alipay = new Alipay(mActivity, new Alipay.AlipayCallback() {
            @Override
            public void alipaySuccess(String resultInfo) {
                if (mIPayCallback != null) {
                    mIPayCallback.paySuccess();
                }
            }

            @Override
            public void alipayFailure(String errMsg) {
                if (mIPayCallback != null) {
                    mIPayCallback.payFailure(-1);
                }
            }
        });
        alipay.startPay(bean.getData().getOrderStr());
        hideLoading();
    }

    @Override
    public void showAliPayFailure(String errorMsg) {
        ShowToast.singleShort(mActivity.getString(R.string.alipay_failure)+errorMsg);
        hideLoading();
    }


    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDismiss() {
        super.onDismiss();
        if (mIPayCallback != null) {
            mIPayCallback = null;
        }
        if (mPayPresenter!=null){
            mPayPresenter.detachView();
        }
    }

    public interface PayCallback {
        /**
         * 成功显示付款的界面，还未付款
         */
        void showPayLoading();

        /**
         * 支付成功
         */
        void paySuccess();

        /**
         * 支付失败
         */
        void payFailure(int errCode);

    }
}


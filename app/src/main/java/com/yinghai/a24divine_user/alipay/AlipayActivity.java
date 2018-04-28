package com.yinghai.a24divine_user.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.yinghai.a24divine_user.R;

import java.util.Map;

import static com.yinghai.a24divine_user.alipay.Alipay.RSA_PRIVATE;

/**
 * 描述：这个类是支付宝支付的测试类（沙箱测试用）
 *
 */
public class AlipayActivity extends AppCompatActivity {
    /** 支付宝支付业务：入参app_id */
//    public static final String APPID = "2016082100304720";//沙箱
    public static final String APPID = "2017111309901663";//正式
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC5qvnH8tZLSuxtQiB2rMuZLW5YYqNFkG/uuEMm8tA8g5Lo+FNIc2PgX8ocZpGcVIYYphWPre3X5BVl9UFCKDtGvtXHx0JP0HaJz00G54W7Bo4OxJYxS+Vk+K5Tlooh7V2WwsKMzw68WJx/PmWQkQBhowZtF+FmKaq9cNoMlorgleJqKhmgpOpUaGNeD0RVage9sZBNOvHhrqFS36esUJ5NoJdelBS3GzmNJts7B9tdaFosMXUklRG5cWcFSO8IP1A0YgwmDW64b3R2b4zelaTqRmGjszcYZSiAzAS5+P5xy2C7q9B9f/m3YklRi+rWH3Ey0B+zArseydsrQ0doxFaJAgMBAAECggEAZB/t34P3SFc5NJpqg1Uf3pT/Yq6euc7wbd30aLac0WVq/jOcRf/kJoXBqpstfw6d2gEGs5rqHnijiX4/GPw1KyuSgc/U1NHoetzN/yEvnvIj/ZbKH/97z33EFNOj3jPxOnIBFIo8uhlXG898Wf+4Iu8VchNkvAFQUCThkdRFRGLiz+9ie7nuewRQZOHUD2+6UBacETel2OX5NCpAIU7NvUwMAPZSJtTgwEOq+IoPqMHMG66IMhy0CwtVMmJgVWxjlc97GiCUmPJjQH1TSCuRPsHCtGZ9Usi2DwLujvk4mfLphLEDaQRb37R4LtDKou0lifLTSYKvrOHRuTUmoNPyOQKBgQD3ngQtot2SeMsJErWzko7O/ZN0bDUjvXqu1FZME9z0VUPqHcOKagfvCDotJbpC4wT9YjZjHGfpN6tdI/hXO9rE2HCWb/aF9B0nQy6/A7oJ9TceVBMWg+k/HtrgKuctnruG3euMDStidV1Bdt9UBATmcfMyMglSX5uKP72pZ6TZYwKBgQC/9BKqYtZK08HG/wfaFdMym/r2WCp6iGzlG3a9ZomL1iF5MaxfYdefQo52RxobemvJojlHT31MnEKndRmUoJB2MTLkGuvgqFcF3BwNXqAj7fuQgtt/PlCltzv8X2FD/NxH+Grz3J9dyZYtR4IPKpeIVzEs3hjSqhshgtUbz0xKIwKBgDQQkahW2mf15hseR/5eDpEPWHXeWr4NFUK1ev+8HXVyiDkt0PPFn61u+WyMxkFSIrZ0dCMa0e6DuhB3g7wz5agGKoY3PIql7YtX4Phg/DkjylciLnFJgz22ayc/XiSFf8C34NMt4bVuIBGnwQWVYsJhq1UemD8twzKqfGKmXE8pAoGBAJX2GJ8EmGXdCYZtt7SpWFYxhd7vAkC4VFqpGhreAJc+suf75q6dwdaqKKI+6iaNfagJFLahY0J20ZwB3/o9dp7f0OJkJeNdoJ7zVvca9gEb62z6zoCaLmdIv5Rq75It+epAKU5bDiY3VxwIwZMXs0rQ05o56SqruoZzsAoJ6ZABAoGAKWbe6pR+pJkOTwnpHbtdO8mpKlTaJ5rio0/c4kRfWvGQGJ8Byabh7WuXqU7XqD8y/SOzAA66Girh70kldHeficNzM7EZv4z6QV5YQl86O/OjtZUN1ypb7zwDf7FmiHVFxNjN3anvvm/cdMJPL/ZBgu6Lth0n+SKjaUQnzh2p7n8=";
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(AlipayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(AlipayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                    break;
                }

                default:
                    break;
            }
        }
    };

    public static void startAlipay(Activity activity,String orderInfo){
        Intent intent = new Intent(activity,AlipayActivity.class);
        intent.putExtra("orderInfo",orderInfo);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);
        payV2();
    }

    /**
     * 支付宝支付业务
     */
    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

//        final String orderInfo = getIntent().getStringExtra("orderInfo");
//        final String orderInfo = "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016082100304720&biz_content=%7B%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%2211ceshi000005%22%2C%22passback_params%22%3A%221%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22seller_id%22%3A%222088102172410930%22%2C%22subject%22%3A%22App%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95Java%22%2C%22timeout_express%22%3A%2215d%22%2C%22total_amount%22%3A%220.11%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F219.131.240.155%3A8085%2Ftwentyfour%2Fcallback%2Falipay&sign=aqSJSebUnhVLs8kFwdn156jf41IrYZqBVy9Jwzrj3wYlDszff8LKcmhSeoul8Zum6tWZtqIJhIZdr1valSkRo2e799UUeFBfxBZftcydeLigXAgDBj3azwtHS2NB6dU%2FZFT6XWKY79baMcjMQJxxge9smv3zofBcQ8vaNw6m9iJenxUMrplFRW0FPy9dl5NDtXa1yQAE76b61S2eiXYsiTAwnw0aoZNq5FFzT4QA8Lmyh104Ux5Y2FJuaCyAIgBRk1YRQrH1zMS5Fn79rR86R0d9eovQQBUkTuCe0hBC8%2BwEZmjDtFyBlgUjo%2F8hTBNYmkKLNSd5ft3aCh2B%2F8TeUg%3D%3D&sign_type=RSA2&timestamp=2017-12-13+14%3A05%3A37&version=1.0";
//        final String orderInfo = "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017111309901663&biz_content=%7B%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22ceshi000005%22%2C%22passback_params%22%3A%221%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22seller_id%22%3A%222088102172410930%22%2C%22subject%22%3A%22App%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95Java%22%2C%22timeout_express%22%3A%2215d%22%2C%22total_amount%22%3A%220.11%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F219.131.240.155%3A8085%2Ftwentyfour%2Fcallback%2Falipay&sign=miF5xHoAW9Zhs0sBcm0X7wJN4BNDRsudPrksxaTPGWfUf6SS%2BjCLmxaOX6EfPXqt5pri5u%2FR%2FezWyDHVe3Ah2OcTRC66UahiqEwtvezIdefvIsD4nH8U%2Fz93mN3YtmIqhZTIK8L0xX9ASTNBAAyaQK9NhJG9WRUiV%2FVpO5siSRZBID0Bi5vgKOMgIz6SnrU3gk1MY5WBl3%2BH1wb5c3mah8dGhhgFpFRarHEjJKgJf9yB2QAGfSR3FVewAyihgQaY8lWGHAFJ3fYqbGb7DkNGH0T5I%2FMKX5M%2FlnZKY%2Bl2GAfLCHD60tlosWwVbNvDeTy%2FuAeqwZ27paO0JPao8HaF0w%3D%3D&sign_type=RSA2&timestamp=2017-12-13+13%3A47%3A39&version=1.0";
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(AlipayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}

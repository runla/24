package com.yinghai.a24divine_user.alipay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.yinghai.a24divine_user.R;

import java.lang.ref.WeakReference;
import java.util.Map;


/**
 * Created by chenjianrun on 2017/11/13.
 * 描述：支付宝支付类
 */

public class Alipay{
    /** 支付宝支付业务：入参app_id */
//    public static final String APPID = "2016082100304720";        //沙箱 appid
    public static final String APPID = "2017111309901663";          //正式 appid


    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static final String RSA2_PRIVATE = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC40vSVoXWjX6ahU6J64t+oeQHI1CplaehqWrMtxACk/UuEseXHQZRDstyK9nCWqJJsRPg2kpc+aryfUpVaRdXzM/cgI+AVpnWaNHaGn+jrVPIqYs0ZTaeqThUsKdKES4MlAxcD0mSrJCxuJ+F4xWQikVLG3P/mIcIZ7qVwnXC1mNainIZowniHxXhBLxEk8EIdrURhR2tF+ASjC98dgNCjwkTlhTNT6IRECwFV1cj/FcQhWbjXHAYaAi8xGGR5dtq8lgTmCEXEvUqIPrcDanm1G6OR9gjE19tM6ikCxOul2hyZjEoAsIL8Sti2YPn+ebnMibRBB1cpq3G59S6j12IzAgMBAAECggEBAJdKJ2esaO04yN9Z5LCOseHoW6uW8LVfAh6869rUjGqB5KVUMfDvH3xjFuCxZewNRP5XXo7dY+x11rzcDuF8NOwFZ47pju5p+e4mb7c3gqPQPwKvKZBC3rIrhG1INsiQP2O2qco5GKp2y9/HyWrguV0KdCXQlbA9hfNQRCBw3+flM3OulfNk7SrnUT4qRkcLovpJsKyWXMJZsNSrz/wPQjnOLEZxHOhxlDVyzCYrCnY8QQ0VBlBIo5EnLaheDHiylPSxOeyuj1NOnPrAsPZeY51EBgUQRVoM2OQLYDFlzxiTCFgycu2luNzEUWht415iOY8trk1ArB+Il0YtOS88PsECgYEA9SSVW7TJLdPAofumep/dh5LupDAE92jHnOqBtoXroBgK1Kkni6lqV8356R/m/oV7QrgsAhnqFTNHgCbIQVNfaerlH7ZR90YQwK74Zizd91wWT394EragFCIyj7JsknEF+lw6NoFhktn0lWqaolOAgrmDTR4myxYRC+p83rB4/NECgYEAwQJ66MNm/3pSo65brl01RLsIybedoUfezEQDaZ3HzOQrPGif3+lDActd3mq3GTAv8xpL8VVQKH8RWXEfd9uzQivX3FR2xZh7O4iGKELi7jw82Xu5K6Jg9MEoozAw8sI7jub2V8tGn2rztc+78L5ZkASlUFJOcLjr9RAcA85Zn8MCgYEA1xw3A7XsIw6BJqLQPyIxHlpMlj6c3Q1Qu9z73dI+JMl0F5AnZ2JOMqZH+c1IwWcX9j/uPhnHUrsgrs9ZQhmKzEKWs/Ekk7sj0DC5P893JyvjV/cewi0G4yPvAjErHIWZHUv4uQNnP5mKCeL1tiNXzV5EXVNr7yF5697jm/V29UECgYAdM/7c0RP4e82dLnh4vWmd6J6/xqR3DqTLS08o39KnUa6pCTuHaYfV8atWR9dEXGIk6kmrTjk+p1s8qT0VqYxJ6tBSagU+iH+81j9WQCiN21dpoKq3y23YGpHHfzcow/K3AIMtbXhWDpypWbEaD/MFM26/5HQx4gjxn2pduQ8eeQKBgQCerQ7+QUzd4ub8vYREUkyDS8+nE1tBJ1U2SfrutxDv97AQiMFPYW1tU2AuHJp9+K1T5Gf/CjLN7AkFg3NiR3BtX+WGiiYkuUADe/HGxTuiMjQRNtYYG6L4N09d+ZxlrCsJpkD/kljX4FWXP/SBfLicdGzo7MwDicy5FbeUUTtbVA==";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private WeakReference<Activity> mActivityWeakReference;
    private AlipayCallback mCallback;
    public Alipay(Activity activity,AlipayCallback callback) {
        mActivityWeakReference = new WeakReference<>(activity);
        mCallback = callback;
    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == SDK_PAY_FLAG){
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();

                if (mCallback == null){
                    return;
                }
                switch (resultStatus) {
                    case "9000":
                        mCallback.alipaySuccess(resultInfo);
                        break;
                    case "8000":
                        mCallback.alipayFailure(mActivityWeakReference.get().getString(R.string.alipay_handler));
                        break;
                    case "4000":
                        mCallback.alipayFailure(mActivityWeakReference.get().getString(R.string.alipay_failure));
                        break;
                    case "5000":
                        mCallback.alipayFailure(mActivityWeakReference.get().getString(R.string.alipay_repeat_pay));
                        break;
                    case "6001":
                        mCallback.alipayFailure(mActivityWeakReference.get().getString(R.string.alipay_cancel));
                        break;
                    case "6002":
                        mCallback.alipayFailure(mActivityWeakReference.get().getString(R.string.alipay_net_error));
                        break;
                    case "6004":
                        mCallback.alipayFailure(mActivityWeakReference.get().getString(R.string.alipay_handler));
                        break;
                    default:
                        mCallback.alipayFailure(mActivityWeakReference.get().getString(R.string.alipay_failure));
                        break;
                }

            }
        }
    };


    /**
     * 支付宝支付业务
     */
    public void startPay(final String payOrderInfo) {
        // 弹出对话框，提示缺少的参数
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(mActivityWeakReference.get())
                    .setTitle("警告")
                    .setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialoginterface, int i) {
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
        /*boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;*/

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivityWeakReference.get());
                Map<String, String> result = alipay.payV2(payOrderInfo, true);
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

    public interface AlipayCallback{
        /**
         * 支付宝支付成功
         * @param resultInfo
         */
        void alipaySuccess(String resultInfo);

        /**
         * 支付宝支付失败
         * @param errMsg
         */
        void alipayFailure(String errMsg);
    }
}

package io.rong.callkit.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by chenjianrun on 2018/1/12.
 * 描述：浮动窗口权限的获取（小米、魅族、华为部分适配）
 */

public class FloatWindowRequest {
    private static int REQUEST_CODE = 101;

    public static void requestPermission(Activity context){
        if (TextUtils.equals("Xiaomi",android.os.Build.BRAND)){
            if (!XiaomiCheckPermission.checkFloatWindowPermission(context)) {
                XiaomiCheckPermission.applyMiuiPermission(context);
            }
            return;
        }

        if (TextUtils.equals("Meizu",android.os.Build.BRAND)){
            if (!MeizuCheckPermission.checkFloatWindowPermission(context)) {
                MeizuCheckPermission.applyPermission(context);
            }
            return;
        }

        if (Build.VERSION.SDK_INT > 23 && !commonROMPermissionCheck(context)) {
            requestAlertWindowPermission(context);
        }
        /*if (TextUtils.equals("HUAWEI",android.os.Build.BRAND)){
            if (!HuaweiCheckPermission.checkFloatWindowPermission(context)) {
                HuaweiCheckPermission.applyPermission(context);
            }
            return;
        }*/
    }

    /**
     * 判断当前是否有显示浮动窗口的权限
     * @param context
     * @return
     */
    public static boolean hasFloatWindowPermission(Context context){
        if (TextUtils.equals("Xiaomi",android.os.Build.BRAND)){
            if (XiaomiCheckPermission.checkFloatWindowPermission(context)) {
                return true;
            }
            return false;
        }

        if (TextUtils.equals("Meizu",android.os.Build.BRAND)){
            if (MeizuCheckPermission.checkFloatWindowPermission(context)) {
                return true;
            }
            return false;
        }


        /*if (TextUtils.equals("HUAWEI",android.os.Build.BRAND)){
            if (HuaweiCheckPermission.checkFloatWindowPermission(context)) {
                return true;
            }
            return false;
        }*/
        return commonROMPermissionCheck(context);
    }


    /**
     * android 6.0 及之后的版本悬浮窗权限判断
     * @param context
     * @return
     */
    private static boolean commonROMPermissionCheck(Context context) {
        Boolean result = true;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class clazz = Settings.class;
                Method canDrawOverlays = clazz.getDeclaredMethod("canDrawOverlays", Context.class);
                result = (Boolean) canDrawOverlays.invoke(null, context);
            } catch (Exception e) {
                Log.e(TAG, Log.getStackTraceString(e));
            }
        }
        return result;
    }

    /**
     * 申请悬浮窗权限
     * @param context
     */
    private static void requestAlertWindowPermission(final Activity context) {
        new AlertDialog.Builder(context)
                .setMessage("是否前往打开浮动框权限")
                .setPositiveButton("前往", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        intent.setData(Uri.parse("package:" + context.getPackageName()));
                        context.startActivityForResult(intent, REQUEST_CODE);
                    }
                }).show();

    }

    //处理回调
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                Log.i(LOGTAG, "onActivityResult granted");
            }
        }
    }*/
}

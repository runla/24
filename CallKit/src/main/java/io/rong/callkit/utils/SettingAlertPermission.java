package io.rong.callkit.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * Created by chenjianrun on 2018/4/10.
 */

public class SettingAlertPermission {
    private static int REQUEST_CODE = 101;

    /**
     * 检测系统弹出权限
     *
     * @return
     */
    @TargetApi(23)
    public static boolean checkSettingAlertPermission(final Activity activity) {
        if (hasPermission(activity)) {
            return true;
        }
        if (TextUtils.equals("Xiaomi", android.os.Build.BRAND)) {
            XiaomiCheckPermission.applyMiuiPermission(activity);
            return false;
        }

        if (TextUtils.equals("Meizu", android.os.Build.BRAND)) {
            MeizuCheckPermission.applyPermission(activity);
            return false;
        }

        new AlertDialog.Builder(activity)
                .setMessage("是否前往打开浮动框权限")
                .setPositiveButton("前往", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + activity.getPackageName()));
                        activity.startActivityForResult(intent, REQUEST_CODE);
                    }
                }).show();
        return false;
    }

    public static boolean hasPermission(Context context) {

        if (TextUtils.equals("Xiaomi", android.os.Build.BRAND)) {
            return XiaomiCheckPermission.checkFloatWindowPermission(context);
        }

        if (TextUtils.equals("Meizu", android.os.Build.BRAND)) {
            return MeizuCheckPermission.checkFloatWindowPermission(context);
        }

        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }

        try {
            return Settings.canDrawOverlays(context);
        }catch (NoSuchMethodError ex){
            return false;
        }
    }
}

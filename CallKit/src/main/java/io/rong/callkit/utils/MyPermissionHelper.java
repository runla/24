package io.rong.callkit.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import java.util.Arrays;
import java.util.List;

import io.rong.callkit.R;

/**
 * Created by chenjianrun on 2017/12/20.
 */

public class MyPermissionHelper {
    private static final int REQUEST_PERMISSION_CODE = 1000;
    private Object mContext;
    private MyPermissionHelper.PermissionListener mListener;
    private List<String> mPermissionList;

    public MyPermissionHelper(@NonNull Object object) {
        this.checkCallingObjectSuitability(object);
        this.mContext = object;
    }

    public void requestPermissions(@NonNull CharSequence hintMessage, @Nullable MyPermissionHelper.PermissionListener listener, @NonNull String... permissions) {
        if(listener != null) {
            this.mListener = listener;
        }

        this.mPermissionList = Arrays.asList(permissions);
        if(!hasPermissions(this.mContext, permissions)) {
            boolean shouldShowRationale = false;
            String[] var5 = permissions;
            int var6 = permissions.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String perm = var5[var7];
                shouldShowRationale = shouldShowRationale || shouldShowRequestPermissionRationale(this.mContext, perm);
            }

            this.executePermissionsRequest(this.mContext, permissions, 1000);
        } else if(this.mListener != null) {
            this.mListener.doAfterGrand(permissions);
        }

    }

    public void handleRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 1000:
                boolean allGranted = true;
                int[] var5 = grantResults;
                int var6 = grantResults.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    int grant = var5[var7];
                    if(grant != 0) {
                        allGranted = false;
                        break;
                    }
                }

                if(allGranted && this.mListener != null && permissions.length != 0) {
                    this.mListener.doAfterGrand((String[])((String[])this.mPermissionList.toArray()));
                } else if(this.mListener != null) {
                    this.mListener.doAfterDenied((String[])((String[])this.mPermissionList.toArray()));
                }
            default:
        }
    }

    public static boolean hasPermissions(@NonNull Object object, @NonNull String... perms) {
        if(Build.VERSION.SDK_INT < 23) {
            return true;
        } else {
            if(object instanceof Fragment) {
                object = ((Fragment)object).getActivity();
            }

            String[] var2 = perms;
            int var3 = perms.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String perm = var2[var4];
                boolean hasPerm = ContextCompat.checkSelfPermission((Context)object, perm) == 0;
                if(!hasPerm) {
                    return false;
                }
            }

            return true;
        }
    }

    @TargetApi(23)
    private static boolean shouldShowRequestPermissionRationale(@NonNull Object object, @NonNull String perm) {
        return object instanceof Activity? ActivityCompat.shouldShowRequestPermissionRationale((Activity)object, perm):(object instanceof Fragment?((Fragment)object).shouldShowRequestPermissionRationale(perm):(object instanceof android.app.Fragment?((android.app.Fragment)object).shouldShowRequestPermissionRationale(perm):false));
    }

    @TargetApi(23)
    private void executePermissionsRequest(@NonNull Object object, @NonNull String[] perms, int requestCode) {
        if(object instanceof Activity) {
            ActivityCompat.requestPermissions((Activity)object, perms, requestCode);
        } else if(object instanceof Fragment) {
            ((Fragment)object).requestPermissions(perms, requestCode);
        } else if(object instanceof android.app.Fragment) {
            ((android.app.Fragment)object).requestPermissions(perms, requestCode);
        }

    }

    private void checkCallingObjectSuitability(@Nullable Object object) {
        if(object == null) {
            throw new NullPointerException("Activity or Fragment should not be null");
        } else {
            boolean isActivity = object instanceof Activity;
            boolean isSupportFragment = object instanceof Fragment;
            boolean isAppFragment = object instanceof android.app.Fragment;
            if(!isSupportFragment && !isActivity && (!isAppFragment || !isNeedRequest())) {
                if(isAppFragment) {
                    throw new IllegalArgumentException("Target SDK needs to be greater than 23 if caller is android.app.Fragment");
                } else {
                    throw new IllegalArgumentException("Caller must be an Activity or a Fragment.");
                }
            }
        }
    }

    @TargetApi(11)
    private static Activity getActivity(@NonNull Object object) {
        return (Activity)(object instanceof Activity?(Activity)object:(object instanceof Fragment?((Fragment)object).getActivity():(object instanceof android.app.Fragment?((android.app.Fragment)object).getActivity():null)));
    }

    public static boolean isNeedRequest() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public void showMessageOKCancel(CharSequence message, DialogInterface.OnClickListener okListener) {
        (new AlertDialog.Builder(getActivity(this.mContext))).setMessage(message).setPositiveButton(R.string.confirm, okListener).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener)null).create().show();
    }

    public interface PermissionListener {
        void doAfterGrand(String... var1);

        void doAfterDenied(String... var1);
    }
}

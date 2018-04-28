package com.yinghai.a24divine_user.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/29 9:47
 *         Describe：
 */

public class HashKeyUtils {

    /**
     * 注意运行的时候，app需要正式的签名
     * @param context
     * @param packageName  app的包名
     */
    public static void getKeyHash(Context context , String packageName ){
        try {
            PackageInfo info = null;
            info = context.getPackageManager().getPackageInfo( packageName , PackageManager.GET_SIGNATURES );
            for ( Signature signature : info.signatures ) {
                MessageDigest messageDigest = null;
                messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                String hs = Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT) ;
                Toast.makeText(context, "" + hs , Toast.LENGTH_SHORT).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

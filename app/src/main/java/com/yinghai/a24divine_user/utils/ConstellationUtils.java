package com.yinghai.a24divine_user.utils;

import com.example.fansonlib.base.AppUtils;
import com.yinghai.a24divine_user.R;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/13 11:38
 *         Describe：星座转换类
 */

public class ConstellationUtils {

    /**
     * 根据后台对应的code获取字符串
     * @param code 后台对应的code
     * @return 字符串
     */
    public static String getString(int code) {
        String result = "";
        switch (code) {
            case 1:
                result = AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[0];
                break;
            case 2:
                result = AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[1];
                break;
            case 3:
                result = AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[2];
                break;
            case 4:
                result = AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[3];
                break;
            case 5:
                result = AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[4];
                break;
            case 6:
                result =AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[5];
                break;
            case 7:
                result = AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[6];
                break;
            case 8:
                result = AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[7];
                break;
            case 9:
                result = AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[8];
                break;
            case 10:
                result = AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[9];
                break;
            case 11:
                result = AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[10];
                break;
            case 12:
                result = AppUtils.getAppContext().getResources().getStringArray(R.array.constellation_array)[11];
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 根据字符串获取后台对应的code
     * @param content 字符串
     * @return 后台对应的code
     */
    public static int getCode(String content) {
        int code = -1;
        switch (content) {
            case "水瓶座":
                code = 1;
                break;
            case "双鱼座":
                code = 2;
                break;
            case "白羊座":
                code = 3;
                break;
            case "金牛座":
                code = 4;
                break;
            case "双子座":
                code = 5;
                break;
            case "巨蟹座":
                code = 6;
                break;
            case "狮子座":
                code = 7;
                break;
            case "处女座":
                code = 8;
                break;
            case "天秤座":
                code = 9;
                break;
            case "天蝎座":
                code = 10;
                break;
            case "射手座":
                code =11;
                break;
            case "魔蝎座":
                code = 12;
                break;
            default:
                break;
        }
        return code;
    }

}

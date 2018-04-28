package com.yinghai.a24divine_user.function.internationalCode;

/**
 * area code item
 */

public class AreaCode {
    private String mArea;
    private String mCode;
    private String mGroupName;
    private boolean mIsTop;

    public AreaCode(String area, String code) {
        mArea = area;
        mCode = code;
    }

    public String getArea() {
        return mArea;
    }

    public String getCode() {
        return mCode;
    }

    public String getGroupName() {
        return mGroupName;
    }

    public boolean isTop() {
        return mIsTop;
    }

    public void setGroupName(String groupName) {
        mGroupName = groupName;
    }

    public void setTop(boolean top) {
        mIsTop = top;
    }
}

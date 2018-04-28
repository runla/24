package com.yinghai.a24divine_user.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by：fanson
 * Created on：2017/10/11 17:19
 * Describe：本地使用图片的Bean
 */

public class PictureBean implements Parcelable{

    private String urlPicture;

    public static final Creator<PictureBean> CREATOR = new Creator<PictureBean>() {
        @Override
        public PictureBean createFromParcel(Parcel in) {
            PictureBean bean = new PictureBean();
            bean.urlPicture = in.readString();
            return bean;
        }

        @Override
        public PictureBean[] newArray(int size) {
            return new PictureBean[size];
        }
    };

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(urlPicture);
    }
}

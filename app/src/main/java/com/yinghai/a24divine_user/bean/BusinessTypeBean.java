package com.yinghai.a24divine_user.bean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/15 17:19
 *         Describe：本地使用的Bean
 */

public class BusinessTypeBean {

    private int bgResourseId;
    private int typeResourseId;
    private String descripe;

    public BusinessTypeBean(int bgResourseId, int typeResourseId, String descripe) {
        this.bgResourseId = bgResourseId;
        this.typeResourseId = typeResourseId;
        this.descripe = descripe;
    }

    public int getBgResourseId() {
        return bgResourseId;
    }

    public void setBgResourseId(int bgResourseId) {
        this.bgResourseId = bgResourseId;
    }

    public int getTypeResourseId() {
        return typeResourseId;
    }

    public void setTypeResourseId(int typeResourseId) {
        this.typeResourseId = typeResourseId;
    }

    public String getDescripe() {
        return descripe;
    }

    public void setDescripe(String descripe) {
        this.descripe = descripe;
    }

}

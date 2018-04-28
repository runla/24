package com.yinghai.a24divine_user.bean;

/**
 * Created by chenjianrun on 2017/11/14.
 * 描述：手机登录、密码登录、获取个人信息、更新个人信息返回的 bean
 */

public class PersonInfoBean {


    /**
     * code : 1
     * msg : 登录操作成功
     * data : {"tfUser":{"uAccid":0,"uBirthday":"","uConstellation":7,"uCountryCode":"86","uCreateTime":"2017-11-10 17:18:35","uDeleted":false,"uDeviceId":"","uDeviceType":0,"uIm":"","uImgUrl":"","uNick":"测试","uPassword":"14e1b600b1fd579f47433b88e8d85291","uSex":true,"uStatus":0,"uTel":"15919161025","uToken":"","uUpdateTime":"","userId":6}}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tfUser : {"uAccid":0,"uBirthday":"","uConstellation":7,"uCountryCode":"86","uCreateTime":"2017-11-10 17:18:35","uDeleted":false,"uDeviceId":"","uDeviceType":0,"uIm":"","uImgUrl":"","uNick":"测试","uPassword":"14e1b600b1fd579f47433b88e8d85291","uSex":true,"uStatus":0,"uTel":"15919161025","uToken":"","uUpdateTime":"","userId":6}
         */

        private TfUserBean tfUser;

        private int thirdPartyId;

        public int getThirdPartyId() {
            return thirdPartyId;
        }

        public void setThirdPartyId(int thirdPartyId) {
            this.thirdPartyId = thirdPartyId;
        }

        public TfUserBean getTfUser() {
            return tfUser;
        }

        public void setTfUser(TfUserBean tfUser) {
            this.tfUser = tfUser;
        }

        public static class TfUserBean {
            /**
             * uAccid : 0
             * uBirthday :
             * uConstellation : 7
             * uCountryCode : 86
             * uCreateTime : 2017-11-10 17:18:35
             * uDeleted : false
             * uDeviceId :
             * uDeviceType : 0
             * uIm :
             * uImgUrl :
             * uNick : 测试
             * uPassword : 14e1b600b1fd579f47433b88e8d85291
             * uSex : true
             * uStatus : 0
             * uTel : 15919161025
             * uToken :
             * uUpdateTime :
             * userId : 6
             * uIsFriend : false
             */

            private int uAccid;
            private String uBirthday;
            private int uConstellation;
            private String uCountryCode;
            private String uCreateTime;
            private boolean uDeleted;
            private String uDeviceId;
            private int uDeviceType;
            private String uIm;
            private String uImgUrl;
            private String uNick;
            private String uPassword;
            private boolean uSex;
            private int uStatus;
            private String uTel;
            private String uToken;
            private String uUpdateTime;
            private int userId;
            private boolean uIsFriend;

            public boolean isuIsFriend() {
                return uIsFriend;
            }

            public void setuIsFriend(boolean uIsFriend) {
                this.uIsFriend = uIsFriend;
            }

            public int getUAccid() {
                return uAccid;
            }

            public void setUAccid(int uAccid) {
                this.uAccid = uAccid;
            }

            public String getUBirthday() {
                return uBirthday;
            }

            public void setUBirthday(String uBirthday) {
                this.uBirthday = uBirthday;
            }

            public int getUConstellation() {
                return uConstellation;
            }

            public void setUConstellation(int uConstellation) {
                this.uConstellation = uConstellation;
            }

            public String getUCountryCode() {
                return uCountryCode;
            }

            public void setUCountryCode(String uCountryCode) {
                this.uCountryCode = uCountryCode;
            }

            public String getUCreateTime() {
                return uCreateTime;
            }

            public void setUCreateTime(String uCreateTime) {
                this.uCreateTime = uCreateTime;
            }

            public boolean isUDeleted() {
                return uDeleted;
            }

            public void setUDeleted(boolean uDeleted) {
                this.uDeleted = uDeleted;
            }

            public String getUDeviceId() {
                return uDeviceId;
            }

            public void setUDeviceId(String uDeviceId) {
                this.uDeviceId = uDeviceId;
            }

            public int getUDeviceType() {
                return uDeviceType;
            }

            public void setUDeviceType(int uDeviceType) {
                this.uDeviceType = uDeviceType;
            }

            public String getUIm() {
                return uIm;
            }

            public void setUIm(String uIm) {
                this.uIm = uIm;
            }

            public String getUImgUrl() {
                return uImgUrl;
            }

            public void setUImgUrl(String uImgUrl) {
                this.uImgUrl = uImgUrl;
            }

            public String getUNick() {
                return uNick;
            }

            public void setUNick(String uNick) {
                this.uNick = uNick;
            }

            public String getUPassword() {
                return uPassword;
            }

            public void setUPassword(String uPassword) {
                this.uPassword = uPassword;
            }

            public boolean isUSex() {
                return uSex;
            }

            public void setUSex(boolean uSex) {
                this.uSex = uSex;
            }

            public int getUStatus() {
                return uStatus;
            }

            public void setUStatus(int uStatus) {
                this.uStatus = uStatus;
            }

            public String getUTel() {
                return uTel;
            }

            public void setUTel(String uTel) {
                this.uTel = uTel;
            }

            public String getUToken() {
                return uToken;
            }

            public void setUToken(String uToken) {
                this.uToken = uToken;
            }

            public String getUUpdateTime() {
                return uUpdateTime;
            }

            public void setUUpdateTime(String uUpdateTime) {
                this.uUpdateTime = uUpdateTime;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}

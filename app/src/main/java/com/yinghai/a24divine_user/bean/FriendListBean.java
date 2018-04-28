package com.yinghai.a24divine_user.bean;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/27 14:27
 *         Describe：好友列表Bean
 */

public class FriendListBean {

    /**
     * code : 1
     * msg : success
     * data : {"friend":[{"uAccid":0,"uBirthday":"","uConstellation":1,"uCountryCode":"86","uCreateTime":"2017-11-13 13:25:35","uDeleted":false,"uDeviceId":"","uDeviceType":0,"uIm":"user9","uImgUrl":"","uNick":"123","uPassword":"504b4b8ad49121e5ba6ac55383149a1d","uSex":true,"uStatus":0,"uTel":"13421266952","uToken":"OWturV++l5CUCS/z54hb+97+WtsNIwZY4vddmjXVTeg/Cx5cWvhO37zLzlAPwDMXClc+Uo4IB7ja3jWrCoLBCQ==","uUpdateTime":"","userId":9},{"uAccid":0,"uBirthday":"","uConstellation":1,"uCountryCode":"86","uCreateTime":"2017-11-13 09:47:11","uDeleted":false,"uDeviceId":"","uDeviceType":0,"uIm":"user8","uImgUrl":"","uNick":"647","uPassword":"14e1b600b1fd579f47433b88e8d85291","uSex":true,"uStatus":0,"uTel":"18933897092","uToken":"fdfgyMyEM4H16MpgUJbWdhjeQp4KnW8wYIhuh4u5bQPjr4awkHWul/wD/0sGpAb+/f5jcCpiYyAIcbz08ThFhg==","uUpdateTime":"","userId":8}]}
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
        private List<FriendBean> friend;

        public List<FriendBean> getFriend() {
            return friend;
        }

        public void setFriend(List<FriendBean> friend) {
            this.friend = friend;
        }

        public static class FriendBean {
            /**
             * uAccid : 0
             * uBirthday :
             * uConstellation : 1
             * uCountryCode : 86
             * uCreateTime : 2017-11-13 13:25:35
             * uDeleted : false
             * uDeviceId :
             * uDeviceType : 0
             * uIm : user9
             * uImgUrl :
             * uNick : 123
             * uPassword : 504b4b8ad49121e5ba6ac55383149a1d
             * uSex : true
             * uStatus : 0
             * uTel : 13421266952
             * uToken : OWturV++l5CUCS/z54hb+97+WtsNIwZY4vddmjXVTeg/Cx5cWvhO37zLzlAPwDMXClc+Uo4IB7ja3jWrCoLBCQ==
             * uUpdateTime :
             * userId : 9
             * uIsFriend : true
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
            /**
             * 本地变量，未读消息的数量
             */
            private int unreadAmount;


            public int getUnreadAmount() {
                return unreadAmount;
            }

            public void setUnreadAmount(int unreadAmount) {
                this.unreadAmount = unreadAmount;
            }

            public int getUAccid() {
                return uAccid;
            }

            public void setUAccid(int uAccid) {
                this.uAccid = uAccid;
            }

            public boolean isuIsFriend() {
                return uIsFriend;
            }

            public void setuIsFriend(boolean uIsFriend) {
                this.uIsFriend = uIsFriend;
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

            @Override
            public boolean equals(Object obj) {
                if (obj == null) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                if (obj instanceof FriendBean) {
                    FriendBean bean = (FriendBean) obj;
                    if (this.userId == bean.getUserId()) {
                        return true;
                    } else {
                        return false;
                    }

                }
                return false;
            }
        }
    }
}



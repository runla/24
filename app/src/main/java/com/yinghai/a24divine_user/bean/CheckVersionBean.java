package com.yinghai.a24divine_user.bean;

/**
 * @author Created by：fanson
 *         Created Time: 2018/3/15 11:33
 *         Describe：检测版本更新的Bean
 */

public class CheckVersionBean {


    /**
     * code : 1
     * msg : success
     * data : {"versionController":{"createTime":"2017-11-07 10:15:28","describe":"1.是是是2.送达","deviceType":"1","forceUpdate":false,"id":4,"priority":0,"realm":"master","updateTime":"","versionId":"2.0.1"}}
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
         * versionController : {"createTime":"2017-11-07 10:15:28","describe":"1.是是是2.送达","deviceType":"1","forceUpdate":false,"id":4,"priority":0,"realm":"master","updateTime":"","versionId":"2.0.1"}
         */

        private VersionControllerBean versionController;

        public VersionControllerBean getVersionController() {
            return versionController;
        }

        public void setVersionController(VersionControllerBean versionController) {
            this.versionController = versionController;
        }

        public static class VersionControllerBean {
            /**
             * createTime : 2017-11-07 10:15:28
             * describe : 1.是是是2.送达
             * deviceType : 1
             * forceUpdate : false
             * id : 4
             * priority : 0
             * realm : master
             * updateTime :
             * versionId : 2.0.1
             */

            private String createTime;
            private String describe;
            private String deviceType;
            private boolean forceUpdate;
            private int id;
            private int priority;
            private String realm;
            private String updateTime;
            private int versionId;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public boolean isForceUpdate() {
                return forceUpdate;
            }

            public void setForceUpdate(boolean forceUpdate) {
                this.forceUpdate = forceUpdate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPriority() {
                return priority;
            }

            public void setPriority(int priority) {
                this.priority = priority;
            }

            public String getRealm() {
                return realm;
            }

            public void setRealm(String realm) {
                this.realm = realm;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getVersionId() {
                return versionId;
            }

            public void setVersionId(int versionId) {
                this.versionId = versionId;
            }
        }
    }
}

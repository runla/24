package com.yinghai.a24divine_user.bean;

/**
 * Created by chenjianrun on 2017/11/14.
 * 描述：开启运势的 bean
 */

public class LuckBean {

    /**
     * code : 1
     * msg : success
     * data : {"lColor":"","lConstellation":9,"lCreateTime":"2017-10-19","lDate":"2017-10-19","lEmotion":"单身者在工作中易与异性投缘，但建议感情别投入太多","lMore":"感情上对另一半的不信任让你心情低落，容易被外人看法影响。事业压力大，不妨跟家人说说，得到家人支持会让烦恼消散不少。对现在这份工作有点厌倦，有想要离职跳槽的想法。","lNumber":3,"lSuit":"宜","lUndertaking":"工作上不要受外人怂恿，跟从自己的内心做事吧。工作上不要受外人怂恿，跟从自己的内心做事吧","lUnsuitable":"不宜","lUpdateTime":"2017-10-19","luckId":1}
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

    @Override
    public String toString() {
        return "LuckBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * lColor :
         * lConstellation : 9
         * lCreateTime : 2017-10-19
         * lDate : 2017-10-19
         * lEmotion : 单身者在工作中易与异性投缘，但建议感情别投入太多
         * lMore : 感情上对另一半的不信任让你心情低落，容易被外人看法影响。事业压力大，不妨跟家人说说，得到家人支持会让烦恼消散不少。对现在这份工作有点厌倦，有想要离职跳槽的想法。
         * lNumber : 3
         * lSuit : 宜
         * lUndertaking : 工作上不要受外人怂恿，跟从自己的内心做事吧。工作上不要受外人怂恿，跟从自己的内心做事吧
         * lUnsuitable : 不宜
         * lUpdateTime : 2017-10-19
         * luckId : 1
         */

        private String lColor;
        private int lConstellation;
        private String lCreateTime;
        private String lDate;
        private String lEmotion;
        private String lMore;
        private int lNumber;
        private String lSuit;
        private String lUndertaking;
        private String lUnsuitable;
        private String lUpdateTime;
        private int luckId;

        public String getLColor() {
            return lColor;
        }

        public void setLColor(String lColor) {
            this.lColor = lColor;
        }

        public int getLConstellation() {
            return lConstellation;
        }

        public void setLConstellation(int lConstellation) {
            this.lConstellation = lConstellation;
        }

        public String getLCreateTime() {
            return lCreateTime;
        }

        public void setLCreateTime(String lCreateTime) {
            this.lCreateTime = lCreateTime;
        }

        public String getLDate() {
            return lDate;
        }

        public void setLDate(String lDate) {
            this.lDate = lDate;
        }

        public String getLEmotion() {
            return lEmotion;
        }

        public void setLEmotion(String lEmotion) {
            this.lEmotion = lEmotion;
        }

        public String getLMore() {
            return lMore;
        }

        public void setLMore(String lMore) {
            this.lMore = lMore;
        }

        public int getLNumber() {
            return lNumber;
        }

        public void setLNumber(int lNumber) {
            this.lNumber = lNumber;
        }

        public String getLSuit() {
            return lSuit;
        }

        public void setLSuit(String lSuit) {
            this.lSuit = lSuit;
        }

        public String getLUndertaking() {
            return lUndertaking;
        }

        public void setLUndertaking(String lUndertaking) {
            this.lUndertaking = lUndertaking;
        }

        public String getLUnsuitable() {
            return lUnsuitable;
        }

        public void setLUnsuitable(String lUnsuitable) {
            this.lUnsuitable = lUnsuitable;
        }

        public String getLUpdateTime() {
            return lUpdateTime;
        }

        public void setLUpdateTime(String lUpdateTime) {
            this.lUpdateTime = lUpdateTime;
        }

        public int getLuckId() {
            return luckId;
        }

        public void setLuckId(int luckId) {
            this.luckId = luckId;
        }
    }
}

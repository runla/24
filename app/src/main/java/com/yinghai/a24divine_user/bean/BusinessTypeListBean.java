package com.yinghai.a24divine_user.bean;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/12/28 14:44
 *         Describe：业务类型（帅选大师业务，文章，商品）
 */

public class BusinessTypeListBean {


    /**
     * code : 1
     * msg : success
     * data : {"total":13,"pages":5,"pageSize":3,"pageNum":1,"tfBusinessTypeList":[{"businessTypeId":1,"typeCreateTime":"2017-12-20 11:37:06","typeDescription":"","typeName":"奇門遁甲","typeUpdateTime":""},{"businessTypeId":2,"typeCreateTime":"2017-12-20 11:37:14","typeDescription":"","typeName":"紫微斗數","typeUpdateTime":""},{"businessTypeId":3,"typeCreateTime":"2017-12-20 11:37:23","typeDescription":"","typeName":"子平八字","typeUpdateTime":""}]}
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
         * total : 13
         * pages : 5
         * pageSize : 3
         * pageNum : 1
         * tfBusinessTypeList : [{"businessTypeId":1,"typeCreateTime":"2017-12-20 11:37:06","typeDescription":"","typeName":"奇門遁甲","typeUpdateTime":""},{"businessTypeId":2,"typeCreateTime":"2017-12-20 11:37:14","typeDescription":"","typeName":"紫微斗數","typeUpdateTime":""},{"businessTypeId":3,"typeCreateTime":"2017-12-20 11:37:23","typeDescription":"","typeName":"子平八字","typeUpdateTime":""}]
         */

        private int total;
        private int pages;
        private int pageSize;
        private int pageNum;
        private List<TfBusinessTypeListBean> tfBusinessTypeList;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public List<TfBusinessTypeListBean> getTfBusinessTypeList() {
            return tfBusinessTypeList;
        }

        public void setTfBusinessTypeList(List<TfBusinessTypeListBean> tfBusinessTypeList) {
            this.tfBusinessTypeList = tfBusinessTypeList;
        }

        public static class TfBusinessTypeListBean {
            /**
             * businessTypeId : 1
             * typeCreateTime : 2017-12-20 11:37:06
             * typeDescription :
             * typeName : 奇門遁甲
             * typeUpdateTime :
             */

            private String businessTypeId;
            private String typeCreateTime;
            private String typeDescription;
            private String typeName;
            private String typeUpdateTime;

            public String getBusinessTypeId() {
                return businessTypeId;
            }

            public void setBusinessTypeId(String businessTypeId) {
                this.businessTypeId = businessTypeId;
            }

            public String getTypeCreateTime() {
                return typeCreateTime;
            }

            public void setTypeCreateTime(String typeCreateTime) {
                this.typeCreateTime = typeCreateTime;
            }

            public String getTypeDescription() {
                return typeDescription;
            }

            public void setTypeDescription(String typeDescription) {
                this.typeDescription = typeDescription;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getTypeUpdateTime() {
                return typeUpdateTime;
            }

            public void setTypeUpdateTime(String typeUpdateTime) {
                this.typeUpdateTime = typeUpdateTime;
            }
        }
    }
}

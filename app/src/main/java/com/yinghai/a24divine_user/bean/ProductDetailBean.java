package com.yinghai.a24divine_user.bean;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/21 11:14
 *         Describe：商品详情Bean
 */

public class ProductDetailBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"tfProduct":{"imgList":[{"imgTmpId":18,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":false,"isCollection":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"http://192.168.0.165:8080/file\\images/article/\\2\\Q6fZ7_fileName.jpg"}],"pAttribution":"浙江金华","pCreateTime":"2017-10-24 13:36:32","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"带金星小叶紫檀深紫发黑的色泽，苍老的沧桑质感浓厚。油脂充足，料质油润细腻，整体荧光质感强烈。","pMasterId":1,"pName":"名木香府老料小叶紫檀满金星手串20毫米男女款满金星老料紫檀手串手链 紫檀Q211款 编号Q211-05","pOffline":false,"pPrice":1800,"pSize":"18-25毫米（18-25mm）","pTotal":7,"pUpdateTime":"2017-11-09 18:40:20","productId":1}}
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
         * tfProduct : {"imgList":[{"imgTmpId":18,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":false,"isCollection":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"http://192.168.0.165:8080/file\\images/article/\\2\\Q6fZ7_fileName.jpg"}],"pAttribution":"浙江金华","pCreateTime":"2017-10-24 13:36:32","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"带金星小叶紫檀深紫发黑的色泽，苍老的沧桑质感浓厚。油脂充足，料质油润细腻，整体荧光质感强烈。","pMasterId":1,"pName":"名木香府老料小叶紫檀满金星手串20毫米男女款满金星老料紫檀手串手链 紫檀Q211款 编号Q211-05","pOffline":false,"pPrice":1800,"pSize":"18-25毫米（18-25mm）","pTotal":7,"pUpdateTime":"2017-11-09 18:40:20","productId":1}
         */

        private TfProductBean tfProduct;

        public TfProductBean getTfProduct() {
            return tfProduct;
        }

        public void setTfProduct(TfProductBean tfProduct) {
            this.tfProduct = tfProduct;
        }

        public static class TfProductBean {
            /**
             * imgList : [{"imgTmpId":18,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":false,"isCollection":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"http://192.168.0.165:8080/file\\images/article/\\2\\Q6fZ7_fileName.jpg"}]
             * pAttribution : 浙江金华
             * pCreateTime : 2017-10-24 13:36:32
             * pDeals : 0
             * pDelete : false
             * pFreeShipping : true
             * pHot : 0
             * pImg :
             * pIntroduction : 带金星小叶紫檀深紫发黑的色泽，苍老的沧桑质感浓厚。油脂充足，料质油润细腻，整体荧光质感强烈。
             * pMasterId : 1
             * pName : 名木香府老料小叶紫檀满金星手串20毫米男女款满金星老料紫檀手串手链 紫檀Q211款 编号Q211-05
             * pOffline : false
             * pPrice : 1800
             * pSize : 18-25毫米（18-25mm）
             * pTotal : 7
             * pUpdateTime : 2017-11-09 18:40:20
             * productId : 1
             */

            private String pAttribution;
            private String pCreateTime;
            private int pDeals;
            private boolean pDelete;
            private boolean pFreeShipping;
            private int pHot;
            private String pImg;
            private String pIntroduction;
            private int pMasterId;
            private String pName;
            private boolean pOffline;
            private int pPrice;
            private String pSize;
            private int pTotal;
            private String pUpdateTime;
            private int productId;
            private List<ImgListBean> imgList;

            public String getPAttribution() {
                return pAttribution;
            }

            public void setPAttribution(String pAttribution) {
                this.pAttribution = pAttribution;
            }

            public String getPCreateTime() {
                return pCreateTime;
            }

            public void setPCreateTime(String pCreateTime) {
                this.pCreateTime = pCreateTime;
            }

            public int getPDeals() {
                return pDeals;
            }

            public void setPDeals(int pDeals) {
                this.pDeals = pDeals;
            }

            public boolean isPDelete() {
                return pDelete;
            }

            public void setPDelete(boolean pDelete) {
                this.pDelete = pDelete;
            }

            public boolean isPFreeShipping() {
                return pFreeShipping;
            }

            public void setPFreeShipping(boolean pFreeShipping) {
                this.pFreeShipping = pFreeShipping;
            }

            public int getPHot() {
                return pHot;
            }

            public void setPHot(int pHot) {
                this.pHot = pHot;
            }

            public String getPImg() {
                return pImg;
            }

            public void setPImg(String pImg) {
                this.pImg = pImg;
            }

            public String getPIntroduction() {
                return pIntroduction;
            }

            public void setPIntroduction(String pIntroduction) {
                this.pIntroduction = pIntroduction;
            }

            public int getPMasterId() {
                return pMasterId;
            }

            public void setPMasterId(int pMasterId) {
                this.pMasterId = pMasterId;
            }

            public String getPName() {
                return pName;
            }

            public void setPName(String pName) {
                this.pName = pName;
            }

            public boolean isPOffline() {
                return pOffline;
            }

            public void setPOffline(boolean pOffline) {
                this.pOffline = pOffline;
            }

            public int getPPrice() {
                return pPrice;
            }

            public void setPPrice(int pPrice) {
                this.pPrice = pPrice;
            }

            public String getPSize() {
                return pSize;
            }

            public void setPSize(String pSize) {
                this.pSize = pSize;
            }

            public int getPTotal() {
                return pTotal;
            }

            public void setPTotal(int pTotal) {
                this.pTotal = pTotal;
            }

            public String getPUpdateTime() {
                return pUpdateTime;
            }

            public void setPUpdateTime(String pUpdateTime) {
                this.pUpdateTime = pUpdateTime;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public List<ImgListBean> getImgList() {
                return imgList;
            }

            public void setImgList(List<ImgListBean> imgList) {
                this.imgList = imgList;
            }

            public static class ImgListBean {
                /**
                 * imgTmpId : 18
                 * itAbsolute :
                 * itAppPath :
                 * itCreateTime :
                 * itIsUser : false
                 * isCollection : true
                 * itKeyId : 0
                 * itMasterId : 0
                 * itType : 1
                 * itUpdateTime :
                 * itUrl : http://192.168.0.165:8080/file\images/article/\2\Q6fZ7_fileName.jpg
                 */

                private int imgTmpId;
                private String itAbsolute;
                private String itAppPath;
                private String itCreateTime;
                private boolean itIsUser;
                private boolean isCollection;
                private int itKeyId;
                private int itMasterId;
                private int itType;
                private String itUpdateTime;
                private String itUrl;

                public int getImgTmpId() {
                    return imgTmpId;
                }

                public void setImgTmpId(int imgTmpId) {
                    this.imgTmpId = imgTmpId;
                }

                public String getItAbsolute() {
                    return itAbsolute;
                }

                public void setItAbsolute(String itAbsolute) {
                    this.itAbsolute = itAbsolute;
                }

                public String getItAppPath() {
                    return itAppPath;
                }

                public void setItAppPath(String itAppPath) {
                    this.itAppPath = itAppPath;
                }

                public String getItCreateTime() {
                    return itCreateTime;
                }

                public void setItCreateTime(String itCreateTime) {
                    this.itCreateTime = itCreateTime;
                }

                public boolean isItIsUser() {
                    return itIsUser;
                }

                public void setItIsUser(boolean itIsUser) {
                    this.itIsUser = itIsUser;
                }

                public boolean isIsCollection() {
                    return isCollection;
                }

                public void setIsCollection(boolean isCollection) {
                    this.isCollection = isCollection;
                }

                public int getItKeyId() {
                    return itKeyId;
                }

                public void setItKeyId(int itKeyId) {
                    this.itKeyId = itKeyId;
                }

                public int getItMasterId() {
                    return itMasterId;
                }

                public void setItMasterId(int itMasterId) {
                    this.itMasterId = itMasterId;
                }

                public int getItType() {
                    return itType;
                }

                public void setItType(int itType) {
                    this.itType = itType;
                }

                public String getItUpdateTime() {
                    return itUpdateTime;
                }

                public void setItUpdateTime(String itUpdateTime) {
                    this.itUpdateTime = itUpdateTime;
                }

                public String getItUrl() {
                    return itUrl;
                }

                public void setItUrl(String itUrl) {
                    this.itUrl = itUrl;
                }
            }
        }
    }
}

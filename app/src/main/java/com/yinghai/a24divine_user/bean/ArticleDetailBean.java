package com.yinghai.a24divine_user.bean;

import java.util.List;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/21 11:12
 *         Describe：文章详情Bean
 */

public class ArticleDetailBean {


    /**
     * code : 1
     * msg : 操作成功
     * data : {"tfArticle":{"aAbortDate":"","aAddType":1,"aContent":"6866168634313元一个平方左右就回来3分钟3块钱一个平方米㎡33左右的3楼话我现在就在我旁边有个朋友吧，。你在哪个医院看看了啊\n[图片]\n\n[图片]\n\n[图片]","aCreateTime":"2017-11-27 18:31:29","aDelete":false,"aImg":"","aMasterId":17,"aPublishDate":"2017-11-27 00:00:00","aReadAmount":89,"aTitle":"你美女","aType":"1","aTypeArray":[],"aTypeName":"奇門遁甲","aUpdateTime":"2018-04-09 17:47:38","aUrl":"","articleId":59,"imgList":[{"imgTmpId":410,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":2,"itUpdateTime":"","itUrl":"/file/images/product/17/aQCtF_1511778699.png"},{"imgTmpId":411,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":2,"itUpdateTime":"","itUrl":"/file/images/product/17/pksVP_1511778699.png"},{"imgTmpId":412,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":2,"itUpdateTime":"","itUrl":"/file/images/product/17/57S4h_1511778699.png"},{"imgTmpId":493,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/15129631621429.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":1,"itUpdateTime":"","itUrl":"/file/images/product/2/jFPh6_15129631621429.png"}],"isCollection":false,"queryUserId":0,"tfMaster":{"mHeadImg":"/file//images/master/186661369011513324742691.png","mNick":"Marlon君","masterId":17}}}
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
         * tfArticle : {"aAbortDate":"","aAddType":1,"aContent":"6866168634313元一个平方左右就回来3分钟3块钱一个平方米㎡33左右的3楼话我现在就在我旁边有个朋友吧，。你在哪个医院看看了啊\n[图片]\n\n[图片]\n\n[图片]","aCreateTime":"2017-11-27 18:31:29","aDelete":false,"aImg":"","aMasterId":17,"aPublishDate":"2017-11-27 00:00:00","aReadAmount":89,"aTitle":"你美女","aType":"1","aTypeArray":[],"aTypeName":"奇門遁甲","aUpdateTime":"2018-04-09 17:47:38","aUrl":"","articleId":59,"imgList":[{"imgTmpId":410,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":2,"itUpdateTime":"","itUrl":"/file/images/product/17/aQCtF_1511778699.png"},{"imgTmpId":411,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":2,"itUpdateTime":"","itUrl":"/file/images/product/17/pksVP_1511778699.png"},{"imgTmpId":412,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":2,"itUpdateTime":"","itUrl":"/file/images/product/17/57S4h_1511778699.png"},{"imgTmpId":493,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/15129631621429.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":1,"itUpdateTime":"","itUrl":"/file/images/product/2/jFPh6_15129631621429.png"}],"isCollection":false,"queryUserId":0,"tfMaster":{"mHeadImg":"/file//images/master/186661369011513324742691.png","mNick":"Marlon君","masterId":17}}
         */

        private TfArticleBean tfArticle;

        public TfArticleBean getTfArticle() {
            return tfArticle;
        }

        public void setTfArticle(TfArticleBean tfArticle) {
            this.tfArticle = tfArticle;
        }

        public static class TfArticleBean {
            /**
             * aAbortDate :
             * aAddType : 1
             * aContent : 6866168634313元一个平方左右就回来3分钟3块钱一个平方米㎡33左右的3楼话我现在就在我旁边有个朋友吧，。你在哪个医院看看了啊
             [图片]

             [图片]

             [图片]
             * aCreateTime : 2017-11-27 18:31:29
             * aDelete : false
             * aImg :
             * aMasterId : 17
             * aPublishDate : 2017-11-27 00:00:00
             * aReadAmount : 89
             * aTitle : 你美女
             * aType : 1
             * aTypeArray : []
             * aTypeName : 奇門遁甲
             * aUpdateTime : 2018-04-09 17:47:38
             * aUrl :
             * articleId : 59
             * imgList : [{"imgTmpId":410,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":2,"itUpdateTime":"","itUrl":"/file/images/product/17/aQCtF_1511778699.png"},{"imgTmpId":411,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":2,"itUpdateTime":"","itUrl":"/file/images/product/17/pksVP_1511778699.png"},{"imgTmpId":412,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":2,"itUpdateTime":"","itUrl":"/file/images/product/17/57S4h_1511778699.png"},{"imgTmpId":493,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/15129631621429.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itSize":"","itType":1,"itUpdateTime":"","itUrl":"/file/images/product/2/jFPh6_15129631621429.png"}]
             * isCollection : false
             * queryUserId : 0
             * tfMaster : {"mHeadImg":"/file//images/master/186661369011513324742691.png","mNick":"Marlon君","masterId":17}
             */

            private String aAbortDate;
            private int aAddType;
            private String aContent;
            private String aCreateTime;
            private boolean aDelete;
            private String aImg;
            private int aMasterId;
            private String aPublishDate;
            private int aReadAmount;
            private String aTitle;
            private String aType;
            private String aTypeName;
            private String aUpdateTime;
            private String aUrl;
            private int articleId;
            private boolean isCollection;
            private TfMasterBean tfMaster;
            private List<?> aTypeArray;
            private List<ImgListBean> imgList;

            public String getAAbortDate() {
                return aAbortDate;
            }

            public void setAAbortDate(String aAbortDate) {
                this.aAbortDate = aAbortDate;
            }

            public int getAAddType() {
                return aAddType;
            }

            public void setAAddType(int aAddType) {
                this.aAddType = aAddType;
            }

            public String getAContent() {
                return aContent;
            }

            public void setAContent(String aContent) {
                this.aContent = aContent;
            }

            public String getACreateTime() {
                return aCreateTime;
            }

            public void setACreateTime(String aCreateTime) {
                this.aCreateTime = aCreateTime;
            }

            public boolean isADelete() {
                return aDelete;
            }

            public void setADelete(boolean aDelete) {
                this.aDelete = aDelete;
            }

            public String getAImg() {
                return aImg;
            }

            public void setAImg(String aImg) {
                this.aImg = aImg;
            }

            public int getAMasterId() {
                return aMasterId;
            }

            public void setAMasterId(int aMasterId) {
                this.aMasterId = aMasterId;
            }

            public String getAPublishDate() {
                return aPublishDate;
            }

            public void setAPublishDate(String aPublishDate) {
                this.aPublishDate = aPublishDate;
            }

            public int getAReadAmount() {
                return aReadAmount;
            }

            public void setAReadAmount(int aReadAmount) {
                this.aReadAmount = aReadAmount;
            }

            public String getATitle() {
                return aTitle;
            }

            public void setATitle(String aTitle) {
                this.aTitle = aTitle;
            }

            public String getAType() {
                return aType;
            }

            public void setAType(String aType) {
                this.aType = aType;
            }

            public String getATypeName() {
                return aTypeName;
            }

            public void setATypeName(String aTypeName) {
                this.aTypeName = aTypeName;
            }

            public String getAUpdateTime() {
                return aUpdateTime;
            }

            public void setAUpdateTime(String aUpdateTime) {
                this.aUpdateTime = aUpdateTime;
            }

            public String getAUrl() {
                return aUrl;
            }

            public void setAUrl(String aUrl) {
                this.aUrl = aUrl;
            }

            public int getArticleId() {
                return articleId;
            }

            public void setArticleId(int articleId) {
                this.articleId = articleId;
            }

            public boolean isIsCollection() {
                return isCollection;
            }

            public void setIsCollection(boolean isCollection) {
                this.isCollection = isCollection;
            }

            public TfMasterBean getTfMaster() {
                return tfMaster;
            }

            public void setTfMaster(TfMasterBean tfMaster) {
                this.tfMaster = tfMaster;
            }

            public List<?> getATypeArray() {
                return aTypeArray;
            }

            public void setATypeArray(List<?> aTypeArray) {
                this.aTypeArray = aTypeArray;
            }

            public List<ImgListBean> getImgList() {
                return imgList;
            }

            public void setImgList(List<ImgListBean> imgList) {
                this.imgList = imgList;
            }

            public static class TfMasterBean {
                /**
                 * mHeadImg : /file//images/master/186661369011513324742691.png
                 * mNick : Marlon君
                 * masterId : 17
                 */

                private String mHeadImg;
                private String mNick;
                private int masterId;

                public String getMHeadImg() {
                    return mHeadImg;
                }

                public void setMHeadImg(String mHeadImg) {
                    this.mHeadImg = mHeadImg;
                }

                public String getMNick() {
                    return mNick;
                }

                public void setMNick(String mNick) {
                    this.mNick = mNick;
                }

                public int getMasterId() {
                    return masterId;
                }

                public void setMasterId(int masterId) {
                    this.masterId = masterId;
                }
            }

            public static class ImgListBean {
                /**
                 * imgTmpId : 410
                 * itAbsolute :
                 * itAppPath :
                 * itCreateTime :
                 * itIsUser : true
                 * itKeyId : 0
                 * itMasterId : 0
                 * itSize :
                 * itType : 2
                 * itUpdateTime :
                 * itUrl : /file/images/product/17/aQCtF_1511778699.png
                 */

                private int imgTmpId;
                private String itAbsolute;
                private String itAppPath;
                private String itCreateTime;
                private boolean itIsUser;
                private int itKeyId;
                private int itMasterId;
                private String itSize;
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

                public String getItSize() {
                    return itSize;
                }

                public void setItSize(String itSize) {
                    this.itSize = itSize;
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

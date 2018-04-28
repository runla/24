package com.yinghai.a24divine_user.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author  Created by：fanson
 * Created on：2017/11/13 14:35
 * Describe：产品Bean
 */

public class ProductBean implements IBean{


    /**
     * code : 1
     * msg : 操作成功
     * data : {"productList":[{"imgList":[{"imgTmpId":294,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1511516318208355.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"/file/images/product//2/UziyC_1511516318208355.png"},{"imgTmpId":295,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1511516318215788.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"/file/images/product//2/8J4cG_1511516318215788.png"},{"imgTmpId":296,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1511516318225538.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"/file/images/product//2/HCaEF_1511516318225538.png"},{"imgTmpId":297,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1511516318230132.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"/file/images/product//2/fnQOH_1511516318230132.png"}],"isCollection":false,"pAttribution":"珠海","pCreateTime":"2017-11-24 17:38:36","pDeals":0,"pDelete":false,"pFreeShipping":true,"pHot":0,"pImg":"","pIntroduction":"7766","pMasterId":2,"pName":"好多","pOffline":false,"pPrice":225,"pSize":"885888","pTotal":588,"pUpdateTime":"","productId":31,"userId":5},{"imgList":[{"imgTmpId":263,"itAbsolute":"","itAppPath":"","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"/file/images/product//0/BNjli_u=1598552568,4236159349&fm=58.jpg"}],"isCollection":true,"pAttribution":"1","pCreateTime":"2017-11-21 15:08:31","pDeals":0,"pDelete":false,"pFreeShipping":false,"pHot":0,"pImg":"","pIntroduction":"2","pMasterId":0,"pName":"2","pOffline":false,"pPrice":200,"pSize":"2","pTotal":1,"pUpdateTime":"2017-11-23 15:40:32","productId":30,"userId":5}]}
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
        private List<ProductListBean> productList;

        public List<ProductListBean> getProductList() {
            return productList;
        }

        public void setProductList(List<ProductListBean> productList) {
            this.productList = productList;
        }

        public static class ProductListBean implements Parcelable{
            /**
             * imgList : [{"imgTmpId":294,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1511516318208355.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"/file/images/product//2/UziyC_1511516318208355.png"},{"imgTmpId":295,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1511516318215788.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"/file/images/product//2/8J4cG_1511516318215788.png"},{"imgTmpId":296,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1511516318225538.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"/file/images/product//2/HCaEF_1511516318225538.png"},{"imgTmpId":297,"itAbsolute":"","itAppPath":"/storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1511516318230132.png","itCreateTime":"","itIsUser":true,"itKeyId":0,"itMasterId":0,"itType":1,"itUpdateTime":"","itUrl":"/file/images/product//2/fnQOH_1511516318230132.png"}]
             * isCollection : false
             * pAttribution : 珠海
             * pCreateTime : 2017-11-24 17:38:36
             * pDeals : 0
             * pDelete : false
             * pFreeShipping : true
             * pHot : 0
             * pImg :
             * pIntroduction : 7766
             * pMasterId : 2
             * pName : 好多
             * pOffline : false
             * pPrice : 225
             * pSize : 885888
             * pTotal : 588
             * pUpdateTime :
             * productId : 31
             * userId : 5
             */

            private boolean isCollection;
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
            private int userId;
            private List<ImgListBean> imgList;

            public ProductListBean(){}

            protected ProductListBean(Parcel in) {
                isCollection = in.readByte() != 0;
                pAttribution = in.readString();
                pCreateTime = in.readString();
                pDeals = in.readInt();
                pDelete = in.readByte() != 0;
                pFreeShipping = in.readByte() != 0;
                pHot = in.readInt();
                pImg = in.readString();
                pIntroduction = in.readString();
                pMasterId = in.readInt();
                pName = in.readString();
                pOffline = in.readByte() != 0;
                pPrice = in.readInt();
                pSize = in.readString();
                pTotal = in.readInt();
                pUpdateTime = in.readString();
                productId = in.readInt();
                userId = in.readInt();
                imgList = in.createTypedArrayList(ImgListBean.CREATOR);
            }

            public static final Creator<ProductListBean> CREATOR = new Creator<ProductListBean>() {
                @Override
                public ProductListBean createFromParcel(Parcel in) {
                    return new ProductListBean(in);
                }

                @Override
                public ProductListBean[] newArray(int size) {
                    return new ProductListBean[size];
                }
            };

            public boolean isIsCollection() {
                return isCollection;
            }

            public void setIsCollection(boolean isCollection) {
                this.isCollection = isCollection;
            }

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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public List<ImgListBean> getImgList() {
                return imgList;
            }

            public void setImgList(List<ImgListBean> imgList) {
                this.imgList = imgList;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeByte((byte) (isCollection ? 1 : 0));
                parcel.writeString(pAttribution);
                parcel.writeString(pCreateTime);
                parcel.writeInt(pDeals);
                parcel.writeByte((byte) (pDelete ? 1 : 0));
                parcel.writeByte((byte) (pFreeShipping ? 1 : 0));
                parcel.writeInt(pHot);
                parcel.writeString(pImg);
                parcel.writeString(pIntroduction);
                parcel.writeInt(pMasterId);
                parcel.writeString(pName);
                parcel.writeByte((byte) (pOffline ? 1 : 0));
                parcel.writeInt(pPrice);
                parcel.writeString(pSize);
                parcel.writeInt(pTotal);
                parcel.writeString(pUpdateTime);
                parcel.writeInt(productId);
                parcel.writeInt(userId);
                parcel.writeTypedList(imgList);
            }

            public static class ImgListBean implements Parcelable{
                /**
                 * imgTmpId : 294
                 * itAbsolute :
                 * itAppPath : /storage/emulated/0/Android/data/com.yinghai.a24divine_master/cache/luban_disk_cache/1511516318208355.png
                 * itCreateTime :
                 * itIsUser : true
                 * itKeyId : 0
                 * itMasterId : 0
                 * itType : 1
                 * itUpdateTime :
                 * itUrl : /file/images/product//2/UziyC_1511516318208355.png
                 */

                private int imgTmpId;
                private String itAbsolute;
                private String itAppPath;
                private String itCreateTime;
                private boolean itIsUser;
                private int itKeyId;
                private int itMasterId;
                private int itType;
                private String itUpdateTime;
                private String itUrl;

                public  ImgListBean(){}

                protected ImgListBean(Parcel in) {
                    imgTmpId = in.readInt();
                    itAbsolute = in.readString();
                    itAppPath = in.readString();
                    itCreateTime = in.readString();
                    itIsUser = in.readByte() != 0;
                    itKeyId = in.readInt();
                    itMasterId = in.readInt();
                    itType = in.readInt();
                    itUpdateTime = in.readString();
                    itUrl = in.readString();
                }

                public static final Creator<ImgListBean> CREATOR = new Creator<ImgListBean>() {
                    @Override
                    public ImgListBean createFromParcel(Parcel in) {
                        return new ImgListBean(in);
                    }

                    @Override
                    public ImgListBean[] newArray(int size) {
                        return new ImgListBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                    parcel.writeInt(imgTmpId);
                    parcel.writeString(itAbsolute);
                    parcel.writeString(itAppPath);
                    parcel.writeString(itCreateTime);
                    parcel.writeByte((byte) (itIsUser ? 1 : 0));
                    parcel.writeInt(itKeyId);
                    parcel.writeInt(itMasterId);
                    parcel.writeInt(itType);
                    parcel.writeString(itUpdateTime);
                    parcel.writeString(itUrl);
                }
            }
        }
    }
}

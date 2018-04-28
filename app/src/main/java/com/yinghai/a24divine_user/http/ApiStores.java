package com.yinghai.a24divine_user.http;


import com.yinghai.a24divine_user.bean.AddressListBean;
import com.yinghai.a24divine_user.bean.AliPayBean;
import com.yinghai.a24divine_user.bean.ArticleBean;
import com.yinghai.a24divine_user.bean.ArticleDetailBean;
import com.yinghai.a24divine_user.bean.BusinessBean;
import com.yinghai.a24divine_user.bean.BusinessTypeListBean;
import com.yinghai.a24divine_user.bean.CheckVersionBean;
import com.yinghai.a24divine_user.bean.CollectBean;
import com.yinghai.a24divine_user.bean.CommentBean;
import com.yinghai.a24divine_user.bean.DivineScheduleTimeBean;
import com.yinghai.a24divine_user.bean.FollowBean;
import com.yinghai.a24divine_user.bean.FriendListBean;
import com.yinghai.a24divine_user.bean.HistoryBean;
import com.yinghai.a24divine_user.bean.LuckBean;
import com.yinghai.a24divine_user.bean.MasterBean;
import com.yinghai.a24divine_user.bean.MasterDetailBean;
import com.yinghai.a24divine_user.bean.NoDataBean;
import com.yinghai.a24divine_user.bean.OrderBean;
import com.yinghai.a24divine_user.bean.PersonInfoBean;
import com.yinghai.a24divine_user.bean.ProductBean;
import com.yinghai.a24divine_user.bean.ProductDetailBean;
import com.yinghai.a24divine_user.bean.ProductOrderListBean;
import com.yinghai.a24divine_user.bean.ScheduleBean;
import com.yinghai.a24divine_user.bean.ShopCarBean;
import com.yinghai.a24divine_user.bean.SubmitOrderBean;
import com.yinghai.a24divine_user.bean.SubmitShopCarBean;
import com.yinghai.a24divine_user.bean.UploadPictureBean;
import com.yinghai.a24divine_user.bean.VerifyCodeBean;
import com.yinghai.a24divine_user.bean.WechatPayBean;
import com.yinghai.a24divine_user.constant.ConHttp;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by：fanson
 * Created on：2017/10/24 13:35
 * Describe：网络请求的API列表
 */

public interface ApiStores {

    /**
     * baseUrl
     */
    String API_SERVER_URL = ConHttp.BASE_URL;

    /**
     * 获取验证码
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    Flowable<VerifyCodeBean> getCode(@Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取（注册或找回密码）验证码
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    Flowable<VerifyCodeBean> getRegisterCode(@Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 手机验证码登录
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<PersonInfoBean> loginPhone(@Header("Cookie") String sessionId, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 用户注册
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<PersonInfoBean> userRegister(@Header("Cookie") String sessionId, @Url String url, @QueryMap Map<String, Object> maps);


    /**
     * 找回密码
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<NoDataBean> findPassword(@Header("Cookie") String sessionId, @Url String url, @QueryMap Map<String, Object> maps);


    /**
     * 绑定第三方时，获取验证码
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<VerifyCodeBean> getBindCode( @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 提交绑定
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<PersonInfoBean> submitBind(@Header("Cookie") String sessionId, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 密码登录
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<PersonInfoBean> loginPassword(@Header("Cookie") String sessionId, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 微信登录
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<PersonInfoBean> thirdLogin(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 登出
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<NoDataBean> logout(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取我的关注
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<FollowBean> getMyFollow(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 添加关注
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<FollowBean> addFollow(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 取消关注
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<NoDataBean> cancelFollow(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取历史记录
     */
    @POST
    @Headers("Realm:user")
    Flowable<HistoryBean> getHistory(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取所有商品
     */
    @Headers("Realm:user")
    @POST
    Flowable<ProductBean> getAllProduct(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 商品详情
     */
    @Headers("Realm:user")
    @POST
    Flowable<ProductDetailBean> productDetail(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 添加到购物车
     */
    @Headers("Realm:user")
    @POST
    Flowable<NoDataBean> addToCar(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取购物车数据
     */
    @Headers("Realm:user")
    @POST
    Flowable<ShopCarBean> getShopCar(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 提交购物车数据
     */
    @Headers("Realm:user")
    @POST
    Flowable<SubmitShopCarBean> submitShopCar(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 删除购物车数据
     */
    @Headers("Realm:user")
    @POST
    Flowable<NoDataBean> deleteShopCar(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 修改购物车数据
     */
    @Headers("Realm:user")
    @POST
    Flowable<NoDataBean> edtiShopCar(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取商品详情
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<CommentBean> getProductComments(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取文章
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<ArticleBean> getArtical(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 文章详情
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<ArticleDetailBean> articalDetail(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取评论
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<CommentBean> getComments(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 发表评论
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<CommentBean> publishComments(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 删除评论
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<CommentBean> deletehComments(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取占卜师
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<MasterBean> getMaster(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取占卜师详情
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<MasterDetailBean> getMasterDetail(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取占卜师业务
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<BusinessBean> getBusiness(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取占卜师的日程
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<ScheduleBean> getMasterSchedule(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取我的收藏
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<CollectBean> getCollect(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 取消收藏
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<CollectBean> cancelCollect(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 添加我的收藏
     */
    @POST
    @Headers("Realm:user")
    Flowable<CollectBean> addCollect(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 开启运势
     */
    @POST
    @Headers("Realm:user")
    Flowable<LuckBean> openLuck(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 更换运势
     */
    @POST
    @Headers("Realm:user")
    Flowable<LuckBean> changeLuck(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取订单
     */
    @POST
    @Headers("Realm:user")
    Flowable<OrderBean> getOrder(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 提交订单
     */
    @POST
    @Headers("Realm:user")
    Flowable<SubmitOrderBean> submitOrder(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);


    /**
     * 完成订单
     */
    @POST
    @Headers("Realm:user")
    Flowable<NoDataBean> completeOrder(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);


    /**
     * 微信下单
     */
    @POST
    @Headers("Realm:user")
    Flowable<WechatPayBean> wechatPay(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 支付宝下单
     */
    @POST
    @Headers("Realm:user")
    Flowable<AliPayBean> aliPay(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 取消占卜订单
     */
    @POST
    @Headers("Realm:user")
    Flowable<NoDataBean> cancelOrder(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取收货地址列表
     */
    @POST
    @Headers("Realm:user")
    Flowable<AddressListBean> getAddress(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);


    /**
     * 修改/新增收货地址
     */
    @POST
    @Headers("Realm:user")
    Flowable<AddressListBean> editAddress(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 修改个人信息
     */
    @POST
    @Headers("Realm:user")
    Flowable<PersonInfoBean> editPerson(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 删除收货地址
     */
    @POST
    @Headers("Realm:user")
    Flowable<AddressListBean> deleteAddress(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 上传图片（单图）
     */
    @POST
    @Multipart
    @Headers("Realm:user")
    Flowable<UploadPictureBean> uploadPicture(@Header("Authorization") String authorization, @Url String url, @Part MultipartBody.Part imgs, @QueryMap Map<String, Object> maps);

    /**
     * 获取好友列表
     */
    @POST
    @Headers("Realm:user")
    Flowable<FriendListBean> getFriendList(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 添加好友
     */
    @POST
    @Headers("Realm:user")
    Flowable<FriendListBean> addFriend(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 删除好友
     */
    @POST
    @Headers("Realm:user")
    Flowable<NoDataBean> deleteFriend(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 处理好友验证消息
     */
    @POST
    @Headers("Realm:user")
    Flowable<FriendListBean> friendRequest(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 根据Id获取其他用户公开信息
     */
    @POST
    @Headers("Realm:user")
    Flowable<PersonInfoBean> getUserInfoById(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取商品订单列表
     */
    @POST
    @Headers("Realm:user")
    Flowable<ProductOrderListBean> getProductOrderList(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 获取业务类型
     */
    @POST
    @Headers("Realm:user")
    Flowable<BusinessTypeListBean> getBusinessType(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);

    /**
     * 检测版本更新
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<CheckVersionBean> checkVersion(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);
    /**
     * 获取占卜预约订单时间表
     *
     * @param url
     * @param maps
     * @return
     */
    @POST
    @Headers("Realm:user")
    Flowable<DivineScheduleTimeBean> getDivineSchedule(@Header("Authorization") String authorization, @Url String url, @QueryMap Map<String, Object> maps);
}

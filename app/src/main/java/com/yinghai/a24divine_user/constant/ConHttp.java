package com.yinghai.a24divine_user.constant;

/**
 * Created by：fanson
 * Created on：2017/10/24 13:28
 * Describe：Http接口列表
 */

public class ConHttp {

//        public static final String BASE_URL = "http://192.168.0.176:8084"; //tianfu?
    public static final String BASE_URL = "http://119.28.15.112:8081";

    /**
     * 手机验证码登录
     */
    public static final String LOGIN_PHONE = "/twentyfour/app/tfuser/loginByTel";

    /**
     * 绑定第三方时，获取验证码
     */
    public static final String THIRD_GET_CODE = "/twentyfour/app/third/party/sendVerifyCode";

    /**
     * 提交绑定
     */
    public static final String SUBMIT_BIND = "/twentyfour/app/third/party/checkVerify";

    /**
     * 密码登录
     */
    public static final String LOGIN_PWD = "/twentyfour/app/tfuser/loginByPassword";

    /**
     * 微信登录
     */
    public static final String THIRD_LOGIN = "/twentyfour/app/third/party/login";

    /**
     * 获取验证码
     */
    public static final String GET_CODE = "/twentyfour/app/tfuser/sendVerifyCode";

    /**
     * 登出
     */
    public static final String LOGOUT = "/twentyfour/app/tfuser/logOut";

    /**
     * 获取我的关注
     */
    public static final String MY_FOLLOW = "/twentyfour/app/subscribe/list";

    /**
     * 添加关注
     */
    public static final String ADD_FOLLOW = "/twentyfour/app/subscribe/create";

    /**
     * 取消关注
     */
    public static final String CANCEL_FOLLOW = "/twentyfour/app/subscribe/delete";

    /**
     * 获取所有商品
     */
    public static final String GET_ALL_PRODUCT = "/twentyfour/app/product/list";

    /**
     * 获取商品评论
     */
    public static final String GET_PRODUCT_COMMENTS = "getProductComments";

    /**
     * 添加到购物车
     */
    public static final String PRODUCT_ADD_CAR = "/twentyfour/app/car/create";

    /**
     * 获取购物车数据
     */
    public static final String GET_SHOP_CAR = "/twentyfour/app/car/list";

    /**
     * 提交购物车数据
     */
    public static final String SUBMIT_SHOP_CAR = "/twentyfour/app/orderTotal/generateTotalOrder";
//    public static final String SUBMIT_SHOP_CAR = "/twentyfour/app/orderTotal/createTotalOrder";

    /**
     * 删除购物车数据
     */
    public static final String DELETE_SHOP_CAR = "/twentyfour/app/car/delete";

    /**
     * 修改购物车数据
     */
    public static final String EDIT_SHOP_CAR = "/twentyfour/app/car/update";

    /**
     * 获取历史记录
     */
    public static final String HISTORY = "/twentyfour/app/history/list";

    /**
     * 获取文章
     */
    public static final String GET_ARTICLE = "/twentyfour/app/tfarticle/list";

    /**
     * 文章详情
     */
    public static final String ARTICLE_DETAIL = "/twentyfour/app/tfarticle/detail";

    /**
     * 获取评论
     */
    public static final String GET_COMMENTS = "/twentyfour/app/comment/list";

    /**
     * 删除评论
     */
    public static final String DELETE_COMMENTS = "/twentyfour/app/comment/delete";

    /**
     * 发表评论
     */
    public static final String PUBLISH_COMMENTS = "/twentyfour/app/comment/create";

    /**
     * 获取占卜师列表
     */
    public static final String LOOK_MASTER = "/twentyfour/app/tfmaster/list";

    /**
     * 获取占卜师详情
     */
    public static final String MASTER_DETAIL = "/twentyfour/app/tfmaster/me";

    /**
     * 发布我的产品
     */
    public static final String ADD_PRODUCT = "addProduct";

    /**
     * 产品详情
     */
    public static final String PRODUCT_DETAIL = "/twentyfour/app/product/detail";

    /**
     * 获取占卜师的业务列表
     */
    public static final String BUSINESS = "/twentyfour/app/business/list";

    /**
     * 获取大师的日程
     */
    public static final String MASTER_TIME = "/twentyfour/app/order/schedule";

    /**
     * 获取我的收藏
     */
    public static final String MY_COLLECT = "/twentyfour/app/collection/list";

    /**
     * 取消收藏
     */
    public static final String CANCEL_COLLECT = "/twentyfour/app/collection/delete";

    /**
     * 添加我的收藏
     */
    public static final String ADD_COLLECT = "/twentyfour/app/collection/create";

    /**
     * 开启运势
     */
    public static final String OPEN_LUCK = "/twentyfour/app/luck/openLuck";

    /**
     * 更改运势
     */
    public static final String CHANGE_LUCK = "/twentyfour/app/luck/changeLuck";

    /**
     * 获取订单
     */
    public static final String GET_ORDER = "/twentyfour/app/order/list";

    /**
     * 提交订单
     */
    public static final String SUBMIT_ORDER = "/twentyfour/app/order/save";

    /**
     * 完成订单
     */
    public static final String COMPLETE_ORDER = "/twentyfour/app/order/complete";

    /**
     * 微信下单
     */
    public static final String WECHAT_PAY = "/twentyfour/app/wechat/pay";

    /**
     * 支付宝下单
     */
    public static final String ALI_PAY = "/twentyfour/app/alipay/pay";

    /**
     * 取消占卜订单
     */
//    public static final String CANCEL_ORDER = "/twentyfour/app/order/cancelOrder";
    public static final String CANCEL_ORDER = "/twentyfour/app/order/cancel";

    /**
     * 获取收货地址列表
     */
    public static final String GET_ADDRESS = "/twentyfour/app/address/list";

    /**
     * 修改/添加收货地址
     */
    public static final String EDIT_ADDRESS = "/twentyfour/app/address/createOrUpdate";

    /**
     * 删除收货地址
     */
    public static final String DELETE_ADDRESS = "/twentyfour/app/address/delete";

    /**
     * 修改个人信息
     */
    public static final String EDIT_PERSON = "/twentyfour/app/tfuser/edit";

    /**
     * 上传图片
     */
    public static final String UPLOAD_PICTRUE = "/twentyfour/app/file/imageDown";

    /**
     * 获取好友列表
     */
    public static final String FRIEND_LIST = "/twentyfour/app/friend/getFriendList";

    /**
     * 添加好友
     */
    public static final String ADD_FRIEND = "/twentyfour/app/friend/addFriend";

    /**
     * 删除好友
     */
    public static final String DELETE_FRIEND = "/twentyfour/app/friend/deleteFriend";

    /**
     * 处理好友验证消息
     */
    public static final String FRIEND_REQUEST = "/twentyfour/app/friend/agreeFriend";

    /**
     * 根据Id获取其他用户公开信息
     */
    public static final String USER_INFO_BYID = "/twentyfour/app/tfuser/publicDetail";

    /**
     * 获取商品订单列表
     */
    public static final String PRODUCT_ORDER_LIST = "/twentyfour/app/order/productList";

    /**
     * 用户注册
     */
    public static final String USER_REGISTER = "/twentyfour/app/tfuser/register";

    /**
     * 用户注册、找回密码发送验证码
     */
    public static final String USER_REGISTER_GET_VERIFY = "/twentyfour/app/tfuser/sendRegisterVerifyCode";

    /**
     * 找回密码
     */
    public static final String USER_FIND_PASSWORD = "/twentyfour/app/tfuser/forgetPwd";

    /**
     * 获取业务类型
     */
    public static final String GET_BUSINESS_TYPE = "/twentyfour/app/tfBusinessType/list";

    /**
     * 检测更新
     */
    public static final String CHECK_VERSION = "/twentyfour/app/version/now";

    /**
     * 获取占卜预约订单时间表
     */
    public static final String BOOK_SCKEDULE = "/twentyfour/app/order/dailySchedule";
}

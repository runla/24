package com.yinghai.a24divine_user.http;

import com.example.fansonlib.http.retrofit.IApiFactory;
import com.example.fansonlib.http.retrofit.RetrofitClient;
import com.example.fansonlib.utils.SharePreferenceHelper;
import com.yinghai.a24divine_user.constant.ConHttp;
import com.yinghai.a24divine_user.constant.ConstantPreference;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by：fanson
 * Created on：2017/10/24 13:35
 * Describe：工厂生成Retrofit用的Api
 */

public class ApiFactoryImpl implements IApiFactory {

    private Flowable mFlowable;

    @Override
    public Flowable createApi(String url, Map params) {
        String sessionId = SharePreferenceHelper.getString(ConstantPreference.S_SESSION_ID, null);
        String authorization = SharePreferenceHelper.getString(ConstantPreference.S_DEVICE_ID, null);
        switch (url) {
            case ConHttp.GET_CODE:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getCode(url, params);
                break;

            case ConHttp.USER_REGISTER_GET_VERIFY:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getRegisterCode(url, params);
                break;

            case ConHttp.LOGIN_PHONE:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).loginPhone("JSESSIONID=" + sessionId, url, params);
                break;

            case ConHttp.USER_REGISTER:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).userRegister("JSESSIONID=" + sessionId, url, params);
                break;

            case ConHttp.USER_FIND_PASSWORD:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).findPassword("JSESSIONID=" + sessionId, url, params);
                break;

            case ConHttp.LOGIN_PWD:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).loginPassword("JSESSIONID=" + sessionId, url, params);
                break;

            case ConHttp.THIRD_GET_CODE:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getBindCode(url, params);
                break;

            case ConHttp.SUBMIT_BIND:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).submitBind("JSESSIONID=" + sessionId, url, params);
                break;

            case ConHttp.LOGOUT:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).logout(authorization, url, params);
                break;

            case ConHttp.MY_FOLLOW:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getMyFollow(authorization, url, params);
                break;

            case ConHttp.CANCEL_FOLLOW:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).cancelFollow(authorization, url, params);
                break;

            case ConHttp.HISTORY:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getHistory(authorization, url, params);
                break;

            case ConHttp.GET_ALL_PRODUCT:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getAllProduct(authorization, url, params);
                break;

            case ConHttp.GET_PRODUCT_COMMENTS:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getProductComments(authorization, url, params);
                break;

            case ConHttp.GET_ARTICLE:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getArtical(authorization, url, params);
                break;

            case ConHttp.GET_COMMENTS:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getComments(authorization, url, params);
                break;

            case ConHttp.LOOK_MASTER:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getMaster(authorization, url, params);
                break;

            case ConHttp.MASTER_TIME:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getMasterSchedule(authorization, url, params);
                break;

            case ConHttp.MY_COLLECT:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getCollect(authorization, url, params);
                break;

            case ConHttp.CANCEL_COLLECT:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).cancelCollect(authorization, url, params);
                break;

            case ConHttp.ADD_COLLECT:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).addCollect(authorization, url, params);
                break;

            case ConHttp.PRODUCT_ADD_CAR:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).addToCar(authorization, url, params);
                break;

            case ConHttp.GET_SHOP_CAR:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getShopCar(authorization, url, params);
                break;

            case ConHttp.SUBMIT_SHOP_CAR:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).submitShopCar(authorization, url, params);
                break;

            case ConHttp.OPEN_LUCK:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).openLuck(authorization, url, params);
                break;

            case ConHttp.CHANGE_LUCK:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).changeLuck(authorization, url, params);
                break;

            case ConHttp.DELETE_SHOP_CAR:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).deleteShopCar(authorization, url, params);
                break;

            case ConHttp.EDIT_SHOP_CAR:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).edtiShopCar(authorization, url, params);
                break;

            case ConHttp.GET_ORDER:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getOrder(authorization, url, params);
                break;

            case ConHttp.BUSINESS:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getBusiness(authorization, url, params);
                break;

            case ConHttp.PUBLISH_COMMENTS:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).publishComments(authorization, url, params);
                break;

            case ConHttp.DELETE_COMMENTS:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).deletehComments(authorization, url, params);
                break;

            case ConHttp.SUBMIT_ORDER:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).submitOrder(authorization, url, params);
                break;

            case ConHttp.COMPLETE_ORDER:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).completeOrder(authorization, url, params);
                break;

            case ConHttp.WECHAT_PAY:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).wechatPay(authorization, url, params);
                break;

            case ConHttp.GET_ADDRESS:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getAddress(authorization, url, params);
                break;

            case ConHttp.EDIT_ADDRESS:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).editAddress(authorization, url, params);
                break;

            case ConHttp.DELETE_ADDRESS:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).deleteAddress(authorization, url, params);
                break;

            case ConHttp.ALI_PAY:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).aliPay(authorization, url, params);
                break;

            case ConHttp.CANCEL_ORDER:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).cancelOrder(authorization, url, params);
                break;

            case ConHttp.ARTICLE_DETAIL:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).articalDetail(authorization, url, params);
                break;

            case ConHttp.PRODUCT_DETAIL:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).productDetail(authorization, url, params);
                break;

            case ConHttp.MASTER_DETAIL:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getMasterDetail(authorization, url, params);
                break;

            case ConHttp.EDIT_PERSON:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).editPerson(authorization, url, params);
                break;

            case ConHttp.FRIEND_LIST:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getFriendList(authorization, url, params);
                break;

            case ConHttp.ADD_FRIEND:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).addFriend(authorization, url, params);
                break;

            case ConHttp.DELETE_FRIEND:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).deleteFriend(authorization, url, params);
                break;

            case ConHttp.USER_INFO_BYID:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getUserInfoById(authorization, url, params);
                break;

            case ConHttp.THIRD_LOGIN:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).thirdLogin(authorization, url, params);
                break;

            case ConHttp.FRIEND_REQUEST:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).friendRequest(authorization, url, params);
                break;

            case ConHttp.ADD_FOLLOW:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).addFollow(authorization, url, params);
                break;

            case ConHttp.PRODUCT_ORDER_LIST:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getProductOrderList(authorization, url, params);
                break;

            case ConHttp.GET_BUSINESS_TYPE:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getBusinessType(authorization, url, params);
                break;

            case ConHttp.CHECK_VERSION:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).checkVersion(authorization, url, params);
                break;

            case ConHttp.BOOK_SCKEDULE:
                mFlowable = RetrofitClient.getRetrofit(ApiStores.class).getDivineSchedule(authorization, url, params);
                break;

            default:
                break;

        }
        return mFlowable;
    }
}

package com.yinghai.a24divine_user.bean;

/**
 * @author Created by：fanson
 *         Created Time: 2017/11/18 17:40
 *         Describe：支付宝下单Bean
 */

public class AliPayBean {


    /**
     * code : 1
     * msg : success
     * data : {"orderStr":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016081600257447&biz_content=%7B%22body%22%3A%22%E5%A4%A7%E5%B8%88%231%E6%9B%BE%E5%AD%A6%E6%9D%BE%E4%B8%9A%E5%8A%A1%234%E5%86%8D%E6%9D%A5%E4%B8%AA%E4%B8%9A%E5%8A%A1%22%2C%22out_trade_no%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22seller_id%22%3A%222088102171405184%22%2C%22subject%22%3A%22%E5%9C%A8%E7%BA%BF%E5%8D%A0%E5%8D%9C%E8%AE%A2%E5%8D%95%22%2C%22timeout_express%22%3A%2215d%22%2C%22total_amount%22%3A%225800000.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F119.28.15.112%3A8081%2Fmacao%2Fwechat%2FweixinOrder&sign=Q3lnUnuO8Wjgfaf%2BGMRjFkPsijzmZDt6eWKXWeQC3AHHfcwvGZOz1FLQdUDk8JVxoeaHR2zgRwLUhn%2BsvxsPQ6%2Fwssa1soAlX5Gad3WKZ24Z9Uh1DD%2BKOpiLrbeK7ftWyiZZTr4l7j%2BIkgiRo1sxV4r8zF3I2vJ7oMj8Q9wu31mPyStZ%2FqGfrR%2BUH51N3qb00OHZ%2BruzWaFQB94lI5srn44UZJXYFkgjgOTqUHGusDUOluKUDyZSM8lPRjjqS1jWwJj2qPY6wYUcfjvswbHMvXmLpA%2FhAQ2LvDjO6W3t26n%2BWP%2BV8yRFNvda7BUNc0NkBR3EUC%2B7b2VfPhaVK4N5xA%3D%3D&sign_type=RSA2&timestamp=2017-11-01+10%3A38%3A18&version=1.0"}
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
         * orderStr : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016081600257447&biz_content=%7B%22body%22%3A%22%E5%A4%A7%E5%B8%88%231%E6%9B%BE%E5%AD%A6%E6%9D%BE%E4%B8%9A%E5%8A%A1%234%E5%86%8D%E6%9D%A5%E4%B8%AA%E4%B8%9A%E5%8A%A1%22%2C%22out_trade_no%22%3A%22%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22seller_id%22%3A%222088102171405184%22%2C%22subject%22%3A%22%E5%9C%A8%E7%BA%BF%E5%8D%A0%E5%8D%9C%E8%AE%A2%E5%8D%95%22%2C%22timeout_express%22%3A%2215d%22%2C%22total_amount%22%3A%225800000.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F119.28.15.112%3A8081%2Fmacao%2Fwechat%2FweixinOrder&sign=Q3lnUnuO8Wjgfaf%2BGMRjFkPsijzmZDt6eWKXWeQC3AHHfcwvGZOz1FLQdUDk8JVxoeaHR2zgRwLUhn%2BsvxsPQ6%2Fwssa1soAlX5Gad3WKZ24Z9Uh1DD%2BKOpiLrbeK7ftWyiZZTr4l7j%2BIkgiRo1sxV4r8zF3I2vJ7oMj8Q9wu31mPyStZ%2FqGfrR%2BUH51N3qb00OHZ%2BruzWaFQB94lI5srn44UZJXYFkgjgOTqUHGusDUOluKUDyZSM8lPRjjqS1jWwJj2qPY6wYUcfjvswbHMvXmLpA%2FhAQ2LvDjO6W3t26n%2BWP%2BV8yRFNvda7BUNc0NkBR3EUC%2B7b2VfPhaVK4N5xA%3D%3D&sign_type=RSA2&timestamp=2017-11-01+10%3A38%3A18&version=1.0
         */

        private String orderStr;

        public String getOrderStr() {
            return orderStr;
        }

        public void setOrderStr(String orderStr) {
            this.orderStr = orderStr;
        }
    }
}

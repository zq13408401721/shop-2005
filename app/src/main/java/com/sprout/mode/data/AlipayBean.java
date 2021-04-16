package com.sprout.mode.data;

public class AlipayBean {

    /**
     * code : 200
     * err :
     * data : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2021002131617263&biz_content=%7B%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22goods_type%22%3A0%2C%22subject%22%3A%22%BB%E1%D4%B1%B9%BA%C2%F2%22%2C%22timeout_express%22%3A%2290m%22%2C%22total_amount%22%3A%220.5%22%2C%22out_trade_no%22%3A%2247727bb9-9ead-4b45-adf6-3f6da3240171%22%2C%22enable_pay_channels%22%3A%22pcredit%2CmoneyFund%2CdebitCardExpress%22%7D&charset=GBK&format=json&method=alipay.trade.app.pay&sign=B5BokwrqTtzPhSmxF4ePO0%2B1OyAw2H%2BmyXQp9uelXFVk7gG0SHOXuaNmLLR8MyLGu4hqBwkmz1AkjJEkBS%2BpojMCiQ9j1MCK2CIywcvUWXTWHqz9LUYJMHrbzrSA%2Bv8F7yID6WYQRHyTYiCHec6BHaB3i5jU%2F1rOKtN9fH9zsclEVs79xym9yHv1i9Lwssn5H%2BUCM572aHlFQuV%2FUhFAf0gtLV6p1mzew9oCsgA%2BFQhN3s6ztkklbku%2BfuC%2BFQ5b4KSCIzhAPb3N5XViV%2FkZz1pgWUSFT0VYlsHrsUhqBFEnFgW8gizHPxlN8CHZstxOay8azTcpmVO1kRLIFuKd3A%3D%3D&sign_type=RSA2&timestamp=2021-04-16+14%3A51%3A01&version=1.0
     */

    private int code;
    private String err;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

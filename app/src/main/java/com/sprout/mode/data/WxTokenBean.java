package com.sprout.mode.data;

public class WxTokenBean {


    /**
     * access_token : 44_ilJ95tnMaP8EYvFtJGi9WGyZ_e4zb16gOzxpB_JUGuBSvuO9Fs4yKNIiBKfKKH5gqwRceo5D9WWrHc-KcN5wOT7_n_lzG6-sqohsGmMi6qs
     * expires_in : 7200
     * refresh_token : 44_dkZ-BFafy2fE_9in1AiY5IG7hJe_W-2vrlcyv07jSy65aqYRgGKL5ksb9e26-XaUELgYio0Ma7mnoaanH_Us_Kai2nQi-Xxzh8OIEvBR0wg
     * openid : ovYnossUnndCBez_sqSoIN4OLbKY
     * scope : snsapi_userinfo
     * unionid : o7Q2Ft4YORFR6k39mDc5AprB-aeU
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}

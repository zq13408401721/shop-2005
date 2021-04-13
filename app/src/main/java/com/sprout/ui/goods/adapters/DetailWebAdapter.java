package com.sprout.ui.goods.adapters;

import android.content.Context;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.sprout.R;
import com.sprout.app.Constants;
import com.sprout.app.Delegate;
import com.sprout.base.BaseDelegateAdapter;
import com.sprout.mode.data.GoodDetailBean;
import com.sprout.utils.TextViewUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DetailWebAdapter extends BaseDelegateAdapter<GoodDetailBean.DataBeanX.InfoBean> {

    private String webContent = "<html>\n" +
            "            <style>\n" +
            "                html,body{\n" +
            "                    margin: auto auto;\n" +
            "                }\n" +
            "                p{\n" +
            "                    margin: auto auto;\n" +
            "                }\n" +
            "                img{\n" +
            "                    width: 100%;\n"+
            "                }\n" +
            "            </style>\n" +
            "            <body>\n" +
            "                content\n" +
            "            </body>\n" +
            "        </html>";

    public DetailWebAdapter(Context context, GoodDetailBean.DataBeanX.InfoBean data) {
        super(context, data, Delegate.OBJECT);
    }

    @Override
    protected LayoutHelper getLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_gooddetail_desc;
    }

    @Override
    protected void bindData(BaseDelegateAdapter.BaseViewHolder holder, GoodDetailBean.DataBeanX.InfoBean data) {
        if(data.getGoods_desc() == null || data.getGoods_desc().length() == 0) return;
        webContent = webContent.replace("content",data.getGoods_desc());
        WebView webView = (WebView) holder.getViewById(R.id.webView);
        webView.loadData(webContent,"text/html","utf-8");

    }
}

package com.by5388.sy95306v2.module.guangzhou.screen;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.guangzhou.BaseGzFragment;
import com.by5388.sy95306v2.module.guangzhou.screen.adapter.MyAdapter;
import com.by5388.sy95306v2.module.guangzhou.screen.view.IScreenView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 车站大屏幕
 *
 * @author by5388  on 2018/8/3.
 */

public class StationScreenFragment extends BaseGzFragment implements IScreenView {
    /**
     * 数据来源：http://www.gtbyxx.com/wxg/ky/houcheng.jsp
     * jyx_station：非高铁车站，提供候车室查询（广州站、广州东站、韶关东站）
     * crh_station：高铁车站，提供检票口查询(深圳北+广州南)
     */
    private static final String[] STATION_CODE = {
            "jyx_station.jsp?code=GZZ",
            "jyx_station.jsp?code=GZD",
            "crh_station.jsp?code=GZN",
            "crh_station.jsp?code=SZB",
            "jyx_station.jsp?code=SGD"};
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    //  可以增加长沙南站的
    private WebView webView;

    public static StationScreenFragment newInstance() {
        StationScreenFragment fragment = new StationScreenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        List<String> strings = new ArrayList<>();
        strings.add("广州站");
        strings.add("广州东站");
        strings.add("广州南站");
        strings.add("深圳北站");
        strings.add("韶关东站");
        strings.add("长沙南站");
        adapter = new MyAdapter(Objects.requireNonNull(getContext()), strings, this);
    }

    @Override
    protected void loadData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(adapter);
        //TODO   WebSettings
        WebSettings settings = webView.getSettings();
        // FIXME: 2018/8/22 security
        settings.setJavaScriptEnabled(true);
        settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_screen;
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_View_screen_name);
        webView = view.findViewById(R.id.web_view_screen);

    }

    @Override
    public void showStationScreen(int position) {
        final String baseUrl = "http://www.gtbyxx.com/wxg/station/";
        String url;
        int changShaNan = 5;
        if (changShaNan == position) {
            url = "http://123.56.101.72/tky/cfdp";
        } else {
            url = baseUrl + STATION_CODE[position];
        }
        webView.loadUrl(url);
//        webView.setWebViewClient(new WebViewClient() {
//            // TODO: 2018/8/22 过时方法
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {


    }

    @Override
    public void startQuery() {

    }

    @Override
    public void finishQuery() {

    }

    @Override
    public void showError(String tip) {

    }
}

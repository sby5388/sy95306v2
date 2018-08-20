package com.by5388.sy95306v2.fragment.gz.screen;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.fragment.gz.BaseGuangZhouFragment;
import com.by5388.sy95306v2.fragment.gz.screen.adapter.MyAdapter;
import com.by5388.sy95306v2.fragment.gz.screen.view.IScreenView;

import java.util.ArrayList;
import java.util.List;

/**
 * 车站大屏幕
 *
 * @author by5388  on 2018/8/3.
 */

public class StationScreenFragment extends BaseGuangZhouFragment implements IScreenView {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private WebView webView;
    //  可以增加长沙南站的
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
        adapter = new MyAdapter(getContext(), strings, this);
    }

    @Override
    protected void loadData() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(adapter);
        //TODO   WebSettings
        WebSettings settings = webView.getSettings();
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
        String url = "";
        int changShaNan = 5;
        if (changShaNan == position) {
            url = "http://123.56.101.72/tky/cfdp";
        } else {
            url = baseUrl + STATION_CODE[position];
        }
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void updateView(int year, int month, int dayOfMonth) {


    }


}

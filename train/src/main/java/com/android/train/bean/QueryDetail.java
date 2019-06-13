package com.android.train.bean;

import android.net.Uri;

/**
 * @author Administrator  on 2019/4/12.
 */
public class QueryDetail implements QueryParam {
    String mDate;
    String mCode;

    String TRAIN_CODE = "train_code";
    String TRAIN_DATE = "train_date";
    String FROM_STATION = "from_station";
    String TO_STATION = "to_station";

    String QUERY_LIST = "GetRsultInfo1";
    String QUERY_DETAIL = "GetRsultInfo2";

    public QueryDetail(String date, String code) {
        this.mDate = date;
        this.mCode = code;
    }


    @Override
    public Uri getQueryParam() {
        return Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(QUERY_DETAIL)
                .appendQueryParameter(TRAIN_DATE, mDate)
                .appendQueryParameter(TRAIN_CODE, mCode)
                .build();
    }
}

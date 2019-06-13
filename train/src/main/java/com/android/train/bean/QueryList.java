package com.android.train.bean;

import android.net.Uri;

/**
 * @author Administrator  on 2019/4/12.
 */
public class QueryList implements QueryParam {
    String mDate;
    String mFrom;
    String mTo;

    String TRAIN_CODE = "train_code";
    String TRAIN_DATE = "train_date";
    String FROM_STATION = "from_station";
    String TO_STATION = "to_station";

    String QUERY_LIST = "GetRsultInfo1";
    String QUERY_DETAIL = "GetRsultInfo2";


    public QueryList(String date, String from, String to) {
        mDate = date;
        mFrom = from;
        mTo = to;
    }

    @Override
    public Uri getQueryParam() {
        return Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(QUERY_LIST)
                .appendQueryParameter(TRAIN_DATE, mDate)
                .appendQueryParameter(FROM_STATION, mFrom)
                .appendQueryParameter(TO_STATION, mTo)
                .build();
    }
}

package com.by5388.sy95306.kotlin;

import com.by5388.sy95306.kotlin.bean.Station;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388  on 2019/1/14.
 */
public class StringToStationList {
    @Test
    public void stringTest() {
        List<Station> stations = new ArrayList<>();
        String text = "bjb|北京北|VAP|beijingbei|bjb|0@bjd|北京东|BOP|beijingdong|bjd|1@bji|北京|BJP|beijing|bj|2@bjn|北京南|VNP|beijingnan|bjn|3@bjx|北京西|BXP|beijingxi|bjx|4@gzn|广州南|IZQ|guangzhounan|gzn|5@cqb|重庆北|CUW|chongqingbei|cqb|6@cqi|重庆|CQW|chongqing|cq|7@cqn|重庆南|CRW|chongqingnan|cqn|8@cqx|重庆西|CXW|chongqingxi|cqx|9";
        String[] stationNames = text.split("@");
        for (String stationName : stationNames) {
            String[] itemCity = stationName.split("\\|");
            if (itemCity.length == 6) {
                stations.add(new Station(-1, itemCity[0], itemCity[1], itemCity[2],
                        itemCity[3], itemCity[4], Integer.parseInt(itemCity[5])));
            }
        }
        showStationList(stations);
    }

    private void showStationList(List<Station> mStations) {
        for (Station station : mStations) {
            System.out.println(station.component3());
        }
    }
}

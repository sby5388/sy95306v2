package com.by5388.sy95306v2.module.chengdu.bean.yupiao;

import java.util.List;

/**
 * @author by5388
 * @date 2018/8/13 11:19
 */
@SuppressWarnings("all")
public class CdAllResultDataBean {
        /*
        datas : [
        {"mStationTrainCode":"G2926","mStartStationName":"深圳北","to_station_name":"贵阳北","lishi":"04:58","yz_num":"0","yw_num":"0","rz_num":"0","rw_num":"0","wz_num":"0","mStartTime":"07:58","mArriveTime":"12:56","zy_num":"4","ze_num":"0","fzx":"01"},
        {"mStationTrainCode":"K486","mStartStationName":"深圳","to_station_name":"贵阳","lishi":"21:53","yz_num":"0","yw_num":"97","rz_num":"0","rw_num":"1","wz_num":"48","mStartTime":"11:05","mArriveTime":"08:58","zy_num":"0","ze_num":"0","fzx":"01"},
        {"mStationTrainCode":"K830","mStartStationName":"深圳东","to_station_name":"贵阳","lishi":"23:47","yz_num":"0","yw_num":"16","rz_num":"0","rw_num":"3","wz_num":"58","mStartTime":"13:40","mArriveTime":"13:27","zy_num":"0","ze_num":"0","fzx":"01"},
        {"mStationTrainCode":"G2930","mStartStationName":"深圳北","to_station_name":"贵阳北","lishi":"05:21","yz_num":"0","yw_num":"16","rz_num":"0","rw_num":"3","wz_num":"58","mStartTime":"14:54","mArriveTime":"20:15","zy_num":"0","ze_num":"0","fzx":"01"},
        {"mStationTrainCode":"G2922","mStartStationName":"深圳北","to_station_name":"贵阳北","lishi":"04:59","yz_num":"0","yw_num":"0","rz_num":"0","rw_num":"0","wz_num":"0","mStartTime":"15:25","mArriveTime":"20:24","zy_num":"0","ze_num":"0","fzx":"01"}]
        flag : true
        */

    private String flag;
    private List<CdRemainTicketDetailBean> datas;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<CdRemainTicketDetailBean> getDatas() {
        return datas;
    }

    public void setDatas(List<CdRemainTicketDetailBean> datas) {
        this.datas = datas;
    }
}

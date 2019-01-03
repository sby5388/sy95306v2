package com.by5388.sy95306v2.chengdu.bean.yupiao;

/**
 * 余票查询返回数据的顶层包装
 *
 * @author by5388
 * @date 2018/8/13 11:17
 */
@SuppressWarnings("all")
public class CdYpTop {

    /**
     * data : {"datas":[{"station_train_code":"G2926","start_station_name":"深圳北","to_station_name":"贵阳北","lishi":"04:58","yz_num":"0","yw_num":"0","rz_num":"0","rw_num":"0","wz_num":"0","start_time":"07:58","arrive_time":"12:56","zy_num":"4","ze_num":"0","fzx":"01"},{"station_train_code":"K486","start_station_name":"深圳","to_station_name":"贵阳","lishi":"21:53","yz_num":"0","yw_num":"97","rz_num":"0","rw_num":"1","wz_num":"48","start_time":"11:05","arrive_time":"08:58","zy_num":"0","ze_num":"0","fzx":"01"},{"station_train_code":"K830","start_station_name":"深圳东","to_station_name":"贵阳","lishi":"23:47","yz_num":"0","yw_num":"16","rz_num":"0","rw_num":"3","wz_num":"58","start_time":"13:40","arrive_time":"13:27","zy_num":"0","ze_num":"0","fzx":"01"},{"station_train_code":"G2930","start_station_name":"深圳北","to_station_name":"贵阳北","lishi":"05:21","yz_num":"0","yw_num":"16","rz_num":"0","rw_num":"3","wz_num":"58","start_time":"14:54","arrive_time":"20:15","zy_num":"0","ze_num":"0","fzx":"01"},{"station_train_code":"G2922","start_station_name":"深圳北","to_station_name":"贵阳北","lishi":"04:59","yz_num":"0","yw_num":"0","rz_num":"0","rw_num":"0","wz_num":"0","start_time":"15:25","arrive_time":"20:24","zy_num":"0","ze_num":"0","fzx":"01"}],"flag":"true"}
     */

    private CdAllResultDataBean data;

    public CdAllResultDataBean getData() {
        return data;
    }

    public void setData(CdAllResultDataBean data) {
        this.data = data;
    }


}

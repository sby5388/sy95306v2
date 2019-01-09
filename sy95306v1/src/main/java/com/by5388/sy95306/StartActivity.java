package com.by5388.sy95306;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.by5388.sy95306.bean.Station;
import com.by5388.sy95306.database.DataBaseApi;
import com.by5388.sy95306.database.DataBaseTempApiImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 启动界面
 * 用于加载插入数据库
 *
 * @author by5388
 * @date 20180621
 */
public class StartActivity extends AppCompatActivity {
    /**
     * 读写文件权限：1
     */
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION = 1;

    /**
     * 双击退出时间内
     */

    private DataBaseApi service;
    public static final int SUCCESS = 1;
    public static final int FAIL = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initData();
    }

    private void initData() {
        checkPermission();
    }

    private void checkPermission() {
        // 检查是否获得了权限（Android6.0运行时权限）
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请授权！", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_PERMISSION);
            }
        } else {
            // 已经获得授权，可以写数据库
            loadDataBase();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //授权成功-->写数据库
                    loadDataBase();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 对数据库进行初始化
     */
    private void loadDataBase() {
        service = DataBaseTempApiImpl.getInstance();
        boolean isEmpty = service.getStationStatus();
        if (!isEmpty) {
            toMainActivity();
            return;
        }
        insertCityData();

    }

    /**
     *
     */
    private void toMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        StartActivity.this.finish();
    }

    /**
     * TODO  这里大量数据插入的工作应该放在启动界面，由后台去实现
     * 实现应该是把数据库文件放在 raw文件夹中，启动时复制到数据库文件夹之中，避免大量的IO耗时操作
     */
    private void insertCityData() {
        String stationList = getCityList();
        List<Station> stations = getCityList(stationList);

        Observable.create((ObservableOnSubscribe<Integer>) e ->
                e.onNext(service.addStation(stations)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    if (SUCCESS == integer) {
                        toMainActivity();
                    } else {
                        Toast.makeText(StartActivity.this, "初始化失败", Toast.LENGTH_SHORT).show();
                    }
                }, throwable -> Toast.makeText(StartActivity.this, "初始化失败", Toast.LENGTH_SHORT).show());
    }

    private static List<Station> getCityList(String cityList) {
        List<Station> stations = new ArrayList<>();
        String[] stationNames = cityList.split("@");
        for (String stationName : stationNames) {
            String[] itemCity = stationName.split("\\|");
            if (itemCity.length == 6) {
                stations.add(new Station(itemCity[0], itemCity[1], itemCity[2],
                        itemCity[3], itemCity[4], Integer.parseInt(itemCity[5])));
            }
        }
        return stations;

    }


    private String getCityList() {
        try {
            AssetManager manager = getAssets();
            String path = "citiesList1.txt";
            InputStreamReader inputReader = new InputStreamReader(manager.open(path), "utf8");
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }
    //TODO  数据初始化过程中  要屏蔽返回键的点击事件


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean success = KeyEvent.KEYCODE_BACK == keyCode && KeyEvent.ACTION_DOWN == event.getAction();
        if (success) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}

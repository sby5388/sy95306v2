package com.by5388.sy95306;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.by5388.sy95306.common.ImageTool;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author by5388  on 2018/7/11.
 */

public abstract class BaseListActivity extends AppCompatActivity {
    /**
     * 读写文件权限：1
     */
    private static final String TAG = "BaseListActivity";

    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION = 1;
    private boolean checkedPermission = false;
    private static final int ACTION_SAVE = 1;
    private static final int ACTION_SHARE = 2;
    private int currentAction = 1;
    private String fileName = "";
    protected String imageName = "";
    private static final int MAX_ITEM_COUNT = 150;

    protected View topView;
    protected View secondView;
    protected View bottomView;
    /**
     * 是否后台操作：屏蔽新的请求
     */
    private boolean isLoading = false;
    protected RecyclerView recyclerView;
    protected Disposable disposable;


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
            // 已经获得授权，可以打写数据库
            startAction(currentAction);
        }
    }

    private void doAction(int actionType) {
        if (isLoading) {
            Toast.makeText(this, "后台操作中，请稍候", Toast.LENGTH_SHORT).show();
            return;
        }
        if (recyclerView.getAdapter().getItemCount() > MAX_ITEM_COUNT) {
            Toast.makeText(this, "车次数量过多，请筛选", Toast.LENGTH_SHORT).show();
            return;
        }

        currentAction = actionType;
        if (!checkedPermission) {
            checkPermission();
        } else {
            startAction(actionType);
        }
    }

    private void startAction(int actionType) {
        isLoading = true;
        if (ACTION_SAVE == actionType) {
            saveImage();
        } else {
            shareImageView();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //授权成功-->写数据库
                    checkedPermission = true;
                    startAction(currentAction);
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
     * 创建图片
     *
     * @return 图片路径
     */
    private Observable<String> generateBitmap() {
        return createBitmap()
                .flatMap(bitmap -> {
                    fileName = ImageTool.saveImageToGallery(BaseListActivity.this, bitmap, imageName);
                    return Observable.just(fileName);
                });
    }

    /**
     * 创建图片
     *
     * @return 创建图片的观察者
     */
    protected abstract Observable<Bitmap> createBitmap();


    /**
     * 分享图片
     * 如果已经创建过：则从图片路径中读取
     */
    private void shareImageView() {
        Observable<String> observable;
        if (TextUtils.isEmpty(fileName)) {
            observable = generateBitmap();
        } else {
            observable = Observable.just(fileName);
        }
        observable.flatMap(path -> {
            Uri imageUri = Uri.fromFile(new File(path));
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_STREAM, imageUri);
            intent.setType("image/*");
            return Observable.just(intent);
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(intent -> {
                    isLoading = false;
                    startActivity(Intent.createChooser(intent, "分享到 "));
                });
    }

    /**
     * 保存图片
     */
    private void saveImage() {
        if (TextUtils.isEmpty(fileName)) {
            generateBitmap()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(string -> {
                        isLoading = false;
                        Toast.makeText(BaseListActivity.this, string, Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(BaseListActivity.this, "已保存", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                doAction(ACTION_SAVE);
                break;
            case R.id.share:
                doAction(ACTION_SHARE);
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }

    /**
     * 关闭页面：关闭联系
     */
    private void unSubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    protected void showException(Throwable e) {
        Log.d(TAG, "onError: " + e.getMessage());
        Log.d(TAG, "onError:'JSON格式转换错误' " + e.getLocalizedMessage());
        Toast.makeText(this, "未查到相关车次信息", Toast.LENGTH_SHORT).show();
        finish();
    }
}

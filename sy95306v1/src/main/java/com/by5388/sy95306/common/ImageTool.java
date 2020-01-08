package com.by5388.sy95306.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.LruCache;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片处理相关的工具类
 *
 * @author by5388  on 2018/6/19.
 */

public class ImageTool {

    /*
     * http://stackoverflow.com/questions/12742343/android-get-screenshot-of-all-listview-items
     */

    /**
     * 根据ListView 来生成长截图
     * -->下一步 保存到手机相册之中
     *
     * @param listView
     * @return
     * @author https://blog.csdn.net/juesai2015/article/details/78961655
     */
    public static Bitmap shotListView(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        int itemCount = adapter.getCount();
        int allItemHeight = 0;
        List<Bitmap> bitmaps = new ArrayList<>();

        for (int i = 0; i < itemCount; i++) {
            View childView = adapter.getView(i, null, listView);
            childView.measure(
                    View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            childView.setDrawingCacheEnabled(true);
            childView.buildDrawingCache();
            bitmaps.add(childView.getDrawingCache());
            allItemHeight += childView.getMeasuredHeight();
        }

        Bitmap bigBitmap =
                Bitmap.createBitmap(listView.getMeasuredWidth(), allItemHeight, Bitmap.Config.ARGB_8888);
        Canvas bigCanvas = new Canvas(bigBitmap);

        Paint paint = new Paint();
        int iHeight = 0;

        for (int i = 0; i < bitmaps.size(); i++) {
            Bitmap bmp = bitmaps.get(i);
            bigCanvas.drawBitmap(bmp, 0, iHeight, paint);
            iHeight += bmp.getHeight();

            bmp.recycle();
        }
        return bigBitmap;
    }

    public static final String TAG = "ImageTool";

    public static Bitmap shotRecyclerView(@NonNull RecyclerView view) {
        RecyclerView.Adapter adapter = view.getAdapter();
        Bitmap bigBitmap = null;
        if (adapter != null) {
            int size = adapter.getItemCount();
            int height = 0;
            Paint paint = new Paint();
            int iHeight = 0;
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = maxMemory / 8;
            //TODO 缓存？
            LruCache<String, Bitmap> bitmapCache = new LruCache<>(cacheSize);
            for (int i = 0; i < size; i++) {
                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));
                adapter.onBindViewHolder(holder, i);
                holder.itemView.measure(
                        View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(),
                        holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();
                Bitmap drawingCache = holder.itemView.getDrawingCache();
                if (drawingCache != null) {
                    bitmapCache.put(String.valueOf(i), drawingCache);
                }
                height += holder.itemView.getMeasuredHeight();
            }
            // FIXME: 2019/1/4 
            bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888);
            Canvas bigCanvas = new Canvas(bigBitmap);
            Drawable lBackground = view.getBackground();
            if (lBackground instanceof ColorDrawable) {
                ColorDrawable lColorDrawable = (ColorDrawable) lBackground;
                int lColor = lColorDrawable.getColor();
                bigCanvas.drawColor(lColor);
            }

            for (int i = 0; i < size; i++) {
                Bitmap bitmap = bitmapCache.get(String.valueOf(i));
                bigCanvas.drawBitmap(bitmap, 0f, iHeight, paint);
                iHeight += bitmap.getHeight();
                bitmap.recycle();
            }
        }
        return bigBitmap;
    }

    /**
     * 保存图片到手机相册，并通知图库更新
     *
     * @param context
     * @param bmp     图片bitmap
     * @return 返回图片保存的路径，开发人员可以根据返回的路径在手机里面查看，部分手机发送通知图库并不会更新
     */

    public static String saveImageToGallery(Context context, Bitmap bmp, String imageName) {
        String newName = imageName.replace("/", "-");
        // 首先保存图片
        // File appDir = new File(Environment.getExternalStorageDirectory(), "saveImage");
        File appDir = new File(Environment.getExternalStorageDirectory(), "sy95306");
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        String fileName = newName + System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        String path = Environment.getExternalStorageDirectory() + "/sy95306/" + fileName;
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
        return Environment.getExternalStorageDirectory() + "/sy95306/" + fileName;
    }

    /**
     * View-->Bitmap
     * https://blog.csdn.net/SayYesOrNo/article/details/70258366
     *
     * @param view 需要绘画的View
     * @return bitmap
     */
    public static Bitmap getBitmap(@NonNull View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        //TODO 最后一个参数？
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        //如果不设置canvas画布为白色，则生成透明
        canvas.drawColor(Color.WHITE);
        //TODO ?
        view.draw(canvas);
        //// TODO: 2018/7/12 ???
        view.destroyDrawingCache();
        return bitmap;
    }

    /**
     * 把多个图片合并成同一个图片
     *
     * @param bitmaps 多个图片：假设宽度都相同
     * @return 图片
     */
    public static Bitmap longImage(@NonNull List<Bitmap> bitmaps) {
        //总高度
        int height = 0;
        for (Bitmap bitmap : bitmaps) {
            height += bitmap.getHeight();
        }
        int currentHeight = 0;
        int width = bitmaps.get(0).getWidth();
        Bitmap resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resultBitmap);
        for (Bitmap bitmap : bitmaps) {
            canvas.drawBitmap(bitmap, 0, currentHeight, null);
            currentHeight += bitmap.getHeight();
        }
        return resultBitmap;
    }

    public static Bitmap longImage(@NonNull Bitmap firstBitmap, @NonNull Bitmap secondBitmap) {
        List<Bitmap> bitmaps = new ArrayList<>();
        bitmaps.add(firstBitmap);
        bitmaps.add(secondBitmap);
        return longImage(bitmaps);
    }


    /**
     * 把多个view合并成同一个图片
     *
     * @param topView    上
     * @param mainView   中
     * @param bottomView 下
     * @return
     */
    public static Bitmap longImage(@NonNull View topView, @NonNull View mainView, @NonNull View bottomView) {
        Bitmap topBitmap = getBitmap(topView);
        Bitmap mainBitmap = getBitmap(mainView);
        Bitmap bottomBitmap = getBitmap(bottomView);
        int width = topBitmap.getWidth();
        int height = topBitmap.getHeight() + mainBitmap.getHeight() + bottomBitmap.getHeight();
        Bitmap resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(resultBitmap);
        //(bitmap,left, top,paint)
        canvas.drawBitmap(topBitmap, 0, 0, null);
        canvas.drawBitmap(mainBitmap, 0, topBitmap.getHeight(), null);
        canvas.drawBitmap(bottomBitmap, 0, topBitmap.getHeight() + mainBitmap.getHeight(), null);
//        List<Bitmap> bitmaps = new ArrayList<>();
//        bitmaps.add(topBitmap);
//        bitmaps.add(mainBitmap);
//        bitmaps.add(bottomBitmap);
//        return longImage(bitmaps);

        return resultBitmap;
    }

    /**
     * view -> bitmap
     *
     * @param views
     * @return
     */
    public static Bitmap longImage(List<View> views, int i) {
        List<Bitmap> bitmaps = new ArrayList<>(views.size());
        for (View view : views) {
            bitmaps.add(getBitmap(view));
        }
        return longImage(bitmaps);

    }
}

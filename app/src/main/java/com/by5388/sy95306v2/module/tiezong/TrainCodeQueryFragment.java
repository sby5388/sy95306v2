package com.by5388.sy95306v2.module.tiezong;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.sy95306v2.MyListener;
import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.tiezong.api.version2.TzNetTools2;
import com.by5388.sy95306v2.module.tiezong.api.version2.TzService2;
import com.by5388.sy95306v2.module.tiezong.api.version2.TzTrainCodeResult;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @author Administrator  on 2019/9/19.
 */
public class TrainCodeQueryFragment extends BaseTzFragment {

    private TzService2 mService2;

    public static BaseTzFragment newInstance() {
        return new TrainCodeQueryFragment();
    }

    private Button mButtonDate;
    private Button mButtonQuery;
    private EditText mEditText;
    private ListView mListView;
    private MyListener dateListener;
    private Calendar mCalendar;
    private MyAdapter mAdapter;
    private Disposable mDisposable;


    @Override
    public void updateView(int year, int month, int dayOfMonth) {
        mCalendar = Calendar.getInstance();
        mCalendar.set(year, month, dayOfMonth);
        mButtonDate.setText(getData(mCalendar));
    }


    @Override
    protected void initData() {
        dateListener = new MyListener(this);
        mCalendar = Calendar.getInstance();
        Retrofit retrofit = new TzNetTools2().getRetrofit();
        mService2 = retrofit.create(TzService2.class);
        mAdapter = new MyAdapter();
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.tz_train_code_query;
    }

    @Override
    protected void initView(View view) {
        mButtonDate = view.findViewById(R.id.button_select_date);
        mButtonDate.setOnClickListener(v -> {
            selectDate(dateListener, mCalendar);
        });
        mButtonDate.setText(getData(Calendar.getInstance()));
        mButtonQuery = view.findViewById(R.id.button_query);
        mEditText = view.findViewById(R.id.input_train_code);
        mListView = view.findViewById(R.id.listView_result);
        mButtonQuery.setOnClickListener(v -> query());
        mListView.setAdapter(mAdapter);

    }

    private void query() {
        final String trainNo = mEditText.getText().toString().trim();
        if (TextUtils.isEmpty(trainNo)) {
            Toast.makeText(getContext(), "输入为空", Toast.LENGTH_SHORT).show();
            return;
        }
        final String trainDate = mButtonDate.getText().toString().trim();
        final String randCode = "";
        mButtonQuery.setEnabled(false);
        mDisposable = mService2.getTrainDetail(trainNo, trainDate, randCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<TzTrainCodeResult>() {
                    @Override
                    public void accept(TzTrainCodeResult tzTrainCodeResult) throws Exception {
                        if (!tzTrainCodeResult.status || tzTrainCodeResult.mHttpStatus != HttpURLConnection.HTTP_OK) {
                            throw new Exception("没有获取到正确的数值");
                        }
                        final List<TzTrainCodeResult.DataBeanX.DataBean> data = tzTrainCodeResult.data.data;
                        mAdapter.setDataBeans(data);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mButtonQuery.setEnabled(true);
                        Toast.makeText(getContext(), throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mButtonQuery.setEnabled(true);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    private class MyAdapter extends BaseAdapter {
        private List<TzTrainCodeResult.DataBeanX.DataBean> mDataBeans;


        MyAdapter() {
            mDataBeans = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return mDataBeans.size();
        }

        @Override
        public TzTrainCodeResult.DataBeanX.DataBean getItem(int position) {
            return mDataBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sh_number, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.bind(getItem(position));
            return convertView;
        }

        private void setDataBeans(List<TzTrainCodeResult.DataBeanX.DataBean> dataBeans) {
            if (dataBeans == null || dataBeans.isEmpty()) {
                return;
            }
            mDataBeans = dataBeans;
            notifyDataSetChanged();
        }
    }

    private static class ViewHolder {

        final TextView stationName;
        final TextView stayTime;
        final TextView stationNum;
        final TextView arrive;
        final TextView endTime;
        final TextView startTime;
        final TextView delay;
        final ProgressBar progressBar;

        private ViewHolder(View view) {
            stationName = view.findViewById(R.id.stationName);
            stayTime = view.findViewById(R.id.stayTime);
            stationNum = view.findViewById(R.id.stationNum);
            arrive = view.findViewById(R.id.arrive_time);
            endTime = view.findViewById(R.id.endTime);
            startTime = view.findViewById(R.id.startTime);
            delay = view.findViewById(R.id.textView_delay);
            progressBar = view.findViewById(R.id.progressBar);
        }

        private void bind(TzTrainCodeResult.DataBeanX.DataBean dataBean) {
            stationName.setText(dataBean.mStationName);
            arrive.setText(dataBean.mArriveTime);
            startTime.setText(dataBean.mStartTime);
        }
    }


}

package com.by5388.sy95306v2.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.dialog.bean.FilterBean;
import com.by5388.sy95306v2.dialog.bean.FilterData;

import java.util.ArrayList;
import java.util.List;

import static android.widget.AbsListView.CHOICE_MODE_MULTIPLE;

/**
 * 车次过滤对话框
 *
 * @author by5388  on 2018/8/7.
 */

public class TrainFilterDialog implements ITrainFilterView {

    private final Context context;
    private final AlertDialog dialog;
    private View mainView;
    private ListView listView;
    private List<FilterBean> beans;
    private ArrayAdapter<FilterBean> arrayAdapter;
    private List<Integer> selected;
    private final UpdateFilterDataCallBack callBack;


    public TrainFilterDialog(Context context, UpdateFilterDataCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        initData();
        initView();

        dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.title_train_type)
                .setView(mainView)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.ok, (dialog, which) -> isSingle())
                .create();
    }

    private void initData() {
        beans = FilterData.getFilterItems();
        arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice);
        arrayAdapter.addAll(beans);
    }


    private void initView() {
        mainView = LayoutInflater.from(context).inflate(R.layout.dialog_filter, new LinearLayout(context), false);
        listView = mainView.findViewById(R.id.listView);
        listView.setChoiceMode(CHOICE_MODE_MULTIPLE);
        listView.setAdapter(arrayAdapter);
        for (int i = 0; i < beans.size(); i++) {
            listView.setItemChecked(i, beans.get(i).isSelected());
        }
    }

    @Override
    public void show() {
        dialog.show();
    }

    @Override
    public void dismiss() {
        dialog.dismiss();
    }

    @Override
    public boolean isShowing() {
        return dialog.isShowing();
    }

    @Override
    public List<Integer> getSelectedType() {
        if (null == selected) {
            selected = new ArrayList<>();
        }
        selected.clear();
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i).isSelected()) {
                selected.add(i);
            }
        }
        return selected;
    }

    @Override
    public ITrainFilterView refresh() {
        for (int i = 0; i < beans.size(); i++) {
            listView.setItemChecked(i, beans.get(i).isSelected());
        }
        return this;
    }

    /**
     * 参考 https://blog.csdn.net/Hai_pp/article/details/79234092
     * 确定按钮的监听事件
     */
    private void isSingle() {
        //通过listView对象获取到当前listView中被选择的条目position;
        //以下方法实现会对返回一个SparseBooleanArray集合，其中对listview的触发过点击事件的每个条目进行
        // 标记（键值对）键==position/值==boolean，若该条目被选中则显示true，否则显示false;
        //TODO
        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        //循环遍历集合中所有的数据，获取每个item是否在SparseBooleanArray存储，以及对应的值；
        for (int i = 0; i < beans.size(); i++) {
            //根据key获取对应的boolean值，没有则返回false
            FilterBean bean = beans.get(i);
            bean.setSelected(checkedItemPositions.get(i));
        }
        callBack.updateFilterData();
    }

    public interface UpdateFilterDataCallBack {
        /**
         * 回调接口，只是刷新
         */
        void updateFilterData();
    }
}

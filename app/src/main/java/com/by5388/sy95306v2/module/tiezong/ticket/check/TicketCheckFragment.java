package com.by5388.sy95306v2.module.tiezong.ticket.check;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.by5388.sy95306v2.R;
import com.by5388.sy95306v2.module.tiezong.BaseTzFragment;
import com.by5388.sy95306v2.module.tiezong.ticket.check.adapter.TicketCheckAdapter;

/**
 * @author admin  on 2019/1/24.
 */
public class TicketCheckFragment extends BaseTzFragment implements TextWatcher {
    private BaseAdapter mAdapter;

    @Override
    public void updateView(int year, int month, int dayOfMonth) {

    }

    @Override
    protected void initData() {
        mAdapter = new TicketCheckAdapter(getContext());
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tz_ticket_check;
    }

    @Override
    protected void initView(View view) {
        ListView listView = view.findViewById(R.id.listView);
        EditText editText = view.findViewById(R.id.editText_train_code);
        Button buttonDate = view.findViewById(R.id.button_query_date);
        Button buttonQuery = view.findViewById(R.id.button_query);
        listView.setAdapter(mAdapter);

        editText.addTextChangedListener(this);
        buttonQuery.setOnClickListener(v -> queryTicket());
    }

    private void queryTicket() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

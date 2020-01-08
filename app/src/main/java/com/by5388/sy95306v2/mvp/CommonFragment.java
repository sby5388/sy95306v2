package com.by5388.sy95306v2.mvp;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 在InCallUi 中  BaseFragment作为MVP中的M，用于对 V ,P 做关联,解耦
 *
 * @author Administrator  on 2019/9/12.
 */
public abstract class CommonFragment<T extends Presenter<U>, U extends Ui> extends Fragment {
    // TODO: 2019/9/12 对于这个可以自定义某一些关键动作
    private static final String KEY_FRAGMENT_HIDDEN = "key_fragment_hidden";
    private T mPresenter;
    private FragmentDisplayManager mManager;

    protected CommonFragment() {
        mPresenter = createPresenter();
    }

    public abstract T createPresenter();

    public abstract U getUI();

    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.onUiReady(getUI());
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mPresenter.onRestoreInstanceState(savedInstanceState);
            if (savedInstanceState.getBoolean(KEY_FRAGMENT_HIDDEN)) {
                getFragmentManager().beginTransaction().hide(this).commit();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onUiUnReady(getUI());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onRestoreInstanceState(outState);
        // TODO: 2019/9/12 保存当前状态
        outState.putBoolean(KEY_FRAGMENT_HIDDEN, isHidden());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentDisplayManager) {
            mManager = (FragmentDisplayManager) context;
            mManager.onFragmentAttach(this);
        } else {
            throw new RuntimeException("Context must implement FragmentDisplayManager");
        }
    }
}

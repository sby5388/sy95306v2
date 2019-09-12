package com.by5388.sy95306v2.mvp;

import android.os.Bundle;

/**
 * todo onXX() 一般用于回调使用
 *
 * @author Administrator  on 2019/9/12.
 */
public abstract class Presenter<U extends Ui> {
    private U mUi;


    public U getUi() {
        return mUi;
    }

    public void onUiReady(U ui) {
        this.mUi = ui;
    }

    public void onUiUnReady(U ui) {

    }

    public final void onUiDestroy(U ui) {
        onUiReady(ui);
        mUi = null;
    }

    public void onSaveInstanceState(Bundle instanceState) {

    }

    public void onRestoreInstanceState(Bundle instanceState) {

    }


}

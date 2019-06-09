package com.by5388.sy95306.kotlin;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * @author by5388  on 2019/1/14.
 */
public class Teet2 {
    public void test(Context mContext) {
        AlertDialog updateTipDialog = new AlertDialog.Builder(mContext)
                .setTitle(R.string.title_update_tip)
                .setMessage(R.string.message_update_tip)
                //todo
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        show(dialog, which);
                    }
                })
                .create();
    }

    public void show(DialogInterface mDialogInterface, int which) {

    }
}

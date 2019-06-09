package com.by5388.sy95306.kotlin

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog

/**
 * @author by5388  on 2019/1/14.
 */
class Teet {
    fun test(mContext: Context) {
        val updateTipDialog = AlertDialog.Builder(mContext)
                .setTitle(R.string.title_update_tip)
                .setMessage(R.string.message_update_tip)
                //todo
                .setPositiveButton(android.R.string.ok) { dialog, which -> show(dialog, which) }
                .create()
    }

    fun show(mDialogInterface: DialogInterface, which: Int) {

    }
}

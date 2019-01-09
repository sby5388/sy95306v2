package com.by5388.sy95306.kotlin.list.presenter

/**
 * @author  by5388  on 2019/1/7.
 */
interface IListPresenter {
    fun query(date: Int, fromStation: String, toStation: String);
    fun unSubject();
}
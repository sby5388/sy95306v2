package com.by5388.sy95306.kotlin.bean

import org.jetbrains.annotations.Nullable

/**
 * @author  by5388  on 2019/1/5.
 */
data class Station(val id: Int,
                   val nameFirst: String,
                   val name: String,
                   val nameUpper: String,
                   @Nullable  val nameEn: String,
                   @Nullable val nameLower: String,
                   @Nullable val code: Int){
}
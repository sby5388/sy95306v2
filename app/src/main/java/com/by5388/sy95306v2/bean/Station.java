package com.by5388.sy95306v2.bean;

import android.support.annotation.NonNull;

/**
 * 车站：用于选择出发站、终点站，车次返回后的名称
 *
 * @author by5388
 */
@SuppressWarnings("all")
public class Station {
    public final static int EMPTY_ID = -1;
    /**
     * nameFirst:bjb
     * name:北京北
     * nameUpper:VAP
     * nameEn:beijingbei
     * nameLower:bjb
     * code:0
     * <p>
     * gzx|广州西|GXQ|guangzhouxi|gzx|32
     * kmi|昆明|KMM|kunming|km|48
     * mds|磨刀石|MOB|modaoshi|mds|1288
     * 本地数据库查询：nameFirst/name/nameEn/nameLower 都可以
     */


    private int id = EMPTY_ID;
    private String nameFirst;
    private String name;
    private String nameUpper;
    private String nameEn;
    private String nameLower;
    private int code;

    public Station() {
    }

    public Station(int id, @NonNull String nameFirst, @NonNull String name, @NonNull String nameUpper, @NonNull String nameEn, @NonNull String nameLower, int code) {
        super();
        this.id = id;
        this.nameFirst = nameFirst;
        this.name = name;
        this.nameUpper = nameUpper;
        this.nameEn = nameEn;
        this.nameLower = nameLower;
        this.code = code;
    }

    public Station(@NonNull String nameFirst, @NonNull String name, @NonNull String nameUpper, @NonNull String nameEn, @NonNull String nameLower, int code) {
        super();
        this.nameFirst = nameFirst;
        this.name = name;
        this.nameUpper = nameUpper;
        this.nameEn = nameEn;
        this.nameLower = nameLower;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUpper() {
        return nameUpper;
    }

    public void setNameUpper(String nameUpper) {
        this.nameUpper = nameUpper;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameLower() {
        return nameLower;
    }

    public void setNameLower(String nameLower) {
        this.nameLower = nameLower;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Station)) {
            return false;
        }
        Station station = (Station) obj;
        return this.nameUpper.equals(station.nameUpper);
    }
}

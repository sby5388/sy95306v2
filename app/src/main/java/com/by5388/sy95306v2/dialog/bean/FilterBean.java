package com.by5388.sy95306v2.dialog.bean;

/**
 * @author by5388  on 2018/8/8.
 */

public class FilterBean {

    private String itemName;
    private boolean selected;

    FilterBean(String itemName, boolean selected) {
        this.itemName = itemName;
        this.selected = selected;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return this.itemName;
    }
}

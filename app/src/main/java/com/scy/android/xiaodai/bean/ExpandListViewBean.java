package com.scy.android.xiaodai.bean;

/**
 * created by scy on 2019/7/8 13:43
 * gmail：cherseey@gmail.com
 */
public class ExpandListViewBean {
    private Integer drawable;
    private String name;
    private String num;

    public ExpandListViewBean(Integer drawable, String name, String num) {
        this.drawable = drawable;
        this.name = name;
        this.num = num;
    }

    public Integer getDrawable() {
        return drawable;
    }

    public void setDrawable(Integer drawable) {
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}

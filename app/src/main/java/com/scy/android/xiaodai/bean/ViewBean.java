package com.scy.android.xiaodai.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * created by scy on 2019/7/11 15:59
 * gmail：cherseey@gmail.com
 */
public class ViewBean implements Parcelable {

    private String title;
    private String url;
    private String img;
    private String kind;
    private String playcount;
    private String plcount;
    private String time;

    public ViewBean(String title, String url, String img, String kind, String playcount, String plcount, String time) {
        this.title = title;
        this.url = url;
        this.img = img;
        this.kind = kind;
        this.playcount = playcount;
        this.plcount = plcount;
        this.time = time;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getPlcount() {
        return plcount;
    }

    public void setPlcount(String plcount) {
        this.plcount = plcount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.img);
        dest.writeString(this.kind);
        dest.writeString(this.playcount);
        dest.writeString(this.plcount);
        dest.writeString(this.time);
    }

    protected ViewBean(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
        this.img = in.readString();
        this.kind = in.readString();
        this.playcount = in.readString();
        this.plcount = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<ViewBean> CREATOR = new Parcelable.Creator<ViewBean>() {
        @Override
        public ViewBean createFromParcel(Parcel source) {
            return new ViewBean(source);
        }

        @Override
        public ViewBean[] newArray(int size) {
            return new ViewBean[size];
        }
    };
}

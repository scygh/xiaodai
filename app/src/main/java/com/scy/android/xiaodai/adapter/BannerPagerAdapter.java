package com.scy.android.xiaodai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scy.android.xiaodai.R;

import java.util.List;

/**
 * created by scy on 2019/7/3 11:51
 * gmail：cherseey@gmail.com
 */
public class BannerPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Integer> mPictures;
    public static final int FAKE_BANNER_SIZE = 1000;

    public BannerPagerAdapter(Context context, List<Integer> pictures) {
        mContext = context;
        mPictures = pictures;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_item,container,false);
        ImageView imageView= view.findViewById(R.id.iv_banner_item);
        position %= mPictures.size();
        imageView.setImageResource(mPictures.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return FAKE_BANNER_SIZE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}

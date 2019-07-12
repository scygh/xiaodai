package com.scy.android.xiaodai.view.selfdesign;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.view.ViewPager;

import com.scy.android.xiaodai.R;

import java.util.List;

/**
 * created by scy on 2019/7/3 10:35
 * gmail：cherseey@gmail.com
 */
public class indicatorView extends LinearLayout implements ViewPager.OnPageChangeListener {
    //指示器个数
    private int childViewCount = 0;
    //指示器间距
    private int mInterval;
    //当前选中的位置
    private int mCurrentPosition = 0;
    private Bitmap normalBp;
    private Bitmap selectBp;
    private ViewPager mViewpager;
    //指示器单项宽度
    private int mWidth;
    //高度
    private int mHeight;
    private int mRadius;
    //普通状态圆点颜色
    private int normalColor;
    private int selectColor;
    //是否循环
    private boolean isCirculate;

    //对外回调接口
    public interface OnLbPageChangeListener {
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        public void onPageSelected(int position);

        public void onPageScrollStateChanged(int state);

    }

    private OnLbPageChangeListener mOnLbPageChangeListener;

    public void setOnLbPageChangeListener(OnLbPageChangeListener onLbPageChangeListener) {
        mOnLbPageChangeListener = onLbPageChangeListener;
    }


    private Bitmap drawableToBitamp(Drawable drawable) {
        if (null == drawable) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    private Bitmap makeIndicatorBp(int color) {
        Bitmap normalBp = Bitmap.createBitmap(mRadius * 2, mRadius * 2, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        Canvas canvas = new Canvas(normalBp);
        canvas.drawCircle(mRadius, mRadius, mRadius, paint);
        return normalBp;
    }

    public indicatorView(Context context) {
        super(context);
    }

    public indicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public indicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView);
        normalBp = drawableToBitamp(ta.getDrawable(R.styleable.IndicatorView_normalDrawable));
        selectBp = drawableToBitamp(ta.getDrawable(R.styleable.IndicatorView_selectDrawable));
        mInterval = ta.getDimensionPixelOffset(R.styleable.IndicatorView_indicatorInterval, 6);
        normalColor = ta.getColor(R.styleable.IndicatorView_normalColor, Color.GRAY);
        selectColor = ta.getColor(R.styleable.IndicatorView_selectColor, Color.RED);
        mRadius = ta.getInteger(R.styleable.IndicatorView_indicatorRadius, 5);
        isCirculate = ta.getBoolean(R.styleable.IndicatorView_isCirculate, true);
        //单例。释放使得其他模块复用之
        ta.recycle();
        init();
    }

    private void init() {
        if (null == normalBp) {
            normalBp = makeIndicatorBp(normalColor);
        }
        if (null == selectBp) {
            selectBp = makeIndicatorBp(selectColor);
        }
        mWidth = normalBp.getWidth();
        mHeight = normalBp.getHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        // 如果是wrap_content设置为图片宽高,否则设置为父容器宽高
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth : (mWidth + mInterval) * childViewCount, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight : mHeight);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (getChildCount() < childViewCount && getChildCount() == 0) {
            for (int i = 0; i < childViewCount; i++) {
                addView(makeIndicatorItem());
            }
            setIndicatorState(mCurrentPosition);
        }
        super.dispatchDraw(canvas);
    }

    private View makeIndicatorItem() {
        ImageView iv = new ImageView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        /*lp.width = normalBp.getWidth();
        lp.height = normalBp.getHeight();*/
        lp.width = 10;
        lp.height = 10;
        lp.rightMargin = mInterval;
        iv.setImageBitmap(normalBp);
        iv.setLayoutParams(lp);
        return iv;
    }

    public void setIndicatorState(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            if (i == position) ((ImageView) getChildAt(i)).setImageBitmap(selectBp);
            else ((ImageView) getChildAt(i)).setImageBitmap(normalBp);
        }
    }

    public void setViewpager(ViewPager viewpager) {
        if (null == viewpager) {
            return;
        }
        if (null == viewpager.getAdapter()) {
            throw new IllegalStateException("Viewpager is not have Adapter");
        }
        this.mViewpager = viewpager;
        this.mViewpager.addOnPageChangeListener(this);
        this.childViewCount = viewpager.getAdapter().getCount();
        invalidate();
    }

    public void setViewPager(ViewPager viewpager, int currposition) {
        if (null == viewpager) {
            return;
        }
        if (null == viewpager.getAdapter()) {
            throw new IllegalStateException("ViewPager does not have adapter.");
        }
        this.mViewpager = viewpager;
        this.mViewpager.addOnPageChangeListener(this);
        this.childViewCount = viewpager.getAdapter().getCount();
        this.mCurrentPosition = currposition;
        invalidate();
    }

    public void setViewPager(int childViewCount, ViewPager viewpager) {
        if (null == viewpager) {
            return;
        }
        if (null == viewpager.getAdapter()) {
            throw new IllegalStateException("ViewPager does not have adapter.");
        }
        this.mViewpager = viewpager;
        this.mViewpager.addOnPageChangeListener(this);
        this.childViewCount = childViewCount;
        removeAllViews();
        invalidate();
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
        if (null!= mOnLbPageChangeListener) {
            mOnLbPageChangeListener.onPageScrolled(i,v,i1);
        }
    }

    @Override
    public void onPageSelected(int i) {
        if (isCirculate &&0!=childViewCount) {
            i%=childViewCount;
        }
        setIndicatorState(i);
        if (null != mOnLbPageChangeListener) {
            mOnLbPageChangeListener.onPageSelected(i);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        if (null!= mOnLbPageChangeListener) {
            mOnLbPageChangeListener.onPageScrollStateChanged(i);
        }
    }
}

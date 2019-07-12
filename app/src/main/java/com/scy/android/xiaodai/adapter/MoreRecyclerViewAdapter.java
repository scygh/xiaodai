package com.scy.android.xiaodai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.bean.ViewBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * created by scy on 2019/7/11 15:53
 * gmail：cherseey@gmail.com
 */
public class MoreRecyclerViewAdapter extends RecyclerView.Adapter<MoreRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<ViewBean> mViewBeans;
    private onMyItemClickListener mOnMyItemClickListener;
    public interface onMyItemClickListener {
        void onMyItemClick(int position,View view);
    }

    public void setOnMyItemClickListener(onMyItemClickListener onMyItemClickListener) {
        mOnMyItemClickListener = onMyItemClickListener;
    }

    public MoreRecyclerViewAdapter(Context context, ArrayList<ViewBean> viewBeans) {
        mContext = context;
        mViewBeans = viewBeans;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.jz_iv)
        ImageView jziv;
        @BindView(R.id.fruit_name)
        TextView fruitName;
        @BindView(R.id.kind_name)
        TextView kindName;
        @BindView(R.id.sd)
        ImageView sd;
        @BindView(R.id.play_count2)
        TextView playCount2;
        @BindView(R.id.pl2)
        TextView pl2;
        @BindView(R.id.time_long)
        TextView timeLong;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnMyItemClickListener!=null) {
                        mOnMyItemClickListener.onMyItemClick(getAdapterPosition(),itemView);
                    }
                }
            });
        }

        public void bind() {
            int position = getAdapterPosition();
            Glide.with(mContext).load(mViewBeans.get(position).getImg()).into(jziv);
            fruitName.setText(mViewBeans.get(position).getTitle());
            kindName.setText(mViewBeans.get(position).getKind());
            timeLong.setText(mViewBeans.get(position).getTime());
            playCount2.setText(mViewBeans.get(position).getPlaycount());
            pl2.setText(mViewBeans.get(position).getPlcount());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.more_rv_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind();
    }

    @Override
    public int getItemCount() {
        return mViewBeans.size();
    }
}

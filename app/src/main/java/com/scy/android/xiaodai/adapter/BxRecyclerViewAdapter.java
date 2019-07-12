package com.scy.android.xiaodai.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scy.android.xiaodai.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * created by scy on 2019/7/7 18:53
 * gmail：cherseey@gmail.com
 */
public class BxRecyclerViewAdapter extends RecyclerView.Adapter<BxRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<Integer> pictures;

    public BxRecyclerViewAdapter(Context context, List<Integer> pictures) {
        mContext = context;
        this.pictures = pictures;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.bx_item_img)
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
         public void bind(int i) {
            imageView.setImageResource(i);
         }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bx_recyclerview_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(pictures.get(i));
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }
}

package com.scy.android.xiaodai.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.scy.android.xiaodai.R;
import com.scy.android.xiaodai.bean.Elbdetail;
import com.scy.android.xiaodai.bean.ExpandListViewBean;

import java.util.List;

/**
 * created by scy on 2019/7/8 14:02
 * gmail：cherseey@gmail.com
 */
public class MyExpandListViewAdapter extends BaseExpandableListAdapter {

    private List<ExpandListViewBean> groupList;
    private List<List<Elbdetail>> childList;
    private Context mContext;

    public MyExpandListViewAdapter(List<ExpandListViewBean> groupList, List<List<Elbdetail>> childList, Context context) {
        this.groupList = groupList;
        this.childList = childList;
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.groupitem,null);
        ImageView iv1 = convertView.findViewById(R.id.group_iv);
        TextView tv1 = convertView.findViewById(R.id.group_name);
        TextView tv2 = convertView.findViewById(R.id.group_num);
        iv1.setImageResource(groupList.get(groupPosition).getDrawable());
        tv1.setText(groupList.get(groupPosition).getName());
        tv2.setText(groupList.get(groupPosition).getNum());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext,R.layout.childitem,null);
        TextView name = convertView.findViewById(R.id.child_name);
        TextView num = convertView.findViewById(R.id.child_num);
        name.setText(childList.get(groupPosition).get(childPosition).getName());
        num.setText(childList.get(groupPosition).get(childPosition).getNum());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

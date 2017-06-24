package com.wuwg.component.advancedui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuwg.component.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuwengao on 2017/6/24.
 */
public class SimpleAdaper extends RecyclerView.Adapter<SimpleAdaper.MyViewHolder> {


    protected List<String> mDatas;
    private LayoutInflater inflater;

    public SimpleAdaper(RecyclerView recyclerView) {
        inflater = LayoutInflater.from(recyclerView.getContext());
        initData();
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        mDatas.add("泽井芽衣");
        mDatas.add("大浦安娜");
        mDatas.add("波多野结衣");
        mDatas.add("卯月麻衣");
        mDatas.add("泷泽萝拉");
        mDatas.add("樱井莉亚");
        mDatas.add("雾岛奈津美");
        mDatas.add("杉原杏璃");
        mDatas.add("雨宫琴音");
        mDatas.add("松岛枫");
        mDatas.add("仁科百华");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sliding_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String text = mDatas.get(position);
        holder.text.setText(text);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}

package com.wuwg.component.advancedui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wuwg.component.R;

/**
 * Created by wuwengao on 2017/6/24.
 */
@CoordinatorLayout.DefaultBehavior(SlidingCardBehavior.class)
public class SlidingCardLayout extends FrameLayout {

    private int mHeaderViewHeight;

    public SlidingCardLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.widget_card, this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SlidingCardLayout);
        TextView header = (TextView) findViewById(R.id.header);
        header.setBackgroundColor(a.getColor(R.styleable.SlidingCardLayout_android_colorBackground, Color.BLACK));
        header.setText(a.getText(R.styleable.SlidingCardLayout_android_text));

        //RecyclerView
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.Adapter adapter = new SimpleAdaper(list);
        list.setAdapter(adapter);
        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh){
            mHeaderViewHeight = findViewById(R.id.header).getMeasuredHeight();
        }
    }

    public int getHeaderHeight(){
        return mHeaderViewHeight;
    }
}

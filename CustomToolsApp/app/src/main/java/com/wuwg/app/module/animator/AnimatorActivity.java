package com.wuwg.app.module.animator;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;

import com.wuwg.app.R;
import com.wuwg.component.animator.AnimatorPath;
import com.wuwg.component.animator.PathEvaluator;
import com.wuwg.component.animator.PathPoint;
import com.wuwg.custom.base.BaseActivity;

/**
 * Created by wuwengao on 2017/6/22.
 */
public class AnimatorActivity extends BaseActivity implements View.OnClickListener{

    private ImageView imgAnimator;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_animator);
    }

    @Override
    protected void initView() {
        imgAnimator = (ImageView) findViewById(R.id.img_animator);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        imgAnimator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_animator:
                startAnimator();
                break;
        }
    }

    /**
     * 开始动画
     */
    private void startAnimator(){
        AnimatorPath path = new AnimatorPath();
        path.moveTo(200, 200);
        path.lineTo(0, 200);
        path.lineTo(0, 600);
        path.lineTo(600, 600);
        path.lineTo(600, 0);
        path.cubicTo(300, 300, 200, -100, 0, 0);
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(this, "draw", new PathEvaluator(), path.getPoints().toArray());
        objectAnimator.setDuration(2000);
        objectAnimator.start();
        objectAnimator.setRepeatCount(100);
    }




    /**
     * 通过反射调用该方法
     */
    public void setDraw(PathPoint pathPoint){
        if(pathPoint != null){
            imgAnimator.setTranslationX(pathPoint.mX);
            imgAnimator.setTranslationY(pathPoint.mY);
        }

    }



}

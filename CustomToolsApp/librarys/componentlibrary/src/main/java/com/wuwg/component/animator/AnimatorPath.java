package com.wuwg.component.animator;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 存储动画运动轨迹
 * Created by wuwengao on 2017/6/22.
 */
public class AnimatorPath {

    ArrayList<PathPoint> points = new ArrayList<PathPoint>();

    public void moveTo(float x, float y) {
        points.add(PathPoint.newMoveTo(x, y));
    }

    public void lineTo(float x, float y) {
        points.add(PathPoint.newLine(x, y));
    }

    public void cubicTo(float c0x, float c0y, float c1x, float c1y, float x, float y) {
        points.add(PathPoint.newCubic(c0x, c0y, c1x, c1y, x, y));
    }

    /**
     * 获取点的集合
     *
     * @return
     */
    public Collection<PathPoint> getPoints() {
        return points;
    }
}
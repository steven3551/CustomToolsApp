package com.wuwg.component.animator;

import android.animation.TypeEvaluator;

/**
 * 轨迹策略
 * Created by wuwengao on 2017/6/22.
 */
public class PathEvaluator implements TypeEvaluator {

    /**
     * @param t          开始到结束的百分比(0-1)
     * @param startValue 起始位置
     * @param endValue   结束位置
     * @return
     */
    @Override
    public Object evaluate(float t, Object startValue, Object endValue) {
        float x = 0, y = 0;
        PathPoint startPoint = (PathPoint) startValue;
        PathPoint endPoint = (PathPoint) endValue;
        if (endPoint.mOperation == PathPoint.MOVE) {
            x = endPoint.mX;
            y = endPoint.mY;
        } else if (endPoint.mOperation == PathPoint.LINE) {
            x = startPoint.mX + t * (endPoint.mX - startPoint.mX);
            y = startPoint.mY + t * (endPoint.mY - startPoint.mY);
        } else if (endPoint.mOperation == PathPoint.CUBIC) {
            float leftPercent = 1 - t;
            x = leftPercent * leftPercent * leftPercent * startPoint.mX +
                    2 * t * leftPercent * leftPercent * endPoint.mControl0x +
                    3 * t * t * leftPercent * endPoint.mControl1x +
                    t * t * t * endPoint.mX;
            y = leftPercent * leftPercent * leftPercent * startPoint.mY +
                    2 * t * leftPercent * leftPercent * endPoint.mControl0y +
                    3 * t * t * leftPercent * endPoint.mControl1y +
                    t * t * t * endPoint.mY;
        }
        return PathPoint.newMoveTo(x, y);
    }
}

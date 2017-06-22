package com.wuwg.component.animator;

/**
 * 定义路径
 * Created by wuwengao on 2017/6/22.
 */
public class PathPoint {

    public static final int MOVE = 0;       //定位到那个位置
    public static final int LINE = 1;       //从定位的位置画直线到目标位置
    public static final int CUBIC = 2;      //画赛贝尔曲线

    public int mOperation;      //操作的类型
    public float mX, mY;
    public float mControl0x, mControl0y;
    public float mControl1x, mControl1y;

    public PathPoint(int operation, float x, float y) {
        mOperation = operation;
        mX = x;
        mY = y;
    }

    public PathPoint(int mOperation, float mControl0x, float mControl0y, float mControl1x, float mControl1y, float mX, float mY) {
        this.mOperation = mOperation;
        this.mX = mX;
        this.mY = mY;
        this.mControl0x = mControl0x;
        this.mControl0y = mControl0y;
        this.mControl1x = mControl1x;
        this.mControl1y = mControl1y;
    }

    public static PathPoint newMoveTo(float x, float y) {
        return new PathPoint(MOVE, x, y);
    }

    public static PathPoint newLine(float x, float y) {
        return new PathPoint(LINE, x, y);
    }

    public static PathPoint newCubic(float mControl0x, float mControl0y, float mControl1x, float mControl1y, float mX, float mY) {
        return new PathPoint(CUBIC, mControl0x, mControl0y, mControl1x, mControl1y, mX, mY);
    }

}

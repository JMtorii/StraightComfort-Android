package com.jrs.StraightComfort.Utilities;

import android.content.Context;
import android.gesture.Gesture;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomViewPager extends ViewPager{

    private boolean mEndPaging = false;
    private boolean mStartPaging = false;
    private final int L2R_SWIPE = -1;
    private final int R2L_SWIPE = 1;

    float mStartDragX;
    float mStartDragX2;
    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        int dir = 0;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartDragX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mStartDragX < x ) {
                    dir=L2R_SWIPE;
                } else if (mStartDragX > x ) {
                    dir=R2L_SWIPE;
                }
                break;
        }
        if (dir == L2R_SWIPE)
        {
            if (mStartPaging)
                return false;
            else return super.onInterceptTouchEvent(ev);
        }
        else if (dir == R2L_SWIPE)
        {
            if (mEndPaging)
                return false;
            else return super.onInterceptTouchEvent(ev);
        }
        else
            return super.onInterceptTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        int dir = 0;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartDragX2 = x;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mStartDragX2 < x) {
                    dir = L2R_SWIPE;
                } else if (mStartDragX2 > x) {
                    dir = R2L_SWIPE;
                }
                break;
        }
        if (dir == L2R_SWIPE)
        {
            if (mStartPaging)
                return false;
            else return super.onTouchEvent(ev);
        }
        else if (dir == R2L_SWIPE)
        {
            if (mEndPaging)
                return false;
            else return super.onTouchEvent(ev);
        }
        else
            return super.onTouchEvent(ev);

    }

    public void setEndPaging(boolean endPaging) {
        this.mEndPaging = endPaging;
    }

    public void setStartPaging(boolean startPaging){
        this.mStartPaging = startPaging;
    }
}

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

    private boolean isPaging = true;
    private boolean isStartPaging = false;
    private int currPos = 0;
    OnSwipeOutListener mListener;



    public void setOnSwipeOutListener(OnSwipeOutListener listener) {
        mListener = listener;
    }

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
                    Log.d("Message","Right2");
                    dir=-1;
                } else if (mStartDragX > x ) {
                    Log.d("Message","Left2");
                    dir=1;
                }
                break;
        }
        if (isPaging && !isStartPaging) {
            return super.onInterceptTouchEvent(ev);
        }
        else if (!isPaging)
        {
            if (dir==-1)
                this.setCurrentItem(this.getCurrentItem() - 1);

            return false;
        }
        else if (isStartPaging)
        {
            if (dir==1)
                this.setCurrentItem(this.getCurrentItem()+1);
            return false;
        }
        else
            return false;

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
                if (mStartDragX2 < x && getCurrentItem() == 0) {
                    Log.d("Message","Right1");
                    dir = -1;
                } else if (mStartDragX2 > x && getCurrentItem() == getAdapter().getCount() - 1) {
                    Log.d("Message","Left1");
                    dir = 1;
                }
                break;
        }
        if (isPaging && !isStartPaging) {
            return super.onTouchEvent(ev);
        }
        else if (!isPaging)
        {
            if (dir==-1)
                this.setCurrentItem(this.getCurrentItem() - 1);

            return false;
        }
        else if (isStartPaging)
        {
            if (dir==1)
                this.setCurrentItem(this.getCurrentItem()+1);
            return false;
        }
        else
            return false;

    }


    public void setPaging(boolean isPaging) {
        this.isPaging = isPaging;
    }

    public void setCurrPos(int currPos){
        this.currPos = currPos;
    }
    public void setStartPaging(boolean isStartPaging){
        this.isStartPaging = isStartPaging;
    }
    public interface OnSwipeOutListener {
        public void onSwipeOutAtStart();
        public void onSwipeOutAtEnd();
    }


}


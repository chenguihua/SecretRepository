package com.secretrepository.app.list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by chenguihua on 2016/7/5.
 */
public class SwipeMenuListView extends ListView {
    private static final String TAG = "SwipeMenuListView";

    SlideMode mSlideMode = SlideMode.NONE;

    private float mInitialMotionX;
    private float mInitialMotionY;

    private int mTouchSlop;

    private OnSwipeMenuClickListener mSwipeMenuClickListener;


    public SwipeMenuListView(Context context) {
        super(context);
        init();
    }

    public SwipeMenuListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeMenuListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        setVerticalScrollBarEnabled(false);
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        SwipeMenuListAdapter mAdapter = new SwipeMenuListAdapter(getContext(), adapter);
        super.setAdapter(mAdapter);
    }


    public SlideMode getSlideMode() {
        return mSlideMode;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        boolean intercepted = false;
        float x = ev.getX();
        float y = ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mInitialMotionX = x;
                mInitialMotionY = y;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float deltaX = x - mInitialMotionX;
                float deltaY = y - mInitialMotionY;
                if (mSlideMode == SlideMode.NONE) {
                    if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > mTouchSlop) {
                        intercepted = false;
                        mSlideMode = SlideMode.HORIZONTAL;
                    }
                    if (Math.abs(deltaX) < Math.abs(deltaY) && Math.abs(deltaY) > mTouchSlop) {
                        intercepted = true;
                        mSlideMode = SlideMode.VERTICAL;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                mSlideMode = SlideMode.NONE;
                break;
            }
        }

        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:{
                if (isAnySwipeMenuOpen()) {
                    closeAnySwipeMenu();
                    return false;
                }
                break;
            }
            case MotionEvent.ACTION_UP:
                if (mSlideMode != SlideMode.NONE) {
                    mSlideMode = SlideMode.NONE;
                }
                break;

        }
        return super.onTouchEvent(ev);
    }

    public boolean isAnySwipeMenuOpen() {
        boolean isAnyOpen = false;
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View item = getChildAt(i);
            if (!(item instanceof SwipeItemLayout)) {
                throw new IllegalArgumentException("item of SwipeListView must be SwipeItemLayout!");
            }
            if (((SwipeItemLayout) item).isSwipeMenuOpen()) {
                isAnyOpen = true;
                break;
            }
        }

        return isAnyOpen;
    }

    public void closeAnySwipeMenu() {
        for (int i = 0; i < getChildCount(); i++) {
            View item = getChildAt(i);
            if (!(item instanceof SwipeItemLayout)) {
                throw new IllegalArgumentException("item of SwipeListView must be SwipeItemLayout!");
            }
            ((SwipeItemLayout) item).closeSwipeMenu();
        }
    }

    enum SlideMode {
        VERTICAL, HORIZONTAL, NONE
    }

    public interface OnSwipeMenuClickListener {
        public void onSwipeMenuClick(View view, int position);
    }

    public void setOnSwipeMenuClickListener(OnSwipeMenuClickListener listener) {
        mSwipeMenuClickListener = listener;
    }

    public OnSwipeMenuClickListener getSwipeMenuClickListener() {
        return mSwipeMenuClickListener;
    }

}

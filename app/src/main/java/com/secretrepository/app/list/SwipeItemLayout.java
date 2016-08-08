package com.secretrepository.app.list;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.secretrepository.app.util.DisplayUtil;

/**
 * Created by chenguihua on 2016/7/5.
 */
public class SwipeItemLayout extends LinearLayout {
    public static String TAG = "SwipeItemLayout";
    public static int SWIPE_CLOSE = 0;
    public static int SWIPE_DRAGGING = 1;
    public static int SWIPE_OPEN = 2;

    public final int DEFAULT_MENU_WIDTH = 70;

    private long mId;
    private View mContentView, mMenuView;
    SwipeItemCallbacks mSwipeCallback;

    private ViewDragHelper mDragHelper;

    public int menuWidth;
    public int mStatus;

    public interface SwipeItemCallbacks {
        public void onStatusChange(long mId, int status);
    }

    public SwipeItemLayout(long id, View contentView, View menuView, SwipeItemCallbacks callback) {
        super(contentView.getContext());
        mId = id;
        mContentView = contentView;
        mMenuView = menuView;
        mSwipeCallback = callback;
        mDragHelper = ViewDragHelper.create(this, 15.0f, mCallback);
        initLayout();
    }

    private void initLayout() {
        setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        mContentView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        addView(mContentView);
        menuWidth = DisplayUtil.dp2px(getContext(), DEFAULT_MENU_WIDTH);
        mMenuView.setLayoutParams(new LinearLayout.LayoutParams(menuWidth, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mMenuView);
    }

    public void slideSwipeMenu(int dx) {
        mMenuView.offsetLeftAndRight(dx);
    }

    public void closeSwipeMenu() {
        mDragHelper.smoothSlideViewTo(mContentView, 0, 0);
        ViewCompat.postInvalidateOnAnimation(SwipeItemLayout.this);
    }

    public void cancel() {
        mDragHelper.cancel();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mContentView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            if (left > 0) {
                return 0;
            } else if (left < -1 * menuWidth) {
                return -1 * menuWidth;
            }
            return left;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            slideSwipeMenu(dx);
            invalidate();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (Math.abs(mContentView.getLeft()) < menuWidth / 2) {
                mDragHelper.smoothSlideViewTo(mContentView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(SwipeItemLayout.this);
            } else {
                mDragHelper.smoothSlideViewTo(mContentView, -menuWidth, 0);
                ViewCompat.postInvalidateOnAnimation(SwipeItemLayout.this);
                Log.d("chen", "");
            }
        }

        @Override
        public void onViewDragStateChanged(int state) {
            if (mDragHelper.getViewDragState() == ViewDragHelper.STATE_IDLE) {
                if (mContentView.getLeft() == 0) {
                    mStatus = SWIPE_CLOSE;
                } else {
                    mStatus = SWIPE_OPEN;
                }
            } else {
                mStatus = SWIPE_DRAGGING;
            }

            if (mSwipeCallback != null) {
                mSwipeCallback.onStatusChange(mId, mStatus);
            }
        }
    };

}

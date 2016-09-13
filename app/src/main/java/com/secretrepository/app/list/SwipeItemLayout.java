package com.secretrepository.app.list;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.secretrepository.app.R;


/**
 * Created by chenguihua on 2016/9/6.
 */
public class SwipeItemLayout extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "SwipeItemLayout";

    // Default width of swpie menu
    private final int DEFAULT_MENU_WIDTH = 60;
    private final float AUTO_SCROLL_PERCENT = 0.5f;

    private View mContentView;
    private View mMenuView;

    private ViewDragHelper mDragHelper;

    public SwipeItemLayout(Context context, View contentView) {
        super(context);
        initLayoutParams();
        initViews(contentView);

        mDragHelper = ViewDragHelper.create(this, mCallBack);
    }


    private void initLayoutParams() {
        AbsListView.LayoutParams lp = (AbsListView.LayoutParams) getLayoutParams();
        if (lp == null) {
            lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            setLayoutParams(lp);
        }
        setOrientation(LinearLayout.HORIZONTAL);
    }

    private void initViews(View contentView) {
        //View view = LayoutInflater.from(getContext()).inflate(contentLayoutId, null);
        LayoutParams lpContent = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(contentView, lpContent);

        Button button = new Button(getContext());
        button.setId(R.id.swipe_button_1);
        button.setText("delete");
        button.setBackgroundResource(android.R.color.holo_red_dark);
        button.setOnClickListener(this);
        LayoutParams lpMenu = new LayoutParams(120, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(button, lpMenu);

        mContentView = contentView;
        mMenuView = button;
    }

    public void setContentView(View mContentView) {
        this.mContentView = mContentView;
    }

    public View getContentView() {
        return mContentView;
    }

    public SwipeMenuListView getSwipeParent() {
        View view = (View) getParent();
        if (view instanceof SwipeMenuListView) {
            return (SwipeMenuListView) view;
        }
        return null;
    }

    @Override
    public void onClick(View v) {
        SwipeMenuListView listview = getSwipeParent();
        SwipeMenuListView.OnSwipeMenuClickListener listener = null;
        if (listview != null && (listener = listview.getSwipeMenuClickListener()) != null) {
            int position = listview.getPositionForView(this);
            listener.onSwipeMenuClick(this, position);
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDragHelper.cancel();
                break;
        }

        boolean interceptForDrag = mDragHelper.shouldInterceptTouchEvent(ev);
        return interceptForDrag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = (View) getParent();
                if (view instanceof SwipeMenuListView) {
                    if (((SwipeMenuListView) view).isAnySwipeMenuOpen()) {
                        return false;
                    }
                } else {
                    throw new IllegalArgumentException("list item's parent is not SwipeMenuListView");
                }
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    private int computeValidLeft(int left) {
        if (left > 0) return 0;
        if (left < -mMenuView.getMeasuredWidth()) return -mMenuView.getMeasuredWidth();

        return left;
    }

    private void onContentViewDragged(int dx) {
        mMenuView.offsetLeftAndRight(dx);
    }

    public void closeSwipeMenu() {
        if (mContentView.getLeft() != 0) {
            mDragHelper.smoothSlideViewTo(mContentView, 0, 0);
            ViewCompat.postInvalidateOnAnimation(SwipeItemLayout.this);
        }
    }

    public boolean isSwipeMenuOpen() {
        return mContentView.getLeft() < 0;
    }


    ViewDragHelper.Callback mCallBack = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mContentView;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return getSwipeParent().getSlideMode() == SwipeMenuListView.SlideMode.HORIZONTAL
                    ? computeValidLeft(left) : 0;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            onContentViewDragged(dx);
            invalidate();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            if (Math.abs(mContentView.getLeft()) > mMenuView.getMeasuredWidth() * AUTO_SCROLL_PERCENT) {
                mDragHelper.smoothSlideViewTo(mContentView, -mMenuView.getMeasuredWidth(), 0);
            } else {
                mDragHelper.smoothSlideViewTo(mContentView, 0, 0);
            }
            ViewCompat.postInvalidateOnAnimation(SwipeItemLayout.this);
        }
    };


}

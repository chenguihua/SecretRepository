package com.secretrepository.app.list;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.secretrepository.app.list.SwipeListAdapter.SwipeData;

/**
 * Created by chenguihua on 2016/7/5.
 */
public class SwipeListView extends ListView {

    private float mInitialMotionX;
    private float mInitialMotionY;
    private float mLastMotionX;
    private float mLastMotionY;

    private boolean isListScroll = false;
    private boolean isItemSwipe = false;
    private boolean isItemClick = false;
    private int mActiveItem = -1;
    private int mSwipeTouchSlop;

    public SwipeListView(Context context) {
        super(context);
        init();
    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        final ViewConfiguration configuration = ViewConfiguration.get(getContext());
        mSwipeTouchSlop = configuration.getScaledTouchSlop();
        //Log.d("cgh", "mSwipeTouchSlop : " + mSwipeTouchSlop);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        SwipeListAdapter mAdapter = new SwipeListAdapter(getContext(), adapter);
        super.setAdapter(mAdapter);
    }

    public boolean isSwipeMenuOpenOrDragging() {
        for (int i = 0; i < getAdapter().getCount(); i++) {
            SwipeData data = ((SwipeListAdapter) getAdapter()).getSwipeItem(i);
            if (data.itemStatus == SwipeItemLayout.SWIPE_OPEN) {
                return true;
            }
        }
        return false;
    }

    public void closeAllSwipeMenu() {
        for (int i = 0; i < getChildCount(); i++) {
            ((SwipeItemLayout) getChildAt(i)).closeSwipeMenu();
        }
    }

    public void cancelSwipe() {
        for (int i = 0; i < getChildCount(); i++) {
            ((SwipeItemLayout) getChildAt(i)).cancel();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                final float x = ev.getX();
                final float y = ev.getY();
                mInitialMotionX = mLastMotionX = x;
                mInitialMotionY = mLastMotionY = y;
//                swipe = isSwipeMenuOpenOrDragging();
//                if (swipe) {
//                    return true;
//                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                final float x = ev.getX();
                final float y = ev.getY();
                float dx = Math.abs(x - mLastMotionX);
                float dy = Math.abs(y - mLastMotionY);
                Log.d("cgh", "dx : " + dx);
                Log.d("cgh", "dy : " + dy);
                if (dy > dx && dy > mSwipeTouchSlop) {
                    isItemSwipe = false;
                    isListScroll = true;
                    isItemClick = false;
                    return true;
                }

                mLastMotionX = x;
                mLastMotionY = y;
                break;
            }
            case MotionEvent.ACTION_UP: {
                final float x = ev.getX();
                final float y = ev.getY();
//                if (Math.abs(x - mInitialMotionX) < 5
//                        && Math.abs(y - mInitialMotionY) < 5) {
//                    int position = pointToPosition((int) x, (int) y);
//                    long id = pointToRowId((int) x, (int) y);
//                    View view = getChildAt(position - getFirstVisiblePosition());
//                    performItemClick(view, position, id);
//                }
                break;
            }
        }
        //return super.onInterceptTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = MotionEventCompat.getActionMasked(ev);
        switch (action) {
//            case MotionEvent.ACTION_DOWN: {
//                closeAllSwipeMenu();
//                break;
//            }
        }
        return super.onTouchEvent(ev);
        //return false;
    }
}

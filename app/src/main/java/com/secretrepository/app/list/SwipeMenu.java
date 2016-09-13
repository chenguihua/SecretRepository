package com.secretrepository.app.list;

import android.content.Context;
import android.view.View;
import android.widget.Button;

/**
 * Created by chenguihua on 2016/9/12.
 */
public class SwipeMenu extends Button {
    private static final int DEFAULT_MENU_WIDTH = 60;

    public SwipeMenu(Context context) {
        super(context);
    }

    public static class Builder{
        Context context;
        int width = SwipeMenu.DEFAULT_MENU_WIDTH;
        int backgroundResId;
        String text;
        View.OnClickListener clickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setBackgroundResId(int backgroundResId) {
            this.backgroundResId = backgroundResId;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setClickListener(View.OnClickListener clickListener) {
            this.clickListener = clickListener;
            return this;
        }

        public SwipeMenu create() {
            SwipeMenu swipeMenu = new SwipeMenu(context);
            swipeMenu.setWidth(width);
            swipeMenu.setBackgroundResource(backgroundResId);
            swipeMenu.setText(text);
            return swipeMenu;
        }
    }
}

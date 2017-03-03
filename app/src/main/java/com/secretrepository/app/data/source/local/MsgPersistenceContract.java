package com.secretrepository.app.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by chenguihua on 2017/3/3.
 */

public final class MsgPersistenceContract {
    private MsgPersistenceContract() {

    }
    /* Inner class that defines the table content */
    public static abstract class MsgEntry implements BaseColumns {
        public static final String TABLE_NAME = "LoginMessage";
        public static final String COLUMN_NAME_ENTRY_ID = "entry_id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }

}

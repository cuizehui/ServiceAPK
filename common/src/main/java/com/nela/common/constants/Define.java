package com.nela.common.constants;

import android.net.Uri;

public class Define {

    //same with  Manifest's Provider authorities
    public static final String TABLE_A_AUTHORITY = "table_a_authority";

    public static final class TABLE_A {
        public static final String TABLE_NAME = "table_a";

        public static final String _ID = "_id";
        public static final String NAME = "name";
        public static final String BODY = "body";
        public static final String BEGIN_TIME = "begin_time";
        public static final String END_TIME = "end_time";
        public static final String EXTENDS = "extends";
        public static final Uri CONTENT_URI = Uri.parse("content://" + TABLE_A_AUTHORITY);
        public static final Uri CONTENT_URI_TABLE = Uri.parse("content://" + TABLE_A_AUTHORITY + "/" + TABLE_NAME);
    }
}

package com.nela.module.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Binder;
import android.text.TextUtils;
import android.util.Log;

import com.nela.common.constants.Define;

public class AProvider extends ContentProvider {

    private final static String TAG = "AProvider";
    private static final Uri NOTIFICATION_URI = Uri.parse("content://" + Define.TABLE_A_AUTHORITY);

    private DatabaseHelper mDatabaseHelper;

    private static final UriMatcher sURLMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);

    static {
        sURLMatcher.addURI(Define.TABLE_A_AUTHORITY, Define.TABLE_A.TABLE_NAME, 0);
    }


    @Override
    public boolean onCreate() {
        mDatabaseHelper = DatabaseHelper.getInstance(getContext());
        return false;
    }

    @androidx.annotation.Nullable
    @Override
    public Cursor query(@androidx.annotation.NonNull Uri uri, @androidx.annotation.Nullable String[] projection,
                        @androidx.annotation.Nullable String selection, @androidx.annotation.Nullable String[] selectionArgs,
                        @androidx.annotation.Nullable String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String table = Define.TABLE_A.TABLE_NAME;
        int match = sURLMatcher.match(uri);
        switch (match) {
            case 0:
                qb.setTables(table);
                break;

            default:
                throw new IllegalArgumentException("Unknown URL");
        }

        String orderBy = null;

        if (table == Define.TABLE_A.TABLE_NAME) {
            if (!TextUtils.isEmpty(sortOrder)) {
                orderBy = sortOrder;
            } else {
                orderBy = Define.TABLE_A.BEGIN_TIME;
            }
        } else {
            orderBy = sortOrder;
        }

        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        Cursor ret = qb.query(db, projection, selection, selectionArgs, null,
                null, orderBy);

        if (table == Define.TABLE_A.TABLE_NAME)
            ret.setNotificationUri(getContext().getContentResolver(),
                    NOTIFICATION_URI);
        return ret;
    }

    @androidx.annotation.Nullable
    @Override
    public String getType(@androidx.annotation.NonNull Uri uri) {
        return null;
    }

    @androidx.annotation.Nullable
    @Override
    public Uri insert(@androidx.annotation.NonNull Uri uri, @androidx.annotation.Nullable ContentValues values) {
        final int callerUid = Binder.getCallingUid();
        final String callerPkg = getCallingPackage();
        long token = Binder.clearCallingIdentity();

        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        int match = sURLMatcher.match(uri);
        String table = Define.TABLE_A.TABLE_NAME;
        switch (match) {
            case 0:
                String begintime = values.getAsString(Define.TABLE_A.BEGIN_TIME);
                String endtime = values.getAsString(Define.TABLE_A.END_TIME);
                String body = values.getAsString(Define.TABLE_A.BODY);

                break;
            default:
                return null;
        }
        long rowID = db.insert(table, null, values);
        if (rowID > 0) {
            Uri resultUri = Uri.withAppendedPath(Define.TABLE_A.CONTENT_URI, rowID + "");
            return resultUri;
        } else {
            Log.e(TAG, "insert: failed! " + values.toString());
        }
        return null;
    }

    @Override
    public int delete(@androidx.annotation.NonNull Uri uri, @androidx.annotation.Nullable String selection, @androidx.annotation.Nullable String[] selectionArgs) {

        int count = 0;
        int match = sURLMatcher.match(uri);
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        switch (match) {
            case 0:
                //  deleteSms(where, whereArgs);
                //   count = db.delete(TABLE_RMS, where, whereArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URL");
        }
        return count;
    }

    @Override
    public int update(@androidx.annotation.NonNull Uri uri, @androidx.annotation.Nullable ContentValues values, @androidx.annotation.Nullable String selection, @androidx.annotation.Nullable String[] selectionArgs) {
        return 0;
    }
}

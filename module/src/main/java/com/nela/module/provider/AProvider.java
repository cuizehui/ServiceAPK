//package com.nela.rcsmodule.rcs.provider;
//
//import android.content.ContentProvider;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.UriMatcher;
//import android.database.Cursor;
//import android.database.DatabaseUtils;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteQueryBuilder;
//import android.net.Uri;
//import android.os.Binder;
//import android.provider.BaseColumns;
//import android.provider.Telephony;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.nela.common.constants.Define;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class AProvider extends ContentProvider {
//
//    private final static String TAG = "AProvider";
//    private static final Uri NOTIFICATION_URI = Uri.parse("content://" + Define.TABLEA_AUTHORITY);
//    public static final String TABLE_A = "table_a";
//    private static final String TABLE_WORDS = "words";
//    private RcsDatabaseHelper mDatabaseHelper;
//
//    @Override
//    public int delete(Uri url, String where, String[] whereArgs) {
//        int count = 0;
//        int match = sURLMatcher.match(url);
//        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
//
//        switch (match) {
//            case RMS_LOG:
//                deleteSms(where, whereArgs);
//                count = db.delete(TABLE_RMS, where, whereArgs);
//                break;
//
//            case RMS_LOG_ID:
//                try {
//                    int message_id = Integer.parseInt(url.getPathSegments().get(1));
//                    deleteSms(Rms._ID + "=" + message_id, null);
//                    count = db.delete(TABLE_RMS, Rms._ID + "=" + message_id, null);
//                } catch (Exception e) {
//                    throw new IllegalArgumentException("Bad message id: "
//                            + url.getPathSegments().get(0));
//                }
//                break;
//
//            default:
//                throw new IllegalArgumentException("Unknown URL");
//        }
//        return count;
//    }
//
//    @Override
//    public String getType(Uri url) {
//        return null;
//    }
//
//    @Override
//    public Uri insert(Uri url, ContentValues values) {
//        final int callerUid = Binder.getCallingUid();
//        final String callerPkg = getCallingPackage();
//        long token = Binder.clearCallingIdentity();
//        try {
//            SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
//            int match = sURLMatcher.match(url);
//            String table = TABLE_RMS;
//            switch (match) {
//                case RMS_LOG:
//                    table = TABLE_RMS;
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unknown URL");
//            }
//
//            if (table.equals(TABLE_RMS)) {
//                Long threadId = values.getAsLong(Rms.THREAD_ID);
//                String address = values.getAsString(Rms.ADDRESS);
//                String groupChatId = values.getAsString(Rms.GROUP_CHAT_ID);
//                String body = values.getAsString(Rms.BODY);
//                if (threadId == null || threadId == 0) {
//                    if (!TextUtils.isEmpty(groupChatId)) {
//                        threadId = getOrCreateThreadId(getContext(), groupChatId);
//                        values.put(Rms.THREAD_ID, threadId);
//                    } else if (!TextUtils.isEmpty(address)) {
//                        String addresses[] = address.split(";");
//                        Set<String> recipients = new HashSet<String>();
//                        for (String addr : addresses) {
//                            recipients.add(addr);
//                        }
//                        threadId = getOrCreateThreadId(getContext(), recipients);
//
//                        values.put(Rms.THREAD_ID, threadId);
//                    } else {
//                        throw new IllegalArgumentException("Unknown log type");
//                    }
//                }
//
//                long rowID = db.insert(table, null, values);
//                if (rowID > 0) {
//                    Uri uri = Uri.withAppendedPath(Rms.CONTENT_URI_LOG, rowID + "");
//                    return uri;
//                } else {
//                    Log.e(TAG, "insert: failed! " + values.toString());
//                }
//            }
//        } finally {
//            Binder.restoreCallingIdentity(token);
//        }
//        return null;
//    }
//
//    @Override
//    public boolean onCreate() {
//        mDatabaseHelper = RcsDatabaseHelper.getInstance(getContext());
//        return true;
//    }
//
//    @Override
//    public Cursor query(Uri url, String[] projectionIn, String selection,
//                        String[] selectionArgs, String sort) {
//
//        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
//        String table = TABLE_RMS;
//        int match = sURLMatcher.match(url);
//        switch (match) {
//            case RMS_LOG:
//                qb.setTables(TABLE_RMS);
//                break;
//
//            case RMS_LOG_ID:
//                qb.setTables(TABLE_RMS);
//                qb.appendWhere("(_id = " + url.getPathSegments().get(1) + ")");
//                break;
//
//            default:
//                throw new IllegalArgumentException("Unknown URL");
//        }
//
//        String orderBy = null;
//
//        if (table == TABLE_RMS) {
//            if (!TextUtils.isEmpty(sort)) {
//                orderBy = sort;
//            } else {
//                orderBy = Rms.DEFAULT_SORT_ORDER;
//            }
//        } else {
//            orderBy = sort;
//        }
//
//        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
//        Cursor ret = qb.query(db, projectionIn, selection, selectionArgs, null,
//                null, orderBy);
//
//        if (table == TABLE_RMS)
//            ret.setNotificationUri(getContext().getContentResolver(),
//                    NOTIFICATION_URI);
//        return ret;
//    }
//
//    @Override
//    public int update(Uri url, ContentValues values, String where,
//                      String[] whereArgs) {
//        int count = 0;
//        String table = TABLE_RMS;
//        String extraWhere = null;
//        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
//
//        int match = sURLMatcher.match(url);
//        switch (match) {
//            case RMS_LOG:
//                break;
//            case RMS_LOG_ID:
//                extraWhere = "_id=" + url.getPathSegments().get(1);
//                break;
//            default:
//                throw new IllegalArgumentException("Unknown URL");
//        }
//
//        where = DatabaseUtils.concatenateWhere(where, extraWhere);
//        count = db.update(table, values, where, whereArgs);
//
//        return count;
//    }
//
//    private void deleteSms(String rmshereClause, String[] rmsWhereArgs) {
//        // 查询所有符合条件的sms id
//        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
//        Cursor cursor = db.query(true, TABLE_RMS, new String[]{Rms.SMS_ID}, rmshereClause, rmsWhereArgs, null, null, null, null);
//        if (cursor != null) {
//            try {
//                while (cursor.moveToNext()) {
//                    long smsId = cursor.getLong(0);
//                    getContext().getContentResolver().delete(Telephony.Sms.CONTENT_URI, Telephony.Sms._ID + "=" + smsId, null);
//                }
//            } finally {
//                cursor.close();
//            }
//        }
//    }
//
//    private static final int RMS_LOG = 0;
//    private static final int RMS_LOG_ID = 1;
//
//    private static final UriMatcher sURLMatcher = new UriMatcher(
//            UriMatcher.NO_MATCH);
//
//    static {
//        sURLMatcher.addURI(RmsDefine.RMS_AUTHORITY, "rms_log", RMS_LOG);
//        sURLMatcher.addURI(RmsDefine.RMS_AUTHORITY, "rms_log/#", RMS_LOG_ID);
//    }
//
//    /**
//     * 将系统函数拷贝过来
//     */
//
//    private static final String[] ID_PROJECTION = {BaseColumns._ID};
//    private static final Uri THREAD_ID_CONTENT_URI = Uri.parse(
//            "content://mms-sms/threadID");
//
//    private long getOrCreateThreadId(Context context, String recipient) {
//        Set<String> recipients = new HashSet<String>();
//
//        recipients.add(recipient);
//        return getOrCreateThreadId(context, recipients);
//    }
//
//    private long getOrCreateThreadId(
//            Context context, Set<String> recipients) {
//        Uri.Builder uriBuilder = THREAD_ID_CONTENT_URI.buildUpon();
//
//        for (String recipient : recipients) {
//            uriBuilder.appendQueryParameter("recipient", recipient);
//        }
//
//        Uri uri = uriBuilder.build();
//
//        Cursor cursor = context.getContentResolver().query(
//                uri, ID_PROJECTION, null, null, null);
//        if (cursor != null) {
//            try {
//                if (cursor.moveToFirst()) {
//                    return cursor.getLong(0);
//                } else {
//                }
//            } finally {
//                cursor.close();
//            }
//        }
//
//        throw new IllegalArgumentException("Unable to find or allocate a thread ID.");
//    }
//}
/**/
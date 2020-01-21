/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nela.module.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nela.common.constants.Define;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static DatabaseHelper sInstance = null;

    static final String DATABASE_NAME = "apkservice.db";
    static final int DATABASE_VERSION = 2;
    private final Context mContext;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }


    /**
     * Return a singleton helper for the combined MMS and SMS
     * database.
     */
    /* package */
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context);
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        createTables(db);
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Define.TABLE_A.TABLE_NAME + "(" +
                Define.TABLE_A._ID + " INTEGER PRIMARY KEY ," +
                Define.TABLE_A.NAME + " TEXT ," +
                Define.TABLE_A.BEGIN_TIME + " INTEGER DEFAULT 0," +
                Define.TABLE_A.END_TIME + " INTEGER DEFAULT 0," +
                Define.TABLE_A.BODY + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int currentVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion
                + " to " + currentVersion + ".");
        switch (oldVersion) {
            case 1:
                if (currentVersion <= 1) {
                    return;
                }
                db.beginTransaction();
                try {
                    upgradeDatabaseToVersion2(db);
                    db.setTransactionSuccessful();
                } catch (Throwable ex) {
                    Log.e(TAG, ex.getMessage(), ex);
                    break;
                } finally {
                    db.endTransaction();
                }
        }
    }

    private void upgradeDatabaseToVersion2(SQLiteDatabase db) {
        Log.d(TAG, "upgradeDatabaseToVersion2");
        try {
            db.execSQL("ALTER TABLE " + Define.TABLE_A.TABLE_NAME + " ADD COLUMN " + Define.TABLE_A.EXTENDS + " TEXT");
        } catch (SQLiteException e) {
            Log.e(TAG, "upgradeDatabaseToVersion2" + e);
        }
    }
}

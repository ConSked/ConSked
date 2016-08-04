package com.emailxl.consked.internal_db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.emailxl.consked.utils.AppConstants;

/**
 * The ConSked Content Provider
 *
 * @author ECG
 */

public class ConSkedProvider extends ContentProvider {
    // database table names
    public static final String EXPO_TABLE = "expo";
    public static final String SHIFTASSIGNMENT_TABLE = "shiftassignment";
    public static final String SHIFTSTATUS_TABLE = "shiftstatus";
    public static final String STATIONJOB_TABLE = "stationjob";
    public static final String WORKER_TABLE = "worker";
    // fields for my content provider
    static final String PROVIDER_NAME = AppConstants.AUTHORITY;
    // for MIME types
    static final String LIST = "vnd.android.cursor/";
    // database information
    static final String DATABASE_NAME = "ConSked";
    static final int DATABASE_VERSION = 1;
    // database field names;
    static final String IDINT = "idInt";
    static final String EXPOIDEXT = "expoIdExt";
    static final String STARTTIME = "startTime";
    static final String STOPTIME = "stopTime";
    static final String TITLE = "title";
    static final String SHIFTSTATUSIDEXT = "shiftstatusIdExt";
    static final String WORKERIDEXT = "workerIdExt";
    static final String JOBIDEXT = "jobIdExt";
    static final String STATIONIDEXT = "stationIdExt";
    static final String STATUSTYPE = "statusType";
    static final String STATUSTIME = "statusTime";
    static final String STATIONTITLE = "stationTitle";
    static final String FIRSTNAME = "firstName";
    static final String LASTNAME = "lastName";
    static final String AUTHROLE = "authrole";

    // integer values used in content URI
    static final int EXPO_LIST = 1;
    static final int SHIFTASSIGNMENT_LIST = 2;
    static final int SHIFTSTATUS_LIST = 3;
    static final int STATIONJOB_LIST = 4;
    static final int WORKER_LIST = 5;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(PROVIDER_NAME, EXPO_TABLE, EXPO_LIST);
        uriMatcher.addURI(PROVIDER_NAME, SHIFTASSIGNMENT_TABLE, SHIFTASSIGNMENT_LIST);
        uriMatcher.addURI(PROVIDER_NAME, SHIFTSTATUS_TABLE, SHIFTSTATUS_LIST);
        uriMatcher.addURI(PROVIDER_NAME, STATIONJOB_TABLE, STATIONJOB_LIST);
        uriMatcher.addURI(PROVIDER_NAME, WORKER_TABLE, WORKER_LIST);
    }

    private DBHelper dbHelper;

    @Override
    public boolean onCreate() {

        Context context = getContext();
        dbHelper = new DBHelper(context);

        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        int match = uriMatcher.match(uri);
        switch (match) {
            case EXPO_LIST:
                queryBuilder.setTables(EXPO_TABLE);
                break;
            case SHIFTASSIGNMENT_LIST:
                queryBuilder.setTables(SHIFTASSIGNMENT_TABLE);
                break;
            case SHIFTSTATUS_LIST:
                queryBuilder.setTables(SHIFTSTATUS_TABLE);
                break;
            case STATIONJOB_LIST:
                queryBuilder.setTables(STATIONJOB_TABLE);
                break;
            case WORKER_LIST:
                queryBuilder.setTables(WORKER_TABLE);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        Context context = getContext();

        if (context != null) {
            cursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {

        int match = uriMatcher.match(uri);
        switch (match) {
            case EXPO_LIST:
                return LIST + PROVIDER_NAME + "." + EXPO_TABLE;
            case SHIFTASSIGNMENT_LIST:
                return LIST + PROVIDER_NAME + "." + SHIFTASSIGNMENT_TABLE;
            case SHIFTSTATUS_LIST:
                return LIST + PROVIDER_NAME + "." + SHIFTSTATUS_TABLE;
            case STATIONJOB_LIST:
                return LIST + PROVIDER_NAME + "." + STATIONJOB_TABLE;
            case WORKER_LIST:
                return LIST + PROVIDER_NAME + "." + WORKER_TABLE;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        long row;
        String url = AppConstants.SCHEME + "://" + PROVIDER_NAME;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        switch (match) {
            case EXPO_LIST:
                row = db.insert(EXPO_TABLE, "", values);
                url += "/" + EXPO_TABLE;
                break;
            case SHIFTASSIGNMENT_LIST:
                row = db.insert(SHIFTASSIGNMENT_TABLE, "", values);
                url += "/" + SHIFTASSIGNMENT_TABLE;
                break;
            case SHIFTSTATUS_LIST:
                row = db.insert(SHIFTSTATUS_TABLE, "", values);
                url += "/" + SHIFTSTATUS_TABLE;
                break;
            case STATIONJOB_LIST:
                row = db.insert(STATIONJOB_TABLE, "", values);
                url += "/" + STATIONJOB_TABLE;
                break;
            case WORKER_LIST:
                row = db.insert(WORKER_TABLE, "", values);
                url += "/" + WORKER_TABLE;
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }

        if (row > 0) {
            return ContentUris.withAppendedId(Uri.parse(url), row);
        }
        throw new SQLException("Fail to add a new record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        int count;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        switch (match) {
            case EXPO_LIST:
                count = db.delete(EXPO_TABLE, selection, selectionArgs);
                break;
            case SHIFTASSIGNMENT_LIST:
                count = db.delete(SHIFTASSIGNMENT_TABLE, selection, selectionArgs);
                break;
            case SHIFTSTATUS_LIST:
                count = db.delete(SHIFTSTATUS_TABLE, selection, selectionArgs);
                break;
            case STATIONJOB_LIST:
                count = db.delete(STATIONJOB_TABLE, selection, selectionArgs);
                break;
            case WORKER_LIST:
                count = db.delete(WORKER_TABLE, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        int count;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        switch (match) {
            case EXPO_LIST:
                count = db.update(EXPO_TABLE, values, selection, selectionArgs);
                break;
            case SHIFTASSIGNMENT_LIST:
                count = db.update(SHIFTASSIGNMENT_TABLE, values, selection, selectionArgs);
                break;
            case SHIFTSTATUS_LIST:
                count = db.update(SHIFTSTATUS_TABLE, values, selection, selectionArgs);
                break;
            case STATIONJOB_LIST:
                count = db.update(STATIONJOB_TABLE, values, selection, selectionArgs);
                break;
            case WORKER_LIST:
                count = db.update(WORKER_TABLE, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI " + uri);
        }
        return count;
    }

    private static final class DBHelper extends SQLiteOpenHelper {

        private static final String CREATE_EXPO_TABLE = "CREATE TABLE " + EXPO_TABLE
                + " (" + IDINT + " INTEGER PRIMARY KEY, "
                + EXPOIDEXT + " INTEGER, "
                + STARTTIME + " TEXT, "
                + STOPTIME + " TEXT, "
                + TITLE + " TEXT)";
        private static final String CREATE_SHIFTASSIGNMENT_TABLE = "CREATE TABLE " + SHIFTASSIGNMENT_TABLE
                + " (" + IDINT + " INTEGER PRIMARY KEY, "
                + WORKERIDEXT + " INTEGER, "
                + JOBIDEXT + " INTEGER, "
                + STATIONIDEXT + " INTEGER, "
                + EXPOIDEXT + " INTEGER)";
        private static final String CREATE_SHIFTSTATUS_TABLE = "CREATE TABLE " + SHIFTSTATUS_TABLE
                + " (" + IDINT + " INTEGER PRIMARY KEY, "
                + SHIFTSTATUSIDEXT + " INTEGER, "
                + WORKERIDEXT + " INTEGER, "
                + STATIONIDEXT + " INTEGER, "
                + EXPOIDEXT + " INTEGER, "
                + STATUSTYPE + " TEXT, "
                + STATUSTIME + " TEXT)";
        private static final String CREATE_STATIONJOB_TABLE = "CREATE TABLE " + STATIONJOB_TABLE
                + " (" + IDINT + " INTEGER PRIMARY KEY, "
                + STATIONIDEXT + " INTEGER, "
                + EXPOIDEXT + " INTEGER, "
                + STARTTIME + " TEXT, "
                + STOPTIME + " TEXT, "
                + STATIONTITLE + " TEXT)";
        private static final String CREATE_WORKER_TABLE = "CREATE TABLE " + WORKER_TABLE
                + " (" + IDINT + " INTEGER PRIMARY KEY, "
                + WORKERIDEXT + " INTEGER, "
                + FIRSTNAME + " TEXT, "
                + LASTNAME + " TEXT, "
                + AUTHROLE + " TEXT)";

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_EXPO_TABLE);
            db.execSQL(CREATE_SHIFTASSIGNMENT_TABLE);
            db.execSQL(CREATE_SHIFTSTATUS_TABLE);
            db.execSQL(CREATE_STATIONJOB_TABLE);
            db.execSQL(CREATE_WORKER_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + EXPO_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + SHIFTASSIGNMENT_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + SHIFTSTATUS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + STATIONJOB_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + WORKER_TABLE);

            onCreate(db);
        }
    }
}

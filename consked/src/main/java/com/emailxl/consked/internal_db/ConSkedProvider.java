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
    public static final String JOB_TABLE = "job";
    public static final String SHIFTSTATUS_TABLE = "shiftstatus";
    public static final String STATION_TABLE = "station";
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
    static final String IDEXT = "idExt";
    static final String JSON = "json";
    static final String WORKERIDEXT = "workerIdExt";
    static final String STATIONIDEXT = "stationIdExt";
    static final String EXPOIDEXT = "expoIdExt";
    static final String STATUSTYPE = "statusType";
    static final String STATUSTIME = "statusTime";

    // integer values used in content URI
    static final int EXPO_LIST = 1;
    static final int JOB_LIST = 2;
    static final int SHIFTSTATUS_LIST = 3;
    static final int STATION_LIST = 4;
    static final int WORKER_LIST = 5;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(PROVIDER_NAME, EXPO_TABLE, EXPO_LIST);
        uriMatcher.addURI(PROVIDER_NAME, JOB_TABLE, JOB_LIST);
        uriMatcher.addURI(PROVIDER_NAME, SHIFTSTATUS_TABLE, SHIFTSTATUS_LIST);
        uriMatcher.addURI(PROVIDER_NAME, STATION_TABLE, STATION_LIST);
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
            case JOB_LIST:
                queryBuilder.setTables(JOB_TABLE);
                break;
            case SHIFTSTATUS_LIST:
                queryBuilder.setTables(SHIFTSTATUS_TABLE);
                break;
            case STATION_LIST:
                queryBuilder.setTables(STATION_TABLE);
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
            case JOB_LIST:
                return LIST + PROVIDER_NAME + "." + JOB_TABLE;
            case SHIFTSTATUS_LIST:
                return LIST + PROVIDER_NAME + "." + SHIFTSTATUS_TABLE;
            case STATION_LIST:
                return LIST + PROVIDER_NAME + "." + STATION_TABLE;
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
            case JOB_LIST:
                row = db.insert(JOB_TABLE, "", values);
                url += "/" + JOB_TABLE;
                break;
            case SHIFTSTATUS_LIST:
                row = db.insert(SHIFTSTATUS_TABLE, "", values);
                url += "/" + SHIFTSTATUS_TABLE;
                break;
            case STATION_LIST:
                row = db.insert(STATION_TABLE, "", values);
                url += "/" + STATION_TABLE;
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
            case JOB_LIST:
                count = db.delete(JOB_TABLE, selection, selectionArgs);
                break;
            case SHIFTSTATUS_LIST:
                count = db.delete(SHIFTSTATUS_TABLE, selection, selectionArgs);
                break;
            case STATION_LIST:
                count = db.delete(STATION_TABLE, selection, selectionArgs);
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
            case JOB_LIST:
                count = db.update(JOB_TABLE, values, selection, selectionArgs);
                break;
            case SHIFTSTATUS_LIST:
                count = db.update(SHIFTSTATUS_TABLE, values, selection, selectionArgs);
                break;
            case STATION_LIST:
                count = db.update(STATION_TABLE, values, selection, selectionArgs);
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
                + IDEXT + " INTEGER, "
                + JSON + " TEXT)";
        private static final String CREATE_JOB_TABLE = "CREATE TABLE " + JOB_TABLE
                + " (" + IDINT + " INTEGER PRIMARY KEY, "
                + IDEXT + " INTEGER, "
                + JSON + " TEXT)";
        private static final String CREATE_SHIFTSTATUS_TABLE = "CREATE TABLE " + SHIFTSTATUS_TABLE
                + " (" + IDINT + " INTEGER PRIMARY KEY, "
                + IDEXT + " INTEGER, "
                + WORKERIDEXT + " INTEGER "
                + STATIONIDEXT + " INTEGER "
                + EXPOIDEXT + " INTEGER "
                + STATUSTYPE + " TEXT "
                + STATUSTIME + " TEXT)";
        private static final String CREATE_STATION_TABLE = "CREATE TABLE " + STATION_TABLE
                + " (" + IDINT + " INTEGER PRIMARY KEY, "
                + IDEXT + " INTEGER, "
                + JSON + " TEXT)";
        private static final String CREATE_WORKER_TABLE = "CREATE TABLE " + WORKER_TABLE
                + " (" + IDINT + " INTEGER PRIMARY KEY, "
                + IDEXT + " INTEGER, "
                + JSON + " TEXT)";

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_EXPO_TABLE);
            db.execSQL(CREATE_JOB_TABLE);
            db.execSQL(CREATE_SHIFTSTATUS_TABLE);
            db.execSQL(CREATE_STATION_TABLE);
            db.execSQL(CREATE_WORKER_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + EXPO_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + JOB_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + SHIFTSTATUS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + STATION_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + WORKER_TABLE);

            onCreate(db);
        }
    }
}

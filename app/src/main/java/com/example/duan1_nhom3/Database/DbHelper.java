package com.example.duan1_nhom3.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{

    private static DbHelper dbInstance;

    public static synchronized DbHelper getDbInstance(Context ctx){
        if (dbInstance == null) dbInstance = new DbHelper(ctx);
        return dbInstance;
    }

    public DbHelper(@Nullable Context context) {
        super(context, "MiniBar", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng FOOD
        db.execSQL("CREATE TABLE IF NOT EXISTS FOOD(" +
                "FOOD_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FOOD_NAME TEXT," +
                "FOOD_PRICE DOUBLE," +
                "FOOD_TYPE INTEGER," +
                "FOOD_NUMBER INTEGER," +
                "FOOD_STATUS INTEGER," +
                "FOOD_IMAGE BLOB)");

        // Tạo bảng BOARD
        db.execSQL("CREATE TABLE IF NOT EXISTS BOARD(" +
                "BOARD_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "BOARD_NUMBER INTEGER," +
                "BOARD_STATUS INTEGER)");

        // Tạo bảng BILL
        db.execSQL("CREATE TABLE IF NOT EXISTS BILL(" +
                "BILL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "BILL_DATE TEXT," +
                "BOARD_ID INTEGER REFERENCES BOARD(BOARD_ID))");

        // Tạo bảng FODOBOARD
        db.execSQL("CREATE TABLE IF NOT EXISTS FOODBOARD(" +
                "FOODBOARD_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FOOD_ID INTEGER REFERENCES FOOD(FOOD_ID)," +
                "BOARD_ID INTEGER REFERENCES BOARD(BOARD_ID))");

        // Tạo bảng STAFF
        db.execSQL("CREATE TABLE IF NOT EXISTS STAFF(" +
                "STAFF_ID PRIMARY KEY AUTOINCREMENT," +
                "STAFF_NAME TEXT," +
                "STAFF_SDT TEXT," +
                "STAFF_BIRTH TEXT," +
                "STAFF_MONEY DOUBLE," +
                "STAFF_CALENDAR TEXT," +
                "STAFF_TIME INTEGER," +
                "STAFF_PASSWORD TEXT," +
                "STAFF_ROLE INTEGER)," +
                "STAFF_IMAGE BLOB");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS FOOD");
            db.execSQL("DROP TABLE IF EXISTS BOARD");
            db.execSQL("DROP TABLE IF EXISTS BILL");
            db.execSQL("DROP TABLE IF EXISTS STAFF");
            db.execSQL("DROP TABLE IF EXISTS BOARDFOOD");
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}

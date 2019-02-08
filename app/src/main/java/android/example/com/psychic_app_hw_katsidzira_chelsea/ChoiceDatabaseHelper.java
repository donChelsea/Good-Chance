package android.example.com.psychic_app_hw_katsidzira_chelsea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChoiceDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "choices.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "choices";
    private static final String RIGHT_COLUMN = "right_choices";
    private static final String WRONG_COLUMN = "wrong_choices";
    private static final String TOTAL_COLUMN = "total";
    private static ChoiceDatabaseHelper instance;

    public static ChoiceDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ChoiceDatabaseHelper(context);
        }
        return instance;
    }


    public ChoiceDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "right_choices INTEGER, wrong_choices INTEGER, total INTEGER);");
        Log.d("DATABASE HELPER", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(int startValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RIGHT_COLUMN, startValue);
        contentValues.put(WRONG_COLUMN, startValue);
        contentValues.put(TOTAL_COLUMN, startValue);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public void updateChoices(int point) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RIGHT_COLUMN, point);
        db.update(TABLE_NAME, contentValues, RIGHT_COLUMN, null);
        db.close();
    }

    public void queryChoices() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] cols = new String[] {RIGHT_COLUMN, WRONG_COLUMN, TOTAL_COLUMN};
        String selection = RIGHT_COLUMN + " = ?";
        String [] selectionargs = {"0"};
        Cursor cursor = db.query(TABLE_NAME, cols, null, null, null, null, null);
//        Log.i("db helper", "count of children " + cursor.getCount());
        if (cursor.getCount() > 0) {
            int rightCol = cursor.getColumnIndex(RIGHT_COLUMN);
            int wrongCol = cursor.getColumnIndex(WRONG_COLUMN);
            int totalCol = cursor.getColumnIndex(TOTAL_COLUMN);
            while (cursor.moveToNext()) {
                int right = cursor.getInt(rightCol);
                int wrong = cursor.getInt(wrongCol);
                int total = cursor.getInt(totalCol);
                Log.i("db helper", right + " " + wrong + " " + total);
            }
        }
        cursor.close();
        db.close();
    }
//
//    public void updateWrong(int point) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(WRONG_COLUMN, point);
//        contentValues.put(TOTAL_COLUMN, Math.abs(point));
//        db.update(TABLE_NAME, contentValues, WRONG_COLUMN, null);
//    }
}

package android.example.com.psychic_app_hw_katsidzira_chelsea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.example.com.psychic_app_hw_katsidzira_chelsea.model.Choice;
import android.util.Log;

public class ChoiceDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "choices.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "choices";
    private static final String COL_1 = "right_choices";
    private static final String COL_2 = "wrong_choices";
    private static final String COL_3 = "total";
    Context context;


    public ChoiceDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "right_choices INTEGER, wrong_choices INTEGER, total INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(int startValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, startValue);
        contentValues.put(COL_2, startValue);
        contentValues.put(COL_3, startValue);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

//    public void updateChoices(int point) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1, point);
//        db.update(TABLE_NAME, contentValues, COL_1, null);
//        db.close();
//    }
//
//    public void updateWrong(int point) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_2, point);
//        contentValues.put(COL_3, Math.abs(point));
//        db.update(TABLE_NAME, contentValues, COL_2, null);
//    }
}

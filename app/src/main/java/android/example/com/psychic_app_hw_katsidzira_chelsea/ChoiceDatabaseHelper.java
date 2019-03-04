package android.example.com.psychic_app_hw_katsidzira_chelsea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ChoiceDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "ChoiceDatabaseHelper";
    private static final String DATABASE_NAME = "choices.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "choices";
    private static final String RIGHT_CHOICES = "right_choices";
    private static final String ID = "_id";
    private static final String SCORE = "score";
    private static ChoiceDatabaseHelper instance;

    public static ChoiceDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ChoiceDatabaseHelper(context);
        }
        return instance;
    }


    public ChoiceDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "right_choices INTEGER);");
        Log.d(TAG, "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addScore(int score) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RIGHT_CHOICES, score);
        Log.d(TAG, "added score " + score);
        long newRowID = db.insert(TABLE_NAME, null, contentValues);
    }

    public int getCorrectAnswers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = ChoiceDatabaseHelper.RIGHT_CHOICES + " = ?";
        String[] selectionArgs = { "1" };
        String sortOrder = ChoiceDatabaseHelper.ID + " DESC";
        Cursor cursor = db.query(TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        List<Integer> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            int itemId = cursor.getInt(cursor.getColumnIndex(ID));
            itemIds.add(itemId);
        }
        cursor.close();
        return itemIds.size();
    }

    public int getAllAnswers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_NAME, null);
        List<String> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(cursor.getColumnIndex(RIGHT_CHOICES));
            itemIds.add(itemId);
        }
        cursor.close();
        return itemIds.size();
    }
}

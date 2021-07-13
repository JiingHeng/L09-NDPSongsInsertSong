package sg.edu.rp.c346.id20013327.l09_ndpsongsinsertsong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simplesongs.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SONG = "Songs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "_title";
    private static final String COLUMN_SINGER = "_singer";
    private static final String COLUMN_YEAR = "_year";
    private static final String COLUMN_STAR = "_star";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGER + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_STAR + " INTEGER)";
        db.execSQL(createSongTableSql);

        Log.i("info", "created table");

        for(int i = 0; i < 4; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, "Data number " + i);
            values.put(COLUMN_TITLE, "Our Singapore");
            values.put(COLUMN_SINGER, "JJ Lin/Dick Lee");
            values.put(COLUMN_YEAR, "2015");
            values.put(COLUMN_STAR, "5");
            db.insert(TABLE_SONG, null, values);
        }
        Log.i("info", "dummy records inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);
    }

    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        String selectQuery = "Select " + COLUMN_ID + ","
                + COLUMN_TITLE + ","
                + COLUMN_SINGER + ","
                + COLUMN_YEAR + ","
                + COLUMN_STAR + " FROM " + TABLE_SONG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(title, singers, year, stars);
                song.setId(id);
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }

    public int updateSong(Song data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGER, data.getSingers());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STAR, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }

    public long insertSong(String title, String singers, int year, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGER, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STAR, stars);
        long result = db.insert(TABLE_SONG, null, values);
        db.close();
        Log.d("SQL Insert", "ID:"+ result);
        return result;
    }

}

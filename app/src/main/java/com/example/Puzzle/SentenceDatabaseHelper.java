package com.example.lab2_1089635;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Random;

public class SentenceDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sentences.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Sentences";
    private static final String COLUMN_SENTENCE = "sentence";

    public SentenceDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_SENTENCE + " TEXT)";
        db.execSQL(createTable); //things to execute in query

        // Insert six sentences initially
        insertInitialSentences(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private void insertInitialSentences(SQLiteDatabase db) {
        insertSentence(db, "The quick brown fox jumps over the lazy dog.");
        insertSentence(db, "Learning Android development is fun and challenging.");
        insertSentence(db, "Swipe and touch to solve the puzzle.");
        insertSentence(db, "Persistence leads to success.");
        insertSentence(db, "Coding skills grow with practice.");
        insertSentence(db, "Keep calm and code on.");
    }
    public void insertSentence(SQLiteDatabase db, String sentence) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_SENTENCE, sentence);
        db.insert(TABLE_NAME, null, values);
    }
    public void insertSentence(String sentence) {
        SQLiteDatabase db = getWritableDatabase();
        insertSentence(db, sentence);
        db.close();
    }

    public String getRandomSentence() {
        ArrayList<String> sentences = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_SENTENCE + " FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            sentences.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        if (sentences.size() == 0) return "";
        int idx = new Random().nextInt(sentences.size());
        return sentences.get(idx);
    }
}
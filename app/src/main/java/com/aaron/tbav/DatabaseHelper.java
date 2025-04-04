package com.aaron.tbav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "game.db";
    private static final int DATABASE_VERSION = 2;

    // Player stats table
    public static final String TABLE_PLAYER_STATS = "player_stats";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CURRENT_HEALTH = "current_health";
    public static final String COLUMN_MAX_HEALTH = "max_health";
    public static final String COLUMN_ATTACK = "attack";
    public static final String COLUMN_DEFENCE = "defence";
    public static final String COLUMN_SPEED = "speed";
    public static final String COLUMN_INTELLIGENCE = "intelligence";
    public static final String COLUMN_POTIONS = "potions";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create player stats table
        String createTable = "CREATE TABLE " + TABLE_PLAYER_STATS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_CURRENT_HEALTH + " INTEGER, "
                + COLUMN_MAX_HEALTH + " INTEGER, "
                + COLUMN_ATTACK + " INTEGER, "
                + COLUMN_DEFENCE + " INTEGER, "
                + COLUMN_SPEED + " INTEGER, "
                + COLUMN_INTELLIGENCE + " INTEGER, "
                + COLUMN_POTIONS + " INTEGER"
                + ")";
        db.execSQL(createTable);
    }

    @Override
    // Update stats--------------------------------------------------------------------------------------
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_STATS);
        onCreate(db);
    }

    // Update methods
    public void updatePlayerStat(String column, int newValue, int playerId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column, newValue);

        // Update the player's stat for the specific player ID
        db.update(TABLE_PLAYER_STATS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(playerId)});
        db.close();
    }

    //Insert-----------------------------------------------------------------------------------------------
    public void insertPlayerStats(int currentHealth, int maxHealth, int attack, int defence, int speed, int intelligence, int potions) {
        // Use try-with-resources to ensure database is closed properly
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_CURRENT_HEALTH, currentHealth);
            values.put(COLUMN_MAX_HEALTH, maxHealth);
            values.put(COLUMN_ATTACK, attack);
            values.put(COLUMN_DEFENCE, defence);
            values.put(COLUMN_SPEED, speed);
            values.put(COLUMN_INTELLIGENCE, intelligence);
            values.put(COLUMN_POTIONS, potions);

            // Insert the new row
            long newRowId = db.insert(TABLE_PLAYER_STATS, null, values);

            // Optionally, check if the insert was successful
            if (newRowId == -1) {
                Log.e("DatabaseHelper", "Error inserting player stats");
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error while inserting player stats", e);
        }
    }

    // Retrieve player stats------------------------------------------------------------------------------------
    public Cursor getPlayerStats() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_PLAYER_STATS, null, null, null, null, null, null);
    }

    // Get methods
    public int getPlayerStat(String column) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLAYER_STATS, new String[]{column}, null, null, null, null, null);

        int value = 0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                value = cursor.getInt(0); // 0 is the index of the first column
            }
            cursor.close(); // Don't forget to close the cursor
        }

        return value;
    }

    public int getPlayerCurrentHealth() {
        return getPlayerStat(COLUMN_CURRENT_HEALTH);
    }

    public int getPlayerAttack() {
        return getPlayerStat(COLUMN_ATTACK);
    }

    public int getPlayerDefence() {
        return getPlayerStat(COLUMN_DEFENCE);
    }

    public int getPlayerSpeed() {
        return getPlayerStat(COLUMN_SPEED);
    }

    public int getPlayerIntelligence() {
        return getPlayerStat(COLUMN_INTELLIGENCE);
    }

    public int getPlayerPotions() {
        return getPlayerStat(COLUMN_POTIONS);
    }
}

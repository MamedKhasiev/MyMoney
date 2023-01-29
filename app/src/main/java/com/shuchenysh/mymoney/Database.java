package com.shuchenysh.mymoney;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database instance = null;
    private static final String DB_NAME = "money.db";

    public static Database getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application, Database.class, DB_NAME).build();
        }
        return instance;
    }

    public abstract Dao dao();

}

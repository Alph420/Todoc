package com.cleanup.todoc.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.Random;

/**
 * Created by Alph4 le 07/05/2020.
 * Projet: Todoc
 **/
@Database(entities = {Project.class, Task.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    private static volatile TodocDatabase INSTANCE;

    // --- INSTANCE ---
    public static TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Random rnd = new Random();
                int color1 = Color.argb(255, rnd.nextInt(256 - 0), rnd.nextInt(256 - 0), rnd.nextInt(256 - 0));

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("name", "Project test");
                contentValues.put("color", color1);

                db.insert("Project", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }
}

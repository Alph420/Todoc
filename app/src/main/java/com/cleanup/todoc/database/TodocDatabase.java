package com.cleanup.todoc.database;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
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

    // --- DAO ---
    public abstract ProjectDao projetDao();

    public abstract TaskDao taskDao();

    // --- INSTANCE ---
    public static TodocDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
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
                int color1 = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                int color2 = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                int color3 = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

                ContentValues projectTartampion = new ContentValues();
                projectTartampion.put("id", 1);
                projectTartampion.put("name", "Projet Tartampion");
                projectTartampion.put("color", color1);

                ContentValues projectLucidia = new ContentValues();
                projectLucidia.put("id", 2);
                projectLucidia.put("name", "Projet Lucidia");
                projectLucidia.put("color", color2);

                ContentValues projectCircus = new ContentValues();
                projectCircus.put("id", 3);
                projectCircus.put("name", "Projet Circus");
                projectCircus.put("color", color3);


                db.insert("Project", OnConflictStrategy.IGNORE, projectTartampion);
                db.insert("Project", OnConflictStrategy.IGNORE, projectLucidia);
                db.insert("Project", OnConflictStrategy.IGNORE, projectCircus);
            }
        };
    }
}

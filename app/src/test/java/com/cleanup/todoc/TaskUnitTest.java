package com.cleanup.todoc;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Unit tests for tasks
 *
 * @author Gaëtan HERFRAY
 */
@RunWith(AndroidJUnit4.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class TaskUnitTest {
    private TodocDatabase mDataBase;
    private ProjectDao mProjectDao;
    private TaskDao mTaskDao;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        mDataBase = Room.inMemoryDatabaseBuilder(context, TodocDatabase.class)
                .allowMainThreadQueries()
                .addCallback(prepopulateDatabase())
                .build();

        mProjectDao = mDataBase.projetDao();
        mTaskDao = mDataBase.taskDao();
    }

    @After
    public void closeDb() {
        mDataBase.close();
    }


    private static RoomDatabase.Callback prepopulateDatabase() {
        return new RoomDatabase.Callback() {

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


    @Test

    public void test_projects() {
        final Task task1 = new Task(0, 1, "task 1", new Date().getTime());
        final Task task2 = new Task(1, 2, "task 2", new Date().getTime());
        final Task task3 = new Task(2, 3, "task 3", new Date().getTime());
        final Task task4 = new Task(3, 4, "task 4", new Date().getTime());


        assertEquals("Projet Tartampion", mProjectDao.getProject(task1.getProjectId()).getName());
        assertEquals("Projet Lucidia", mProjectDao.getProject(task2.getProjectId()).getName());
        assertEquals("Projet Circus", mProjectDao.getProject(task3.getProjectId()).getName());
        assertNull(mProjectDao.getProject(task4.getProjectId()));


    }

    @Test
    public void test_az_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskAZComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task2);
    }

    @Test
    public void test_za_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskZAComparator());

        assertSame(tasks.get(0), task2);
        assertSame(tasks.get(1), task3);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_recent_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskRecentComparator());

        assertSame(tasks.get(0), task3);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task1);
    }

    @Test
    public void test_old_comparator() {
        final Task task1 = new Task(1, 1, "aaa", 123);
        final Task task2 = new Task(2, 2, "zzz", 124);
        final Task task3 = new Task(3, 3, "hhh", 125);

        final ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        Collections.sort(tasks, new Task.TaskOldComparator());

        assertSame(tasks.get(0), task1);
        assertSame(tasks.get(1), task2);
        assertSame(tasks.get(2), task3);
    }
}
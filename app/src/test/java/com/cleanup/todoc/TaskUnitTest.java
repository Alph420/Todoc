package com.cleanup.todoc;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.viewModel.MainViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Unit tests for tasks
 *
 * @author Gaëtan HERFRAY
 */
@RunWith(RobolectricTestRunner.class)
public class TaskUnitTest {
    TodocDatabase mDataBase;
    ProjectDao mProjectDao;
    TaskDao mTaskDao;
    MainViewModel mMainViewModel;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDataBase = TodocDatabase.getInstance(context);
        mProjectDao = mDataBase.projetDao();
        mTaskDao = mDataBase.taskDao();


         /*   ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(context);
        this.mMainViewModel = ViewModelProviders.of(this,mViewModelFactory).get(MainViewModel.class); */
    }


    @Test
    public void test_projects() {
        final Task task1 = new Task(1, 1, "task 1", new Date().getTime());
        final Task task2 = new Task(2, 2, "task 2", new Date().getTime());
        final Task task3 = new Task(3, 3, "task 3", new Date().getTime());
        final Task task4 = new Task(4, 4, "task 4", new Date().getTime());

        assertEquals("Projet Tartampion", mDataBase.projetDao().getProject(task1.getProjectId()).getName());
        assertEquals("Projet Lucidia", mDataBase.projetDao().getProject(task2.getProjectId()).getName());
        assertEquals("Projet Circus", mDataBase.projetDao().getProject(task3.getProjectId()).getName());
        assertNull(mDataBase.projetDao().getProject(task4.getProjectId()));
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
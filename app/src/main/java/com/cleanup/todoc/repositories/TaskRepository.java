package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by Alph4 le 18/05/2020.
 * Projet: Todoc
 **/
public class TaskRepository {

    private final TaskDao taskDao;

    //CONSTRUCTOR
    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    //--- GET ---
    public Task getTask(long taskId) {
        return this.taskDao.getTask(taskId);
    }

    public LiveData<List<Task>> getTasks() {
        return this.taskDao.getTasks();
    }

    // --- CREATE ---
    public void createTask(Task task) {
        this.taskDao.insertTask(task);
    }

    // --- INSERT ---
    public void insertTask(Task task) {
        this.taskDao.insertTask(task);
    }

    // --- DELETE ---
    public void deleteTask(long taskId) {
        this.taskDao.deleteTask(taskId);
    }

    // --- UPDATE ---
    public void updateTask(Task task) {
        this.taskDao.updateTask(task);
    }
}

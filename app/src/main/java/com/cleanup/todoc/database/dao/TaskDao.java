package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by Alph4 le 07/05/2020.
 * Projet: Todoc
 **/
@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task WHERE id = :taskId")
    Task getTask(long taskId);

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getTasks();

    @Insert
    long insertTask(Task item);

    @Update
    int updateTask(Task item);

    @Query("DELETE FROM Task WHERE id = :taskId")
    int deleteTask(long taskId);

    @Query("SELECT projectId FROM Task WHERE id = :taskId")
    long getProjectId(long taskId);
}

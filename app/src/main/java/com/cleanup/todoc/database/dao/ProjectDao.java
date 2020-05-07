package com.cleanup.todoc.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cleanup.todoc.model.Project;

import java.util.List;

/**
 * Created by Alph4 le 07/05/2020.
 * Projet: Todoc
 **/

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM Project WHERE id = :idProject")
    LiveData<List<Project>> getProject(long idProject);

    @Insert
    long insertProject(Project project);

    @Update
    int updateProject(Project project);

    @Query("DELETE FROM Project WHERE id = :idProject")
    int deleteProject(long idProject);
}

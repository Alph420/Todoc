package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;

import java.util.List;

/**
 * Created by Alph4 le 07/05/2020.
 * Projet: Todoc
 **/

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM Project WHERE id = :idProject")
    Project getProject(long idProject);

    @Query("SELECT * FROM Project")
    LiveData<List<Project>> getProjects();

    @Insert
    long insertProject(Project project);

    @Update
    int updateProject(Project project);

    @Query("DELETE FROM Project WHERE id = :idProject")
    int deleteProject(long idProject);
}

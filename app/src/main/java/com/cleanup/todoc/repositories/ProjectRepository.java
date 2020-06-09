package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

/**
 * Created by Alph4 le 15/05/2020.
 * Projet: Todoc
 **/
public class ProjectRepository {

    private final ProjectDao mProjectDao;

    //CONSTRUCTOR
    public ProjectRepository(ProjectDao projectDao) {
        this.mProjectDao = projectDao;
    }

    //--- GET ---
    public Project getProject(long projectId) {
        return this.mProjectDao.getProject(projectId);
    }

    public LiveData<List<Project>> getProjects() {
        return this.mProjectDao.getProjects();
    }

    //--- INSERT ---
    public void insertProject(Project project) {
        this.mProjectDao.insertProject(project);
    }

    //--- UPDATE ---
    public void updateProject(Project project) {
        this.mProjectDao.updateProject(project);
    }

    //--- DELETE ---
    public void deleteProject(long idProject) {
        this.mProjectDao.deleteProject(idProject);
    }

}

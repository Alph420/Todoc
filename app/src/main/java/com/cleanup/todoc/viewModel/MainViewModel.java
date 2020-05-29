package com.cleanup.todoc.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Alph4 le 18/05/2020.
 * Projet: Todoc
 **/
public class MainViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskRepository taskDataSource;
    private final ProjectRepository projectDataSource;
    private final Executor executor;


    public MainViewModel(TaskRepository taskDataSource, ProjectRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    // -------------
    // FOR PROJECT
    // -------------
    public Project getProject(long projectId) {
        return projectDataSource.getProject(projectId);
    }

    public LiveData<List<Project>> getProjects() {
        return projectDataSource.getProjects();
    }

    public void createProject(final Project project) {
        executor.execute(() -> projectDataSource.insertProject(project));
    }

    public void deleteProject(long projectId) {
        executor.execute(() -> projectDataSource.deleteProject(projectId));
    }

    public void updateProject(Project project) {
        executor.execute(() -> projectDataSource.updateProject(project));
    }

    // -------------
    // FOR TASK
    // -------------
    public Task getTask(long taskId) {
        return taskDataSource.getTask(taskId);
    }

    public LiveData<List<Task>> getTasks() {
        return taskDataSource.getTasks();
    }

    public void createTask(final Task task) {
        executor.execute(() -> taskDataSource.createTask(task));
    }

    public void deleteTask(long taskId) {
        executor.execute(() -> taskDataSource.deleteTask(taskId));
    }

    public void updateTask(Task task) {
        executor.execute(() -> taskDataSource.updateTask(task));
    }


}

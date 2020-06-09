package com.cleanup.todoc.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.concurrent.Executor;

/**
 * Created by Alph4 le 05/05/2020.
 * Projet: SaveMyTrip
 **/
public class ViewModelFactory implements ViewModelProvider.Factory {
    private final TaskRepository itemTaskSource;
    private final ProjectRepository itemProjectSource;
    private final Executor executor;

    ViewModelFactory(TaskRepository itemTaskSource, ProjectRepository itemProjectSource, Executor executor) {
        this.itemTaskSource = itemTaskSource;
        this.itemProjectSource = itemProjectSource;
        this.executor = executor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(itemTaskSource, itemProjectSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

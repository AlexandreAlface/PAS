package com.pas.pos_system.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.repository.Repository;

import java.util.List;

public class ComidaViewModel extends AndroidViewModel {

    private Repository repository;

    public ComidaViewModel(@NonNull Application application) {
        super(application);
        this.repository = new  Repository(application);
    }

    public LiveData<List<Comidas>> getComida() {

        return this.repository.getListComidas();
    }

    public void updateComidaList() {
        this.repository.updateComidaList();
    }
}
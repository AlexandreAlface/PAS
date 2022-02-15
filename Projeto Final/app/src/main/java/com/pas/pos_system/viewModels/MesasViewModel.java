package com.pas.pos_system.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pas.pos_system.models.Mesas;
import com.pas.pos_system.repository.Repository;

import java.util.List;

public class MesasViewModel extends AndroidViewModel {

    private Repository repository;

    public MesasViewModel(@NonNull Application application) {
        super(application);

        this.repository = new  Repository(application);
    }

    public LiveData<List<Mesas>> getMesas() {

        return this.repository.getListMesas();
    }

    public void updateMesasList() {
        this.repository.updateMesasList();
    }

}
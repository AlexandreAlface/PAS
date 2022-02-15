package com.pas.pos_system.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pas.pos_system.models.Balcaos;
import com.pas.pos_system.repository.Repository;

import java.util.List;

public class BalcaoViewModel extends AndroidViewModel {

    private Repository repository;

    public BalcaoViewModel(@NonNull Application application) {
        super(application);

        this.repository = new  Repository(application);


    }

    public LiveData<List<Balcaos>> getBalcao() {

        return this.repository.getListBalcao();
    }

    public void updateBalcaoList() {
        this.repository.updateBalcaoList();
    }


}
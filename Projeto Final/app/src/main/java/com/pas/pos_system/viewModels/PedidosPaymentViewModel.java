package com.pas.pos_system.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pas.pos_system.models.Pedidos;
import com.pas.pos_system.repository.Repository;

import java.util.List;

public class PedidosPaymentViewModel extends AndroidViewModel {

    private Repository repository;

    public PedidosPaymentViewModel(@NonNull Application application) {
        super(application);
        this.repository = new  Repository(application);
    }

    public LiveData<List<Pedidos>> getPedidos() {

        return this.repository.getListPedidos();
    }

    public void updatePedidosList() {
        this.repository.updatePedidosList();
    }
}
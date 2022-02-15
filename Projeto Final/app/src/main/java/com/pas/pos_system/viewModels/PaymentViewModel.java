package com.pas.pos_system.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.models.ComidasPorPedidos;
import com.pas.pos_system.models.Pedidos;
import com.pas.pos_system.repository.Repository;

import java.util.List;

public class PaymentViewModel extends AndroidViewModel {

    private Repository repository;

    public PaymentViewModel(@NonNull Application application) {
        super(application);
        this.repository = new  Repository(application);
    }

    public LiveData<List<ComidasPorPedidos>> getComidasPorPedidos(long idPedido) {

        return this.repository.getComidasPorPedido(idPedido);
    }

    public void updatePedidosList(long idPedido) {
        this.repository.updateComidasPorPedido(idPedido);
    }

    public void delete(long idPedido){
        this.repository.deleteComidasPorPedido(idPedido);
    }

    public void deleteComidasPorPedido(long idPedido){
        this.repository.delete(idPedido);
    }
}
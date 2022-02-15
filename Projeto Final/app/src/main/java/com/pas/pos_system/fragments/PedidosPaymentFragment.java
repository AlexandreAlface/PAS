package com.pas.pos_system.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pas.pos_system.R;
import com.pas.pos_system.adapters.RecyclerViewComidasAdapter;
import com.pas.pos_system.adapters.RecyclerViewPedidosAdapter;
import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.models.Pedidos;
import com.pas.pos_system.viewModels.ComidaViewModel;
import com.pas.pos_system.viewModels.PedidosPaymentViewModel;

import java.util.List;

public class PedidosPaymentFragment extends Fragment {

    private PedidosPaymentViewModel mViewModel;

    public static PedidosPaymentFragment newInstance() {
        return new PedidosPaymentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pedidos_payment_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Bundle extras = getArguments();

        //long idPedido = (long) extras.get("idPedido");

        RecyclerViewPedidosAdapter adapter;

        RecyclerView recyclerViewPedidos = view.findViewById(R.id.recyclerViewPedidos);
        adapter = new RecyclerViewPedidosAdapter(view.getContext());
        recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewPedidos.setAdapter(adapter);

        this.mViewModel = new ViewModelProvider(this).get(PedidosPaymentViewModel.class);

        this.mViewModel.getPedidos().observe(getViewLifecycleOwner(), new Observer<List<Pedidos>>() {
            @Override
            public void onChanged(List<Pedidos> pedidos) {

                adapter.updateList(pedidos);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        this.mViewModel = new ViewModelProvider(this).get(PedidosPaymentViewModel.class);
        this.mViewModel.updatePedidosList();

    }
}
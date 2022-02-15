package com.pas.pos_system.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pas.pos_system.adapters.RecyclerViewBalcaoAdapter;
import com.pas.pos_system.models.Balcaos;
import com.pas.pos_system.viewModels.BalcaoViewModel;
import com.pas.pos_system.R;
import com.pas.pos_system.viewModels.MesasViewModel;

import java.util.List;

public class BalcaoFragment extends Fragment {

    private BalcaoViewModel mViewModel;

    public static BalcaoFragment newInstance() {
        return new BalcaoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.balcao_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerViewBalcaoAdapter balcaoAdapter;

        RecyclerView recyclerViewBalcao = view.findViewById(R.id.recyclerViewBalcaos);
        balcaoAdapter = new RecyclerViewBalcaoAdapter(view.getContext());
        recyclerViewBalcao.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewBalcao.setAdapter(balcaoAdapter);

        this.mViewModel = new ViewModelProvider(this).get(BalcaoViewModel.class);

        this.mViewModel.getBalcao().observe(getViewLifecycleOwner(), new Observer<List<Balcaos>>() {
            @Override
            public void onChanged(List<Balcaos> balcao) {
                balcaoAdapter.updateList(balcao);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        this.mViewModel = new ViewModelProvider(this).get(BalcaoViewModel.class);
        this.mViewModel.updateBalcaoList();

    }

}
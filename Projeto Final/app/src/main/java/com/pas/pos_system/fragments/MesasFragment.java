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

import com.pas.pos_system.adapters.RecyclerViewMesasAdapter;
import com.pas.pos_system.models.Mesas;
import com.pas.pos_system.viewModels.MesasViewModel;
import com.pas.pos_system.R;

import java.util.List;

public class MesasFragment extends Fragment {

    private MesasViewModel mViewModel;

    public static MesasFragment newInstance() {
        return new MesasFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mesas_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerViewMesasAdapter recyclerViewMesasAdapter;

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMesas);
        recyclerViewMesasAdapter = new RecyclerViewMesasAdapter(view.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(recyclerViewMesasAdapter);

        this.mViewModel = new ViewModelProvider(this).get(MesasViewModel.class);

        this.mViewModel.getMesas().observe(getViewLifecycleOwner(), new Observer<List<Mesas>>() {
            @Override
            public void onChanged(List<Mesas> mesas) {
            recyclerViewMesasAdapter.updateList(mesas);
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();

        this.mViewModel = new ViewModelProvider(this).get(MesasViewModel.class);
        this.mViewModel.updateMesasList();

    }

}
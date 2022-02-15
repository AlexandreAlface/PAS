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

import com.pas.pos_system.adapters.RecyclerViewComidasAdapter;
import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.viewModels.ComidaViewModel;
import com.pas.pos_system.R;

import java.util.List;

public class ComidaFragment extends Fragment {

    private ComidaViewModel mViewModel;

    public static ComidaFragment newInstance() {
        return new ComidaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.comida_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle extras = getArguments();

        long lugar = (long) extras.get("idLugar");
        Boolean tipoLugar = (Boolean) extras.get("tipoLugar");

        RecyclerViewComidasAdapter comidaAdapter;

        RecyclerView recyclerViewComida = view.findViewById(R.id.recyclerViewComida);
        comidaAdapter = new RecyclerViewComidasAdapter(view.getContext(), lugar, tipoLugar);
        recyclerViewComida.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewComida.setAdapter(comidaAdapter);

        this.mViewModel = new ViewModelProvider(this).get(ComidaViewModel.class);

        this.mViewModel.getComida().observe(getViewLifecycleOwner(), new Observer<List<Comidas>>() {
            @Override
            public void onChanged(List<Comidas> comida) {

                comidaAdapter.updateList(comida);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        this.mViewModel = new ViewModelProvider(this).get(ComidaViewModel.class);
        this.mViewModel.updateComidaList();
    }
}
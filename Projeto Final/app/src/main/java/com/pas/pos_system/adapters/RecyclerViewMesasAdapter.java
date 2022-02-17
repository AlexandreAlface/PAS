package com.pas.pos_system.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pas.pos_system.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pas.pos_system.models.Mesas;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMesasAdapter extends RecyclerView.Adapter<RecyclerViewMesasAdapter.ViewHolder> {

    private final Context context;
    private List<Mesas> mesasList = new ArrayList<>();

    public RecyclerViewMesasAdapter(Context context) {

        this.context = context;
        mesasList = new ArrayList<>();
    }


    @NonNull
    @Override
    public RecyclerViewMesasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.context).inflate(R.layout.adapter_mesas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMesasAdapter.ViewHolder holder, int position) {

        Mesas mesas = this.mesasList.get(position);

        holder.textView.setText("Mesa " + mesas.getId());

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "mesa " + mesas.getId() + " escolhida", Toast.LENGTH_SHORT).show();

                Bundle data = new Bundle();
                data.putLong("idLugar", mesas.getId());
                data.putBoolean("tipoLugar", true);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_mesasFragment_to_comidaFragment, data);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mesasList.size();
    }

    public void updateList(List<Mesas> mesasList) {

        this.mesasList = mesasList;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;

        TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root = itemView;

            textView = itemView.findViewById(R.id.textViewMesas);


        }
    }





}

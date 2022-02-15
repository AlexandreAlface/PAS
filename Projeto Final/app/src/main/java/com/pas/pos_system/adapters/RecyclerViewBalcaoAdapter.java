package com.pas.pos_system.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.pas.pos_system.R;
import com.pas.pos_system.models.Balcaos;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewBalcaoAdapter extends RecyclerView.Adapter<RecyclerViewBalcaoAdapter.ViewHolder> {

    private final Context context;
    private List<Balcaos> balcaoList = new ArrayList<>();

    public RecyclerViewBalcaoAdapter(Context context) {
        this.context = context;
        this.balcaoList = new ArrayList<>();
    }


    @NonNull
    @Override
    public RecyclerViewBalcaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.context).inflate(R.layout.adapter_balcaos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBalcaoAdapter.ViewHolder holder, int position) {

        Balcaos balcao = this.balcaoList.get(position);

        holder.textView.setText("Balcao " + balcao.getId());

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "balcao " + balcao.getId() + " escolhida", Toast.LENGTH_LONG).show();

                Bundle data = new Bundle();

                data.putLong("idLugar", balcao.getId());
                data.putBoolean("tipoLugar", false);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_balcaoFragment_to_comidaFragment, data);

            }
        });

    }

    @Override
    public int getItemCount() {
        return balcaoList.size();
    }

    public void updateList(List<Balcaos> balcaoList) {

        this.balcaoList = balcaoList;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root = itemView;

            textView = itemView.findViewById(R.id.textViewBalcao);

        }
    }

}


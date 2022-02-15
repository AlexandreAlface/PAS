package com.pas.pos_system.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.pas.pos_system.R;
import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.models.ComidasPorPedidos;
import com.pas.pos_system.models.Pedidos;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewsPaymentAdapter extends RecyclerView.Adapter<RecyclerViewsPaymentAdapter.ViewHolder> {

    private final Context context;
    private List<ComidasPorPedidos> comidasPorPedidosList;

    public RecyclerViewsPaymentAdapter(Context context) {
        this.context = context;
        this.comidasPorPedidosList = new ArrayList<>();
    }


    @NonNull
    @Override
    public RecyclerViewsPaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.context).inflate(R.layout.adapter_payment, parent, false);
        return new RecyclerViewsPaymentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewsPaymentAdapter.ViewHolder holder, int position) {

        ComidasPorPedidos comidasPorPedidos = this.comidasPorPedidosList.get(position);

        //Comidas comidas = comidasList.get((int) comidasPorPedidos.getIdComida());

        holder.textViewComidaPayment.setText(""+ comidasPorPedidos.getIdComida());

        holder.textViewQuantidadePayment.setText(""+ comidasPorPedidos.getQuantidade());

    }

    @Override
    public int getItemCount() {
        return comidasPorPedidosList.size();
    }

    public void updateList(List<ComidasPorPedidos> comidasPorPedidos) {

        this.comidasPorPedidosList = comidasPorPedidos;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;

        TextView textViewComidaPayment;
        TextView textViewQuantidadePayment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root = itemView;

            this.textViewComidaPayment = itemView.findViewById(R.id.textViewComidaPayment);

            this.textViewQuantidadePayment = itemView.findViewById(R.id.textViewQuantidadePayment);

        }
    }

}


package com.pas.pos_system.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.pas.pos_system.database.AppDataBase;
import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.models.ComidasPorPedidos;
import com.pas.pos_system.models.Pedidos;
import com.pas.pos_system.repository.Repository;

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

        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(RecyclerViewsPaymentAdapter.this.context);
                builder.setTitle("Eliminar Pedido");
                builder.setMessage("Tem a certeza que pretende esta Comida " + comidasPorPedidos.getIdComida() + "?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //AppDataBase.getInstance(RecyclerViewsPaymentAdapter.this.context).comidasPorPedidos().delete(comidasPorPedidos.getIdPedido());
                        comidasPorPedidosList.remove(comidasPorPedidos);
                        notifyDataSetChanged();
                        Repository repository = new Repository(context);
                        repository.delete(comidasPorPedidos.getIdPedido());
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


                return true;
            }

        });

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


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
import com.pas.pos_system.models.Pedidos;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPedidosAdapter extends RecyclerView.Adapter<RecyclerViewPedidosAdapter.ViewHolder> {

    private final Context context;
    private List<Pedidos> pedidosList;

    public RecyclerViewPedidosAdapter(Context context) {
        this.context = context;
        this.pedidosList = new ArrayList<>();
    }


    @NonNull
    @Override
    public RecyclerViewPedidosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.context).inflate(R.layout.adapter_pedidos, parent, false);
        return new RecyclerViewPedidosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewPedidosAdapter.ViewHolder holder, int position) {

        Pedidos pedidos = this.pedidosList.get(position);

        if(pedidos.getIdMesa()!= 0){

            holder.textViewPedido.setText("Pedido Mesa " + pedidos.getIdMesa());

        }else {

            holder.textViewPedido.setText("Pedido Balcao " + pedidos.getIdBalcao());
        }

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Bundle data = new Bundle();

                data.putLong("idPedido", pedidos.getId());
                data.putInt("pagamento", pedidosList.size());

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_pedidosPaymentFragment_to_paymentFragment, data);

            }
        });

    }

    @Override
    public int getItemCount() {
        return pedidosList.size();
    }

    public void updateList(List<Pedidos> pedidosList) {

        this.pedidosList = pedidosList;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;

        TextView textViewPedido;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root = itemView;

            this.textViewPedido = itemView.findViewById(R.id.textViewPedido);



        }
    }

}

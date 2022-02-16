package com.pas.pos_system.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pas.pos_system.R;
import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewComidasAdapter extends RecyclerView.Adapter<RecyclerViewComidasAdapter.ViewHolder> {

    private final Context context;
    private List<Comidas> comidaList;
    private long idLugar;
    private Boolean tipoLugar;

    public RecyclerViewComidasAdapter(Context context, long idLugar, Boolean tipoLugar)
    {
        this.context = context;
        this.comidaList = new ArrayList<>();
        this.idLugar = idLugar;
        this.tipoLugar = tipoLugar;
    }


    @NonNull
    @Override
    public RecyclerViewComidasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(this.context).inflate(R.layout.adapter_comida, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewComidasAdapter.ViewHolder holder, int position) {

        Comidas comida = comidaList.get(position);

        holder.textViewName.setText(comida.getNome());
        holder.textViewValor.setText(comida.getValor()+"€");

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Repository repository = new Repository(v.getContext());

                Toast.makeText(context, "comida " + comida.getNome() + " adicionado ao pedido", Toast.LENGTH_LONG).show();

                if(tipoLugar.equals(true)){
                    //repository.postPedidoMesa(v.getContext(),idLugar);

                    //long idPedido = repository.getPedidosByMesa(v.getContext(), idLugar);

                    repository.postComidasPorPedido(v.getContext(),idLugar,tipoLugar,comida.getId(),1);


                } else{
                    //repository.postPedidoBalcao(v.getContext(),idLugar);
                    repository.postComidasPorPedido(v.getContext(),idLugar,tipoLugar,comida.getId(),1);
                }



            }
        });


    }

    @Override
    public int getItemCount() {
        return comidaList.size();
    }

    public void updateList(List<Comidas> comidaList) {

        this.comidaList = comidaList;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View root;

        TextView textViewName;
        TextView textViewValor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.root = itemView;

            textViewName = itemView.findViewById(R.id.textViewComida);
            textViewValor = itemView.findViewById(R.id.textViewPreco);

        }
    }

}
package com.pas.pos_system.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.pas.pos_system.R;
import com.pas.pos_system.adapters.RecyclerViewPedidosAdapter;
import com.pas.pos_system.adapters.RecyclerViewsPaymentAdapter;
import com.pas.pos_system.database.AppDataBase;
import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.models.ComidasPorPedidos;
import com.pas.pos_system.models.Pedidos;
import com.pas.pos_system.viewModels.PaymentViewModel;
import com.pas.pos_system.viewModels.PedidosPaymentViewModel;

import java.util.List;

public class PaymentFragment extends Fragment {

    private PaymentViewModel mViewModel;

    private long idPedido;
    private int pagamento;
    private TextView textViewValor;
    private float conta = 0;

    private Button button;

    public static PaymentFragment newInstance() {
        return new PaymentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.payment_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle extras = getArguments();
        idPedido = (long) extras.get("idPedido");
        pagamento = (int) extras.get("pagamento");

        this.textViewValor = view.findViewById(R.id.textViewValor);

        RecyclerViewsPaymentAdapter adapter;

        RecyclerView recyclerViewPayment = view.findViewById(R.id.recyclerViewPayment);
        adapter = new RecyclerViewsPaymentAdapter(view.getContext());
        recyclerViewPayment.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewPayment.setAdapter(adapter);

        this.mViewModel = new ViewModelProvider(this).get(PaymentViewModel.class);

        this.mViewModel.getComidasPorPedidos(idPedido).observe(getViewLifecycleOwner(), new Observer<List<ComidasPorPedidos>>() {
            @Override
            public void onChanged(List<ComidasPorPedidos> comidasPorPedidos) {
                adapter.updateList(comidasPorPedidos);
            }
        });

        List<ComidasPorPedidos> comidasPorPedidos = AppDataBase.getInstance(getContext()).comidasPorPedidos().getComidasPorPedidosLocal(idPedido);

        for (int i = 0; i < comidasPorPedidos.size(); i++) {

            Comidas comida = AppDataBase.getInstance(getContext()).comidasDao().getComidaId(comidasPorPedidos.get(i).getIdComida());

            conta += comida.getValor();
            textViewValor.setText(""+conta);
        }



        button = view.findViewById(R.id.buttonPayment);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                
               mViewModel = new ViewModelProvider(requireActivity()).get(PaymentViewModel.class);

                for (int i = 0; i < pagamento; i++) {

                    mViewModel.deleteComidasPorPedido(idPedido);
                    mViewModel.delete(idPedido);

                }

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        this.mViewModel = new ViewModelProvider(this).get(PaymentViewModel.class);
        this.mViewModel.updatePedidosList(idPedido);

    }

}
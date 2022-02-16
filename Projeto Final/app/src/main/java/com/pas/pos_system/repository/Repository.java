package com.pas.pos_system.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.pas.pos_system.R;
import com.pas.pos_system.activitys.HomeActivity;
import com.pas.pos_system.activitys.LoginActivity;
import com.pas.pos_system.database.AppDataBase;
import com.pas.pos_system.database.BalcaosDao;
import com.pas.pos_system.database.ComidasDao;
import com.pas.pos_system.database.ComidasPorPedidosDao;
import com.pas.pos_system.database.MesasDao;
import com.pas.pos_system.database.PedidosDao;
import com.pas.pos_system.models.Balcaos;
import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.models.ComidasPorPedidos;
import com.pas.pos_system.models.Mesas;
import com.pas.pos_system.models.Pedidos;
import com.pas.pos_system.models.Utilizadores;
import com.pas.pos_system.posts.ComidasPorPedidoPost;
import com.pas.pos_system.posts.CreateLoginPost;
import com.pas.pos_system.webServices.ApiService;
import com.pas.pos_system.webServices.DataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private MesasDao mesasDao;
    private BalcaosDao balcaosDao;
    private ComidasDao comidasDao;
    private PedidosDao pedidosDao;
    private ComidasPorPedidosDao comidasPorPedidosDao;


    ComidasPorPedidoPost pedidoPost;



    public Repository(Context context) {

        this.mesasDao = AppDataBase.getInstance(context).getMesasDao();
        this.balcaosDao = AppDataBase.getInstance(context).balcaosDao();
        this.comidasDao = AppDataBase.getInstance(context).comidasDao();
        this.pedidosDao = AppDataBase.getInstance(context).pedidosDao();
        this.comidasPorPedidosDao = AppDataBase.getInstance(context).comidasPorPedidos();
    }

    public void login(Context context, String username, String password){

        ApiService apiService = DataSource.getAPIService();

        Call<List<Utilizadores>> callLogin = apiService.login(username,password);

        callLogin.enqueue(new Callback<List<Utilizadores>>() {
            @Override
            public void onResponse(Call<List<Utilizadores>>call, Response<List<Utilizadores>> response) {

                List<Utilizadores> res = response.body();

                if(res.size()==1)
                {
                    HomeActivity.startActivity(context);


                }
                else
                {
                    Toast.makeText(context, R.string.LoginWrong, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Utilizadores>> call, Throwable t) {

                t.printStackTrace();

                Toast.makeText(context, "erro request", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void createLogin(Context context, String username, String password, String name)
    {


            CreateLoginPost createLoginPost = new CreateLoginPost(username, password, name);

            ApiService apiService = DataSource.getAPIService();

            Call<CreateLoginPost> call = apiService.createLogin(createLoginPost);

            call.enqueue(new Callback<CreateLoginPost>() {
                @Override
                public void onResponse(Call<CreateLoginPost> call, Response<CreateLoginPost> response) {

                    Toast.makeText(context, "Login Criado", Toast.LENGTH_LONG).show();
                    LoginActivity.startActivity(context);



                }

                @Override
                public void onFailure(Call<CreateLoginPost> call, Throwable t) {

                    Toast.makeText(context, "Dados Errados", Toast.LENGTH_LONG).show();

                }
            });

        }

    public LiveData<List<Mesas>> getListMesas() {

        return this.mesasDao.getMesas();

    }

    public void updateMesasList() {

        ApiService apiService = DataSource.getAPIService();


        apiService.getMesas().enqueue(new Callback<List<Mesas>>() {
            @Override
            public void onResponse(Call<List<Mesas>> call, Response<List<Mesas>> response) {
                if (response.isSuccessful()) {
                    List<Mesas> newList = response.body();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mesasDao.add(newList);
                        }
                    }).start();
                } else {
                    // Log error to logcat
                }
            }

            @Override
            public void onFailure(Call<List<Mesas>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public LiveData<List<Balcaos>> getListBalcao() {

        return this.balcaosDao.getBalcao();

    }

    public void updateBalcaoList() {

        ApiService apiService = DataSource.getAPIService();


        apiService.getBalcaos().enqueue(new Callback<List<Balcaos>>() {
            @Override
            public void onResponse(Call<List<Balcaos>> call, Response<List<Balcaos>> response) {
                if (response.isSuccessful()) {
                    List<Balcaos> newList = response.body();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            balcaosDao.add(newList);
                        }
                    }).start();
                } else {
                    // Log error to logcat
                }
            }

            @Override
            public void onFailure(Call<List<Balcaos>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public LiveData<List<Comidas>> getListComidas() {

        return this.comidasDao.getComida();

    }

    public void updateComidaList() {

        ApiService apiService = DataSource.getAPIService();

        apiService.getComida().enqueue(new Callback<List<Comidas>>() {
            @Override
            public void onResponse(Call<List<Comidas>> call, Response<List<Comidas>> response) {
                if (response.isSuccessful()){
                    List<Comidas> comidaList = response.body();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            comidasDao.add(comidaList);
                        }
                    }).start();
                }
            }

            @Override
            public void onFailure(Call<List<Comidas>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


    public void postComidasPorPedido(Context context, long idLugar,  Boolean flgMesaBalcao , long idComida, long quantidade) {

        ApiService apiService = DataSource.getAPIService();

        if (flgMesaBalcao.equals(true)) {

            Call<List<Pedidos>> call = apiService.getPedidosByMesa(idLugar);

            call.enqueue(new Callback<List<Pedidos>>() {
                @Override
                public void onResponse(Call<List<Pedidos>> call, Response<List<Pedidos>> response) {

                    List<Pedidos> res = response.body();

                    pedidoPost = new ComidasPorPedidoPost(res.get(0).getId(), idComida, 1);

                    Call<ComidasPorPedidoPost> callPost = apiService.postComidaPorPedidoPost(pedidoPost);

                    callPost.enqueue(new Callback<ComidasPorPedidoPost>() {
                        @Override
                        public void onResponse(Call<ComidasPorPedidoPost> call, Response<ComidasPorPedidoPost> response) {

                        }

                        @Override
                        public void onFailure(Call<ComidasPorPedidoPost> call, Throwable t) {

                        }
                    });

                }

                @Override
                public void onFailure(Call<List<Pedidos>> call, Throwable t) {

                }
            });

        } else {

            Call<List<Pedidos>> call = apiService.getPedidosByBalcao(idLugar);

            call.enqueue(new Callback<List<Pedidos>>() {
                @Override
                public void onResponse(Call<List<Pedidos>> call, Response<List<Pedidos>> response) {

                    List<Pedidos> res = response.body();


                    pedidoPost = new ComidasPorPedidoPost(res.get(0).getId(), idComida, 1);

                    Call<ComidasPorPedidoPost> callPost = apiService.postComidaPorPedidoPost(pedidoPost);

                    callPost.enqueue(new Callback<ComidasPorPedidoPost>() {
                        @Override
                        public void onResponse(Call<ComidasPorPedidoPost> call, Response<ComidasPorPedidoPost> response) {

                        }

                        @Override
                        public void onFailure(Call<ComidasPorPedidoPost> call, Throwable t) {

                        }
                    });

                }

                @Override
                public void onFailure(Call<List<Pedidos>> call, Throwable t) {

                }
            });

        }
    }

    public LiveData<List<Pedidos>> getListPedidos() {

        return this.pedidosDao.getPedidos();

    }

    public void updatePedidosList() {

        ApiService apiService = DataSource.getAPIService();


        apiService.getPedidos().enqueue(new Callback<List<Pedidos>>() {
            @Override
            public void onResponse(Call<List<Pedidos>> call, Response<List<Pedidos>> response) {
                if (response.isSuccessful()) {
                    List<Pedidos> newList = response.body();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            pedidosDao.add(newList);
                        }
                    }).start();
                } else {
                    // Log error to logcat
                }
            }

            @Override
            public void onFailure(Call<List<Pedidos>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public LiveData<List<ComidasPorPedidos>> getComidasPorPedido(long idPedido) {

        return this.comidasPorPedidosDao.getComidasPorPedidos(idPedido);

    }

    public void updateComidasPorPedido(long idPedido) {

        ApiService apiService = DataSource.getAPIService();


        apiService.getAllComidasPorPedidos(idPedido).enqueue(new Callback<List<ComidasPorPedidos>>() {
            @Override
            public void onResponse(Call<List<ComidasPorPedidos>> call, Response<List<ComidasPorPedidos>> response) {

                if (response.isSuccessful()) {

                List<ComidasPorPedidos> newList = response.body();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        comidasPorPedidosDao.add(newList);
                    }
                }).start();
            } else

            {
                // Log error to logcat
            }
        }


            @Override
            public void onFailure(Call<List<ComidasPorPedidos>> call, Throwable t) {

            }
        });

    }

    public void deleteComidasPorPedido(long idPedido) {

        this.comidasPorPedidosDao.delete(idPedido);

    }

    public void delete(long idPedido){

        ApiService apiService = DataSource.getAPIService();

        Call<List<ComidasPorPedidos>> call = apiService.deleteComidasPorPedido(idPedido);

        call.enqueue(new Callback<List<ComidasPorPedidos>>() {
            @Override
            public void onResponse(Call<List<ComidasPorPedidos>> call, Response<List<ComidasPorPedidos>> response) {

            }

            @Override
            public void onFailure(Call<List<ComidasPorPedidos>> call, Throwable t) {

            }
        });

    }


}

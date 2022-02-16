package com.pas.pos_system.webServices;

import androidx.room.Delete;

import com.pas.pos_system.models.Balcaos;
import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.models.ComidasPorPedidos;
import com.pas.pos_system.models.Mesas;
import com.pas.pos_system.models.Pedidos;
import com.pas.pos_system.models.Utilizadores;
import com.pas.pos_system.posts.ComidasPorPedidoPost;
import com.pas.pos_system.posts.CreateLoginPost;
import com.pas.pos_system.posts.PedidoPostByBalcao;
import com.pas.pos_system.posts.PedidoPostByMesa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("utilizadores/login/{username}/{password}")
    Call<List<Utilizadores>> login(@Path("username")String username, @Path("password")String password );

    @POST("utilizadores/criarUtilizador")
    Call<CreateLoginPost> createLogin(@Body CreateLoginPost createLoginPost);

    @GET("mesas/getAllMesas")
    Call<List<Mesas>> getMesas();

    @GET("balcaos/getAllBalcao")
    Call<List<Balcaos>> getBalcaos();

    @GET("comida/getAllComida")
    Call<List<Comidas>> getComida();

    @GET("comida/getComida/{id}")
    Call<Comidas> getComidaById(@Path("id")Long id);

    @POST("pedidos/createPedido")
    Call<PedidoPostByMesa> postPedidoByMesa(@Body PedidoPostByMesa pedidoPost);

    @POST("pedidos/createPedido")
    Call<PedidoPostByBalcao> postPedidoByBalcao(@Body PedidoPostByBalcao pedidoPost);

    @GET("pedidos/getPedidobyMesa/{idMesa}")
    Call<List<Pedidos>> getPedidosByMesa(@Path("idMesa")long idMesa);

    @GET("pedidos/getPedidobyBalcao/{idBalcao}")
    Call<List<Pedidos>> getPedidosByBalcao(@Path("idBalcao")long idBalcao);

    @POST("ComidasPorPedido/createComidasPorPedido")
    Call<ComidasPorPedidoPost> postComidaPorPedidoPost(@Body ComidasPorPedidoPost pedidoPost);

    @GET("pedidos/getAllPedido")
    Call<List<Pedidos>> getPedidos();

    @GET("ComidasPorPedido/getComidasPorPedidoByIdPedido/{idPedido}")
    Call<List<ComidasPorPedidos>> getAllComidasPorPedidos(@Path("idPedido")long idPedido);

    @DELETE("ComidasPorPedido/deleteComidasPorIdPedido/{idPedido}")
    Call<List<ComidasPorPedidos>> deleteComidasPorPedido(@Path("idPedido") long idPedido);

}

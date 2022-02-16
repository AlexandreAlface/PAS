package com.pas.pos_system.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pas.pos_system.models.ComidasPorPedidos;

import java.util.List;

@Dao
public interface ComidasPorPedidosDao {

    @Query("SELECT * FROM ComidasPorPedidos WHERE idPedido = :idPedido")
    LiveData<List<ComidasPorPedidos>> getComidasPorPedidos(long idPedido);

    @Query("SELECT * FROM ComidasPorPedidos WHERE idPedido = :idPedido")
    List<ComidasPorPedidos> getComidasPorPedidosLocal(long idPedido);

    @Query("DELETE  FROM ComidasPorPedidos WHERE idPedido = :idPedido")
    void deletByPedido(long idPedido);

    @Query("DELETE  FROM ComidasPorPedidos WHERE idComida = :idComida")
    void deletByComida(long idComida);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(List<ComidasPorPedidos> postList);
}

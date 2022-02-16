package com.pas.pos_system.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.models.Pedidos;

import java.util.List;

@Dao
public interface PedidosDao {

    @Query("SELECT * FROM Pedidos")
    LiveData<List<Pedidos>> getPedidos();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(List<Pedidos> postList);

}

package com.pas.pos_system.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pas.pos_system.models.Balcaos;
import com.pas.pos_system.models.Comidas;
import com.pas.pos_system.models.ComidasPorPedidos;
import com.pas.pos_system.models.Mesas;
import com.pas.pos_system.models.Pedidos;

@Database(entities = {Mesas.class, Balcaos.class, Comidas.class, Pedidos.class, ComidasPorPedidos.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract MesasDao getMesasDao();
    public abstract BalcaosDao balcaosDao();
    public abstract ComidasDao comidasDao();
    public abstract PedidosDao pedidosDao();
    public abstract ComidasPorPedidosDao comidasPorPedidos();

    private static AppDataBase INSTANCE;

    public static AppDataBase getInstance(Context context){

        if( INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "PosDB").allowMainThreadQueries()
                    .build();

        }

        return INSTANCE;

    }

}

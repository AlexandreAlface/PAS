package com.pas.pos_system.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pas.pos_system.models.Mesas;

import java.util.List;

@Dao
public interface MesasDao {

    @Query("SELECT * FROM Mesas")
    LiveData<List<Mesas>> getMesas();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(List<Mesas> postList);

}

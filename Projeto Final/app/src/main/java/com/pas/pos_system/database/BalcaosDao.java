package com.pas.pos_system.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pas.pos_system.models.Balcaos;

import java.util.List;

@Dao
public interface BalcaosDao {

    @Query("SELECT * FROM Balcaos")
    LiveData<List<Balcaos>> getBalcao();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(List<Balcaos> postList);

}

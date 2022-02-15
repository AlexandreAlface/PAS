package com.pas.pos_system.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pas.pos_system.models.Comidas;

import java.util.List;

@Dao
public interface ComidasDao {

    @Query("SELECT * FROM Comidas")
    LiveData<List<Comidas>> getComida();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(List<Comidas> postList);

}

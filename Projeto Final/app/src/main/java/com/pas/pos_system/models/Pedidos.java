package com.pas.pos_system.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pedidos {

    @PrimaryKey()
    private long id;
    private long idMesa;
    private long idBalcao;

    public Pedidos(long id, long idMesa, long idBalcao) {
        this.id = id;
        this.idMesa = idMesa;
        this.idBalcao = idBalcao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(long idMesa) {
        this.idMesa = idMesa;
    }

    public long getIdBalcao() {
        return idBalcao;
    }

    public void setIdBalcao(long idBalcao) {
        this.idBalcao = idBalcao;
    }
}

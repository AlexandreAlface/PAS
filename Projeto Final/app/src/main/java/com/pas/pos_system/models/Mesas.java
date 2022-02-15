package com.pas.pos_system.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Mesas {

    @PrimaryKey()
    private long id;
    private String nomeCliente;
    private String idPedido;

    public Mesas(long id, String nomeCliente, String idPedido) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.idPedido = idPedido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

}

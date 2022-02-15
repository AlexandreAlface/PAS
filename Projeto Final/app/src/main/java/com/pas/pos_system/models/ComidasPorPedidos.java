package com.pas.pos_system.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ComidasPorPedidos {

    @PrimaryKey()
    private long id;
    private long idPedido;
    private long idComida;
    private long Quantidade;

    public ComidasPorPedidos(long id, long idPedido, long idComida, long Quantidade) {
        this.id = id;
        this.idPedido = idPedido;
        this.idComida = idComida;
        this.Quantidade = Quantidade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public long getIdComida() {
        return idComida;
    }

    public void setIdComida(long idComida) {
        this.idComida = idComida;
    }

    public long getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.Quantidade = Quantidade;
    }
}

package com.pas.pos_system.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Comidas {

    @PrimaryKey()
    private long id;
    private String nome;
    private float valor;

    public Comidas(long id, String nome, float valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}

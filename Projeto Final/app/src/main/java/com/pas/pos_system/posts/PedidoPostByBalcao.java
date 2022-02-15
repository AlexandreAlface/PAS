package com.pas.pos_system.posts;

public class PedidoPostByBalcao {

    private long idMesa;
    private long idBalcao;

    public PedidoPostByBalcao(long idMesa, long idBalcao) {
        this.idMesa = idMesa;
        this.idBalcao = idBalcao;
    }

    public long getIdBalcao() {
        return idBalcao;
    }

    public void setIdBalcao(long idBalcao) {
        this.idBalcao = idBalcao;
    }
}

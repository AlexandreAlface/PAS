package com.pas.pos_system.posts;

public class PedidoPostByMesa {

    private long idMesa;
    private long idBalcao;

    public PedidoPostByMesa(long idMesa, long idBalcao) {
        this.idMesa = idMesa;
        this.idBalcao = idBalcao;
    }

    public long getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(long idMesa) {
        this.idMesa = idMesa;
    }


}

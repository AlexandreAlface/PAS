package com.pas.pos_system.posts;

public class ComidasPorPedidoPost {

    private long idPedido;
    private long idComida;
    private long Quantidade;

    public ComidasPorPedidoPost(long idPedido, long idComida, long Quantidade) {
        this.idPedido = idPedido;
        this.idComida = idComida;
        this.Quantidade = Quantidade;
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

    public void setQuantidade(long Quantidade) {
        this.Quantidade = Quantidade;
    }

}

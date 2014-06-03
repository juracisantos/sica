package br.com.dynantec.type;

public enum TipoMovimento {

    DEPOSITO("Deposito"),
    RETIRADA("Retirada");
    
    private final String descricao;

    private TipoMovimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

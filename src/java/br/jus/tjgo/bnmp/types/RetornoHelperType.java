package br.jus.tjgo.bnmp.types;

public enum RetornoHelperType {

    MSG_OK("ok"),
    MSG_ERRO("erro"),;
    
    private final String descricao;

    private RetornoHelperType(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
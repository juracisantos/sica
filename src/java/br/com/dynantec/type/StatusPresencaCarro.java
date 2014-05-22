package br.com.dynantec.type;

public enum StatusPresencaCarro {

    PRESENTE("Presente"),
    AUSENTE("Ausente");
    
    private final String descricao;

    private StatusPresencaCarro(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

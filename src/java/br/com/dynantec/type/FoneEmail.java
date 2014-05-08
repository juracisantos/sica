package br.com.dynantec.type;

public enum FoneEmail {

    FIXO("Fixo"),
    CELULAR("Celular"),
    FAX("Fax"),
    EMAIL("E-mail"),
    SITE("Site");
    private final String descricao;

    private FoneEmail(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

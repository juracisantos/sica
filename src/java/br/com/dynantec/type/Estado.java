package br.com.dynantec.type;


public enum Estado {

    ACRE("Acre", "AC"),
    ALAGOAS("Alagoas", "AL"),
    AMAPA("Amapá", "AP"),
    AMAZONAS("Amazonas", "AM"),
    BAHIA("Bahia", "BA"),
    CEARA("Ceará", "CE"),
    DISTRITO_FEDERAL("Distrito Federal", "DF"),
    ESPIRITO_SANTO("Espírito Santo", "ES"),
    GOIAS("Goiás", "GO"),
    MARANHAO("Maranhão", "MA"),
    MATO_GROSSO("Mato Grosso", "MT"),
    MATO_GROSSO_DO_SUL("Mato Grosso do Sul", "MS"),
    MINAS_GERAIS("Minas Gerais", "MG"),
    PARA("Pará", "PA"),
    PARAIBA("Paraíba", "PB"),
    PARANA("Paraná", "PR"),
    PERNAMBUCO("Pernambuco", "PE"),
    PIAUI("Piauí", "PI"),
    RIO_DE_JANEIRO("Rio de Janeiro", "RJ"),
    RIO_GRANDE_DO_NORTE("Rio Grade do Norte", "RN"),
    RIO_GRANDE_DO_SUL("Rio Grande do Sul", "RS"),
    RONDONIA("Rondônio", "RO"),
    RORAIMA("Roraima", "RR"),
    SANTA_CATARINA("Santa Catarina", "SC"),
    SAO_PAULO("São Paulo", "SP"),
    SERGIPE("Sergipe", "SE"),
    TOCANTINS("Tocantins", "TO");
    
    private final String descricao;
    private final String sigla;

    private Estado(String descricao, String sigla) {
        this.descricao = descricao;
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSigla() {
        return sigla;
    }
}

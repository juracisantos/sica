package br.com.dynatec.controlador;

import br.com.dynantec.type.TipoMovimento;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class TipoMovimentoControl implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<TipoMovimento> tipoMovimentoList;

    public TipoMovimentoControl() {
        this.setTipoMovimentoList(Arrays.asList(TipoMovimento.values()));
    }

    public List<TipoMovimento> getTipoMovimentoList() {
        return tipoMovimentoList;
    }

    public void setTipoMovimentoList(List<TipoMovimento> tipoMovimentoList) {
        this.tipoMovimentoList = tipoMovimentoList;
    }

}

package br.com.dynatec.controlador;

import br.com.dynantec.type.FoneEmail;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean
@ApplicationScoped
public class TipoTelefoneControl implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<FoneEmail> tipos;

    public TipoTelefoneControl() {
        this.setTipos(Arrays.asList(FoneEmail.values()));
    }

    public List<FoneEmail> getTipos() {
        return tipos;
    }

    public final void setTipos(List<FoneEmail> tipos) {
        this.tipos = tipos;
    }
}

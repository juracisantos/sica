package br.com.dynatec.controlador;

import br.com.dynantec.type.Estado;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean
@ApplicationScoped
public class EstadosControl implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<Estado> estados;

    public EstadosControl() {
        this.setEstados(Arrays.asList(Estado.values()));
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public final void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
}

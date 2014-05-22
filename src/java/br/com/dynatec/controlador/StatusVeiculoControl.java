package br.com.dynatec.controlador;

import br.com.dynantec.type.StatusPresencaCarro;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class StatusVeiculoControl implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<StatusPresencaCarro> statusList;

    public StatusVeiculoControl() {
        this.setStatusList(Arrays.asList(StatusPresencaCarro.values()));
    }

    public List<StatusPresencaCarro> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<StatusPresencaCarro> statusList) {
        this.statusList = statusList;
    }

}

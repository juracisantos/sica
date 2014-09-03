/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.negocio;

import br.com.dynatec.persistencia.RelatoriosSistemaDAO;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jura
 */
public class RelatoriosSistemaNeg {

    private final RelatoriosSistemaDAO relatoriosSistemaDAO;

    public RelatoriosSistemaNeg() {
        this.relatoriosSistemaDAO = new RelatoriosSistemaDAO();
    }

    public List<Map<String, Object>> mensalisataPorPeriodo(Date inicio, Date termino) {
        return relatoriosSistemaDAO.mensalisataPorPeriodo(inicio, termino);
    }
     
}

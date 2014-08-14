/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import br.com.dynatec.negocio.RelatoriosSistemaNeg;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author jura
 */
@ManagedBean(name = "relatoriosSistemaControlador")
@RequestScoped
public class RelatoriosSistemaControlador implements Serializable {

    private static final long serialVersionUID = 2L;

    private Date inicio;
    private Date termino;

    private List<Map<String, Object>> tuplas;
    private final RelatoriosSistemaNeg negocio = new RelatoriosSistemaNeg();

    public RelatoriosSistemaControlador() {
    }

    public String index() {
        return "/relatorios/mensalista/index.jsf";
    }
    
    public String imprimir() {
        tuplas = negocio.mensalisataPorPeriodo(inicio, termino);
        return "/relatorios/mensalista/show.jsf";
    }
    
    public String consultar() {
        tuplas = negocio.mensalisataPorPeriodo(inicio, termino);
        return "/relatorios/mensalista/show.jsf";
    }
    

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public List<Map<String, Object>> getTuplas() {
        return tuplas;
    }

    public void setTuplas(List<Map<String, Object>> tuplas) {
        this.tuplas = tuplas;
    }
    
    

}

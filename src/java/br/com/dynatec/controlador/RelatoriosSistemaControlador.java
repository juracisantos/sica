/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import br.com.dynatec.helper.AcessoSituacao;
import br.com.dynatec.negocio.RelatoriosSistemaNeg;
import br.jus.tjgo.bnmp.util.UtilConexao;
import br.jus.tjgo.bnmp.util.UtilFaces;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author jura
 */
@ManagedBean(name = "relatoriosSistemaControlador")
@RequestScoped
public class RelatoriosSistemaControlador implements Serializable {

    private static final long serialVersionUID = 2L;

    private List<AcessoSituacao> acessosSituacao;

    private Date inicio;
    private Date termino;

    private List<Map<String, Object>> tuplas;
    private final RelatoriosSistemaNeg negocio = new RelatoriosSistemaNeg();

    public RelatoriosSistemaControlador() {
    }

    public String index() {
        return "/relatorios/mensalista/index.jsf";
    }

    public String acessosIndex() {
        return "/relatorios/acesso/index.jsf";
    }
    
    public String acessoFechamento() {
        return "/relatorios/fechamento/index.jsf";
    }

    public String imprimir() {
        String path = UtilFaces.getPathRelatorios();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        Map parametros = new HashMap<>();
        parametros.put("INICIO", inicio);
        parametros.put("TERMINO", termino);

        try {
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport(path + "mensalista/mensalista.jasper", parametros, UtilConexao.getConexao());

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (ClassNotFoundException | JRException | IOException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void imprimirAcessos() {
        String path = UtilFaces.getPathRelatorios();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        Map parametros = new HashMap<>();
        parametros.put("INICIO", inicio);
        parametros.put("TERMINO", termino);

        try {
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport(path + "acesso/acessos.jasper", parametros, UtilConexao.getConexao());

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (ClassNotFoundException | JRException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void imprimirFechamento() {
        String path = UtilFaces.getPathRelatorios();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        Map parametros = new HashMap<>();
        parametros.put("INICIO", inicio);
        parametros.put("TERMINO", termino);

        try {
            JasperPrint jasperPrint = JasperFillManager
                    .fillReport(path + "fechamento/fechamento_diario_agrupado.jasper", parametros, UtilConexao.getConexao());

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (ClassNotFoundException | JRException | IOException | SQLException e) {
            e.printStackTrace();
        }
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

    public List<AcessoSituacao> getAcessosSituacao() {
        return acessosSituacao;
    }

    public void setAcessosSituacao(List<AcessoSituacao> acessosSituacao) {
        this.acessosSituacao = acessosSituacao;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import br.com.dynatec.entidade.MovimentoCaixa;
import br.com.dynatec.helper.fianceiro.ExtratoDiario;
import br.com.dynatec.negocio.MovimentoCaixaNeg;
import br.jus.tjgo.bnmp.util.UtilDateTime;
import br.jus.tjgo.bnmp.util.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jura
 */
@ManagedBean(name = "movimentoCaixaControlador")
@SessionScoped
public class MovimentoCaixaControlador extends BaseControlador<MovimentoCaixa> implements Serializable {

    private static final long serialVersionUID = 2L;

    private MovimentoCaixaNeg negocio = new MovimentoCaixaNeg();
    private List<MovimentoCaixa> movimentos;
    private final HttpSession session = UtilFaces.getSession();
    private ExtratoDiario extratoDia;
    private Date dataDia = new Date();

    public MovimentoCaixaControlador() {
        setSelectedObject(new MovimentoCaixa());
        movimentos = new ArrayList<>();
    }

    public String listar() {
        this.extratoDia = this.negocio.extratoDiaSintetico(dataDia);
        this.movimentos = this.negocio.findAll();
        return "/movimentoscaixa/list.jsf";
    }

    public String novo() {
        this.setSelectedObject(new MovimentoCaixa());
        this.getSelectedObject().setUsuario_id(Integer.valueOf(session.getAttribute("usuario_id").toString()));
        this.getSelectedObject().setNomePessoa(session.getAttribute("pessoa_nome").toString());
        return "/movimentoscaixa/new.jsf";
    }

    public String salvar() {
        try {
            this.negocio.salvar(getSelectedObject());
            this.extratoDia = this.negocio.extratoDiaSintetico(this.getSelectedObject().getDataMovimento());
            return null;
        } catch (Exception ex) {
            UtilFaces.addErrorMessage(ex.getLocalizedMessage());
            return null;
        }
    }

    public String edit() {
        this.getSelectedObject().setNomePessoa(session.getAttribute("pessoa_nome").toString());
        return "/movimentoscaixa/edit.jsf";
    }

    public MovimentoCaixaNeg getNegocio() {
        return negocio;
    }

    public void setNegocio(MovimentoCaixaNeg negocio) {
        this.negocio = negocio;
    }

    public List<MovimentoCaixa> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(List<MovimentoCaixa> movimentos) {
        this.movimentos = movimentos;
    }

    public ExtratoDiario getExtratoDia() {
        return extratoDia;
    }

    public void setExtratoDia(ExtratoDiario extratoDia) {
        this.extratoDia = extratoDia;
    }
    
    public String getDataDiaStr() {
        return UtilDateTime.dateToString(this.dataDia);
    }
    
    public Date getDataDia() {
        return dataDia;
    }

    public void setDataDia(Date dataDia) {
        this.dataDia = dataDia;
    }

}

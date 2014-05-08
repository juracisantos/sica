/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import br.com.dynatec.entidade.Acesso;
import br.com.dynatec.entidade.Tabela;
import br.com.dynatec.negocio.AcessoNeg;
import br.com.dynatec.negocio.TabelaNeg;
import br.jus.tjgo.bnmp.util.UtilFaces;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jura
 */
@ManagedBean(name = "acessoControlador")
@SessionScoped
public class AcessoControlador extends BaseControlador<Acesso> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final TabelaNeg tabelaNegocio = new TabelaNeg();
    private final AcessoNeg negocio = new AcessoNeg();
    private Tabela tabelaSelecionada;

    public AcessoControlador() {
        setSelectedObject(new Acesso());
        tabelaSelecionada = this.tabelaNegocio.first();
    }

    public String novo() {
        Acesso acesso = new Acesso();
        acesso.setTabela(this.tabelaSelecionada);
        this.setSelectedObject(acesso);

        return "/acessos/add.jsf";
    }

    public String salvar() {
        try {
            this.tabelaSelecionada = getSelectedObject().getTabela();
            this.negocio.salvar(getSelectedObject());
            return novo();
        } catch (Exception ex) {
            UtilFaces.addErrorMessage(ex.getLocalizedMessage());
            return null;
        }
    }

    public String consultarCartao() {
        try {
            setSelectedObject(this.negocio.consultarECalcular(getSelectedObject()));
            return null;
        } catch (Exception ex) {
            UtilFaces.addErrorMessage(ex.getLocalizedMessage());
            return null;
        }
    }

    public String calcularTroco() {
        try {
            this.negocio.calcularTroco(getSelectedObject());
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            UtilFaces.addErrorMessage(ex.getLocalizedMessage());
            return null;
        }
    }

    public String edit() {
        return "/acessos/edit.jsf";
    }

    public Tabela getTabelaSelecionada() {
        return tabelaSelecionada;
    }

    public void setTabelaSelecionada(Tabela tabelaSelecionada) {
        this.tabelaSelecionada = tabelaSelecionada;
    }

}

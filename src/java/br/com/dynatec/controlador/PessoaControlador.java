/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import br.com.dynatec.entidade.Endereco;
import br.com.dynatec.entidade.Pessoa;
import br.com.dynatec.entidade.TelefoneEmail;
import br.com.dynatec.entidade.Veiculo;
import br.com.dynatec.negocio.PessoaNeg;
import br.jus.tjgo.bnmp.util.UtilFaces;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jura
 */
@ManagedBean
@SessionScoped
public class PessoaControlador extends BaseControlador<Pessoa> implements Serializable {

    private static final long serialVersionUID = 2L;

    private final PessoaNeg negocio = new PessoaNeg();
    private List<Pessoa> pessoas;
    private TelefoneEmail telefoneSelecionado;
    private Veiculo veiculoSelecionado;

    public PessoaControlador() {                     
    }

    public String addTelefone() {
        this.getSelectedObject().getPessoaTelefonesEmails().add(new TelefoneEmail());
        return null;
    }

    public String removeTelefone() {;
        int i = this.getSelectedObject().getPessoaTelefonesEmails().indexOf(this.telefoneSelecionado);
        this.getSelectedObject().getPessoaTelefonesEmails().remove(i);
        return null;
    }

    public String addVeiculo() {
        this.getSelectedObject().getVeiculos().add(new Veiculo());
        return null;
    }

    public String removeVeiculo() {;
        int i = this.getSelectedObject().getVeiculos().indexOf(this.veiculoSelecionado);
        this.getSelectedObject().getVeiculos().remove(i);
        return null;
    }

    public String listar() {
        this.pessoas = this.negocio.findAll();
        return "/mensalistas/list.jsf";
    }

    public String novo() {
        this.setSelectedObject(negocio.getNovaPessoa());
        return "/mensalistas/add.jsf";
    }

    public String salvar() {
        try {
            this.negocio.salvar(getSelectedObject());
            return listar();
        } catch (Exception ex) {
            UtilFaces.addErrorMessage(ex.getLocalizedMessage());
            return null;
        }
    }

    public String edit() {
        if (getSelectedObject().getEndereco() == null)
            getSelectedObject().setEndereco(new Endereco());
        return "/mensalistas/edit.jsf";
    }

    public String remover() {
        return null;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public TelefoneEmail getTelefoneSelecionado() {
        return telefoneSelecionado;
    }

    public void setTelefoneSelecionado(TelefoneEmail telefoneSelecionado) {
        this.telefoneSelecionado = telefoneSelecionado;
    }

    public Veiculo getVeiculoSelecionado() {
        return veiculoSelecionado;
    }

    public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
        this.veiculoSelecionado = veiculoSelecionado;
    }         

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import br.com.dynatec.entidade.Endereco;
import br.com.dynatec.entidade.Pessoa;
import br.com.dynatec.entidade.TelefoneEmail;
import br.com.dynatec.entidade.Veiculo;
import br.com.dynatec.negocio.CartaoNeg;
import br.com.dynatec.negocio.PessoaNeg;
import br.jus.tjgo.bnmp.util.UtilFaces;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author jura
 */
@ManagedBean
@SessionScoped
public class PessoaControlador extends BaseControlador<Pessoa> implements Serializable {

    private static final long serialVersionUID = 5L;

    private final PessoaNeg negocio = new PessoaNeg();
    private final CartaoNeg cartaoNeg = new CartaoNeg();
    
    private List<Pessoa> pessoas;
    private TelefoneEmail telefoneSelecionado;
    private Veiculo veiculoSelecionado;

    public PessoaControlador() {                     
    }

    public String addTelefone() {
        this.getSelectedObject().addTelefone(new TelefoneEmail());
        return null;
    }

    public String removeTelefone() {;
        int i = this.getSelectedObject().getTelefones().indexOf(this.telefoneSelecionado);
        this.getSelectedObject().getTelefones().remove(i);
        return null;
    }

    public String addVeiculo() {
        Veiculo veiculo = new Veiculo();
        
        String numCartao = cartaoNeg.gerarNumeroCartao();
        
        veiculo.setCartao(numCartao);
        this.getSelectedObject().addVeidulo(veiculo);
        return null;
    }

    public String removeVeiculo() {;
        int i = this.getSelectedObject().getVeiculos().indexOf(this.veiculoSelecionado);
        this.getSelectedObject().getVeiculos().remove(i);
        return null;
    }

    public String listar() {
        this.pessoas = this.negocio.findAll();
        return "/pessoas/list.jsf";
    }

    public String novo() {
        this.setSelectedObject(negocio.getNovaPessoa());
        return "/pessoas/add.jsf";
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
        return "/pessoas/edit.jsf";
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
    
    public SelectItem[] getPessoasSelectOne() {
        return UtilFaces.getSelectItems(negocio.findAll(), true);
    }
    
    public Pessoa getPessoa(Integer idPessoa) {
        return negocio.find(idPessoa);
    }
    
    @FacesConverter(forClass = Pessoa.class)
    public static class PessoasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PessoaControlador controller = (PessoaControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pessoaControlador");
            return controller.getPessoa(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Pessoa) {
                Pessoa o = (Pessoa) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Pessoa.class.getName());
            }
        }

    }

}

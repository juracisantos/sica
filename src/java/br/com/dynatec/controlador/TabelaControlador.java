/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import br.com.dynatec.entidade.Regra;
import br.com.dynatec.entidade.Tabela;
import br.com.dynatec.negocio.TabelaNeg;
import br.jus.tjgo.bnmp.util.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.persistence.OrderBy;

/**
 *
 * @author jura
 */
@ManagedBean(name = "tabelaControlador")
@SessionScoped
public class TabelaControlador extends BaseControlador<Tabela> implements Serializable {

    private static final long serialVersionUID = 1L;

    private TabelaNeg negocio = new TabelaNeg();
    private List<Tabela> tabelas;
    private Regra regraSelecionada;

    public TabelaControlador() {
        setSelectedObject(new Tabela());
        tabelas = new ArrayList<>();
    } 
    
    public String addRegra() {
        getSelectedObject().addRegra(new Regra());
        return null;
    }
    
    public String removeRegra() {
        int i = this.getSelectedObject().getRegras().indexOf(this.regraSelecionada);
        this.getSelectedObject().getRegras().remove(i);
        return null;
    }

    public String listar() {
        this.tabelas = this.negocio.findAll();
        return "/tabelas/list.jsf";
    }

    public String novo() {
        this.setSelectedObject(new Tabela());
        return "/tabelas/add.jsf";
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
    
    public SelectItem[] getTabelasSelectOne() {
        return UtilFaces.getSelectItems(negocio.findAll(), true);
    }
    
    public String edit() {
       return "/tabelas/edit.jsf"; 
    }

    public TabelaNeg getNegocio() {
        return negocio;
    }

    public void setNegocio(TabelaNeg negocio) {
        this.negocio = negocio;
    }

    public List<Tabela> getTabelas() {
        return tabelas;
    }

    public void setTabelas(List<Tabela> tabelas) {
        this.tabelas = tabelas;
    }
    
    public Tabela getTabela(Integer idTabela) {
        return negocio.find(idTabela);
    }

    public Regra getRegraSelecionada() {
        return regraSelecionada;
    }

    public void setRegraSelecionada(Regra regraSelecionada) {
        this.regraSelecionada = regraSelecionada;
    }        
    
    @FacesConverter(forClass = Tabela.class)
    public static class TabelasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TabelaControlador controller = (TabelaControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tabelaControlador");
            return controller.getTabela(getKey(value));
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
            if (object instanceof Tabela) {
                Tabela o = (Tabela) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Tabela.class.getName());
            }
        }

    }

}

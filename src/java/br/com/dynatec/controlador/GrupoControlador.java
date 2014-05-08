/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import br.com.dynatec.entidade.Grupo;
import br.com.dynatec.negocio.GrupoNeg;
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

/**
 *
 * @author jura
 */
@ManagedBean(name = "grupoControlador")
@SessionScoped
public class GrupoControlador extends BaseControlador<Grupo> implements Serializable {

    private static final long serialVersionUID = 2L;

    private GrupoNeg negocio = new GrupoNeg();
    private List<Grupo> grupos;

    public GrupoControlador() {
        setSelectedObject(new Grupo());
        grupos = new ArrayList<>();
    }

    public String listar() {
        this.grupos = this.negocio.findAll();
        return "/grupos/list.jsf";
    }

    public String novo() {
        this.setSelectedObject(new Grupo());
        return "/grupos/new.jsf";
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
    
    public SelectItem[] getGruposSelectOne() {
        return UtilFaces.getSelectItems(negocio.findAll(), true);
    }
    
    public String edit() {
       return "/grupos/edit.jsf"; 
    }

    public GrupoNeg getNegocio() {
        return negocio;
    }

    public void setNegocio(GrupoNeg negocio) {
        this.negocio = negocio;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    public Grupo getGrupo(Integer idGrupo) {
        return negocio.find(idGrupo);
    }
    
    @FacesConverter(forClass = Grupo.class)
    public static class GruposControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GrupoControlador controller = (GrupoControlador) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "grupoControlador");
            return controller.getGrupo(getKey(value));
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
            if (object instanceof Grupo) {
                Grupo o = (Grupo) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Grupo.class.getName());
            }
        }

    }

}

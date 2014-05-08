/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import br.com.dynatec.entidade.Usuario;
import br.com.dynatec.negocio.UsuarioNeg;
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
public class UsuarioControlador extends BaseControlador<Usuario> implements Serializable {

    private static final long serialVersionUID = 2L;

    private final UsuarioNeg negocio = new UsuarioNeg();
    private List<Usuario> usuarios;

    public UsuarioControlador() {
        setSelectedObject(new Usuario());
    }
    
    public String listar() {
        this.usuarios = this.negocio.findAll();
        return "/usuario/list.jsf";
    }

    public String novo() {
        this.setSelectedObject(new Usuario());
        return "/usuario/add.jsf";
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
       return "/usuario/edit.jsf"; 
    }
    
    public String remover() {
        return null;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import br.com.dynatec.entidade.Configuracao;
import br.com.dynatec.negocio.ConfiguracaoNeg;
import br.jus.tjgo.bnmp.util.UtilFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author jura
 */
@ManagedBean(name = "configuracaoControlador")
@SessionScoped
public class ConfiguracaoControlador extends BaseControlador<Configuracao> implements Serializable {

    private static final long serialVersionUID = 2L;

    private ConfiguracaoNeg negocio = new ConfiguracaoNeg();
    private List<Configuracao> configuracoes;

    public ConfiguracaoControlador() {
        setSelectedObject(new Configuracao());
        configuracoes = new ArrayList<>();
    }

    public String listar() {
        this.configuracoes = this.negocio.findAll();
        return "/configuracoes/list.jsf";
    }

    public String novo() {
        this.setSelectedObject(new Configuracao());
        return "/configuracoes/new.jsf";
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
    
    public SelectItem[] getConfiguracaosSelectOne() {
        return UtilFaces.getSelectItems(negocio.findAll(), true);
    }
    
    public String edit() {
       return "/configuracoes/edit.jsf"; 
    }

    public ConfiguracaoNeg getNegocio() {
        return negocio;
    }

    public void setNegocio(ConfiguracaoNeg negocio) {
        this.negocio = negocio;
    }

    public List<Configuracao> getConfiguracaos() {
        return configuracoes;
    }

    public void setConfiguracaos(List<Configuracao> configuracoes) {
        this.configuracoes = configuracoes;
    }
    
    public Configuracao getConfiguracao(Integer idConfiguracao) {
        return negocio.find(idConfiguracao);
    }        

}

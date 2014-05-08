/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.negocio;

import br.com.dynatec.entidade.Grupo;
import br.com.dynatec.persistencia.GrupoDao;
import java.util.List;

/**
 *
 * @author jura
 */
public class GrupoNeg {
   
    private final GrupoDao grupoDao;
    
    public GrupoNeg() {
        this.grupoDao = new GrupoDao();
    }
    
    public List<Grupo> findAll() {
        return grupoDao.findAll();
    }
    
    public Grupo find(Integer idGrupo) {
        return grupoDao.find(idGrupo);
    }
    
    public Grupo salvar(Grupo grupo) throws Exception  {
        return grupoDao.salvar(grupo);
    }
}

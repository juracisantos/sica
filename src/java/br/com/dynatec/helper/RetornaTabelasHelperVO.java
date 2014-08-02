/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.helper;

import br.com.dynatec.entidade.Tabela;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jura
 */
@XmlRootElement
public class RetornaTabelasHelperVO  extends RetornoHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Tabela> tabelas;

    public List<Tabela> getTabelas() {
        return tabelas;
    }

    public void setTabelas(List<Tabela> tabelas) {
        this.tabelas = tabelas;
    }

}

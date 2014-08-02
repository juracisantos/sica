/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.helper;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jura
 */
@XmlRootElement
public class RetornoLoginVO extends RetornoHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    private String usuario_id;
    private String usuario_nome;
    private String pessoa_nome;
    private String usuario_grupo;

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_nome() {
        return usuario_nome;
    }

    public void setUsuario_nome(String usuario_nome) {
        this.usuario_nome = usuario_nome;
    }

    public String getPessoa_nome() {
        return pessoa_nome;
    }

    public void setPessoa_nome(String pessoa_nome) {
        this.pessoa_nome = pessoa_nome;
    }

    public String getUsuario_grupo() {
        return usuario_grupo;
    }

    public void setUsuario_grupo(String usuario_grupo) {
        this.usuario_grupo = usuario_grupo;
    }

}

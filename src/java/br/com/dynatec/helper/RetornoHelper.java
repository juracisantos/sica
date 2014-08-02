package br.com.dynatec.helper;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class RetornoHelper {

    public static final String STATUS_OK = "ok";
    public static final String STATUS_ERRO = "erro";    
    
    private String status;
    private String mensagem;    

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }    
}
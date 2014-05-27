/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.negocio;

import br.com.dynantec.type.Estado;
import br.com.dynatec.entidade.Acesso;
import br.com.dynatec.entidade.Configuracao;
import br.com.dynatec.entidade.Endereco;
import br.com.dynatec.entidade.Grupo;
import br.com.dynatec.entidade.Pessoa;
import br.com.dynatec.entidade.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jura
 */
public class Seed implements Serializable {

    private static final long serialVersionUID = 1l;
    
    public static void populaBanco() {
        UsuarioNeg usuarioNeg = new UsuarioNeg();
        GrupoNeg grupoNeg = new GrupoNeg();
        AcessoNeg acessoNeg = new AcessoNeg();
        PessoaNeg pessoaNeg = new PessoaNeg();
        ConfiguracaoNeg configuracaoNeg = new ConfiguracaoNeg();

        try {
            Grupo g;
            g = grupoNeg.findByNome("Administrador");
            if (g == null) {
                g = new Grupo();
                g.setNome("Administrador");
                grupoNeg.salvar(g);
            }

            g = grupoNeg.findByNome("Caixa");
            if (g == null) {
                g = new Grupo();
                g.setNome("Caixa");
                grupoNeg.salvar(g);
            }

            Acesso a;
            a = acessoNeg.find_by_numero_cartao("123456789012");
            if (a == null) {
                a = new Acesso();
                a.setAtivo(Boolean.TRUE);
                a.setCartao("123456789012");
                a.setEntrada(new Date());
                a.setUsuarioRegistrouEntrada(null);
                acessoNeg.salvar(a);
            }

            a = acessoNeg.find_by_numero_cartao("123456789013");
            if (a == null) {
                a = new Acesso();
                a.setAtivo(Boolean.TRUE);
                a.setCartao("123456789013");
                a.setEntrada(new Date());
                a.setUsuarioRegistrouEntrada(null);
                acessoNeg.salvar(a);
            }

            Pessoa p;
            p = pessoaNeg.findByEmail("admin@dynantec.com.br");
            if (p == null) {
                p = new Pessoa();                
                p.setNome("Dynantec");
                p.setAtivo(Boolean.TRUE);
                p.setCpf("22641628139");
                p.setCreatedAt(new Date());
                p.setDataNascimento(new Date());
                p.setEmail("admin@dynantec.com.br");
                     
                Endereco endereco = new Endereco();
                endereco.setCep("38380000");
                endereco.setComplemento("Rua 1");
                endereco.setEstado(Estado.GOIAS);
                endereco.setLogradouro("Altamira");
                endereco.setMunicipio("Goiânia");
                endereco.setSetor("Altamira"); 
                p.setEndereco(endereco);
                
                p.setRg("1234567");
                p.setUpdatedAt(new Date());
                p = pessoaNeg.salvar(p);
            }

            Usuario u;
            Grupo grupo = grupoNeg.findByNome("Administrador");
            int qtd = usuarioNeg.getQtdUsuarios();
            if (qtd == 0) {
                u = new Usuario();
                u.setAtivo(Boolean.TRUE);
                u.setGrupo(grupo);
                u.setNome("admin");                
                u.setSenha("cain2abel");
                u.setConfSenha("cain2abel");                
                u.seteAdmin(Boolean.TRUE);
                u.setPessoa(p);
                usuarioNeg.salvar(u);
            }
            
            Configuracao conf;
            conf = configuracaoNeg.findByDescricao("Padrão");
            if (conf == null) {
                conf = new Configuracao();
                conf.setDescricao("Padrão");
                conf.setTolerancia(10);
                conf.setToleranciaMensalista(5);
                configuracaoNeg.salvar(conf);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(AcessoNeg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

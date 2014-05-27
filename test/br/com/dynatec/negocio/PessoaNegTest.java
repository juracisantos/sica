/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.dynatec.negocio;

import br.com.dynantec.type.Estado;
import br.com.dynatec.entidade.Endereco;
import br.com.dynatec.entidade.Pessoa;
import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jura
 */
public class PessoaNegTest {
    
    private static EJBContainer containerSell;
    private static PessoaNeg negocio;
    
    public PessoaNegTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        containerSell = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        negocio = new PessoaNeg();
    }
    
    @AfterClass
    public static void tearDownClass() {
        containerSell.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetNovaPessoa() {
        System.out.println("getNovaPessoa");        
        Pessoa result = negocio.getNovaPessoa();
        assertNotNull(result);
        fail("get pessoa.");
    }
    
    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Pessoa result = negocio.getNovaPessoa();
        result.setAtivo(true);
        result.setCpf("321321321-32");
        result.setCreatedAt(new Date());
        result.setDataNascimento(new Date());
        result.setEmail("teset@teste.com.br");
        
        Endereco e = new Endereco();
        e.setCep("38380100");
        e.setComplemento("Complemento");
        e.setEstado(Estado.ACRE);
        e.setLogradouro("Logradouro");
        e.setMunicipio("Municipio");
        e.setSetor("Setor");
                
        result.setEndereco(e);
        
        result.setNome("Nome");
        result.setRg("rg");
        result.setUpdatedAt(new Date());
        
        result = negocio.salvar(result);
        assertTrue(result.getId() != null);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindAll() {
        System.out.println("findAll");
        PessoaNeg instance = new PessoaNeg();
        List<Pessoa> expResult = null;
        List<Pessoa> result = instance.findAll();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetQtdPessoas() {
        System.out.println("getQtdPessoas");
        PessoaNeg instance = new PessoaNeg();
        int expResult = 0;
        int result = instance.getQtdPessoas();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }   

    @Test
    public void testFindByNome() {
        System.out.println("findByNome");
        String nome = "";
        PessoaNeg instance = new PessoaNeg();
        Pessoa expResult = null;
        Pessoa result = instance.findByNome(nome);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testFindByEmail() {
        System.out.println("findByEmail");
        String email = "";
        PessoaNeg instance = new PessoaNeg();
        Pessoa expResult = null;
        Pessoa result = instance.findByEmail(email);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}

package br.com.dynatec.negocio;

import br.com.dynatec.entidade.Endereco;
import br.com.dynatec.entidade.Pessoa;
import br.com.dynatec.entidade.TelefoneEmail;
import br.com.dynatec.entidade.Veiculo;
import br.com.dynatec.entidade.VeiculoLogStatusPreseca;
import br.com.dynatec.persistencia.PessoaDao;
import java.util.Date;
import java.util.List;

public class PessoaNeg {

    private final PessoaDao pessoaDao;
    private final CartaoNeg cartaoNeg = new CartaoNeg();
    
    public Pessoa getNovaPessoa() {
        Pessoa pessoa = new Pessoa();
        
        pessoa.setEndereco(new Endereco());
        pessoa.addTelefone(new TelefoneEmail());               
//        pessoa.addVeiculo(new Veiculo());                
        
        return pessoa;
    }
    
    public PessoaNeg() {
        this.pessoaDao = new PessoaDao();
    }
    
    public List<Pessoa> findAll() {
        return this.pessoaDao.findAll();
    }
    
    public int getQtdPessoas() {
        return this.pessoaDao.getQtdPessoas();
    }
    
    public Pessoa salvar(Pessoa pessoa) throws Exception {

//        for (Veiculo v : pessoa.getVeiculos()) {
//            if (cartaoNeg.teveAlteracaoStatusPresencaCarro(v)) {
//                VeiculoLogStatusPreseca sp = new VeiculoLogStatusPreseca();
//                sp.setCreatedAt(new Date());
//                sp.setPessoa(pessoa);
//                sp.setUpdatedAt(new Date());
//                sp.setVeiculo(v);
//                v.getLogsStatusPresenca().add(sp);
//            }
//        }

        return pessoaDao.salvar(pessoa);
    }
    
    public Pessoa findByNome(String nome) {
        return this.pessoaDao.findByNome(nome);
    }
    
    public Pessoa findByEmail(String email) {
        return this.pessoaDao.findByEmail(email);
    } 
    
    public Pessoa findByCartao(String cartao) {
        return this.pessoaDao.findByCartao(cartao);
    }
    
    public Pessoa find(Integer idPessoa) {
        return pessoaDao.find(idPessoa);
    }
    
    
    
}

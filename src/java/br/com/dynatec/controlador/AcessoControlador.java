/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import br.com.dynantec.type.TipoMovimento;
import br.com.dynatec.entidade.Acesso;
import br.com.dynatec.entidade.Configuracao;
import br.com.dynatec.entidade.MovimentoCaixa;
import br.com.dynatec.entidade.Pessoa;
import br.com.dynatec.entidade.Tabela;
import br.com.dynatec.helper.fianceiro.ExtratoDiario;
import br.com.dynatec.negocio.AcessoNeg;
import br.com.dynatec.negocio.ConfiguracaoNeg;
import br.com.dynatec.negocio.MovimentoCaixaNeg;
import br.com.dynatec.negocio.TabelaNeg;
import br.jus.tjgo.bnmp.util.UtilDateTime;
import br.jus.tjgo.bnmp.util.UtilFaces;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jura
 */
@ManagedBean(name = "acessoControlador")
@SessionScoped
public class AcessoControlador extends BaseControlador<Acesso> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final HttpSession session = UtilFaces.getSession();
    private final TabelaNeg tabelaNegocio = new TabelaNeg();
    private AcessoNeg negocio;
    private final ConfiguracaoNeg configuracaoNeg;
    private final MovimentoCaixaNeg movimentoCaixaNeg;
    private ExtratoDiario extratoDia;
    private Tabela tabelaSelecionada;
    private Date dataTrandacaoFinanceira;
    private MovimentoCaixa movimentoCaixa;
    private Pessoa pessoa;
    private boolean registraSaida;
    private boolean registraPeriodoAdicional;

    public AcessoControlador() {
        this.movimentoCaixaNeg = new MovimentoCaixaNeg();
        setSelectedObject(new Acesso());
        tabelaSelecionada = this.tabelaNegocio.first();
        pessoa = null;
        configuracaoNeg = new ConfiguracaoNeg();
        negocio = new AcessoNeg();
    }

    public String novo() {
        this.dataTrandacaoFinanceira = new Date();
        this.addNovoAcesso();

        return "/acessos/add.jsf";
    }

    public void imprimir(ActionEvent actionevent) {
        this.dataTrandacaoFinanceira = new Date();
        this.addNovoAcesso();
        System.out.println("Impresso....");
    }
    
    public String salvarPeriodoAdicional() {
       try {            
            this.tabelaSelecionada = getSelectedObject().getTabela();
            this.getSelectedObject().setDataTransacaoFinaceira(this.dataTrandacaoFinanceira);
            
            this.negocio.salvarPeriodoAdicional(getSelectedObject());
            
            //setSelectedObject(this.negocio.consultarECalcular(getSelectedObject()));
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            UtilFaces.addErrorMessage(ex.getLocalizedMessage());
            return null;
        }
    }
        
    public String salvar() {
        try {
            if (this.pessoa != null) {
                this.movimentoCaixa.setMensalisa(Boolean.TRUE);
                this.movimentoCaixa.setDataMovimento(new Date());
                this.movimentoCaixaNeg.salvar(movimentoCaixa);
                
                this.negocio.salvarPessoa(pessoa);
            }   
            this.tabelaSelecionada = getSelectedObject().getTabela();
            this.getSelectedObject().setDataTransacaoFinaceira(this.dataTrandacaoFinanceira);
            
            this.negocio.salvar(getSelectedObject());
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            UtilFaces.addErrorMessage(ex.getLocalizedMessage());
            return null;
        }
    }

    public String consultarCartao() {
        try {
            //Verificar se é mensalista
            negocio = new AcessoNeg();
            pessoa = this.negocio.findPessoaByCartao(getSelectedObject().getCartao());
            if (pessoa != null) {
                this.movimentoCaixa = new MovimentoCaixa();
                this.movimentoCaixa.setDataMovimento(dataTrandacaoFinanceira);
                this.movimentoCaixa.setNomePessoa(session.getAttribute("pessoa_nome").toString());
                this.movimentoCaixa.setTipoMovimento(TipoMovimento.DEPOSITO);
                this.movimentoCaixa.setUsuario_id(Integer.valueOf(session.getAttribute("usuario_id").toString()));
                this.movimentoCaixa.setValor(pessoa.getVeiculos().get(0).getValorMensalidade());
                this.movimentoCaixa.setMensalisa(Boolean.TRUE);

                List<Configuracao> configuracoes = configuracaoNeg.findAll();
                Integer diasIncrementar = configuracoes == null ? 0 : configuracoes.get(0).getToleranciaMensalista();

                Date vencimento = UtilDateTime.proximoMesPassandoDia(this.pessoa.getVeiculos().get(0).getDataVencimento());
                Date liberadoAte = UtilDateTime.incrementarDiasData(vencimento, diasIncrementar);
                this.pessoa.getVeiculos().get(0).setLiberado_ate(liberadoAte);
                this.pessoa.getVeiculos().get(0).setDataVencimento(vencimento);
            } else {
                setSelectedObject(this.negocio.consultarECalcular(getSelectedObject()));
                if (getSelectedObject() == null) {
                    this.addNovoAcesso();
                    UtilFaces.addErrorMessage("Cartão não encontrado.");
                }
            }
            return null;
        } catch (Exception ex) {
            UtilFaces.addErrorMessage(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public String edit() {
        return "/acessos/edit.jsf";
    }

    public Tabela getTabelaSelecionada() {
        return tabelaSelecionada;
    }

    public void setTabelaSelecionada(Tabela tabelaSelecionada) {
        this.tabelaSelecionada = tabelaSelecionada;
    }

    public MovimentoCaixa getMovimentoCaixa() {
        return movimentoCaixa;
    }

    public void setMovimentoCaixa(MovimentoCaixa movimentoCaixa) {
        this.movimentoCaixa = movimentoCaixa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ExtratoDiario getExtratoDia() {
        return extratoDia;
    }

    public void setExtratoDia(ExtratoDiario extratoDia) {
        this.extratoDia = extratoDia;
    }

    private void addNovoAcesso() {
        Acesso acesso = new Acesso();
        acesso.setDataTransacaoFinaceira(this.dataTrandacaoFinanceira);
        acesso.setTabela(this.tabelaSelecionada);
        this.setSelectedObject(acesso);
    }
    
    public boolean isRegistraSaida() {
        return this.getSelectedObject().getId() != null && this.getSelectedObject().getRegistroSaida() == null;
    }

    public void setRegistraSaida(boolean registraSaida) {
        this.registraSaida = registraSaida;
    }

    public boolean isRegistraPeriodoAdicional() {
        return this.getSelectedObject().getId() != null && this.getSelectedObject().getRegistroSaida() != null;
    }

    public void setRegistraPeriodoAdicional(boolean registraPeriodoAdicional) {
        this.registraPeriodoAdicional = registraPeriodoAdicional;
    }        
}

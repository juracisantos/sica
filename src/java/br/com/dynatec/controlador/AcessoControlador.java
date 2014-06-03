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
import br.com.dynatec.negocio.CartaoNeg;
import br.com.dynatec.negocio.ConfiguracaoNeg;
import br.com.dynatec.negocio.MovimentoCaixaNeg;
import br.com.dynatec.negocio.PessoaNeg;
import br.com.dynatec.negocio.TabelaNeg;
import br.jus.tjgo.bnmp.util.UtilDateTime;
import br.jus.tjgo.bnmp.util.UtilFaces;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    private final AcessoNeg negocio = new AcessoNeg();
    private final ConfiguracaoNeg configuracaoNeg;
    private final CartaoNeg cartaoNeg;
    private final MovimentoCaixaNeg movimentoCaixaNeg;
    private ExtratoDiario extratoDia;
    private Tabela tabelaSelecionada;
    private Date dataTrandacaoFinanceira;
    private MovimentoCaixa movimentoCaixa;
    private Pessoa pessoa;

    public AcessoControlador() {
        this.movimentoCaixaNeg = new MovimentoCaixaNeg();
        setSelectedObject(new Acesso());
        tabelaSelecionada = this.tabelaNegocio.first();
        pessoa = null;
        configuracaoNeg = new ConfiguracaoNeg();
        cartaoNeg = new CartaoNeg();
    }

    public String novo() {
        this.dataTrandacaoFinanceira = new Date();
        Acesso acesso = new Acesso();
        acesso.setDataTransacaoFinaceira(this.dataTrandacaoFinanceira);
        acesso.setTabela(this.tabelaSelecionada);
        this.setSelectedObject(acesso);

        return "/acessos/add.jsf";
    }

    public String salvar() {
        try {
            if (this.pessoa == null) {
                this.tabelaSelecionada = getSelectedObject().getTabela();
                
                this.negocio.salvar(getSelectedObject());
            } else {
                this.movimentoCaixa.setMensalisa(Boolean.TRUE);
                this.movimentoCaixa.setDataMovimento(new Date());
                this.movimentoCaixaNeg.salvar(movimentoCaixa);
                this.negocio.salvarPessoa(pessoa);
            }
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
                    UtilFaces.addErrorMessage("Cartão não encontrado.");
                }
            }
            return null;
        } catch (Exception ex) {
            UtilFaces.addErrorMessage(ex.getLocalizedMessage());
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
}

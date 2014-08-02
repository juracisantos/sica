/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador.ws;

import br.com.dynantec.type.TipoMovimento;
import br.com.dynatec.entidade.Acesso;
import br.com.dynatec.entidade.Configuracao;
import br.com.dynatec.entidade.MovimentoCaixa;
import br.com.dynatec.entidade.Pessoa;
import br.com.dynatec.entidade.Usuario;
import br.com.dynatec.helper.MovimentoCaixaHelperVO;
import br.com.dynatec.helper.RetornaTabelasHelperVO;
import br.com.dynatec.helper.RetornoConsultaCartaoVO;
import br.com.dynatec.helper.RetornoHelper;
import br.com.dynatec.helper.RetornoLoginVO;
import br.com.dynatec.helper.fianceiro.ExtratoDiario;
import br.com.dynatec.negocio.AcessoNeg;
import br.com.dynatec.negocio.ConfiguracaoNeg;
import br.com.dynatec.negocio.MovimentoCaixaNeg;
import br.com.dynatec.negocio.TabelaNeg;
import br.com.dynatec.negocio.UsuarioNeg;
import br.jus.tjgo.bnmp.util.UtilDateTime;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jura
 */
@WebService(serviceName = "AcessoControle")
public class AcessoControle {

    private AcessoNeg negocio;
    private ConfiguracaoNeg configuracaoNeg;
    private MovimentoCaixaNeg movimentoCaixaNeg;

    /**
     * Operação de Web service
     *
     * @param cartao
     * @param dataTransasaoFinanceira - No formato dd/MM/yyyy
     * @param codTabela
     * @param desconto
     * @param valorRecebido
     * @param usuario_id
     * @param placaCarro
     * @return
     */
    @WebMethod(operationName = "consultaCartao")
    public RetornoConsultaCartaoVO consultaCartao(
            @WebParam(name = "cartao") final String cartao,
            @WebParam(name = "dataTransasaoFinanceira") String dataTransasaoFinanceira,
            @WebParam(name = "codTabela") final Integer codTabela,
            @WebParam(name = "desconto") final Double desconto,
            @WebParam(name = "valorRecebido") final Double valorRecebido,
            @WebParam(name = "usuario_id") final Integer usuario_id,
            @WebParam(name = "placaCarro") final String placaCarro) {

        return processarAcesso(dataTransasaoFinanceira, cartao, desconto, valorRecebido, codTabela, false, usuario_id, placaCarro);
    }

    @WebMethod(operationName = "processaCartao")
    public RetornoConsultaCartaoVO processaCartao(
            @WebParam(name = "cartao") final String cartao,
            @WebParam(name = "dataTransasaoFinanceira") String dataTransasaoFinanceira,
            @WebParam(name = "codTabela") final Integer codTabela,
            @WebParam(name = "desconto") final Double desconto,
            @WebParam(name = "valorRecebido") final Double valorRecebido,
            @WebParam(name = "usuario_id") final Integer usuario_id,
            @WebParam(name = "placaCarro") final String placaCarro) {

        return processarAcesso(dataTransasaoFinanceira, cartao, desconto, valorRecebido, codTabela, true, usuario_id, placaCarro);
    }

    private RetornoConsultaCartaoVO processarAcesso(String dataTransasaoFinanceira, final String cartao, final Double desconto,
            final Double valorRecebido, final Integer codTabela, final Boolean persiste, final Integer usuario_id, final String placaCarro) {
        //Verificar se é mensalista
        negocio = new AcessoNeg();
        configuracaoNeg = new ConfiguracaoNeg();

        Date dataTransacao;
        try {
            dataTransacao = UtilDateTime.strToDate(dataTransasaoFinanceira);
        } catch (ParseException ex) {
            dataTransacao = new Date();
            Logger.getLogger(AcessoControle.class.getName()).log(Level.SEVERE, null, ex);
        }

        Acesso acesso = new Acesso();
        RetornoConsultaCartaoVO retorno = new RetornoConsultaCartaoVO();
        Pessoa pessoa = this.negocio.findPessoaByCartao(cartao);
        MovimentoCaixa movimentoCaixa = new MovimentoCaixa();

        if (pessoa != null) {
            movimentoCaixa.setDataMovimento(dataTransacao);
            movimentoCaixa.setTipoMovimento(TipoMovimento.DEPOSITO);
            movimentoCaixa.setValor(pessoa.getVeiculos().get(0).getValorMensalidade());
            movimentoCaixa.setMensalisa(Boolean.TRUE);
            movimentoCaixa.setDesconto(desconto);
            movimentoCaixa.setValorRecebido(valorRecebido);
            movimentoCaixa.setMensalista_id(pessoa.getId());
            movimentoCaixa.setUsuario_id(usuario_id);

            List<Configuracao> configuracoes = configuracaoNeg.findAll();
            Integer diasIncrementar = configuracoes == null ? 0 : configuracoes.get(0).getToleranciaMensalista();

            Date vencimento = UtilDateTime.proximoMesPassandoDia(pessoa.getVeiculos().get(0).getDataVencimento());
            Date liberadoAte = UtilDateTime.incrementarDiasData(vencimento, diasIncrementar);
            pessoa.getVeiculos().get(0).setLiberado_ate(liberadoAte);
            pessoa.getVeiculos().get(0).setDataVencimento(vencimento);

            retorno.setStatus(RetornoHelper.STATUS_OK);
            retorno.setMensagem("Operação realizada com sucesso.");
            if (persiste) {
                MovimentoCaixaNeg movimentoCaixaNeg = new MovimentoCaixaNeg();
                try {
                    movimentoCaixaNeg.salvar(movimentoCaixa);
                    this.negocio.salvarPessoa(pessoa);                    
                } catch (Exception ex) {
                    Logger.getLogger(AcessoControle.class.getName()).log(Level.SEVERE, null, ex);
                    retorno.setStatus(RetornoHelper.STATUS_ERRO);
                    retorno.setMensagem("Operação não realizada: Motivo: " + ex.getMessage());
                }
            }
        } else {
            try {
                acesso = this.negocio.consultarECalcular(cartao, codTabela, valorRecebido, desconto);                                
                if (persiste) {
                    acesso.setPlacaVeiculo(placaCarro);
                    this.negocio.salvar(acesso);
                    System.out.println("Persistido..:" + acesso.getId());
                }
                retorno.setStatus(RetornoHelper.STATUS_OK);
                retorno.setMensagem("Consulta realizada com sucesso.");
            } catch (Exception ex) {
                retorno.setMensagem("Operação não realizada: Motivo: " + ex.getMessage());
                retorno.setStatus(RetornoHelper.STATUS_ERRO);
                Logger.getLogger(AcessoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        retorno = popuplaRetornoConsultaCartao(retorno, acesso, pessoa, movimentoCaixa);
        
        return retorno;
    }

    /**
     * Operação de Web service
     *
     * @param login
     * @param senha
     * @return
     */
    @WebMethod(operationName = "login")
    public RetornoLoginVO login(@WebParam(name = "login") final String login, @WebParam(name = "senha") final String senha) {
        RetornoLoginVO retorno = new RetornoLoginVO();
        UsuarioNeg usuarioNeg = new UsuarioNeg();
        Usuario usuario = new Usuario();
        boolean usuarioLogado;

        usuario = usuarioNeg.findByUserName(login);
        try {
            if ((usuario
                    != null) && (usuarioNeg.validaSenha(usuario.getSenha(), senha))) {
                usuarioLogado = true;
                retorno.setStatus(RetornoHelper.STATUS_OK);
                retorno.setMensagem("Logado com sucesso.");
            } else {
                usuarioLogado = false;
            }
        } catch (Exception ex) {
            Logger.getLogger(AcessoControle.class.getName()).log(Level.SEVERE, null, ex);
            usuarioLogado = false;
            retorno.setStatus(RetornoHelper.STATUS_ERRO);
            retorno.setMensagem(ex.getMessage());
        }

        if (usuarioLogado) {
            retorno.setPessoa_nome(usuario.getPessoa().getNome());
            retorno.setUsuario_grupo(usuario.getGrupo().getNome());
            retorno.setUsuario_id(usuario.getId().toString());
            retorno.setUsuario_nome(usuario.getNome());
        }
        return retorno;
    }

    /**
     * Operação de Web service
     *
     * @return
     */
    @WebMethod(operationName = "recuperaTabelas")
    public RetornaTabelasHelperVO recuperaTabelas() {
        TabelaNeg tabelaNeg = new TabelaNeg();

        RetornaTabelasHelperVO retorno = new RetornaTabelasHelperVO();
        retorno.setTabelas(tabelaNeg.findAll());
        return retorno;
    }

    private RetornoConsultaCartaoVO popuplaRetornoConsultaCartao(RetornoConsultaCartaoVO retorno, Acesso acesso, Pessoa pessoa, MovimentoCaixa movimentoCaixa) {
        retorno.setCartao(acesso.getCartao());

        retorno.setMensalista(pessoa != null);

        if (pessoa == null) {
            if (acesso.getTabela() != null) {
                retorno.setTabela_id(acesso.getTabela().getId());
            }
            retorno.setDescontoAtual(acesso.getDescontoAtual());
            retorno.setValorRecebido(acesso.getValorRecebido());
            retorno.setPlacaVeiculo(acesso.getPlacaVeiculo());

            retorno.setDesconboAcumulado(acesso.getDescontoAcumulado());

            retorno.setEntrada(acesso.getEntrada());
            retorno.setSaida(acesso.getRegistroSaida());
            retorno.setPermanencia(acesso.getPermancencia());
            retorno.setLimiteParaSair(acesso.getLimiteParaSair());
            retorno.setLimiteParaSairEmMinutos(acesso.getMinutosParaSair());

            retorno.setValorCobrado(acesso.getValorCobrado());
            retorno.setValorJaPago(acesso.getUltimoValorPago());
            retorno.setValorReceber(acesso.getValorAReceber());

            retorno.setTroco(acesso.getTroco());
            retorno.setUltimoValorPago(acesso.getUltimoValorPago());
        } else {
            retorno.setNomeMensalista(pessoa.getNome());
            retorno.setCpf(pessoa.getCpf());
            retorno.setQtdCartoes(pessoa.getQtdVeiculos());
            retorno.setTipoTransacao(movimentoCaixa.getTipoMovimento().getDescricao());
            retorno.setValorReceber(movimentoCaixa.getValor());
            retorno.setDescontoAtual(movimentoCaixa.getDesconto());
            retorno.setValorRecebido(movimentoCaixa.getValorRecebido());
            retorno.setTroco(movimentoCaixa.getTroco());
            retorno.setProximoVencimento(pessoa.getVeiculos().get(0).getDataVencimento());
        }

        return retorno;
    }

    @WebMethod(operationName = "registrarMovimento")
    public br.com.dynatec.helper.MovimentoCaixaHelperVO registrarMovimento(@WebParam(name = "movimentoCaixa") br.com.dynatec.helper.MovimentoCaixaHelperVO movimentoCaixa) {
        movimentoCaixaNeg = new MovimentoCaixaNeg();
        MovimentoCaixaHelperVO retorno = new MovimentoCaixaHelperVO();
        try {
            System.out.println("---------------------------------");
            System.out.println(movimentoCaixa);

            MovimentoCaixa mc = new MovimentoCaixa();
            mc.setTipoMovimento(movimentoCaixa.getTipoMovimento());
            mc.setObservacao(movimentoCaixa.getObservacao());
            mc.setValor(movimentoCaixa.getValor());
            mc.setUsuario_id(movimentoCaixa.getUsuario_id());

            mc = movimentoCaixaNeg.salvar(mc);

            retorno.setId(mc.getId());

            retorno.setMensagem("Gravado com sucesso.");
            retorno.setStatus(retorno.STATUS_OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            retorno.setMensagem(ex.getMessage());
            retorno.setStatus(retorno.STATUS_ERRO);
        }
        return retorno;
    }

    /**
     * Operação de Web service
     *
     * @param dataTransacao
     * @return
     */
    @WebMethod(operationName = "extratoDia")
    public ExtratoDiario extratoDia(@WebParam(name = "dataTransacao") final String dataTransacao) {
        movimentoCaixaNeg = new MovimentoCaixaNeg();
        Date data;
        try {
            data = UtilDateTime.strToDate(dataTransacao);
        } catch (ParseException ex) {
            data = new Date();
            Logger.getLogger(AcessoControle.class.getName()).log(Level.SEVERE, null, ex);
        }

        return movimentoCaixaNeg.extratoDiaSintetico(data);
    }
}

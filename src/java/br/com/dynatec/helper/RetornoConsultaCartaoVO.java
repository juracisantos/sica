/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.helper;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jura
 */
@XmlRootElement
public class RetornoConsultaCartaoVO extends RetornoHelper implements Serializable {

    private static final long serialVersionUID = 1L;

    //Se acesso normal sem ser pessoa mensalista
    private String cartao;
    private Integer tabela_id;
    private String placaVeiculo;
    private Date entrada;
    private Date saida;
    private String permanencia;
    private Date limiteParaSair;
    private Integer limiteParaSairEmMinutos;
    private Double valorCobrado;
    private Double valorJaPago;
    private Double ultimoValorPago;
    private Double desconboAcumulado;
    private Double DescontoAculumado;

    //Valores em comun
    private Double descontoAtual;
    private Double valorRecebido;
    private Double troco;
    private Double valorReceber;

    //Se pagando mensalidade => mensalista
    private boolean mensalista;
    private String nomeMensalista;
    private String cpf;
    private Integer qtdCartoes;
    private String tipoTransacao;
    private Date proximoVencimento;

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public Integer getTabela_id() {
        return tabela_id;
    }

    public void setTabela_id(Integer tabela_id) {
        this.tabela_id = tabela_id;
    }

    public Double getDescontoAtual() {
        return descontoAtual;
    }

    public void setDescontoAtual(Double descontoAtual) {
        this.descontoAtual = descontoAtual;
    }

    public Double getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(Double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public String getPermanencia() {
        return permanencia;
    }

    public void setPermanencia(String permanencia) {
        this.permanencia = permanencia;
    }

    public Date getLimiteParaSair() {
        return limiteParaSair;
    }

    public void setLimiteParaSair(Date limiteParaSair) {
        this.limiteParaSair = limiteParaSair;
    }

    public Double getValorCobrado() {
        return valorCobrado;
    }

    public void setValorCobrado(Double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }

    public Double getValorJaPago() {
        return valorJaPago;
    }

    public void setValorJaPago(Double valorJaPago) {
        this.valorJaPago = valorJaPago;
    }

    public Double getValorReceber() {
        return valorReceber;
    }

    public void setValorReceber(Double valorReceber) {
        this.valorReceber = valorReceber;
    }

    public Double getTroco() {
        return troco;
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }

    public Double getUltimoValorPago() {
        return ultimoValorPago;
    }

    public void setUltimoValorPago(Double ultimoValorPago) {
        this.ultimoValorPago = ultimoValorPago;
    }

    public Double getDesconboAcumulado() {
        return desconboAcumulado;
    }

    public void setDesconboAcumulado(Double desconboAcumulado) {
        this.desconboAcumulado = desconboAcumulado;
    }

    public Double getDescontoAculumado() {
        return DescontoAculumado;
    }

    public void setDescontoAculumado(Double DescontoAculumado) {
        this.DescontoAculumado = DescontoAculumado;
    }

    public String getNomeMensalista() {
        return nomeMensalista;
    }

    public void setNomeMensalista(String nomeMensalista) {
        this.nomeMensalista = nomeMensalista;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getQtdCartoes() {
        return qtdCartoes;
    }

    public void setQtdCartoes(Integer qtdCartoes) {
        this.qtdCartoes = qtdCartoes;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Date getProximoVencimento() {
        return proximoVencimento;
    }

    public void setProximoVencimento(Date proximoVencimento) {
        this.proximoVencimento = proximoVencimento;
    }

    public boolean isMensalista() {
        return mensalista;
    }

    public void setMensalista(boolean mensalista) {
        this.mensalista = mensalista;
    }

    public Integer getLimiteParaSairEmMinutos() {
        return limiteParaSairEmMinutos;
    }

    public void setLimiteParaSairEmMinutos(Integer limiteParaSairEmMinutos) {
        this.limiteParaSairEmMinutos = limiteParaSairEmMinutos;
    }

}

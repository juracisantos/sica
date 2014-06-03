/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.helper.fianceiro;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jura
 */
public class ExtratoDiario {

    private Date dia;
    private Double saldoInicial;
    private List<Movimento> movimentos;
    private Double saldoAtual;

    public ExtratoDiario() {
        this.movimentos = new LinkedList<>();
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public List<Movimento> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(List<Movimento> movimentos) {
        this.movimentos = movimentos;
    }

    public Double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(Double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

}

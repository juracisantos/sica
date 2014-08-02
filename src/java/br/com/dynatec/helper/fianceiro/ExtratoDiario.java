/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.helper.fianceiro;

import br.com.dynatec.helper.RetornoHelper;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author jura
 */
@XmlRootElement
@XmlType(name = "extrado", namespace = "br.com.dynatec.helper.fianceiro.ExtratoDiario")
public class ExtratoDiario extends RetornoHelper {

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
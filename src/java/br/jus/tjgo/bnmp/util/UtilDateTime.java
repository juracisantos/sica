/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.tjgo.bnmp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jura
 */
public class UtilDateTime {

    public static String dateToString(Date data) {
        if (data != null) {
            SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
            return dateformatDDMMYYYY.format(data);
        } else {
            return null;
        }

    }

    public static String dateTimeToString(Date data) {
        SimpleDateFormat dateformatDDMMYYYYHHNNSS = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return dateformatDDMMYYYYHHNNSS.format(data);
    }

    public static Date strToDate(String data) throws ParseException {
        SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd/MM/yyyy");
        return dateformatDDMMYYYY.parse(data);
    }

    public static double diferencaEmDias(Date dataInicial, Date dataFinal) {
        double result = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        double diferencaEmDias = (diferenca / 1000) / 60 / 60 / 24; //resultado é diferença entre as datas em dias
        long horasRestantes = (diferenca / 1000) / 60 / 60 % 24; //calcula as horas restantes
        result = diferencaEmDias + (horasRestantes / 24d); //transforma as horas restantes em fração de dias

        return result;
    }

    public static double diferencaEmHoras(Date dataInicial, Date dataFinal) {
        double result = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        long diferencaEmHoras = (diferenca / 1000) / 60 / 60;
        long minutosRestantes = (diferenca / 1000) / 60 % 60;
        double horasRestantes = minutosRestantes / 60d;
        result = diferencaEmHoras + (horasRestantes);

        return result;
    }

    public static double diferencaEmMinutos(Date dataInicial, Date dataFinal) {
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        double diferencaEmMinutos = (diferenca / 1000) / 60; //resultado é diferença entre as datas em minutos              
        return diferencaEmMinutos;
    }

    public static String minToHora(Integer minutos) {
        int inteira = minutos / 60;
        int resto = minutos % 60;

        return String.valueOf(inteira) + ":" + String.valueOf(resto);
    }

    public static String formataHoraHHMM(String hora) {
        String[] retorno;
        retorno = (hora != null) ? hora.split(":") : "".split(":");
        if (retorno.length != 1) {
            hora = retorno[0].length() == 1 ? ("0" + retorno[0]) : retorno[0];
            hora += ":";
            hora += retorno[1].length() == 1 ? ("0" + retorno[1]) : retorno[1];
        }
        return hora;
    }

    public static Date incrementarDiasData(Date data, Integer incremento) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.DAY_OF_MONTH, incremento);
        return cal.getTime();
    }

    public static Date proximoMesPassandoDia(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }
}

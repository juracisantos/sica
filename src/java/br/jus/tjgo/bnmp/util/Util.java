/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.tjgo.bnmp.util;

/**
 *
 * @author jura
 */
public class Util {

    public static Double getValorDouble(String valor) {
        return valor.length() != 0 ? Double.valueOf(valor) : 0.0d;
    }

    public static Long getValorLong(String valor) {
        return valor.length() != 0 ? Long.valueOf(valor) : 0l;
    }

    public static boolean isNumero(String valor) {
        try {
            Integer.valueOf(valor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String retiraCaracteres(String valor, String... caracteres) {
        valor = valor.replaceAll("\\.", "");
        valor = valor.replaceAll("-", "");
        valor = valor.replaceAll(" ", "");
        String aux = valor.substring(0,1);
        while (valor.substring(0, 1).equals("0"))
            valor = valor.substring(1, valor.length());
        return valor;
    }
}

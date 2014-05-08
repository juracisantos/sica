/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynantec.comparator;

import br.com.dynatec.entidade.Regra;
import java.util.Comparator;

/**
 *
 * @author jura
 */
public class RegrasComparator implements Comparator<Regra> {

    @Override
    public int compare(Regra p1, Regra p2) {
        if ((p1.getSequencia() != null) && (p2.getSequencia() != null)) {
            return p1.getSequencia().compareTo(p2.getSequencia());
        } else {
            return 0;
        }
        
        //if ((p1.getIntervaloHora() != null) && (p2.getIntervaloHora() != null)) {
        //    int nameComp = p1.getIntervaloHora().compareTo(p2.getIntervaloHora());
        //    return ((nameComp == 0) ? p1.getIntervaloMinuto().compareTo(p2.getIntervaloMinuto()) : nameComp);
        //} else {
        //    return 0;
        //}
    }

}

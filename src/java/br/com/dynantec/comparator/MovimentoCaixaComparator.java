/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.dynantec.comparator;

import br.com.dynatec.entidade.MovimentoCaixa;
import java.util.Comparator;

/**
 *
 * @author jura
 */
public class MovimentoCaixaComparator implements Comparator<MovimentoCaixa> {

    @Override
    public int compare(MovimentoCaixa p1, MovimentoCaixa p2) {
        if ((p1.getId() != null) && (p2.getId() != null)) {
            return p1.getId().compareTo(p2.getId());
        } else {
            return 0;
        }       
    }
    
}

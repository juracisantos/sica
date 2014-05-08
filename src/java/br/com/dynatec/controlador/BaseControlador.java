/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dynatec.controlador;

import java.io.Serializable;

/**
 *
 * @author jura
 */
public abstract class BaseControlador<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private T selectedObject;

    public T getSelectedObject() {
        return selectedObject;
    }

    public void setSelectedObject(T selectedObject) {
        this.selectedObject = selectedObject;
    }

}

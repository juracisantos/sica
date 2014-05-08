/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.tjgo.bnmp.util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 *
 * @author jura
 */
public class ProxyAuthenticator extends Authenticator {

    private String user, password;

    public ProxyAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password.toCharArray());
    }
}

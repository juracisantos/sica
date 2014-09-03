/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.tjgo.bnmp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jura
 */
public class UtilConexao {

    public static Connection getConexao() throws ClassNotFoundException, SQLException {        
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/estacionamento",
                "usuario", "usuario");
    }

    public static void main(String[] args) {
        try {
            System.out.println(getConexao());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}

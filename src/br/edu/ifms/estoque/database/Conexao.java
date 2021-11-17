/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author santos
 */
public class Conexao {
    
    private Connection conn;
    private static Conexao conexao;
    
    private Conexao() throws SQLException {
        String url = "jdbc:postgresql://localhost/estoque?user=postgres&password=baguvix";
        this.conn = DriverManager.getConnection(url);
    }
    
    public static Conexao getInstance() throws SQLException {
        if (conexao == null) {
            conexao = new Conexao();
        }
        return conexao;
    }
    
    public void close() throws SQLException {
        if (!isClosed()) {
            conn.close();
        }
        conn = null;
    }
    
    public Connection getConn() {
        return conn;
    }
    
    public Boolean isClosed() throws SQLException {
        return conn.isClosed();
    }
    
    public static void main(String[] args) {
        try {
            Conexao c = Conexao.getInstance();
            if (!c.isClosed()) {
                System.out.println("Conexão realizada com sucesso");
            }
            c.close();
            System.out.println("Fechamento realizado com sucesso");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author santos
 */
public class ListarMarcas {
    
    public static void main(String[] args) {
        try {
            Conexao conexao = Conexao.getInstance();
            Connection conn = conexao.getConn();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, nome FROM marca");
            
            ResultSetMetaData metaData = resultSet.getMetaData();
            System.out.println("Tabela de Marcas\n");
            for(int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            }
            System.out.println("");
            while(resultSet.next()) {
                for(int i = 1; i <= metaData.getColumnCount(); i++) {
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                }
                System.out.println("");
            }
            resultSet.close();
            statement.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

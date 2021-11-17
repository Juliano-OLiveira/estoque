/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque;

import javax.swing.JFrame;

/**
 *
 * @author santos
 */
public class TesteProduto {

    public static void main(String[] args) {
        TelaProduto tela = new TelaProduto();
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(300, 250);
        tela.setVisible(true);
    }
}

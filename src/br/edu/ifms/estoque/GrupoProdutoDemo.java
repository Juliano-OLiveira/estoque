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
public class GrupoProdutoDemo {

    public static void main(String[] args) {
        TelaListagemGrupoProduto tela = new TelaListagemGrupoProduto();
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.pack();
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
}

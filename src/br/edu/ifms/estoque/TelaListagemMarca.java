/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque;

import br.edu.ifms.estoque.model.Marca;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author santos
 */
public class TelaListagemMarca extends JFrame {

    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btInserir;
    private JButton btExcluir;
    private JButton btEditar;
    private JButton btFechar;
    private DefaultTableModel modelo;

    public TelaListagemMarca() {
        super("Listagem de Marcas");

        criarBotoes();
        criarTabela();
    }

    private void criarBotoes() {
        btInserir = new JButton("Inserir");
        btEditar = new JButton("Editar");
        btExcluir = new JButton("Excluir");
        btFechar = new JButton("Fechar");
        
        ButtonHandler handler = new ButtonHandler();
        btInserir.addActionListener(handler);
        btEditar.addActionListener(handler);
        btExcluir.addActionListener(handler);
        btFechar.addActionListener(handler);

        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.CENTER);

        painelBotoes = new JPanel(layout);
        painelBotoes.add(btInserir);
        painelBotoes.add(btEditar);
        painelBotoes.add(btExcluir);
        painelBotoes.add(btFechar);
    }

    private void criarTabela() {
        modelo = new DefaultTableModel();
        modelo.addColumn("Id");
        modelo.addColumn("Nome");

        tabela = new JTable(modelo);
        tabela.setSize(640, 480);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(300);

        barraRolagem = new JScrollPane(tabela);

        Font font = new Font("Times", Font.PLAIN, 30);
        JLabel titulo = new JLabel("Listagem de Marcas");
        titulo.setFont(font);
        titulo.setHorizontalAlignment(JLabel.CENTER);

        BorderLayout layout = new BorderLayout(5, 5);
        painelFundo = new JPanel(layout);
        painelFundo.add(titulo, BorderLayout.NORTH);
        painelFundo.add(barraRolagem, BorderLayout.CENTER);
        painelFundo.add(painelBotoes, BorderLayout.SOUTH);

        add(painelFundo);
    }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            Object source = ae.getSource();
            if (source == btFechar) {
                dispose();
            } else if (source == btInserir) {
                TelaMarca tela = new TelaMarca(TelaListagemMarca.this);
                tela.setVisible(true);
                
                Long sequencia = modelo.getRowCount() + 1L;
                Marca marca = tela.getMarca();
                if (marca.getId() == null) {
                    marca.setId(sequencia);
                }
                modelo.addRow(marca.toArray());
            }
        }

    }
}

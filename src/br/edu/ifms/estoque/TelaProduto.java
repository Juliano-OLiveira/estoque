/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author santos
 */
public class TelaProduto extends JFrame {
    
    private JTextField campoId;
    private JTextField campoNome;
    private JComboBox comboMarca;
    private JComboBox comboUnidadeMedida;
    private JButton botaoSalvar;
    private JButton botaoFechar;
    
    public TelaProduto() {
        super("Formulário de cadastro de produto");
        setLayout(new FlowLayout());
        
        add(new JLabel("Id. do Produto"));
        campoId = new JTextField(20);
        campoId.setEditable(false);
        add(campoId);
        
        add(new JLabel("Nome do Produto"));
        campoNome = new JTextField(20);
        add(campoNome);
        
        String marcas[] = {"Jhonson && Jhonson", "Adidas", "Dove", "Omo", "Rexona"};
        add(new JLabel("Marca do produto"));
        comboMarca = new JComboBox(marcas);
        add(comboMarca);
        
        String unidades[] = {"Unidade", "Quilo", "Metro", "Litro", "Pacote", "Caixa"};
        add(new JLabel("Unidade de Medida do Produto"));
        comboUnidadeMedida = new JComboBox(unidades);
        add(comboUnidadeMedida);
        
        EventoClick evento = new EventoClick();
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(evento);
        add(botaoSalvar);
        
        botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(evento);
        add(botaoFechar);
    }
    
    private boolean verificarCampos() {
        String mensagem = "";
        if ("".equals(campoNome.getText())) {
            mensagem = "O campo NOME não foi informado";
        } else if (comboMarca.getSelectedIndex() < 0) {
            mensagem = "O campo MARCA não foi selecionado";
        } else if (comboUnidadeMedida.getSelectedIndex() < 0) {
            mensagem = "O campo UNIDADE DE MEDIDA não foi selecionado";
        } else {
            return true;
        }
        
        JOptionPane.showMessageDialog(TelaProduto.this, mensagem, "Aviso", 
                JOptionPane.WARNING_MESSAGE);
        return false;
    }
    
    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        comboMarca.setSelectedItem(null);
        comboUnidadeMedida.setSelectedItem(null);
    }
    
    private class EventoClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoFechar) {
                dispose();
            } else if (verificarCampos()) {
                JOptionPane.showMessageDialog(TelaProduto.this, "Dados salvos com sucesso!",
                    "Informação", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            }
        }
        
    }
}

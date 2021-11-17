/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author santos
 */
public class TelaUnidadeMedida extends JFrame {

    private JTextField campoId;
    private JTextField campoNome;
    private JRadioButton opcaoFrancionado;
    private JRadioButton opcaoNaoFracionado;
    private JButton botaoSalvar;
    private JButton botaoFechar;

    public TelaUnidadeMedida() {
        super("Formulário de Cadastro de Unidade de Medida");
        setLayout(new FlowLayout());

        add(new JLabel("Id. da Unid. Medida"));
        campoId = new JTextField(20);
        campoId.setEditable(false);
        add(campoId);

        add(new JLabel("Nome da Unid. de Medida"));
        campoNome = new JTextField(20);
        add(campoNome);

        opcaoFrancionado = new JRadioButton("Fracionado");
        opcaoFrancionado.setSelected(true);
        add(opcaoFrancionado);

        opcaoNaoFracionado = new JRadioButton("Não fracionado");
        add(opcaoNaoFracionado);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(opcaoFrancionado);
        grupo.add(opcaoNaoFracionado);

        EventoClick evento = new EventoClick();
        
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(evento);
        add(botaoSalvar);

        botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(evento);
        add(botaoFechar);
    }
    
    private boolean verificarCampos() {
        if ("".equals(campoNome.getText())) {
            JOptionPane.showMessageDialog(TelaUnidadeMedida.this, 
                    "O nome não foi informado", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        opcaoFrancionado.setSelected(true);
    }

    private class EventoClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoFechar) {
                dispose();
            } else if (verificarCampos()) {
                JOptionPane.showMessageDialog(TelaUnidadeMedida.this, 
                        "Dados salvos com sucesso!", "Informação",
                        JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            }
        }

    }
}

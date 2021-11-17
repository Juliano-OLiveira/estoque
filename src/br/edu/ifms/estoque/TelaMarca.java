/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifms.estoque;

import br.edu.ifms.estoque.model.Marca;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author santos
 */
public class TelaMarca extends JDialog {

    private JTextField campoId;
    private JTextField campoNomeMarca;
    private JButton botaoSalvar;
    private JButton botaoFechar;
    private Marca marca;

    public TelaMarca(Frame telaPai) {
        super(telaPai);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setModal(true);
        this.marca = new Marca();

        JLabel lblId = new JLabel("Id. da Marca: ");
        add(lblId);
        campoId = new JTextField(20);
        campoId.setEditable(false);
        add(campoId);

        add(new JLabel("Nome da Marca: "));
        campoNomeMarca = new JTextField(20);
        campoNomeMarca.setFocusable(true);
        campoNomeMarca.requestFocusInWindow();
        add(campoNomeMarca);

        ActionHandler handler = new ActionHandler();

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(handler);
        add(botaoSalvar);

        botaoFechar = new JButton("Fechar");
        botaoFechar.addActionListener(handler);
        add(botaoFechar);
    }

    private void limpar() {
        campoId.setText("");
        campoNomeMarca.setText("");
    }

    private boolean validarCampos() {
        if ("".equals(campoNomeMarca.getText())) {
            JOptionPane.showMessageDialog(TelaMarca.this, "Você deve"
                    + " informar o nome da marca do produto",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public Marca getMarca() {
        return this.marca;
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botaoSalvar) {
                if (validarCampos()) {

                    String sId = campoId.getText();
                    Boolean contemNumero = sId.matches("\\d+");
                    
                    marca.setId(contemNumero ? Long.parseLong(sId) : null);
                    marca.setNome(campoNomeMarca.getText());

                    limpar();

                    JOptionPane.showMessageDialog(TelaMarca.this, "Dados salvos com sucesso!",
                            "Informação", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } else if (ae.getSource() == botaoFechar) {
                dispose();
            }
        }

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import br.edu.ifms.estoque.database.ResultSetTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author santos
 */
public class DisplayQueryResults extends JFrame {

    private JTextArea queryArea;
    private JTextField filterText;
    private ResultSetTableModel tableModel;
    private TableRowSorter<TableModel> sorter;

    private static final String DEFAULT_QUERY = "SELECT id, nome FROM marca";

    public DisplayQueryResults() {
        super("Visualização de resultados de consulta de banco de dados");
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        try {
            addWindowListener(new WindowHandler());
            
            tableModel = new ResultSetTableModel(DEFAULT_QUERY);
            queryArea = new JTextArea(DEFAULT_QUERY, 3, 100);
            queryArea.setWrapStyleWord(true);
            queryArea.setLineWrap(true);
            JScrollPane scrollPane = new JScrollPane(queryArea,
                    ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            // configura o JButton para enviar consulta
            JButton submitButton = new JButton("Enviar query");
            submitButton.addActionListener(new SubmitQueryHandler());
            /**
             * Define um painel do tipo Box para armazenar a área de texto do
             * script e o botão de envio
             */
            Box boxNorth = Box.createHorizontalBox();
            boxNorth.add(scrollPane);
            boxNorth.add(submitButton);

            /**
             * Tabela que apresentará o resultado da execução da consulta no
             * banco de dados
             */
            JTable resultTable = new JTable(tableModel);
            JScrollPane scrollTable = new JScrollPane(resultTable);

            filterText = new JTextField();
            JButton filterButton = new JButton("Aplicar filtro");
            filterButton.addActionListener(new FilterQueryHandler());
            sorter = new TableRowSorter<>(tableModel);
            resultTable.setRowSorter(sorter);

            /**
             * Painel utilizado para permitir a aplicação de filtros na tabela
             */
            Box boxSouth = Box.createHorizontalBox();
            boxSouth.add(new JLabel("Filtro: "));
            boxSouth.add(filterText);
            boxSouth.add(filterButton);

            add(boxNorth, BorderLayout.NORTH);
            add(scrollTable, BorderLayout.CENTER);
            add(boxSouth, BorderLayout.SOUTH);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Database error", JOptionPane.ERROR_MESSAGE);
            tableModel.disconnect();
            // Finalização com erro
            System.exit(1);
        }
    }

    private class SubmitQueryHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                tableModel.setQuery(queryArea.getText());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Database error",
                        JOptionPane.ERROR_MESSAGE);
                // tenta recuperar a partir da consulta de usuário inválida
                // executando consulta padrão
                try {
                    tableModel.setQuery(DEFAULT_QUERY);
                    queryArea.setText(DEFAULT_QUERY);
                } catch (SQLException sqlException2) {
                    JOptionPane.showMessageDialog(null,
                            sqlException2.getMessage(), "Database error",
                            JOptionPane.ERROR_MESSAGE);
                    // assegura que a conexão de banco de dados está fechada
                    tableModel.disconnect();
                    // Finalização com erro
                    System.exit(1); // termina o aplicativo
                }
            }
        }

    }

    private class FilterQueryHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            String text = filterText.getText();
            if ( text.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                try {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                } catch (PatternSyntaxException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Bad regex pattern", "Bad regex pattern",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
    
    private class WindowHandler extends WindowAdapter {

        @Override
        public void windowClosed(WindowEvent e) {
            tableModel.disconnect();
            // Finalização com sucesso
            System.exit(0);
        }
        
    }

}

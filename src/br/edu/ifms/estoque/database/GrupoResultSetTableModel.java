package br.edu.ifms.estoque.database;

import br.edu.ifms.estoque.model.GrupoProduto;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import queries.GrupoProdutoQueries;

/**
 *
 * @author santos
 */
public class GrupoResultSetTableModel extends AbstractTableModel {
private GrupoProdutoQueries queries;
private List<GrupoProduto> lista;
private String[] colunas = {"Id", "Nome","Subgrupo"};


    public GrupoResultSetTableModel()  {
        queries = new GrupoProdutoQueries();
        lista = queries.getAllGrupos();
    }

    public void atualizaTabela( )  {
     lista.clear();
     lista.addAll(queries.getAllGrupos());
        // informa que uma nova consulta foi gerada, portanto deve atualizar os dados
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount()  {
      
        return lista.size();
    }

    @Override
    public int getColumnCount()  {
        
        // retorna 0 em caso de falha
        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
       GrupoProduto gp = lista.get(row);
       switch(col){
           case 0: return gp.getId();
           case 1: return gp.getNome();
           case 2: return gp.getSubgrupo() != null ? gp.getSubgrupo().getNome(): "";
       
       }
       return "";
    }

    /**
     * Retorna a classe que representa a coluna informada
     * 
     * @param columnIndex
     * @return 
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
       switch(columnIndex){
           case 0 : return Integer.class;
           case 1 :
           case 2 : return String.class;
       
       }
        // se ocorrer falhar, retorna o padrão Object
        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    public void disconnect() {
       queries.close();
    }

}

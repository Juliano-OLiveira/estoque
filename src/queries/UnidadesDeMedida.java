package queries;

import br.edu.ifms.estoque.database.Conexao;
import br.edu.ifms.estoque.model.GrupoProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadesDeMedida {

    private Conexao conexao;
    private PreparedStatement selectAllUnidades;
    private PreparedStatement selectGrupoByUnidade;
    private PreparedStatement insertNovaUnidade;
    private long id;
    private String nome;
    private boolean fracionado;

    public UnidadesDeMedida() {
        try {
            conexao = Conexao.getInstance();
            Connection conn = conexao.getConn();

            selectAllUnidades = conn.prepareStatement("select * from unidade_medida");
            selectGrupoByUnidade = conn.prepareStatement("SELECT  id,nome, fracionado from unidade_medida  where nome = ?");
            insertNovaUnidade = conn.prepareStatement("insert into unidade_medida(id, nome,fracionado) values (?,?,?) ");

        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }

    }

    private UnidadesDeMedida(long aLong, String string, boolean aBoolean) {
      this.id = aLong;
      this.nome = string;
      this.fracionado = aBoolean;
    }

    public List<UnidadesDeMedida> getAllUnidades() {
        List<UnidadesDeMedida> results = null;
        ResultSet resultSet = null;

        try {
            resultSet = selectAllUnidades.executeQuery();
            results = mapObjectUnidades(resultSet);
             

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return null;
    }
     public List<UnidadesDeMedida> getUnidadesPorNome(String nome) {
        List<UnidadesDeMedida> results = null;
        ResultSet resultSet = null;

        try {
            selectGrupoByUnidade.setString(1, nome);
            resultSet = selectGrupoByUnidade.executeQuery();
            results = mapObjectUnidades(resultSet);
            
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return null;
    }

    public void close() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private List<UnidadesDeMedida> mapObjectUnidades(ResultSet rs) throws SQLException {
      List <UnidadesDeMedida> lista = new ArrayList<>();
     
         if(rs.getLong("id") != 0){
     while (rs.next()) {
                

                lista.add(new UnidadesDeMedida(
                        rs.getLong("id"),
                        rs.getString("nome"),
                       rs.getBoolean("fracionado")
                ));
            }
         }
     return lista;
    }
}

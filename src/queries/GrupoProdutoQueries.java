/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queries;

import br.edu.ifms.estoque.database.Conexao;
import br.edu.ifms.estoque.model.GrupoProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author santos
 */
public class GrupoProdutoQueries {

    private Conexao conexao;
    private PreparedStatement selectAllGrupos;
    private PreparedStatement selectGrupoByNome;
    private PreparedStatement insertNovoGrupo;
    private PreparedStatement insertSemsubGrupo;
    private PreparedStatement deleteSelectedGrupo;

    public GrupoProdutoQueries() {
        try {
            conexao = Conexao.getInstance();
            Connection conn = conexao.getConn();
            /**
             * Cria a consulta que seleciona todos os dados do grupo de produtos
             */
            selectAllGrupos = conn.prepareStatement(
                    "SELECT gp.id, gp.nome, lgp.id as subgrupo_id, lgp.nome as subgrupo_nome "
                    + "  FROM grupo_produto AS gp"
                    + "  LEFT JOIN grupo_produto AS lgp ON gp.sub_grupo = lgp.id"
            );
            /**
             * Cria consulta que seleciona os grupos de produtos com o nome
             * informado
             */
            selectGrupoByNome = conn.prepareStatement(
                    "SELECT gp.id, gp.nome, lgp.id as subgrupo_id, lgp.nome as subgrupo_nome "
                    + "  FROM grupo_produto AS gp"
                    + "  LEFT JOIN grupo_produto AS lgp ON gp.sub_grupo = lgp.id"
                    + " WHERE gp.nome LIKE ?");
            /**
             * Cria a inserção que adiciona uma nova entrada no banco de dados
             */
            insertNovoGrupo = conn.prepareStatement("INSERT INTO grupo_produto (nome,sub_grupo) VALUES (?, ?)");
            insertSemsubGrupo = conn.prepareStatement("INSERT INTO grupo_produto (nome) VALUES (?)");

            // deletando
            deleteSelectedGrupo = conn.prepareStatement("delete from grupo_produto where id = ?");

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private List<GrupoProduto> mapObjeGrupoProdutos(ResultSet rs) throws SQLException {
        List<GrupoProduto> lista = new ArrayList<>();
        GrupoProduto subGrupoProduto = null;

        while (rs.next()) {
            GrupoProduto subGrupo = new GrupoProduto(
                    rs.getLong("subgrupo_id"),
                    rs.getString("subgrupo_nome"), null);

            lista.add(new GrupoProduto(
                    rs.getLong("id"),
                    rs.getString("nome"),
                    subGrupo
            ));
        }

        return lista;
    }

    public List<GrupoProduto> getAllGrupos() {
        List<GrupoProduto> results = null;
        ResultSet resultSet = null;

        try {
            resultSet = selectAllGrupos.executeQuery();
            results = mapObjeGrupoProdutos(resultSet);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
                return results;
            } catch (SQLException ex) {
                ex.printStackTrace();
                close();
            }
        }
        return null;
    }

    public List<GrupoProduto> getGruposPorNome(String nome) {
        List<GrupoProduto> results = null;
        ResultSet resultSet = null;

        try {
            selectGrupoByNome.setString(1, nome);
            resultSet = selectGrupoByNome.executeQuery();
            results = mapObjeGrupoProdutos(resultSet);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                //  close();
            }
        }
        return null;
    }

    public int addGrupoProduto(String nome, GrupoProduto subGrupo) {
        int result = 0;

        try {
            if (subGrupo == null) {
                insertSemsubGrupo.setString(1, nome);
                result = insertSemsubGrupo.executeUpdate();

            } else {
                insertNovoGrupo.setString(1, nome);
                Long idsubGrupo = subGrupo != null ? subGrupo.getId() : null;
                insertNovoGrupo.setLong(2, idsubGrupo);

                result = insertNovoGrupo.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //  close();
        }
        return result;
    }

    public void close() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int deleteGrupoProduto(Long id) {
        int result = 0;

        try {

            deleteSelectedGrupo.setLong(1, id);
            result = deleteSelectedGrupo.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //  close();
        }
        return result;
    }
}

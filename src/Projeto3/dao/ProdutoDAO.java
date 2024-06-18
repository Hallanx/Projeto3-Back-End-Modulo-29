package Projeto3.dao;

import Projeto3.dao.jdbc.ConnectionFactory;
import Projeto3.domain.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO{

    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO TB_PRODUTO (ID, CODIGO, NOME) VALUES (nextval('SQ_CLIENTE'),?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, produto.getCodigo());
            statement.setString(2, produto.getNome());
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Produto consultar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Produto produto = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM TB_PRODUTO WHERE CODIGO = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, codigo);
            statement.executeQuery();
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                produto = new Produto();
                produto.setId(resultSet.getLong("id"));
                produto.setCodigo(resultSet.getString("codigo"));
                produto.setNome(resultSet.getString("nome"));
            }
            return produto;
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public Integer excluir(Produto produto) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM TB_PRODUTO WHERE CODIGO = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, produto.getCodigo());
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Produto> lista = new ArrayList<>();
        Produto produto = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelectAll();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                produto = new Produto();
                Long id = resultSet.getLong("ID");
                String nome = resultSet.getString("NOME");
                String cd = resultSet.getString("CODIGO");
                produto.setId(id);
                produto.setNome(nome);
                produto.setCodigo(cd);
                lista.add(produto);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return lista;
    }

    private String getSqlSelectAll() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM TB_PRODUTO");
        return stringBuilder.toString();
    }

    @Override
    public Integer atualizar(Produto produtoBanco) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlUpdate();
            statement = connection.prepareStatement(sql);
            adicionarParametrosUpdate(statement, produtoBanco);
            return statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    private void adicionarParametrosUpdate(PreparedStatement statement, Produto produtoBanco) throws SQLException {
        statement.setString(1, produtoBanco.getNome());
        statement.setString(2, produtoBanco.getCodigo());
        statement.setLong(3, produtoBanco.getId());
    }

    private String getSqlUpdate() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE TB_PRODUTO ");
        stringBuilder.append("SET NOME = ?, CODIGO = ? ");
        stringBuilder.append("WHERE ID = ?");
        return stringBuilder.toString();
    }
}

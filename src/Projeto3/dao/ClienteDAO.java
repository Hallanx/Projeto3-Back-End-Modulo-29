package Projeto3.dao;

import Projeto3.dao.jdbc.ConnectionFactory;
import Projeto3.domain.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO implements IClienteDAO {
    @Override
    public Integer cadastrar(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO TB_CLIENTE (ID, CODIGO, NOME) VALUES (nextval('SQ_CLIENTE'),?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getCodigo());
            statement.setString(2, cliente.getNome());
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
    public Cliente consultar(String codigo) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Cliente cliente = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM TB_CLIENTE WHERE CODIGO = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, codigo);
            statement.executeQuery();
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setId(resultSet.getLong("id"));
                cliente.setCodigo(resultSet.getString("codigo"));
                cliente.setNome(resultSet.getString("nome"));
            }
            return cliente;
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
    public Integer excluir(Cliente cliente) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM TB_CLIENTE WHERE CODIGO = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getCodigo());
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
    public List<Cliente> buscarTodos() throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Cliente> lista = new ArrayList<>();
        Cliente cliente = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelectAll();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                cliente = new Cliente();
                Long id = resultSet.getLong("ID");
                String nome = resultSet.getString("NOME");
                String cd = resultSet.getString("CODIGO");
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCodigo(cd);
                lista.add(cliente);
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

    @Override
    public Integer atualizar(Cliente clienteBanco) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlUpdate();
            statement = connection.prepareStatement(sql);
            adicionarParametrosUpdate(statement, clienteBanco);
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

    private void adicionarParametrosUpdate(PreparedStatement statement, Cliente clienteBanco) throws SQLException {
        statement.setString(1, clienteBanco.getNome());
        statement.setString(2, clienteBanco.getCodigo());
        statement.setLong(3, clienteBanco.getId());
    }

    private String getSqlUpdate() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE TB_CLIENTE ");
        stringBuilder.append("SET NOME = ?, CODIGO = ? ");
        stringBuilder.append("WHERE ID = ?");
        return stringBuilder.toString();
    }

    private String getSqlSelectAll() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM TB_CLIENTE");
        return stringBuilder.toString();
    }

    private String getSqlSelect() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM TB_CLIENTE");
        stringBuilder.append("WHERE CODIGO = ?");
        return stringBuilder.toString();
    }
}


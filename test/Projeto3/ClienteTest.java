package Projeto3;

import Projeto3.dao.ClienteDAO;
import Projeto3.dao.IClienteDAO;
import Projeto3.domain.Cliente;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClienteTest {

    @Test
    public void cadastrarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Alan Vaz");

        Integer quantidadeClientesCadastrados = dao.cadastrar(cliente);
        assertTrue(quantidadeClientesCadastrados == 1);

        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteBD);
        assertNotNull(clienteBD.getId());
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer quantidadeDel = dao.excluir(cliente);
        assertNotNull(quantidadeDel == 1);

    }

    @Test
    public void consultarTest() throws Exception {

        IClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Alan Vaz");

        Integer quantidadeClientesCadastrados = dao.cadastrar(cliente);
        assertTrue(quantidadeClientesCadastrados == 1);

        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteBD);
        assertNotNull(clienteBD.getId());
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer quantidadeDel = dao.excluir(cliente);
        assertNotNull(quantidadeDel == 1);

    }

    @Test
    public void excluirTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Alan Vaz");

        Integer quantidadeClientesCadastrados = dao.cadastrar(cliente);
        assertTrue(quantidadeClientesCadastrados == 1);

        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteBD);
        assertNotNull(clienteBD.getId());
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        Integer quantidadeDel = dao.excluir(cliente);
        assertNotNull(quantidadeDel == 1);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Alan Vaz");
        Integer quantidadeClientesCadastrados = dao.cadastrar(cliente);
        assertTrue(quantidadeClientesCadastrados == 1);

        Cliente cliente2 = new Cliente();
        cliente.setCodigo("02");
        cliente.setNome("Alice");
        Integer quantidadeClientesCadastrados2 = dao.cadastrar(cliente);
        assertTrue(quantidadeClientesCadastrados2 == 1);

        List<Cliente> lista = dao.buscarTodos();
        assertNotNull(lista);
        assertEquals(2, lista.size());

        int countDel = 0;
        for (Cliente cli : lista) {
            dao.excluir(cli);
            countDel++;
        }
        assertEquals(lista.size(), countDel);

        lista = dao.buscarTodos();
        assertEquals(lista.size(), 0);
    }
    @Test
    public void atualizarTest() throws Exception {
        IClienteDAO dao = new ClienteDAO();

        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Alan Vaz");
        Integer countCad = dao.cadastrar(cliente);
        assertTrue(countCad == 1);

        Cliente clienteBanco = dao.consultar("01");
        assertNotNull(clienteBanco);
        assertEquals(cliente.getCodigo(), clienteBanco.getCodigo());
        assertEquals(cliente.getNome(), clienteBanco.getNome());

        clienteBanco.setCodigo("10");
        clienteBanco.setNome("Alice");
        Integer countUpdate = dao.atualizar(clienteBanco);
        assertTrue(countUpdate == 1);

        Cliente clienteBanco1 = dao.consultar("01");
        assertNull(clienteBanco1);

        Cliente clienteBanco2 = dao.consultar("10");
        assertNotNull(clienteBanco2);
        assertEquals(clienteBanco.getId(), clienteBanco2.getId());
        assertEquals(clienteBanco.getCodigo(), clienteBanco2.getCodigo());
        assertEquals(clienteBanco.getNome(), clienteBanco2.getNome());

        List<Cliente> lista = dao.buscarTodos();
        for (Cliente cliente1 : lista) {
            dao.excluir(cliente1);
        }
    }
 }

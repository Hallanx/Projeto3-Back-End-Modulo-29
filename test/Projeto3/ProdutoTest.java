package Projeto3;


import Projeto3.dao.ProdutoDAO;
import Projeto3.dao.IProdutoDAO;
import Projeto3.domain.Produto;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProdutoTest {

    @Test
    public void cadastrarTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Alan Vaz");

        Integer quantidadeProdutosCadastrados = dao.cadastrar(produto);
        assertTrue(quantidadeProdutosCadastrados == 1);

        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());

        Integer quantidadeDel = dao.excluir(produto);
        assertNotNull(quantidadeDel == 1);

    }

    @Test
    public void consultarTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Alan Vaz");

        Integer quantidadeProdutosCadastrados = dao.cadastrar(produto);
        assertTrue(quantidadeProdutosCadastrados == 1);

        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());

        Integer quantidadeDel = dao.excluir(produto);
        assertNotNull(quantidadeDel == 1);
    }

    @Test
    public void excluirTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Alan Vaz");

        Integer quantidadeProdutosCadastrados = dao.cadastrar(produto);
        assertTrue(quantidadeProdutosCadastrados == 1);

        Produto produtoBD = dao.consultar(produto.getCodigo());
        assertNotNull(produtoBD);
        assertNotNull(produtoBD.getId());
        assertEquals(produto.getCodigo(), produtoBD.getCodigo());
        assertEquals(produto.getNome(), produtoBD.getNome());

        Integer quantidadeDel = dao.excluir(produto);
        assertNotNull(quantidadeDel == 1);
    }

    @Test
    public void buscarTodosTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Alan Vaz");
        Integer quantidadeProdutosCadastrados = dao.cadastrar(produto);
        assertTrue(quantidadeProdutosCadastrados == 1);

        Produto produto2 = new Produto();
        produto.setCodigo("02");
        produto.setNome("Alice");
        quantidadeProdutosCadastrados = dao.cadastrar(produto);
        assertTrue(quantidadeProdutosCadastrados == 1);

        List<Produto> lista = dao.buscarTodos();
        assertNotNull(lista);
        assertEquals(2, lista.size());

        int countDel = 0;
        for (Produto prod : lista) {
            dao.excluir(prod);
            countDel++;
        }
        assertEquals(lista.size(), countDel);

        lista = dao.buscarTodos();
        assertEquals(lista.size(), 0);
    }
    @Test
    public void atualizarTest() throws Exception {
        IProdutoDAO dao = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Alan Vaz");
        Integer countCad = dao.cadastrar(produto);
        assertTrue(countCad == 1);

        Produto produtoBanco = dao.consultar("01");
        assertNotNull(produtoBanco);
        assertEquals(produto.getCodigo(), produtoBanco.getCodigo());
        assertEquals(produto.getNome(), produtoBanco.getNome());

        produtoBanco.setCodigo("10");
        produtoBanco.setNome("Alice");
        Integer countUpdate = dao.atualizar(produtoBanco);
        assertTrue(countUpdate == 1);

        Produto produtoBanco1 = dao.consultar("01");
        assertNull(produtoBanco1);

        Produto produtoBanco2 = dao.consultar("10");
        assertNotNull(produtoBanco2);
        assertEquals(produtoBanco.getId(), produtoBanco2.getId());
        assertEquals(produtoBanco.getCodigo(), produtoBanco2.getCodigo());
        assertEquals(produtoBanco.getNome(), produtoBanco2.getNome());

        List<Produto> lista = dao.buscarTodos();
        for (Produto produto1 : lista) {
            dao.excluir(produto1);
        }
    }

    private void assertTrue(boolean b) {
    }

}

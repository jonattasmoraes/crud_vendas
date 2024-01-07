package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import main.java.dao.IProdutoDao;
import main.java.dao.ProdutoDao;
import main.java.domain.Produto;

public class ProdutoTest {
    @Test
    public void CadastrarConsultarTest() throws Exception {
        IProdutoDao dao = new ProdutoDao();

        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Notebook Acer Aspire 5 A515-45G-33QZ Intel Core i5");

        Integer quantidade = dao.cadastrar(produto);
        assertTrue(quantidade > 0);

        // Consultar produto e conferir se os dados estão corretos

        Produto produtoDB = dao.consultar(produto.getCodigo());
        assertNotNull(produtoDB);
        assertNotNull(produtoDB.getId());
        assertEquals(produto.getCodigo(), produtoDB.getCodigo());
        assertEquals(produto.getNome(), produtoDB.getNome());

        dao.excluir(produto);
    }

    @Test
    public void ExcluirTest() throws Exception {
        IProdutoDao dao = new ProdutoDao();

        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Notebook Acer Aspire 5 A515-45G-33QZ Intel Core i5");

        Integer quantidadeDel = dao.excluir(produto);
        assertNotNull(quantidadeDel);
    }

    @Test
    public void ConsultarTodosTest() throws Exception {
        IProdutoDao dao = new ProdutoDao();

        Produto produto1 = new Produto();
        produto1.setCodigo("01");
        produto1.setNome("Notebook Acer Aspire 5 A515-45G-33QZ Intel Core i5");

        Produto produto2 = new Produto();
        produto2.setCodigo("02");
        produto2.setNome("Notebook Asus ExpertBook B9450Q Intel Core i7");

        dao.cadastrar(produto1);
        dao.cadastrar(produto2);

        List<Produto> produtos = dao.consultarTodos();

        assertNotNull(produtos);
        assertFalse(produtos.isEmpty());

        assertTrue("01".equals(produtos.get(0).getCodigo())
                && "Notebook Acer Aspire 5 A515-45G-33QZ Intel Core i5".equals(produtos.get(0).getNome()));
        assertTrue("02".equals(produtos.get(1).getCodigo())
                && "Notebook Asus ExpertBook B9450Q Intel Core i7".equals(produtos.get(1).getNome()));

        dao.excluir(produto1);
        dao.excluir(produto2);
    }

    @Test
    public void AtualizarTest() throws Exception {
        IProdutoDao dao = new ProdutoDao();

        Produto produto = new Produto();
        produto.setCodigo("01");
        produto.setNome("Notebook Acer Aspire 5 A515-45G-33QZ Intel Core i5");

        dao.cadastrar(produto);

        produto.setNome("Notebook Asus ExpertBook B9450Q Intel Core i7");
        Integer quantidadeAtualizada = dao.atualizar(produto);

        assertNotNull(quantidadeAtualizada);
        assertTrue(quantidadeAtualizada > 0);

        Produto produtoAtualizado = dao.consultar(produto.getCodigo());

        assertNotNull(produtoAtualizado);
        assertNotNull(produtoAtualizado.getId());
        assertEquals(produto.getCodigo(), produtoAtualizado.getCodigo());
        assertEquals(produto.getNome(), produtoAtualizado.getNome());

        dao.excluir(produto);
    }
}

package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import main.java.dao.ClienteDao;
import main.java.dao.IClienteDao;
import main.java.domain.Cliente;

public class ClienteTest {
    @Test
    public void CadastrarConsultarTest() throws Exception {
        IClienteDao dao = new ClienteDao();

        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Andre Carlos");

        Integer quantidade = dao.cadastrar(cliente);
        assertTrue(quantidade > 0);

        // Consultar o cliente e conferir se os dados estão corretos

        Cliente clienteBD = dao.consultar(cliente.getCodigo());
        assertNotNull(clienteBD);
        assertNotNull(clienteBD.getId());
        assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
        assertEquals(cliente.getNome(), clienteBD.getNome());

        dao.excluir(cliente);
    }

    @Test
    public void ExcluirTest() throws Exception {
        IClienteDao dao = new ClienteDao();

        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Jose Maria");

        Integer quantidadeDel = dao.excluir(cliente);
        assertNotNull(quantidadeDel);
    }

    @Test
    public void ConsultarTodosTest() throws Exception {
        IClienteDao dao = new ClienteDao();

        Cliente cliente1 = new Cliente();
        cliente1.setCodigo("01");
        cliente1.setNome("Andre Carlos");

        Cliente cliente2 = new Cliente();
        cliente2.setCodigo("02");
        cliente2.setNome("Maria Silva");

        dao.cadastrar(cliente1);
        dao.cadastrar(cliente2);

        List<Cliente> clientes = dao.consultarTodos();

        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());

        assertTrue("01".equals(clientes.get(0).getCodigo()) && "Andre Carlos".equals(clientes.get(0).getNome()));
        assertTrue("02".equals(clientes.get(1).getCodigo()) && "Maria Silva".equals(clientes.get(1).getNome()));

        dao.excluir(cliente1);
        dao.excluir(cliente2);
    }

    @Test
    public void AtualizarTest() throws Exception {
        IClienteDao dao = new ClienteDao();

        Cliente cliente = new Cliente();
        cliente.setCodigo("01");
        cliente.setNome("Andre Carlos");

        dao.cadastrar(cliente);

        cliente.setNome("Andre Silva");
        Integer quantidadeAtualizada = dao.atualizar(cliente);

        assertNotNull(quantidadeAtualizada);
        assertTrue(quantidadeAtualizada > 0);

        Cliente clienteAtualizado = dao.consultar(cliente.getCodigo());

        assertNotNull(clienteAtualizado);
        assertNotNull(clienteAtualizado.getId());
        assertEquals(cliente.getCodigo(), clienteAtualizado.getCodigo());
        assertEquals(cliente.getNome(), clienteAtualizado.getNome());

        dao.excluir(cliente);
    }

}

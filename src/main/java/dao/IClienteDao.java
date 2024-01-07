package main.java.dao;

import java.util.List;

import main.java.domain.Cliente;

public interface IClienteDao {
    public Integer cadastrar(Cliente cliente) throws Exception;

    public Cliente consultar(String codigo) throws Exception;

    public Integer excluir(Cliente cliente) throws Exception;

    public List<Cliente> consultarTodos() throws Exception;

    public Integer atualizar(Cliente cliente) throws Exception;
}

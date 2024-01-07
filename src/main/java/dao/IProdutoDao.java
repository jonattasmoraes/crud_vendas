package main.java.dao;

import java.util.List;

import main.java.domain.Produto;

public interface IProdutoDao {
    public Integer cadastrar(Produto produto) throws Exception;

    public Produto consultar(String codigo) throws Exception;

    public Integer excluir(Produto produto) throws Exception;

    public List<Produto> consultarTodos() throws Exception;

    public Integer atualizar(Produto produto) throws Exception;
}

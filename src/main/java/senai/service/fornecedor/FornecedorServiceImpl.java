package senai.service.fornecedor;

import senai.model.Fornecedor;
import senai.repository.fornecedor.FornecedorRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class FornecedorServiceImpl implements FornecedorService{

    private final FornecedorRepositoryImpl  fornecedorRepositoryImpl = new FornecedorRepositoryImpl();

    @Override
    public Fornecedor criarFornecedor(Fornecedor fornecedor) throws SQLException {
        return fornecedorRepositoryImpl.save(fornecedor);
    }

    @Override
    public Fornecedor buscarPorId(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Fornecedor> buscarTodos() throws SQLException {
        return List.of();
    }

    @Override
    public void atualizarFornecedor(Fornecedor fornecedor) throws SQLException {

    }

    @Override
    public void deletarFornecedor(int id) throws SQLException {

    }
}

package senai.repository.fornecedor;
import java.sql.SQLException;
import java.util.List;

import senai.model.Fornecedor;

public interface FornecedorRepository {

    public Fornecedor save(Fornecedor fornecedor) throws SQLException;

    public Fornecedor findById(int id) throws SQLException;

    public List<Fornecedor> list() throws SQLException;

    public void update(Fornecedor fornecedor) throws SQLException;

    public void delete(int id) throws SQLException;
}

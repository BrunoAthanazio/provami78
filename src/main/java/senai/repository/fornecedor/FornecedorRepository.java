package senai.repository.fornecedor;
import java.sql.SQLException;
import java.util.List;

import senai.model.Fornecedor;

public interface FornecedorRepository {

    Fornecedor save(Fornecedor fornecedor) throws SQLException;

    Fornecedor findById(int id) throws SQLException;

    List<Fornecedor> list() throws SQLException;

    void update(Fornecedor fornecedor) throws SQLException;

    void delete(int id) throws SQLException;
}

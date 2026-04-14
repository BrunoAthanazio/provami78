package senai.repository.equipamento;

import java.sql.SQLException;
import java.util.List;

import senai.model.Equipamento;

public interface EquipamentoRepository {
    
    Equipamento save(Equipamento equipamento) throws SQLException;

    Equipamento findById(int id) throws SQLException;

    List<Equipamento> list(int fornecedorId) throws SQLException;
    
    void update(Equipamento equipamento) throws SQLException;

    void delete(int id) throws SQLException;

    void checkIfExistsForUpdate(int id) throws SQLException;

    void checkIfExistsForDelete(int id) throws SQLException;

    void checkIfFornecedorExists(int fornecedorId) throws SQLException;
}

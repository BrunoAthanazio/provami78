package senai.service.equipamento;

import senai.model.Equipamento;
import senai.repository.equipamento.EquipamentoRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class EquipamentoServiceImpl implements EquipamentoService{

    private final EquipamentoRepositoryImpl equipamentoRepositoryImpl = new EquipamentoRepositoryImpl();

    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        return equipamentoRepositoryImpl.save(equipamento);
    }

    @Override
    public Equipamento buscarPorId(int id) throws SQLException {
        return equipamentoRepositoryImpl.findById(id);
    }

    @Override
    public List<Equipamento> buscarPorFornecedorId(int fornecedorId) throws SQLException {
        return equipamentoRepositoryImpl.list(fornecedorId);
    }

    @Override
    public void atualizarEquipamento(Equipamento equipamento) throws SQLException {
        equipamentoRepositoryImpl.update(equipamento);
    }

    @Override
    public void deletarEquipamento(int id) throws SQLException {
        equipamentoRepositoryImpl.delete(id);
    }
}

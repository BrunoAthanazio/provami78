package senai.repository.equipamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import senai.database.Conexao;
import senai.model.Equipamento;

public class EquipamentoRepositoryImpl implements EquipamentoRepository{

    @Override
    public Equipamento save(Equipamento equipamento) throws SQLException {
        String command = """
                INSERT INTO Equipamento
                (nome, numero_serie, fornecedor_id)
                VALUES
                (?,?,?);
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command,
                PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroSerie());
            stmt.setInt(3, equipamento.getFornecedorId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                equipamento.setId(rs.getInt(1));
            }
            return equipamento;
        }
    }

    @Override
    public Equipamento findById(int id) throws SQLException {
        String query = """
                SELECT nome,
                    numero_serie,
                    fornecedor_id
                FROM Equipamento
                WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                String numeroSerie = rs.getString("numero_serie");
                int fornecedorId = rs.getInt("fornecedor_id");

                var equipamento = new Equipamento(id, nome, numeroSerie, fornecedorId);
                return equipamento;
            } else{
                throw new RuntimeException("Id do Equipamento não encontrado!");
            }
        }
    }

    @Override
    public List<Equipamento> list(int fornecedorId) throws SQLException {
        List<Equipamento> equipamentos = new ArrayList<>();
        String query = """
                SELECT id,
                    nome,
                    numero_serie
                FROM Equipamento
                WHERE fornecedor_id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, fornecedorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String numeroSerie = rs.getString("numero_serie");

                var equipamento = new Equipamento(id, nome, numeroSerie, fornecedorId);
                equipamentos.add(equipamento);
            }
            return equipamentos;
        }
    }

    @Override
    public void update(Equipamento equipamento) throws SQLException {
        String command = """
                UPDATE Equipamento
                SET fornecedor_id = ?, nome = ?, numero_serie = ?
                WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, equipamento.getFornecedorId());
            stmt.setString(2, equipamento.getNome());
            stmt.setString(3, equipamento.getNumeroSerie());
            stmt.setInt(4, equipamento.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String command = """
                DELETE FROM Equipamento
                WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void checkIfExistsForUpdate(int id) throws SQLException {
        String query = """
                SELECT id
                FROM Equipamento;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                if(id == rs.getInt("id")){
                    return;
                }
            }
            throw new RuntimeException("Equipamento não encontrado para atualização!");
        }
    }
    
    @Override
    public void checkIfExistsForDelete(int id) throws SQLException {
        String query = """
                SELECT id
                FROM Equipamento;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                if(id == rs.getInt("id")){
                    return;
                }
            }
            throw new RuntimeException("Equipamento não encontrado para exclusão!");
        }
    }

    @Override
    public void checkIfFornecedorExists(int fornecedorId) throws SQLException {
        String query = """
                SELECT id
                FROM Fornecedor;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if(fornecedorId == rs.getInt("id")){
                    return;
                }
            }
            throw new RuntimeException("Fornecedor inválido ou inexistente!");
        }
    }
}

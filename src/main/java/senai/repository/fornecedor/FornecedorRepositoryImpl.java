package senai.repository.fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import senai.database.Conexao;
import senai.model.Fornecedor;

public class FornecedorRepositoryImpl implements FornecedorRepository{

    @Override
    public Fornecedor save(Fornecedor fornecedor) throws SQLException {
        String command = """
                INSERT INTO Fornecedor
                (nome, cnpj)
                VALUES
                (?,?);
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command,
                PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                fornecedor.setId(rs.getInt(1));
            }
            
        }
        return fornecedor;
    }

    @Override
    public Fornecedor findById(int id) throws SQLException {
        String query = """
                SELECT id,
                    nome,
                    cnpj
                FROM Fornecedor
                WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String nome = rs.getString("nome");
                String cnpj = rs.getString("cnpj");
                
                var fornecedor = new Fornecedor(id, nome, cnpj);
                return fornecedor;
            }else{
                throw new RuntimeException("Id do Fornecedor não encontrado!");
            }
        }
    }

    @Override
    public List<Fornecedor> list() throws SQLException {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String query = """
                SELECT id,
                    nome,
                    cnpj
                FROM Fornecedor;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cnpj = rs.getString("cnpj");

                var fornecedor = new Fornecedor(id, nome, cnpj);
                fornecedores.add(fornecedor);
            }
            return fornecedores;
        }
    }

    @Override
    public void update(Fornecedor fornecedor) throws SQLException {
        String commando = """
                UPDATE Fornecedor
                SET nome = ?, cnpj = ?
                WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(commando)){
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setInt(3, fornecedor.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String command = """
                DELETE FROM Fornecedor
                WHERE id = ?;
                """;
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
}

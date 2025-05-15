package com.mycompany.gerenciamentoconsultas.DAO;

import com.mycompany.gerenciamentoconsultas.conexao.ConexaoMySQL;
import com.mycompany.gerenciamentoconsultas.models.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PacienteDAO {
    public void inserir(Paciente paciente) {
        String sql = "INSERT INTO Paciente (nome, cpf, telefone) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getTelefone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public ArrayList<Paciente> listar() {
        ArrayList<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Paciente";
        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Paciente pa = new Paciente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"));
            
                lista.add(pa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
     
    public ArrayList<Paciente> buscarPorNome(String nome) {
        ArrayList<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Paciente WHERE nome LIKE ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente pa = new Paciente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"));
                lista.add(pa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
}
    
    public void atualizar(Paciente paciente) {
        String sql = "UPDATE Paciente SET nome = ?, cpf = ?, telefone = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getTelefone());
            stmt.setInt(4, paciente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void remover(int id) {
        String sql = "DELETE FROM Paciente WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------------------------------------------------------------------------
    //Outros métodos
    
    public boolean cpfExiste(String cpf) {
        String sql = "SELECT COUNT(*) FROM Paciente WHERE cpf = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // true se o CPF já está no banco
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
}
    
    public boolean telefoneExiste(String telefone) {
        String sql = "SELECT COUNT(*) FROM Paciente WHERE telefone = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, telefone);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // true se o telefone já está no banco
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
}
    
    public int getIdPacientePorCpf(String cpf) {
    int id = -1;
    
    try {
        Connection con = ConexaoMySQL.conectar();
        String sql = "SELECT id FROM Paciente WHERE cpf = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            id = rs.getInt("id");
        }
        rs.close();
        stmt.close();
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return id;
}
     
}

package com.mycompany.gerenciamentoconsultas.DAO;

import com.mycompany.gerenciamentoconsultas.conexao.ConexaoMySQL;
import com.mycompany.gerenciamentoconsultas.models.Medico;
   
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MedicoDAO {
    public void inserir(Medico medico) {
        String sql = "INSERT INTO Medico (nome, especialidade, crm) VALUES (?, ?)";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getCrm());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public ArrayList<Medico> listar() {
        ArrayList<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medico";
        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Medico me = new Medico(rs.getInt("id"), rs.getString("nome"), rs.getString("especialidade"), rs.getString("crm"));
                lista.add(me);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
     
    public ArrayList<Medico> buscarPorNome(String nome) {
        ArrayList<Medico> lista = new ArrayList<>();
        String sql = "SELECT * FROM Medico WHERE nome LIKE ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Medico me = new Medico(rs.getInt("id"), rs.getString("nome"), rs.getString("especialidade"), rs.getString("crm"));
                lista.add(me);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
}
    
    public void atualizar(Medico medico) {
        String sql = "UPDATE Medico SET nome = ?, especialidade = ?, crm = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, medico.getNome());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setString(3, medico.getCrm());
            stmt.setInt(4, medico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void remover(int id) {
        String sql = "DELETE FROM Medico WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
//-----------------------------------------------------------------------------------------------------
    //Outros métodos
    
     public boolean crmExiste(String crm) {
        String sql = "SELECT COUNT(*) FROM Medico WHERE crm = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, crm);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // true se o crm já está no banco
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
}
     
    
}

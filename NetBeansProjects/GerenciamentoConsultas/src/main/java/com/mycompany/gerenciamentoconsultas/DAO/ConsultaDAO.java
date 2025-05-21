package com.mycompany.gerenciamentoconsultas.DAO;

import com.mycompany.gerenciamentoconsultas.conexao.ConexaoMySQL;
import com.mycompany.gerenciamentoconsultas.models.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConsultaDAO {
     public void inserir(Consulta consulta) {
        String sql = "INSERT INTO Consulta (id_paciente, id_medico, data, hora, observacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, consulta.getId_paciente());
            stmt.setInt(2, consulta.getId_medico());
            stmt.setDate(3, new java.sql.Date(consulta.getData().getTime()));
            stmt.setTime(4, consulta.getHora());
            stmt.setString(5, consulta.getObservacao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public ArrayList<Consulta> listar() {
        ArrayList<Consulta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Consulta";
        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Consulta con = new Consulta(rs.getInt("id"), rs.getInt("id_paciente"), rs.getInt("id_medico"), rs.getDate("data"), rs.getTime("hora"), rs.getString("observacao"));
                lista.add(con);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
     
    public ArrayList<Consulta> buscarPorNome(String nome) {
        ArrayList<Consulta> lista = new ArrayList<>();
        String sql = "SELECT c.* FROM Consulta c " +
                     "JOIN Paciente p ON c.id_paciente = p.id " +
                     "WHERE p.nome LIKE ?";
                    

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
               Consulta me = new Consulta(
                       rs.getInt("id"), 
                       rs.getInt("id_paciente"), 
                       rs.getInt("id_medico"), 
                       rs.getDate("data"), 
                       rs.getTime("hora"), 
                       rs.getString("observacao"));
                
                lista.add(me);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
}
    
    public void atualizar(Consulta consulta) {
        String sql = "UPDATE Consulta SET id_paciente = ?, id_medico = ?, data = ?, hora = ?, observacao = ? WHERE id = ?";
        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, consulta.getId_paciente());
            stmt.setInt(2, consulta.getId_medico());
            stmt.setDate(3, consulta.getData());
            stmt.setTime(4, consulta.getHora());
            stmt.setString(5, consulta.getObservacao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void remover(int id) {
        String sql = "DELETE FROM Consulta WHERE id = ?";
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
    
     public boolean crmMedicoExiste(String crm) {
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
     
      public boolean cpfPacienteExiste(String cpf) {
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
    
    
}

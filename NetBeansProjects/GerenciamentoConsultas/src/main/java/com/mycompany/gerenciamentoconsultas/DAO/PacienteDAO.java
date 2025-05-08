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
        String sql = "INSERT INTO Paciente (nome, cpf, telefone) VALUES (?, ?)";
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
                Pessoa p = new Pessoa(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("idade")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
}
     
    
}

package com.mycompany.gerenciamentoconsultas.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
     private static final String URL = "jdbc:mysql://localhost:3306/gerenciamento_consultas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco de dados: " + e.getMessage());
        }
    }
    
}

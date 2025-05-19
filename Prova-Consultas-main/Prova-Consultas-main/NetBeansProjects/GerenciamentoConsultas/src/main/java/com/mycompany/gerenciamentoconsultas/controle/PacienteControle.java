package com.mycompany.gerenciamentoconsultas.controle;

import com.mycompany.gerenciamentoconsultas.DAO.PacienteDAO;
import com.mycompany.gerenciamentoconsultas.models.Paciente;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PacienteControle {
    private PacienteDAO dao = new PacienteDAO();

    public boolean cadastrar(String nome, String cpf, String telefone) {
        if (dao.cpfExiste(cpf)) {
            JOptionPane.showMessageDialog(null, "CPF já Cadastrado");
            return false;
        }
        
        if (dao.telefoneExiste(telefone)) {
            JOptionPane.showMessageDialog(null, "Telefone já Cadastrado");
            return false;
        }
        
        Paciente pa = new Paciente(nome, cpf, telefone);
        dao.inserir(pa);
        return true;
    }

    public ArrayList<Paciente> listar() {
        return dao.listar();
    }

    public void atualizar(int id, String nome, String cpf, String telefone) {
        Paciente pa = new Paciente(id, nome, cpf, telefone);
        dao.atualizar(pa);
    }

    public void remover(int id) {
        dao.remover(id);
    }
    
    public ArrayList<Paciente> buscarPorNome(String nome) {
    return dao.buscarPorNome(nome);
}
    
}

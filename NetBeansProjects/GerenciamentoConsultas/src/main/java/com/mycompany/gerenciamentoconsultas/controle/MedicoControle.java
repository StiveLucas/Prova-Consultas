package com.mycompany.gerenciamentoconsultas.controle;

import com.mycompany.gerenciamentoconsultas.DAO.MedicoDAO;
import com.mycompany.gerenciamentoconsultas.models.Medico;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MedicoControle {
     private MedicoDAO dao = new MedicoDAO();

    public boolean cadastrar(String nome, String especialidade, String crm) {
        if (dao.crmExiste(crm)) {
            JOptionPane.showMessageDialog(null, "CRM já Cadastrado");
            return false;
        }
        
        Medico me = new Medico(nome, especialidade, crm);
        dao.inserir(me);
        return true;
    }

    public ArrayList<Medico> listar() {
        return dao.listar();
    }

    public void atualizar(int id, String nome, String especialidade, String crm) {
        Medico me = new Medico(id, nome, especialidade, crm);
        dao.atualizar(me);
    }

    public void remover(int id) {
        dao.remover(id);
    }
    
    public ArrayList<Medico> buscarPorNome(String nome) {
    return dao.buscarPorNome(nome);
}
    
}

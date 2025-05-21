package com.mycompany.gerenciamentoconsultas.controle;

import com.mycompany.gerenciamentoconsultas.DAO.ConsultaDAO;
import com.mycompany.gerenciamentoconsultas.DAO.MedicoDAO;
import com.mycompany.gerenciamentoconsultas.DAO.PacienteDAO;
import com.mycompany.gerenciamentoconsultas.models.Consulta;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

public class ConsultaControle {
    private ConsultaDAO dao = new ConsultaDAO();
    private MedicoDAO daoMedico = new MedicoDAO();
    private PacienteDAO daoPaciente = new PacienteDAO();
    
    public boolean cadastrar(String cpf, String crm, java.sql.Date data, Time hora, String observacao) {
        if (dao.crmMedicoExiste(crm) && dao.cpfPacienteExiste(cpf)) {
            int idMedico = daoMedico.getIdMedicoPorCrm(crm);
            int idPaciente = daoPaciente.getIdPacientePorCpf(cpf);

           Consulta consulta = new Consulta(idPaciente, idMedico, data, hora, observacao);
           dao.inserir(consulta);
           return true;
    }
        return false;
    
    }
    
     public ArrayList<Consulta> listar() {
        return dao.listar();
    }
     
     public ArrayList<Consulta> buscarPorNome(String nome) {
        return dao.buscarPorNome(nome);
    }
     
     
     //-----------------------------------------------------------------------------
     //Outros métodos
     
     private int id_paciente;
     
     public int getId_paciente(){
         return id_paciente;
     }
    
}

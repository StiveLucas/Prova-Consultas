package com.mycompany.gerenciamentoconsultas.models;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Consulta {
    private int id;
    private int id_paciente;
    private int id_medico;
    private Date data;
    private Time hora;
    private String observacao;

    public Consulta() {
    }

    public Consulta(int id_paciente, int id_medico, Date data, Time hora, String observacao) {
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
        this.data = data;
        this.hora = hora;
        this.observacao = observacao;
        
    }

    public Consulta(int id, int id_paciente, int id_medico, Date data, Time hora, String observacao) {
        this.id = id;
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
        this.data = data;
        this.hora = hora;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public int getId_medico() {
        return id_medico;
    }

    public Date getData() {
        return data;
    }

    public Time getHora() {
        return hora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }    
    
}

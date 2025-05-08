package com.mycompany.gerenciamentoconsultas.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Consulta {
    private int id;
    private int id_paciente;
    private int id_medico;
    private LocalDate data;
    private LocalTime hora;
    private String observacao;

    public Consulta() {
    }

    public Consulta(int id_paciente, int id_medico, LocalDate data, LocalTime hora, String observacao) {
        this.id_paciente = id_paciente;
        this.id_medico = id_medico;
        this.data = data;
        this.hora = hora;
        this.observacao = observacao;
        
    }

    public Consulta(int id, int id_paciente, int id_medico, LocalDate data, LocalTime hora, String observacao) {
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

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHora() {
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

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }    
    
}

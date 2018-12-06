/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.estacionamento.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jader
 */
@Entity
public class Estadia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate diaEntrada;
    
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaEntrada;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate diaSaida;
    
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaSaida;

    @ManyToOne
    private Atendente atendente;

    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Veiculo veiculo;

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDiaEntrada() {
        return diaEntrada;
    }

    public void setDiaEntrada(LocalDate diaEntrada) {
        this.diaEntrada = diaEntrada;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDate getDiaSaida() {
        return diaSaida;
    }

    public void setDiaSaida(LocalDate diaSaida) {
        this.diaSaida = diaSaida;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}

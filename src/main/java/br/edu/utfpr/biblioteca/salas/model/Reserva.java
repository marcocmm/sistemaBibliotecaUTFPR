/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Reservas")
public class Reserva {
    @Column(name="id")
    private int id;
    @Column(name="quantidade_alunos")
    private int quantidadeAlunos;
    @Column(name="data_inicial")
    private Date dataInicial;
    @Column(name="data_final")
    private Date dataFinal;
    @Column(name="estudante_ra")
    private Estudante estudanteRA;
    @Column(name="sala_id")
    private Sala sala;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Estudante getEstudanteRA() {
        return estudanteRA;
    }

    public void setEstudanteRA(Estudante estudanteRA) {
        this.estudanteRA = estudanteRA;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
}



    

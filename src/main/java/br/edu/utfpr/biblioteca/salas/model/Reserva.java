/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="Reservas")
public class Reserva {
    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    private int id;
    @Column(name="quantidade_alunos", nullable = false)
    private int quantidadeAlunos;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="data_inicial", nullable = false)
    private Date dataInicial;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="data_final", nullable = false)
    private Date dataFinal;
    @Column(name="estudante_ra", nullable = false, length = 10)
    private Estudante estudanteRA;
    @Column(name="sala_id", nullable = false)
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



    

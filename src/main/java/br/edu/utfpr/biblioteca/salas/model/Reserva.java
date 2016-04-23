/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mateus
 */
@Entity
@Table(name = "Reservas")
@NamedQueries({
    @NamedQuery(name = "Reservas.findAll", query = "SELECT r FROM Reservas r")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "quantidade_alunos")
    private int quantidadeAlunos;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_inicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicial;

    @Basic(optional = false)
    @NotNull
    @Column(name = "data_final")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinal;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "reserva")
    private ReservaAtiva reservaAtiva;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudante")
    private List<Estudante> estudantesReservasAtivas;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "data")
    private List<Date> datasReservasAtivas;

    @JoinColumn(name = "estudante_ra", referencedColumnName = "ra")
    @ManyToOne(optional = false)
    private Estudante estudante;

    @JoinColumn(name = "sala_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sala sala;

    protected Reserva() {
    }

    public Reserva(Estudante estudante, Sala sala, Date dataInicial, int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
        this.dataInicial = dataInicial;
        this.dataFinal = dataInicial;//+1h
        this.estudante = estudante;
        this.sala = sala;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public ReservaAtiva getReservaAtiva() {
        return reservaAtiva;
    }

    public void setReservaAtiva(ReservaAtiva reservaAtiva) {
        this.reservaAtiva = reservaAtiva;
    }

    public List<Estudante> getEstudantesReservasAtivas() {
        return estudantesReservasAtivas;
    }

    public void setEstudantesReservasAtivas(List<Estudante> estudantesReservasAtivas) {
        this.estudantesReservasAtivas = estudantesReservasAtivas;
    }

    public List<Date> getDatasReservasAtivas() {
        return datasReservasAtivas;
    }

    public void setDatasReservasAtivas(List<Date> datasReservasAtivas) {
        this.datasReservasAtivas = datasReservasAtivas;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.biblioteca.salas.model.Reservas[ id=" + id + " ]";
    }

}

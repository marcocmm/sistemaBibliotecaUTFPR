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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservaId")
    private List<ReservaAtiva> reservasAtivasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservas")
    private List<ReservaAtiva> reservasAtivasList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservas1")
    private List<ReservaAtiva> reservasAtivasList2;
    @JoinColumn(name = "estudante_ra", referencedColumnName = "ra")
    @ManyToOne(optional = false)
    private Estudante estudanteRa;
    @JoinColumn(name = "sala_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sala salaId;

    public Reserva() {
    }

    public Reserva(Integer id) {
        this.id = id;
    }

    public Reserva(Integer id, int quantidadeAlunos, Date dataInicial, Date dataFinal) {
        this.id = id;
        this.quantidadeAlunos = quantidadeAlunos;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
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

    public List<ReservaAtiva> getReservasAtivasList() {
        return reservasAtivasList;
    }

    public void setReservasAtivasList(List<ReservaAtiva> reservasAtivasList) {
        this.reservasAtivasList = reservasAtivasList;
    }

    public List<ReservaAtiva> getReservasAtivasList1() {
        return reservasAtivasList1;
    }

    public void setReservasAtivasList1(List<ReservaAtiva> reservasAtivasList1) {
        this.reservasAtivasList1 = reservasAtivasList1;
    }

    public List<ReservaAtiva> getReservasAtivasList2() {
        return reservasAtivasList2;
    }

    public void setReservasAtivasList2(List<ReservaAtiva> reservasAtivasList2) {
        this.reservasAtivasList2 = reservasAtivasList2;
    }

    public Estudante getEstudanteRa() {
        return estudanteRa;
    }

    public void setEstudanteRa(Estudante estudanteRa) {
        this.estudanteRa = estudanteRa;
    }

    public Sala getSalaId() {
        return salaId;
    }

    public void setSalaId(Sala salaId) {
        this.salaId = salaId;
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

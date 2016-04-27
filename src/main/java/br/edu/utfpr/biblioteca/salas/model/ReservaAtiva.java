/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mateus
 */
@Entity(name = "ReservaAtiva")
@Table(name = "Reservas_Ativas")
@NamedQueries({
    @NamedQuery(name = "ReservaAtiva.findAll", query = "SELECT r FROM ReservaAtiva r")})
public class ReservaAtiva implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ReservaAtivaPK reservaAtivaPK;

    @JoinColumn(name = "reserva_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Reserva reserva;

    @JoinColumn(name = "reserva_estudante_ra", referencedColumnName = "estudante_ra", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estudante estudante;

    @JoinColumn(name = "reserva_data_inicial", referencedColumnName = "data_inicial", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;

    protected ReservaAtiva() {
    }

    public ReservaAtiva(ReservaAtivaPK reservasAtivasPK, Reserva reserva) {
        this.reservaAtivaPK = reservasAtivasPK;
        this.reserva = reserva;
    }

    public ReservaAtiva(Estudante estudante, Date dataInicial, Reserva reserva) {
        this.reservaAtivaPK = new ReservaAtivaPK(estudante, dataInicial);
        this.reserva = reserva;
    }

    public ReservaAtivaPK getReservaAtivaPK() {
        return reservaAtivaPK;
    }

    public void setReservaAtivaPK(ReservaAtivaPK reservaAtivaPK) {
        this.reservaAtivaPK = reservaAtivaPK;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservaAtivaPK != null ? reservaAtivaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaAtiva)) {
            return false;
        }
        ReservaAtiva other = (ReservaAtiva) object;
        if ((this.reservaAtivaPK == null && other.reservaAtivaPK != null) || (this.reservaAtivaPK != null && !this.reservaAtivaPK.equals(other.reservaAtivaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.biblioteca.salas.model.ReservaAtiva[ ReservaAtivaPK=" + reservaAtivaPK + " ]";
    }

}

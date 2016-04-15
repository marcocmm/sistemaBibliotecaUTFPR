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
import javax.persistence.Table;

/**
 *
 * @author mateus
 */
@Entity
@Table(name = "Reservas_Ativas")
@NamedQueries({
    @NamedQuery(name = "ReservasAtivas.findAll", query = "SELECT r FROM ReservasAtivas r")})
public class ReservaAtiva implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservaAtivaPK reservasAtivasPK;
    @JoinColumn(name = "reserva_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Reserva reservaId;
    @JoinColumn(name = "reserva_estudante_ra", referencedColumnName = "estudante_ra", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reserva reservas;
    @JoinColumn(name = "reserva_data_inicial", referencedColumnName = "data_inicial", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reserva reservas1;

    public ReservaAtiva() {
    }

    public ReservaAtiva(ReservaAtivaPK reservasAtivasPK) {
        this.reservasAtivasPK = reservasAtivasPK;
    }

    public ReservaAtiva(String reservaEstudanteRa, Date reservaDataInicial) {
        this.reservasAtivasPK = new ReservaAtivaPK(reservaEstudanteRa, reservaDataInicial);
    }

    public ReservaAtivaPK getReservasAtivasPK() {
        return reservasAtivasPK;
    }

    public void setReservasAtivasPK(ReservaAtivaPK reservasAtivasPK) {
        this.reservasAtivasPK = reservasAtivasPK;
    }

    public Reserva getReservaId() {
        return reservaId;
    }

    public void setReservaId(Reserva reservaId) {
        this.reservaId = reservaId;
    }

    public Reserva getReservas() {
        return reservas;
    }

    public void setReservas(Reserva reservas) {
        this.reservas = reservas;
    }

    public Reserva getReservas1() {
        return reservas1;
    }

    public void setReservas1(Reserva reservas1) {
        this.reservas1 = reservas1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservasAtivasPK != null ? reservasAtivasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaAtiva)) {
            return false;
        }
        ReservaAtiva other = (ReservaAtiva) object;
        if ((this.reservasAtivasPK == null && other.reservasAtivasPK != null) || (this.reservasAtivasPK != null && !this.reservasAtivasPK.equals(other.reservasAtivasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.biblioteca.salas.model.ReservasAtivas[ reservasAtivasPK=" + reservasAtivasPK + " ]";
    }
    
}

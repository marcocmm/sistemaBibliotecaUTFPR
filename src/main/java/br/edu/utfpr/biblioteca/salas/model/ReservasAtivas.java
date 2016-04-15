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
public class ReservasAtivas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservasAtivasPK reservasAtivasPK;
    @JoinColumn(name = "reserva_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Reservas reservaId;
    @JoinColumn(name = "reserva_estudante_ra", referencedColumnName = "estudante_ra", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reservas reservas;
    @JoinColumn(name = "reserva_data_inicial", referencedColumnName = "data_inicial", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reservas reservas1;

    public ReservasAtivas() {
    }

    public ReservasAtivas(ReservasAtivasPK reservasAtivasPK) {
        this.reservasAtivasPK = reservasAtivasPK;
    }

    public ReservasAtivas(String reservaEstudanteRa, Date reservaDataInicial) {
        this.reservasAtivasPK = new ReservasAtivasPK(reservaEstudanteRa, reservaDataInicial);
    }

    public ReservasAtivasPK getReservasAtivasPK() {
        return reservasAtivasPK;
    }

    public void setReservasAtivasPK(ReservasAtivasPK reservasAtivasPK) {
        this.reservasAtivasPK = reservasAtivasPK;
    }

    public Reservas getReservaId() {
        return reservaId;
    }

    public void setReservaId(Reservas reservaId) {
        this.reservaId = reservaId;
    }

    public Reservas getReservas() {
        return reservas;
    }

    public void setReservas(Reservas reservas) {
        this.reservas = reservas;
    }

    public Reservas getReservas1() {
        return reservas1;
    }

    public void setReservas1(Reservas reservas1) {
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
        if (!(object instanceof ReservasAtivas)) {
            return false;
        }
        ReservasAtivas other = (ReservasAtivas) object;
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

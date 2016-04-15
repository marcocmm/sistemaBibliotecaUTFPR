/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mateus
 */
@Embeddable
public class ReservaAtivaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "reserva_estudante_ra")
    private String reservaEstudanteRa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reserva_data_inicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservaDataInicial;

    public ReservaAtivaPK() {
    }

    public ReservaAtivaPK(String reservaEstudanteRa, Date reservaDataInicial) {
        this.reservaEstudanteRa = reservaEstudanteRa;
        this.reservaDataInicial = reservaDataInicial;
    }

    public String getReservaEstudanteRa() {
        return reservaEstudanteRa;
    }

    public void setReservaEstudanteRa(String reservaEstudanteRa) {
        this.reservaEstudanteRa = reservaEstudanteRa;
    }

    public Date getReservaDataInicial() {
        return reservaDataInicial;
    }

    public void setReservaDataInicial(Date reservaDataInicial) {
        this.reservaDataInicial = reservaDataInicial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservaEstudanteRa != null ? reservaEstudanteRa.hashCode() : 0);
        hash += (reservaDataInicial != null ? reservaDataInicial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaAtivaPK)) {
            return false;
        }
        ReservaAtivaPK other = (ReservaAtivaPK) object;
        if ((this.reservaEstudanteRa == null && other.reservaEstudanteRa != null) || (this.reservaEstudanteRa != null && !this.reservaEstudanteRa.equals(other.reservaEstudanteRa))) {
            return false;
        }
        if ((this.reservaDataInicial == null && other.reservaDataInicial != null) || (this.reservaDataInicial != null && !this.reservaDataInicial.equals(other.reservaDataInicial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.biblioteca.salas.model.ReservasAtivasPK[ reservaEstudanteRa=" + reservaEstudanteRa + ", reservaDataInicial=" + reservaDataInicial + " ]";
    }
    
}

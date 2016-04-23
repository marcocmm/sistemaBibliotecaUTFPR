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
    private Estudante estudante;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "reserva_data_inicial")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicial;

    protected ReservaAtivaPK() {
    }

    public ReservaAtivaPK(Estudante estudante, Date dataInicial) {
        this.estudante = estudante;
        this.dataInicial = dataInicial;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudante != null ? estudante.hashCode() : 0);
        hash += (dataInicial != null ? dataInicial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaAtivaPK)) {
            return false;
        }
        ReservaAtivaPK other = (ReservaAtivaPK) object;
        if ((this.estudante == null && other.estudante != null) || (this.estudante != null && !this.estudante.equals(other.estudante))) {
            return false;
        }
        if ((this.dataInicial == null && other.dataInicial != null) || (this.dataInicial != null && !this.dataInicial.equals(other.dataInicial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.biblioteca.salas.model.ReservasAtivasPK[ reservaEstudanteRa=" + estudante + ", reservaDataInicial=" + dataInicial + " ]";
    }
    
}

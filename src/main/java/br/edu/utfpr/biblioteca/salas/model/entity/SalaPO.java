/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mateus
 */
@Entity(name = "Sala")
@Table(name = "Salas")
@NamedQueries({
    @NamedQuery(name = "Sala.findAll", query = "SELECT s FROM Sala s")})
public class SalaPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "ar")
    private Boolean ar;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sala")
    private List<ReservaPO> reservas;

    protected SalaPO() {
    }

    public SalaPO(Integer id, boolean ar) {
        this.id = id;
        this.ar = ar;
    }

    public int getId() {
        return id;
    }

    public void setId(String idStr) {
        this.id = Integer.valueOf(idStr);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean hasAr() {
        return ar;
    }

    public void hasAr(Boolean ar) {
        this.ar = ar;
    }

    public List<ReservaPO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaPO> reservas) {
        this.reservas = reservas;
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
        if (!(object instanceof SalaPO)) {
            return false;
        }
        SalaPO other = (SalaPO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.biblioteca.salas.model.Sala[ id=" + id + " ]";
    }

    @Override
    public Object clone() {
        return new SalaPO(this.id, this.ar);
    }
}

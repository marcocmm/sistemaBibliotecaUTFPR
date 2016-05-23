/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.Date;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author marco
 */
@Named(value = "reservaMB")
@ViewScoped
@ManagedBean
public class ReservaMB {

    private ReservaPO reserva;
    private String strHorario;

    /**
     * Creates a new instance of SalaMB
     */
    public ReservaMB() {
        this.reserva = new ReservaPO(new EstudantePO(null, null, null, null), new SalaPO(0, true), new Date(), 0);
    }

    public ReservaPO getReserva() {
        return reserva;
    }

    public String getStrHorario() {
        return strHorario;
    }

    public void reservarSala(){
        throw new UnsupportedOperationException();
    }
}

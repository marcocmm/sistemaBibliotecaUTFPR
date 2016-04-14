/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Reserva;
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

    private Reserva reserva = new Reserva();

    /**
     * Creates a new instance of ReservaMB
     */
    public ReservaMB() {
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void salvarReserva() {
        //Validar
        //Persistir Reserva

    }
}

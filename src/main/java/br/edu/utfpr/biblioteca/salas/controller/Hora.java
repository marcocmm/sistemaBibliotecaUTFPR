/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Reserva;
import java.util.Date;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Hora {

    private Date hora;
    private List<Reserva> reservas;

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public Object clone() {
        Hora hora = new Hora();
        hora.setHora(this.hora);
        hora.reservas.addAll(this.reservas);
        return hora;
    }

}

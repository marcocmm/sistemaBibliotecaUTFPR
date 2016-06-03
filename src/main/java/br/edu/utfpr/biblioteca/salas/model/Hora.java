/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model;

import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Mapeia as tuplas hora das folhas da biblioteca. Cada tupla possui uma lista
 * de reservas, de tamanho máximo uma para cada sala.
 *
 * @author romulo
 */
public class Hora {

    private Date hora;
    private List<ReservaPO> reservas;

    public Hora() {
        this.reservas = new ArrayList<>();
    }

    @Override
    public String toString() {
        if(hora == null){
            return "livre";
        }
        return CalendarioHelper.getData(hora);
    }
    

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public List<ReservaPO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaPO> reservas) {
        this.reservas = reservas;
    }

    public void addReserva(ReservaPO reserva) {
        if (reserva != null) {
            reservas.add(reserva);
        } else {
            reservas.add(new ReservaPO(null, null, null, 0));
        }
    }

    @Override
    public Object clone() {
        Hora hora = new Hora();
        hora.setHora(this.hora);
        hora.reservas.addAll(this.reservas);
        return hora;
    }

}

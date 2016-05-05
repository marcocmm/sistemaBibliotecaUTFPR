/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;


import br.edu.utfpr.biblioteca.salas.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.model.Administrador;
import br.edu.utfpr.biblioteca.salas.model.Reserva;

import tools.CalendarioController;
import br.edu.utfpr.biblioteca.salas.model.Administrador;

import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author marco
 */


@Named(value = "administradorMB")
@ViewScoped
@ManagedBean
public class AdministradorMB {

    private Administrador administrador;

    private ReservaDAO reservaDAO = new ReservaDAO();

    private Date data;
    private int idSala;

    private List<Date> calendario;

    /**
     * Creates a new instance of AdministradorMB
     */
    public AdministradorMB() {
        this.idSala = 2;
        this.data = new Date(2016, 04, 29, 18, 0, 0);
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    /**
     * Método que faz uma consulta no BD com uma data e um id da sala, retorna
     * uma lista de reservas correspondente.
     *
     * @param data Date
     * @param idSala int
     * @return List<Reserva> reservas
     */
    public List<Reserva> getReservas() {
        List<Reserva> allReservas = reservaDAO.list();
        List<Reserva> reservas = null;

        if (allReservas != null) {
            for (Reserva r : allReservas) {
                if (r.getDataInicial().equals(this.data) && r.getId() == this.idSala) {
                    reservas.add(r);
                }
            }
        }

        return reservas;
    }

    
    public List<Date> getCalendario() {
        return CalendarioController.getCalendario(2016, 04);
    }

    public void setCalendario(List<Date> calendario) {
        this.calendario = calendario;
    }

}

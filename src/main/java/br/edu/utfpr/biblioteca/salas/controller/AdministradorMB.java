/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.Reserva;

import tools.CalendarioController;
import br.edu.utfpr.biblioteca.salas.model.Administrador;
import br.edu.utfpr.biblioteca.salas.model.Sala;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import tools.ReservasHorario;

@Named(value = "administradorMB")
@ViewScoped
@ManagedBean
public class AdministradorMB {

    private Administrador administrador;

    private ReservaDAO reservaDAO = new ReservaDAO();
    private SalaDAO salaDAO = new SalaDAO();

    private Date data;
    private int idSala;
    private String sala = "Sala";

    private List<Date> calendario;

    private String horario;
    private String status;

    /**
     * Creates a new instance of AdministradorMB
     */
    public AdministradorMB() {
//        this.idSala = 2;
//        this.data = new Date(2016, 04, 29, 18, 0, 0);
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

    public List<Date> getCalendario() {
        return CalendarioController.getCalendario(2016, 04);
    }

    public void setCalendario(List<Date> calendario) {
        this.calendario = calendario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
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
        List<Reserva> reservas = new ArrayList<>();

        if (allReservas != null) {
            for (Reserva r : allReservas) {
                if (r.getDataInicial().equals(this.data) && r.getId() == this.idSala) {;
                    reservas.add(r);
                }
            }
        }

        if (reservas.isEmpty()) {
            return allReservas;
        }

        return reservas;
    }

    public HashMap<String, String> getSalas() {
        HashMap<String, String> salas = new HashMap<>();
        for (Sala sala : salaDAO.list()) {
            System.out.println("Sala: " + sala.getId());
            salas.put(String.valueOf(sala.getId()), "Sala " + String.valueOf(sala.getId()));
        }
        return salas;
    }

    public List<ReservasHorario> getReservasHorario() {
        List<ReservasHorario> reservasHorario = new ArrayList<>();
        for (Reserva reserva : getReservas()) {
            ReservasHorario rH = new ReservasHorario();
            rH.setHorario(String.valueOf(CalendarioController.getDatabaseDateFormat(reserva.getDataInicial())));
            rH.setStatus(String.valueOf(reserva.getStatus().getName()));
            reservasHorario.add(rH);
        }

        return reservasHorario;
    }

    public void consultarSala(ActionEvent event) {
//        getReservasHorario();
    }

}

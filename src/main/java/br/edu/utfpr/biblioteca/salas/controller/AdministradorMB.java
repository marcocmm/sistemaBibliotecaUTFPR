/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

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
    private List<Date> calendario;

    /**
     * Creates a new instance of AdministradorMB
     */
    public AdministradorMB() {
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public List<Date> getCalendario() {
        return CalendarioController.getCalendario(2016, 04);
    }

    public void setCalendario(List<Date> calendario) {
        this.calendario = calendario;
    }

}

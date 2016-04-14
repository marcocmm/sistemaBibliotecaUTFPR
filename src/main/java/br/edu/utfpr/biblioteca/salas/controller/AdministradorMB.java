/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Administrador;
import java.io.Serializable;
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

    private Administrador administrador = new Administrador();

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

}

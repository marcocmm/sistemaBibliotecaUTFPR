/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.HashMap;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author marco
 */
@Named(value = "salaMB")
@ViewScoped
@ManagedBean
public class SalaMB {

    private SalaPO sala;
    private final SalaDAO salaDAO;

    /**
     * Creates a new instance of SalaMB
     */
    public SalaMB() {
        this.sala = new SalaPO(1, true);
        salaDAO = new SalaDAO();
    }

    public SalaPO getSala() {
        return sala;
    }

    public void setSala(SalaPO sala) {
        this.sala = sala;
    }

    public HashMap<String, String> getSalas() {
        HashMap<String, String> salas = new HashMap<>();
        for (SalaPO sala : salaDAO.list()) {
            System.out.println("Sala: " + sala.getId());
            salas.put(String.valueOf(sala.getId()), "Sala " + String.valueOf(sala.getId()));
        }

        return salas;
    }

}

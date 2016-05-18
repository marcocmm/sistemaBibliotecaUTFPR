/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model;

import java.io.Serializable;

/**
 *
 * @author romulo
 */
public class BotaoHorario implements Serializable {

    private final String value;
    private final String classe;
    private final boolean disabled;
    private final int hora;

    public BotaoHorario(String value, String classe, boolean disabled, int hora) {
        this.value = value;
        this.classe = classe;
        this.disabled = disabled;
        this.hora = hora;
    }

    public String getClasse() {
        return classe;
    }

    public int getHora() {
        return hora;
    }

    public String getValue() {
        return value;
    }

    public boolean getDisabled() {
        return disabled;
    }

}

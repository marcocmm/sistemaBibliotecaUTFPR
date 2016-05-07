/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import java.util.Date;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Dia {

    private Date data;
    private List<Hora> horario;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Hora> getHorario() {
        return horario;
    }

    public void setHorario(List<Hora> horario) {
        this.horario = horario;
    }

    public void addHora(Hora hora) {
        this.horario.add(hora);
    }
}

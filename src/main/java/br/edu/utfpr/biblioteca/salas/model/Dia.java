/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model;

import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Mapeia as folhas de papel existentes na biblioteca. Nas folhas e nesta classe
 * constam o dia no formato Date e uma lista de horas. Cada hora é uma tupla
 * contendo as reservas programadas para o horário em questão (no máximo 6
 * reservas, uma para cada sala).
 *
 * @author romulo
 */
public class Dia {

    private Date data;
    private List<Hora> horario;

    public Dia() {
        this.horario = new ArrayList<>();
        this.data = new Date();
    }

    public Date getData() {
        return data;
    }
    
     public String getStrDia() {
        return CalendarioHelper.getDiaMes(data);
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
        if (hora != null) {
            this.horario.add(hora);
        }
    }
}

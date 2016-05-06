/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import br.edu.utfpr.biblioteca.salas.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.Reserva;
import br.edu.utfpr.biblioteca.salas.model.Sala;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import tools.CalendarioHelper;

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

    public void addHora(Hora horas) {
        this.horario.add(horas);
    }

    public void setHorario(List<Hora> horario) {
        this.horario = horario;
    }

}

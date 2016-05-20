package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.Dia;
import br.edu.utfpr.biblioteca.salas.model.Hora;
import br.edu.utfpr.biblioteca.salas.model.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;

public class ReservaBO {

    public static ReservaDAO reservaDAO = new ReservaDAO();

    /**
     * Método que faz uma consulta no BD com uma data e um id da strSala,
     * retorna uma lista de reservas correspondente.
     *
     * @param data Date
     * @param sala
     * @param idSala int
     * @return List<Reserva> reservas
     */
    public static List<ReservaPO> getReservas(Date data, int idSala) {
        return reservaDAO.listByDateAndIdSala(data, idSala);
    }

    //O que isto faz??? @author comenatar pls
    private static HashMap<SalaPO, ReservaPO> clone(HashMap<SalaPO, ReservaPO> map) {
        HashMap<SalaPO, ReservaPO> copy = new HashMap();

        for (Map.Entry<SalaPO, ReservaPO> entry : map.entrySet()) {
            try {
                copy.put((SalaPO) entry.getKey().clone(), (ReservaPO) entry.getValue().clone());
            } catch (NullPointerException ex) {
                copy.put((SalaPO) entry.getKey().clone(), null);
            }
        }
        return copy;
    }

    /**
     * Método descreve um dia
     *
     * @param date uma data válida
     * @return um hashmap que associa data ao conjunto de salas, cada qual com
     * sua reserva
     */
    public static HashMap<Date, HashMap<SalaPO, ReservaPO>> descreverDiaHash(Date date) {
        ReservaDAO dao = new ReservaDAO();
        SalaDAO salaDAO = new SalaDAO();

        List<SalaPO> salas = salaDAO.list();
        List<Date> horarios = CalendarioHelper.getHorarios(date);
        List<ReservaPO> reservas = dao.listByDate(date);

        HashMap<SalaPO, ReservaPO> salaTemReservas = new HashMap();
        HashMap<Date, HashMap<SalaPO, ReservaPO>> dataTemReservas = new HashMap();

        for (SalaPO sala : salas) {
            salaTemReservas.put(sala, null);
        }

        for (Date horario : horarios) {
            dataTemReservas.put(horario, clone(salaTemReservas));
        }

        for (ReservaPO reserva : reservas) {
            dataTemReservas.get(reserva.getDataInicial()).put(reserva.getSala(), reserva);
        }
        return dataTemReservas;
    }
    public static Dia descreverDia(Date date) {
        HashMap<Date, HashMap<SalaPO, ReservaPO>> dataTemReservas = descreverDiaHash(date);

        Dia dia;
        Hora horario;

        dia = new Dia();
        dia.setData(date);

        for (Map.Entry<Date, HashMap<SalaPO, ReservaPO>> horaTemReserva : dataTemReservas.entrySet()) {
            Date hora = horaTemReserva.getKey();
            HashMap<SalaPO, ReservaPO> salaTemReservas = horaTemReserva.getValue();

            horario = new Hora();
            horario.setHora(hora);
            for (Map.Entry<SalaPO, ReservaPO> salaTemReserva : salaTemReservas.entrySet()) {
                ReservaPO reserva = salaTemReserva.getValue();

                horario.addReserva(reserva);
            }
            dia.addHora(horario);
        }
        return dia;
    }

    public static List<Dia> getMes(Date date) {
        List<Date> dias = CalendarioHelper.getCalendario(date);
        List<Dia> mes = new ArrayList<>();
        for (Date dia : dias) {
            mes.add(descreverDia(dia));
        }
        return mes;
    }

    public static boolean reservar(ReservaPO reserva) {
        if (reservaDAO.isReservado(reserva)) {
            return false;
        }
        return reservaDAO.insert(reserva);
    }

}

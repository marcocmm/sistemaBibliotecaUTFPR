package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.Dia;
import br.edu.utfpr.biblioteca.salas.model.Hora;
import br.edu.utfpr.biblioteca.salas.model.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.StatusPO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import br.edu.utfpr.biblioteca.salas.tools.HashMapHelper;

public class ReservaBO {

    public static ReservaDAO reservaDAO = new ReservaDAO();

    /**
     * Método que invoca o DAO para obter as reservas de um determinado dia-hora
     * e sala.
     *
     * @param data Date
     * @param idSala int
     * @return lista de reservas
     */
    public static List<ReservaPO> getReservas(Date data, int idSala) {
        return reservaDAO.listByDateTimeAndSala(data, idSala);
    }

    /**
     * Sobrecarga de método.
     *
     * @param data Date
     * @param sala
     * @return lista de reservas
     */
    public static List<ReservaPO> getReservas(Date data, SalaPO sala) {
        return getReservas(data, sala.getId());
    }

    /**
     * Método descreve um dia. Obtém um relatório de todas as reservas feitas e
     * não feitas em todas as salas durante um dia.
     *
     * @see descreverDia(Date date)
     * @param date uma data válida
     * @return um hashmap que associa data ao conjunto de salas, cada qual com
     * sua reserva
     */
    public static HashMap<Date, HashMap<SalaPO, ReservaPO>> descreverDiaHash(Date date) {
        ReservaDAO dao = new ReservaDAO();
        SalaDAO salaDAO = new SalaDAO();

        List<SalaPO> salas = salaDAO.list();
        List<Date> horarios = CalendarioHelper.getHorarios(date);
        List<ReservaPO> reservas = dao.listByDateTime(date);

        HashMap<SalaPO, ReservaPO> salaTemReservas = new HashMap();
        HashMap<Date, HashMap<SalaPO, ReservaPO>> dataTemReservas = new HashMap();

        for (SalaPO sala : salas) {
            salaTemReservas.put(sala, null);
        }

        for (Date horario : horarios) {
            dataTemReservas.put(horario, HashMapHelper.clone(salaTemReservas));
        }

        for (ReservaPO reserva : reservas) {
            dataTemReservas.get(reserva.getDataInicial()).put(reserva.getSala(), reserva);
        }
        return dataTemReservas;
    }

    /**
     * Método que descreve um dia. Obtém um relatório de todas as reservas
     * feitas e não feitas em todas as salas durante um dia.
     *
     * @see descreverDiaHash(Date date)
     * @param date
     * @return Um objeto dia composto de uma lista de horário. Cada horário
     * contém uma lista de Reservas cujo tamanho máximo limita-se ao número de
     * salas.
     */
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

    /**
     * Obtém um relatório mensal de todos os dias. Contém todas as reservas
     * feitas e não feitas em todos os horários e salas.
     *
     * @param date
     * @return
     */
    public static List<Dia> descreverMes(Date date) {
        List<Date> dias = CalendarioHelper.getCalendario(date);
        List<Dia> mes = new ArrayList<>();
        for (Date dia : dias) {
            mes.add(descreverDia(dia));
        }
        return mes;
    }

    public static boolean cancelarReserva(ReservaPO reserva){
        reserva.setStatus(new StatusPO("inativa"));
        reservaDAO.update(reserva);
        return false;
    }
}

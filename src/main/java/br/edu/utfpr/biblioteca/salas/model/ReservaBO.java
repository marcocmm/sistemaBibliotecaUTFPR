package br.edu.utfpr.biblioteca.salas.model;

import br.edu.utfpr.biblioteca.salas.model.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import java.util.Date;
import java.util.List;

public class ReservaBO {

    static ReservaDAO daoReservas;

    public ReservaBO() {
        daoReservas = new ReservaDAO();
    }

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
        return daoReservas.listByDateAndIdSala(data, idSala);
    }


}

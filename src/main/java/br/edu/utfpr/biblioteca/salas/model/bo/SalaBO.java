package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import br.edu.utfpr.biblioteca.salas.model.ReservasHorario;
import static br.edu.utfpr.biblioteca.salas.model.bo.ReservaBO.reservaDAO;
import br.edu.utfpr.biblioteca.salas.model.dao.EstudanteDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import br.edu.utfpr.biblioteca.salas.model.entity.StatusPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.Date;

public class SalaBO {

    public static SalaDAO salaDAO = new SalaDAO();

    /**
     * Assinatura do método alterada. Não utilize este método.
     *
     * @see getHorariosDisponiveis(Date date)
     * @param date
     * @return
     * @deprecated utilizar getHorariosDisponiveis(Date date)
     */
    @Deprecated
    public static HashMap<Integer, Boolean> getStatusDaSala(String date) {
        Date dataInicial = CalendarioHelper.parseDate("10-05-2016", "07", "00", "00");
        Date dataFinal = CalendarioHelper.parseDate("10-05-2016", "23", "00", "00");

        int qtdSalas = salaDAO.getQuantidadeSalas();
        HashMap<Integer, Boolean> hashList = null;

        for (int i = 8; i <= 23; i++) {

            Date data = CalendarioHelper.parseDate(date, i, 0, 0);

            int qtdReservas = reservaDAO.getQuantidadeReservas(data);

            if (qtdReservas == qtdSalas) {
                hashList.put(i, Boolean.FALSE);
            } else {
                hashList.put(i, Boolean.FALSE);

            }
        }
        return hashList;
    }

    /**
     * Este método recebe uma data (hora é desconsiderada) e retorna um hash
     * contendo uma chave horário e valor boolean. O valor é true se alguma sala
     * não possui reserva nesse horario ou false se todas as salas estão
     * reservadas naquele horário.
     *
     * @param date
     * @return HashMap<Date, Boolean>
     */
    public static HashMap<Date, Boolean> getHorariosDisponiveis(Date date) {
        HashMap<Date, Boolean> horariosDisponiveis = new HashMap<>();
        List<Date> horarios = CalendarioHelper.getHorarios(date);
        for (Date horario : horarios) {
            horariosDisponiveis.put(horario, salaDAO.isAnyoneSalaVaga(horario));
        }
        return horariosDisponiveis;
    }

    /**
     * Este método recebe uma data-hora, e deve retornar uma lista de salas
     * disponíveis.
     *
     * @param horario
     * @return
     */
    public static List<SalaPO> getSalasDisponiveis(Date horario) {
        return salaDAO.getWhichSalasAreVagas(horario);
    }

    /**
     * Contacta o DAO para obter uma sala dado um id.
     *
     * @param id
     * @return
     */
    public static SalaPO obter(int id) {
        return salaDAO.obter(id);
    }

    /**
     * Obtém uma lista de ReservasHorario que associa um horario a um status.
     *
     * @param sala
     * @return
     * @deprecated
     */
    @Deprecated
    public static List<ReservasHorario> getReservasHorario(SalaPO sala) {
        SalaPO salaPO = obter(sala.getId());
        List<ReservasHorario> reservasHorario = new ArrayList();
        ReservasHorario rH = new ReservasHorario();
        reservasHorario.add(rH);
        return reservasHorario;
    }

    /**
     * Este método constrói o objeto ReservaPO com os atributos passado por
     * parâmetro, e faz os procedimentos para reservar uma sala. Propaga exceção
     * caso dados sejam inválidos.
     *
     * @param reserva
     * @throws java.lang.Exception
     */
    public static void reservarSala(ReservaPO reserva) throws Exception {
        EstudantePO estudante;
        SalaPO sala;
        EstudanteDAO estudanteD = new EstudanteDAO();

        if (EstudanteBO.isAutentico(reserva.getEstudante()) == null) {
            throw new Exception("Credenciais inválidas");
        }

        estudante = EstudanteBO.obterEstudante(reserva.getEstudante().getRa());
        reserva.setEstudante(estudante);

        sala = SalaBO.obter(reserva.getSala().getId());
        reserva.setSala(sala);

        reserva.setStatus(new StatusPO("ativa"));

        if (reservaDAO.isReservado(reserva)) {
            throw new Exception("Sala já reservada");
        }

        if (!(estudanteD.canReservar(reserva.getEstudante(), reserva.getDataInicial()))) {
            throw new Exception("Você já efetuou o limite máximo de reservas diárias!");

        }

        reservaDAO.insert(reserva);
    }

    @Deprecated
    public static void cancelarSala(ReservaPO reserva) throws Exception {
        if (reservaDAO.isReservado(reserva)) {
            reservaDAO.delete(reserva);
        }
    }

    /**
     * Obtém um HashMap que associa o id (String) da sala com o nome da sala.
     *
     * @return
     */
    public static HashMap<String, String> getSalas() {
        HashMap<String, String> salas = new HashMap<>();
        for (SalaPO sala : salaDAO.list()) {
            salas.put(String.valueOf(sala.getId()), "Sala " + String.valueOf(sala.getId()));
        }
        return salas;
    }

}

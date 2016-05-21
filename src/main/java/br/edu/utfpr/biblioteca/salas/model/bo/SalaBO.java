package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import br.edu.utfpr.biblioteca.salas.model.ReservasHorario;
import static br.edu.utfpr.biblioteca.salas.model.bo.ReservaBO.reservaDAO;
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
    public static HashMap<Integer, Boolean> getStatusDaSala(Date date) {
        Date dataInicial = CalendarioHelper.parseDate("10-05-2016", "07", "00", "00");
        Date dataFinal = CalendarioHelper.parseDate("10-05-2016", "23", "00", "00");
        Date dataTeste = CalendarioHelper.parseDate("10-05-2016", "08", "00", "00");
        int full = 0;
        List<ReservaPO> list = new ArrayList<>();
        List<ReservaPO> listR = new ArrayList();
        HashMap<Integer, Boolean> hashList = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDataInicial().equals(date)) {
                listR.add(list.get(i));
            }
        }
        for (int j = 0; j < listR.size(); j++) {
            if (listR.get(j).getStatus().equals(new StatusPO("ativa"))) {
                full++;
                if (full == 6) {
                    int horario = date.getHours();
                    hashList.put(horario, false);
                }
                if (j == listR.size() && full < 6) {
                    int horario = date.getHours();
                    hashList.put(horario, true);
                }
            }
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Este método recebe uma data (Composta de dia e hora) e retorna um hash
     * contendo uma chave horário e valor boolean. O valor é true se alguma sala
     * não possui reserva nesse horario ou false se todas as salas estão
     * reservadas naquele horário.
     *
     * @param date
     * @return HashMap<Integer, Boolean>
     */
    public static HashMap<Integer, Boolean> getHorariosDisponiveis(Date date) {
        //executa o metodo getSalasDisp para todos horarios do dia recebido /\
        //Na lista que retornara: pega a posição 0 se for null põe no hash(dessa função) hash.put(8,false)
        //se não for null pega o horario e põe true

        Date dataInicial = CalendarioHelper.parseDate("10-05-2016", "07", "00", "00");
        Date dataFinal = CalendarioHelper.parseDate("10-05-2016", "23", "00", "00");
        List<ReservaPO> list = new ArrayList<>();
        HashMap<Integer, Boolean> hashList = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus().equals(new StatusPO("inativa"))) {
                hashList.put(list.get(i).getId(), false);
            }
            if (list.get(i).getStatus().equals(new StatusPO("ativa"))) {
                hashList.put(list.get(i).getId(), true);
            }
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Este método recebe uma data-hora, e deve retornar uma lista de salas
     * disponíveis.
     *
     * @param date
     * @return List<SalaPO>
     */
    public static List<SalaPO> getSalasDisponiveis(Date date) {
        //USA O RESERVADAO - metodo listByDateTime
        //Pega todas as reservas dado o horario
        //verifica se dado essas reservas existe alguma sala disponivel e add as salas disponiveis na lista
        Date dataInicial = CalendarioHelper.parseDate("10-05-2016", "07", "00", "00");
        Date dataFinal = CalendarioHelper.parseDate("10-05-2016", "23", "00", "00");
        List<SalaPO> list = salaDAO.getSalasDisponiveis(dataInicial, dataFinal);
        throw new UnsupportedOperationException();
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
    public List<ReservasHorario> getReservasHorario(SalaPO sala) {
        SalaPO salaPO = obter(sala.getId());
        List<ReservasHorario> reservasHorario = new ArrayList();
        ReservasHorario rH = new ReservasHorario();
        reservasHorario.add(rH);
        throw new UnsupportedOperationException();
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

        if (!EstudanteBO.isAutentico(reserva.getEstudante())) {
            throw new Exception("Credenciais inválidas");
        }

        estudante = EstudanteBO.obterEstudante(reserva.getEstudante().getRa());
        reserva.setEstudante(estudante);

        sala = SalaBO.obter(reserva.getSala().getId());
        reserva.setSala(sala);

        if (reservaDAO.isReservado(reserva)) {
            throw new Exception("Sala já reservada");
        }
        reservaDAO.insert(reserva);
    }

    /**
     * Obtém um HashMap que associa o id (String) da sala com o nome da sala.
     *
     * @return
     */
    public HashMap<String, String> getSalas() {
        HashMap<String, String> salas = new HashMap<>();
        for (SalaPO sala : salaDAO.list()) {
            salas.put(String.valueOf(sala.getId()), "Sala " + String.valueOf(sala.getId()));
        }
        return salas;
    }

}

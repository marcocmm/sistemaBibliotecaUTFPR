package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import br.edu.utfpr.biblioteca.salas.model.ReservasHorario;
import br.edu.utfpr.biblioteca.salas.model.entity.StatusPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.Date;

public class SalaBO {

    public static SalaDAO salaDAO = new SalaDAO();

    /**
     * Este método recebe uma data e retorna um hash contendo uma chave horário,
     * set true se alguma sala possui reservas disponíves ou false se todas as
     * salas estão reservadas naquele horário.
     *
     * @param date
     * @return HashMap<Integer, Boolean>
     */
    public static HashMap<Integer, Boolean> getStatusDaSala(Date date) {
        Date dataInicial = CalendarioHelper.parseDate("10-05-2016", "07", "00", "00");
        Date dataFinal = CalendarioHelper.parseDate("10-05-2016", "23", "00", "00");
        SalaDAO salaDAO = new SalaDAO();
        List<ReservaPO> list = salaDAO.getStatusDaSala(dataInicial, dataFinal);
        HashMap<Integer, Boolean> hashList = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus().equals(new StatusPO("inativa"))) {
                hashList.put(list.get(i).getId(), false);

            }
            if (list.get(i).getStatus().equals(new StatusPO("ativa"))) {
                hashList.put(list.get(i).getId(), true);

            }

        }
        //O método não está fazendo oque deveria porque a documentação está confusa.
        return hashList;
    }

    /**
     * Este método recebe uma data e uma hora, e deve retornar uma lista de
     * salas disponíveis.
     *
     * @param date
     * @return List<SalaPO>
     */
    public static List<SalaPO> getSalasDisponiveis(Date date) {
        Date dataInicial = CalendarioHelper.parseDate("10-05-2016", "07", "00", "00");
        Date dataFinal = CalendarioHelper.parseDate("10-05-2016", "23", "00", "00");
        SalaDAO salaDAO = new SalaDAO();
        List<SalaPO> list = salaDAO.getSalasDisponiveis(dataInicial, dataFinal);
        return list;
    }

    /**
     * Contaca o dao para obter uma sala dado um id.
     *
     * @param id
     * @return
     */
    public static SalaPO obter(int id) {
        return salaDAO.obter(id);
    }

    @Deprecated
    public List<ReservasHorario> getReservasHorario(SalaPO salaPO) {
        SalaPO sala = salaDAO.obter(salaPO);
        List<ReservasHorario> reservasHorario = new ArrayList();
//        for (ReservaPO reserva : ReservaBO.getReservas(this.dataSelecionada, this.idSala)) {
        ReservasHorario rH = new ReservasHorario();
//        rH.setHorario(String.valueOf(CalendarioHelper.getDatabaseDateFormat(reserva.getDataInicial())));
//            rH.setStatus(String.valueOf(reserva.getStatus().getName()));
        reservasHorario.add(rH);
//        }
        throw new UnsupportedOperationException();

//        return reservasHorario;
    }

    /**
     * Este método constrói o objeto ReservaPO com os atributos passado por
     * parâmetro, e faz os procedimentos para reservas uma sala, retorn true se
     * a reserva foi feita e false quando não possível realizar.
     *
     * @return boolean Talvez retornar um boolean fique difícil de indentificar
     * o erro (login, sala já reservada) para mandar a mensagem para o usuário.
     */
    public static boolean reservarSala(ReservaPO reserva) {
        //implement the code here!
        //Construir o Objeto ReservaPO com os parâmentros
        //Chamar o insert do dao após as verificaçoes serem aprovasdas...
        if (!EstudanteBO.autenticar(reserva.getEstudante().getRa(), reserva.getEstudante().getSenha())) {//verificando a autenticação do estudante
            return false;
        }
        return false;
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

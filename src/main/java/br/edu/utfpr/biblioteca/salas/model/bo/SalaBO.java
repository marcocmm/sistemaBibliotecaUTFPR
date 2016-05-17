package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import br.edu.utfpr.biblioteca.salas.model.ReservasHorario;

public class SalaBO {

    /**
     * Este método recebe uma data e retorna um hash contendo uma chave horário,
     * set true se alguma sala possui reservas disponíves ou false se todas as
     * salas estão reservasdas naquele horário.
     *
     * @param data
     * @return HashMap<Integer, Boolean>
     */
    public static HashMap<Integer, Boolean> getStatusDaSala(String data) {
        //implement the code here!
        return null;
    }

    /**
     * Este método recebe uma data e uma hora, e deve retornar uma lista de
     * salas disponíveis.
     *
     * @param data
     * @param hora
     * @return List<SalaPO>
     */
    public static List<SalaPO> getSalasDisponiveis(String data, String hora) {
        //implement the code here!
        return null;
    }

    @Deprecated
    public List<ReservasHorario> getReservasHorario(SalaPO salaPO) {
        SalaDAO salaDAO = new SalaDAO();
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
     * @param idSala
     * @param qtdeAlunos
     * @param ra
     * @param senha
     * @param data
     * @param hora
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
        SalaDAO salaDAO = new SalaDAO();
        HashMap<String, String> salas = new HashMap<>();
        for (SalaPO sala : salaDAO.list()) {
            System.out.println("Sala: " + sala.getId());
            salas.put(String.valueOf(sala.getId()), "Sala " + String.valueOf(sala.getId()));
        }

        return salas;
    }

}

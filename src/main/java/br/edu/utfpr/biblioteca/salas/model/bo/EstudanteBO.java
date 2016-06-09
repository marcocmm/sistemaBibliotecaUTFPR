package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.dao.EstudanteDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.Date;

public class EstudanteBO {

    static EstudanteDAO estudanteDAO = new EstudanteDAO();

    /**
     * Este método chama o método isAutentico do dao
     *
     * @param ra
     * @param senha
     * @return boolean
     */
    public static EstudantePO isAutentico(String ra, String senha) {
        return estudanteDAO.isAutentico(ra, senha);
    }

    /**
     * Sobrecarga de método para permitir abstrações. Invoca isAutentico(ra,
     * senha)
     *
     * @param estudante
     * @return boolean
     */
    public static EstudantePO isAutentico(EstudantePO estudante) {
        return isAutentico(estudante.getRa(), estudante.getSenha());
    }

    /**
     * Contata o dao para obter um estudante dado um ra.
     *
     * @param ra
     * @return
     */
    public static EstudantePO obterEstudante(String ra) {
        return estudanteDAO.obter(ra);
    }

    /**
     * verifica se o estudante está cadastrado
     *
     * @param estudante
     * @return boolean
     */
    public static boolean alreadyCadastrado(EstudantePO estudante) {
        return estudanteDAO.obter(estudante.getRa()) != null;
    }

    /**
     * verifica se o estudante já está cadastrado e se a senha é vazia, caso ele
     * nao seja cadastrado e sua senha exista, o estudante é inserido.
     *
     * @param estudante
     */
    public static void cadastrarEstudante(EstudantePO estudante) {
        if (alreadyCadastrado(estudante)) {
            return;
        }
        if (estudante.getSenha().isEmpty()) {
            return;
        }
        estudanteDAO.insert(estudante);
    }

    public static boolean canDoChekin(EstudantePO estudante) {
        Date fifteenBefore;
        Date fifteenAfter;
        Date horaAtual = new Date();
        int minutos = CalendarioHelper.getMinutes(horaAtual);
        if (minutos <= 15) {
            horaAtual = CalendarioHelper.getHoraCheia(horaAtual);
        } else if (minutos >= 45) {
            horaAtual = CalendarioHelper.getHoraCheia(CalendarioHelper.addHora(horaAtual));
        } else {
            return false;
        }
        fifteenBefore = CalendarioHelper.lessHora(horaAtual);
        CalendarioHelper.setMinute(fifteenBefore, 45);
        fifteenAfter = (Date) horaAtual.clone();
        CalendarioHelper.setMinute(fifteenAfter, 15);
        return estudanteDAO.getReservaInTime(estudante, horaAtual) != null;
    }

    public static ReservaPO getMyReservaNow(EstudantePO estudante) {
        Date horaAtual = new Date();
        int minutos = CalendarioHelper.getMinutes(horaAtual);
        if (minutos <= 15) {
            horaAtual = CalendarioHelper.getHoraCheia(horaAtual);
        } else if (minutos >= 45) {
            horaAtual = CalendarioHelper.getHoraCheia(CalendarioHelper.addHora(horaAtual));
        }
        return estudanteDAO.getReservaInTime(estudante, horaAtual);
    }

}

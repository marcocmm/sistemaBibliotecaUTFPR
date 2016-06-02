/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.model.dao.EstudanteDAO;
import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author romulo
 */


public class ReservaDAOTest {

    public ReservaDAOTest() {
    }
    ReservaDAO dao = new ReservaDAO();

//    @Test
    public void reservar() {
        SalaDAO salaDao = new SalaDAO();
        SalaPO sala = salaDao.obter(2);

        EstudanteDAO estudanteDAO = new EstudanteDAO();
        EstudantePO estudante = estudanteDAO.obter("1602063");
        ReservaDAO dao = new ReservaDAO();
        ReservaPO reserva = new ReservaPO(estudante, sala, new Date(), 5);

        boolean test = dao.insert(reserva);
        assertTrue(test);
    }

    public void listar() {
        List<ReservaPO> reservas = dao.list();
        Date date;
        for (ReservaPO reserva : reservas) {
            System.out.println(reserva.getDataInicial());
            System.out.println(reserva);
            date = reserva.getDataInicial();
        }
        assertTrue(reservas != null);
    }
//    @Test

    public void test_getQuantidadeReservas() {
        Date dataInicial = CalendarioHelper.parseDate("10-05-2016", "09", "00", "00");
        int qtde = dao.getQuantidadeReservas(dataInicial);
        System.out.println("QTDE:" + qtde);
        assertEquals(qtde, 3);
    }

}
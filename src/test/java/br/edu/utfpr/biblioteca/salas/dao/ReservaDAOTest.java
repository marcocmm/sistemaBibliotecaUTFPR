/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.Estudante;
import br.edu.utfpr.biblioteca.salas.model.Reserva;
import br.edu.utfpr.biblioteca.salas.model.Sala;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author romulo
 */
public class ReservaDAOTest {

    public ReservaDAOTest() {
    }

    
    public void reservar() {
        SalaDAO salaDao = new SalaDAO();
        Sala sala = salaDao.obter(2);

        EstudanteDAO estudanteDAO = new EstudanteDAO();
        Estudante estudante = estudanteDAO.obter("1137112");
        ReservaDAO dao = new ReservaDAO();
        if (dao.list().isEmpty()) {
            Reserva reserva = new Reserva(estudante, sala, new Date(), 5);
            dao.insert(reserva);
        }
    }

    
    public void listar() {
        ReservaDAO dao = new ReservaDAO();
        List<Reserva> reservas = dao.list();
        Date date;
        for (Reserva reserva : reservas) {
            System.out.println(reserva.getDataInicial());
            System.out.println(reserva);
            date = reserva.getDataInicial();
        }
    }

}

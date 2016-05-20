/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;

/**
 *
 * @author romulo
 */
public class SalaDAOTest {

    static SalaDAO dao = new SalaDAO();

    public SalaDAOTest() {
    }

//    public void cadastrarSala() {
//        if (dao.list().isEmpty()) {
//            dao.insert(new SalaPO(1, true));
//            dao.insert(new SalaPO(2, true));
//            dao.insert(new SalaPO(3, true));
//            dao.insert(new SalaPO(4, true));
//            dao.insert(new SalaPO(5, false));
//        }
//    }
//    @Test
    public void test_getStatusDaSala() {

        //Exemplo para MateusVT como pegar a data 
        Date dI = CalendarioHelper.parseDate("10-05-2016", "07", "00", "00");
        Date dF = CalendarioHelper.parseDate("10-05-2016", "23", "00", "00");
        List<ReservaPO> reservas = dao.getStatusDaSala(dI, dF);
        assertFalse(reservas.isEmpty());
    }

//    @Test
    public void test_getSalaDisponiveis() {
        Date dI = CalendarioHelper.parseDate("10-05-2016", "07", "00", "00");
        Date dF = CalendarioHelper.parseDate("10-05-2016", "23", "00", "00");
        List<SalaPO> salas = dao.getSalasDisponiveis(dI, dF);
        assertFalse(salas.isEmpty());
    }

}

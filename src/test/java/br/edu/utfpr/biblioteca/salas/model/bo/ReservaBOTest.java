/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author marco
 */
public class ReservaBOTest {
    
    ReservaDAO reservaDao = new ReservaDAO();

    public ReservaBOTest() {
    }

    public void testSomeMethod() {
        System.out.println("");
        int a = 10;
        Date data = CalendarioHelper.parseDate("10-05-2016");
        HashMap<Date, HashMap<SalaPO, ReservaPO>> descreverDiaHash = ReservaBO.descreverDiaHash(data);
        System.out.println("");
        System.out.println("");
    }

    public void testeChekin() {
        try {
            ReservaBO.fazerCheckin(UsuarioBO.obterUsuario("1137085"));
            Assert.fail();
        } catch (Exception ex) {
        }
    }

//    @Test
    public void test_getReservasPeriodo(){
        Date dataInicial = CalendarioHelper.parseDate("05-05-2016", "8","0","0");
        Date dataFinal = CalendarioHelper.parseDate("10-05-2016", "22","0","0");
//        System.out.println("Data inicial: " + CalendarioHelper.getData(dataInicial));
//        System.out.println("Data Final: " + CalendarioHelper.getData(dataFinal));
//        List<ReservaPO> reservas = reservaDao.getReservasPeriodo(dataInicial, dataFinal);
//        for (ReservaPO reserva : reservas) {
//            System.out.println("Id: "+reserva.getId());
//            System.out.println("Nome: "+reserva.getUsuario().getNome());
//            System.out.println("Sala: "+reserva.getSala().getId());
//        }
        Assert.assertTrue(reservaDao.getReservasPeriodo(dataInicial, dataFinal) != null);
    }

}

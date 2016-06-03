/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.Dia;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marco
 */
public class ReservaBOTest {

    public ReservaBOTest() {
    }

    @Test
    public void testSomeMethod() {
        System.out.println("");
        int a = 10;
        Dia descreverDia;
        descreverDia = ReservaBO.descreverDia(CalendarioHelper.parseDate("10-05-2016"));
        descreverDia.getData();
        System.out.println("");
        System.out.println("");
    }

}

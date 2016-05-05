/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Reserva;
import java.util.Date;
import java.util.List;
import javax.persistence.ForeignKey;
import javax.validation.constraints.AssertTrue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author leonardo
 */
public class AdministradorMBTest {

    List<Reserva> reservas = null;
    AdministradorMB adminstradorMB;

    public AdministradorMBTest() {

        adminstradorMB = new AdministradorMB();

        Date data = new Date(2016, 04, 29, 18, 0, 0);
        adminstradorMB.setData(data);
        adminstradorMB.setIdSala(2);

    }

//    @Test
    public void teste() {
        reservas = adminstradorMB.getReservas();
        if (reservas == null) {
            System.out.println("Reservas é null");
        }
//        assertTrue(reservas != null);
    }


}

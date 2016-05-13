/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author leonardo
 */
public class AdministradorMBTest {

    List<ReservaPO> reservas = null;
    AdministradorMB adminstradorMB;

    public AdministradorMBTest() {

        adminstradorMB = new AdministradorMB();

        Date data = new Date(2016, 04, 29, 18, 0, 0);
        adminstradorMB.setData(data);
        adminstradorMB.setIdSala(2);

    }

    public void teste() {
        reservas = ReservaMB.getReservas(new Date(), new SalaPO(1, true));
        if (reservas == null) {
            System.out.println("Reservas é null");
        }
        assertTrue(reservas != null);
    }
}

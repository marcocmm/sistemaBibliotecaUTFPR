/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.bo.ReservaBO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author leonardo
 */
public class AdministradorMBTest {

    List<ReservaPO> reservas = null;
    RelatorioMB adminstradorMB;

    public AdministradorMBTest() {

        adminstradorMB = new RelatorioMB();

        Date data = new Date(2016, 04, 29, 18, 0, 0);
//        adminstradorMB.setDataSelecionada(data);

    }

    public void teste() {
        reservas = ReservaBO.getReservaAtiva(new Date(), 1);
        if (reservas == null) {
            System.out.println("Reservas é null");
        }
        assertTrue(reservas != null);
    }
}

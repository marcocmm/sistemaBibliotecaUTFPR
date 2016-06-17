/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author marco
 */
public class ReservaBOTest {

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

}

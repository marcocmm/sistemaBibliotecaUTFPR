/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.Sala;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author romulo
 */
public class SalaDAOTest {

    public SalaDAOTest() {
    }

    @Test
    public void cadastrarSala() {
        SalaDAO dao = new SalaDAO();
        if (dao.list().isEmpty()) {
            dao.insert(new Sala(1, true));
            dao.insert(new Sala(2, true));
            dao.insert(new Sala(3, true));
            dao.insert(new Sala(4, true));
            dao.insert(new Sala(5, false));
        }
    }

}

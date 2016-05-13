/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;

/**
 *
 * @author romulo
 */
public class SalaDAOTest {

    public SalaDAOTest() {
    }

    
    public void cadastrarSala() {
        SalaDAO dao = new SalaDAO();
        if (dao.list().isEmpty()) {
            dao.insert(new SalaPO(1, true));
            dao.insert(new SalaPO(2, true));
            dao.insert(new SalaPO(3, true));
            dao.insert(new SalaPO(4, true));
            dao.insert(new SalaPO(5, false));
        }
    }

}

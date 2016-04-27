/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.Estudante;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author romulo
 */
public class EstudanteDAOTest {

    public EstudanteDAOTest() {
    }

    @Test
    public void cadastrar() {
        EstudanteDAO dao = new EstudanteDAO();
        if (dao.list().isEmpty()) {
            dao.insert(new Estudante("1137212", "Rômulo", "senha", "email@email.com"));
            dao.insert(new Estudante("1137112", "Rômulo", "senha", "emailemail.com"));
            dao.insert(new Estudante("113722", "Rômulo", "senha", "email@mail.com"));
            dao.insert(new Estudante("1137612", "Rômulo", "senha", "emil@email.com"));
        }
    }

}

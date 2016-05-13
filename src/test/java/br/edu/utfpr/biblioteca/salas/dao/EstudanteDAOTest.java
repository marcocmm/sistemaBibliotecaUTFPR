/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.dao.EstudanteDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.Estudante;

/**
 *
 * @author romulo
 */
public class EstudanteDAOTest {

    public EstudanteDAOTest() {
    }

    
    public void cadastrar() {
        EstudanteDAO dao = new EstudanteDAO();
        if (dao.list().isEmpty()) {
            dao.insert(new Estudante("1137212", "Rômulo", "112131", "email@email.com"));
            dao.insert(new Estudante("1602063", "Mateus", "teste", "asd.com"));
            dao.insert(new Estudante("113722", "Rômulo", "senha", "email@mail.com"));
            dao.insert(new Estudante("1137612", "Rômulo", "senha", "emil@email.com"));
            
        }
    }

}

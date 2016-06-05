/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.bo.EstudanteBO;
import br.edu.utfpr.biblioteca.salas.model.dao.EstudanteDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author romulo
 */
public class EstudanteDAOTest {

    EstudanteDAO dao = new EstudanteDAO();

    public EstudanteDAOTest() {
    }

    public void cadastrar() {
        EstudanteDAO dao = new EstudanteDAO();
        if (dao.list().isEmpty()) {
            dao.insert(new EstudantePO("1137212", "Rômulo", "112131", "email@email.com"));
            dao.insert(new EstudantePO("1602063", "Mateus", "teste", "asd.com"));
            dao.insert(new EstudantePO("113722", "Rômulo", "senha", "email@mail.com"));
            dao.insert(new EstudantePO("1137612", "Rômulo", "senha", "emil@email.com"));

        }
    }
//    @Test
    public void test_autenticar() {
        EstudantePO d = dao.isAutentico("1136631", "baiser");
        assertTrue(d != null);
    }
    
//    @Test
    public void test_estudanteCadastrado() {
        EstudantePO estudante = new EstudantePO("1136631", null, "baiser", "");
        assertTrue(EstudanteBO.alreadyCadastrado(estudante));
    }

}

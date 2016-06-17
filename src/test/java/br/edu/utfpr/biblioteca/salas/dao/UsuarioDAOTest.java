/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.bo.UsuarioBO;
import br.edu.utfpr.biblioteca.salas.model.dao.UsuarioDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.UsuarioPO;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author romulo
 */
public class UsuarioDAOTest {

    UsuarioDAO dao = new UsuarioDAO();

    public UsuarioDAOTest() {
    }

    public void cadastrar() {
        UsuarioDAO dao = new UsuarioDAO();
        if (dao.list().isEmpty()) {
            dao.insert(new UsuarioPO("1137212", "Rômulo", "112131", "email@email.com"));
            dao.insert(new UsuarioPO("1602063", "Mateus", "teste", "asd.com"));
            dao.insert(new UsuarioPO("113722", "Rômulo", "senha", "email@mail.com"));
            dao.insert(new UsuarioPO("1137612", "Rômulo", "senha", "emil@email.com"));

        }
    }

//    @Test
    public void test_autenticar() {
        UsuarioPO d = dao.isAutentico("1136631", "baiser");
        assertTrue(d != null);
    }
    
//    @Test
    public void test_usuarioCadastrado() {
        UsuarioPO usuario = new UsuarioPO("1136631", null, "baiser", "");
        assertTrue(UsuarioBO.alreadyCadastrado(usuario));

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.dao.UsuarioDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.UsuarioPO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mateus
 */
public class UsuarioBOTest {
    
    UsuarioDAO daoUsuario = new UsuarioDAO();
    
    public UsuarioBOTest() {
        
    }
    
    @Test
    public void test_CanDoCheckout(){
        UsuarioPO u = daoUsuario.obter("1602063");
        assertTrue(UsuarioBO.canDoCheckout(u));
    }

}

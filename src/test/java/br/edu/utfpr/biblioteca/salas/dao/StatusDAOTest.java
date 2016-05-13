/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.dao.StatusDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.StatusPO;

/**
 *
 * @author romulo
 */
public class StatusDAOTest {
    
    public StatusDAOTest() {
    }

    
    public void criar() {
        StatusDAO dao = new StatusDAO();
        if (dao.list().isEmpty()) {
            dao.insert(new StatusPO("Inativa"));
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.dao.AdministradorDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.Administrador;

/**
 *
 * @author romulo
 */
public class AdministradorDAOTest {

    public AdministradorDAOTest() {
    }

    
    public void cadastrar() {
        AdministradorDAO dao = new AdministradorDAO();
        Administrador administrador = new Administrador("rmeloca", "senha");
        if (dao.list().isEmpty()) {
            dao.insert(administrador);
        }
    }
}

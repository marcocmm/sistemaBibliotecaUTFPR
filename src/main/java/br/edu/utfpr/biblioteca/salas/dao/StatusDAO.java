/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.Status;

/**
 *
 * @author romulo
 */
public class StatusDAO extends GenericDAO<Status>{

    public StatusDAO() {
        super(Status.class);
    }
    
}

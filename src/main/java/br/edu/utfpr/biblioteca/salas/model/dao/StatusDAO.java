/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.Status;

/**
 *
 * @author romulo
 */
public class StatusDAO extends GenericDAO<Status>{

    public StatusDAO() {
        super(Status.class);
    }
    
    public Status obter(String status) {
        entityManager.clear();
        return (Status) entityManager.find(Status.class, status);
    }
    
}

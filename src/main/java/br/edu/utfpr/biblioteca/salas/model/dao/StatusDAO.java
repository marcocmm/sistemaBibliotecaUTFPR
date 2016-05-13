/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.StatusPO;

/**
 *
 * @author romulo
 */
public class StatusDAO extends GenericDAO<StatusPO>{

    public StatusDAO() {
        super(StatusPO.class);
    }
    
    public StatusPO obter(String status) {
        entityManager.clear();
        return (StatusPO) entityManager.find(StatusPO.class, status);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;

/**
 *
 * @author mateus
 */
public class EstudanteDAO extends GenericDAO<EstudantePO> {

    public EstudanteDAO() {
        super(EstudantePO.class);
    }

    public EstudantePO obter(String ra) {
        entityManager.clear();
        return (EstudantePO) entityManager.find(EstudantePO.class, ra);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;

/**
 *
 * @author mateus
 */
public class SalaDAO extends GenericDAO<SalaPO> {

    public SalaDAO() {
        super(SalaPO.class);
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.Sala;

/**
 *
 * @author mateus
 */
public class SalaDAO extends GenericDAO<Sala> {

    public SalaDAO() {
        super(Sala.class);
    }

}
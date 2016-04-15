/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.Estudante;

/**
 *
 * @author mateus
 */


public class EstudanteDAO extends GenericDAO<Estudante> {

    public EstudanteDAO() {
        super(Estudante.class);
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.Pessoa;

/**
 *
 * @author mateus
 */
public class PessoaDAO extends GenericDAO<Pessoa> {

    public PessoaDAO() {
        super(Pessoa.class);
    }

}
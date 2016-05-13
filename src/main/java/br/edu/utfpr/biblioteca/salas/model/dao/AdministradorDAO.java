/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.AdministradorPO;

/**
 *
 * @author mateus
 */
public class AdministradorDAO extends GenericDAO<AdministradorPO> {

    public AdministradorDAO() {
        super(AdministradorPO.class);
    }

}

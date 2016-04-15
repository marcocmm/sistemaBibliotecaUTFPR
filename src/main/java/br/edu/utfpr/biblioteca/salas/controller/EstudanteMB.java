/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Estudante;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author marco
 */
@Named(value = "estudanteMB")
@ViewScoped
@ManagedBean
public class EstudanteMB {
    private Estudante estudante = new Estudante();
    /**
     * Creates a new instance of EstudanteMB
     */
    public EstudanteMB() {
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }
    
    public void salvarEstudante(){
        //validar
        //Persistir Estudante
    }
    
}

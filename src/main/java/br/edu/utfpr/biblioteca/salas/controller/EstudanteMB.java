/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Estudante;
import br.edu.utfpr.biblioteca.salas.dao.EstudanteDAO;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author marco
 */
@Named(value = "estudanteMB")
@ViewScoped
@ManagedBean
public class EstudanteMB {

    private Estudante estudante;
    private EstudanteDAO dao = new EstudanteDAO();

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    private void cadastrarEstudante() {
        if (alreadyCadastrado()) {
            return;
        }
        if (estudante.getSenha().isEmpty()) {
            return;
        }
        dao.insert(estudante);
    }

    private boolean alreadyCadastrado() {
        return dao.obter(estudante) != null;
    }

    public boolean autenticar(String login, String senha) {
        if (!alreadyCadastrado()) {
            return false;
        }
        return dao.obter(login).getSenha().equals(senha);
    }

}

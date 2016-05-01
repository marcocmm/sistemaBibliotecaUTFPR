/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Estudante;
import br.edu.utfpr.biblioteca.salas.dao.EstudanteDAO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.ws.rs.core.Request;
import org.primefaces.context.RequestContext;

/**
 *
 * @author marco
 */
@Named(value = "estudanteMB")
@ViewScoped
@ManagedBean
public class EstudanteMB {

    private String login;
    private String senha;

    private Estudante estudante;
    private EstudanteDAO dao = new EstudanteDAO();

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(String login, String senha) {
        this.estudante = new Estudante(login, null, senha, null);
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
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage menssage = null;
        boolean loggedIn = false;

        setEstudante(login, senha);

        if (!alreadyCadastrado()) {
            menssage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Estudante não cadastrado!", null);
            return false;
        } else {
            loggedIn = dao.obter(login).getSenha().equals(senha);
            if (loggedIn) {
                menssage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo!", getLogin());

            }
            return true;
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        System.out.println("Login:" + login);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
        System.out.println("Senha:" + senha);
    }

}

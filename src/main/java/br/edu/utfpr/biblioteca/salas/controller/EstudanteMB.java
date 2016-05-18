/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.bo.EstudanteBO;
import br.edu.utfpr.biblioteca.salas.model.dao.EstudanteDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marco
 */
@Named(value = "estudanteMB")
@SessionScoped
@ManagedBean
public class EstudanteMB {

    private EstudantePO estudante;

    public EstudanteMB() {
        this.estudante = new EstudantePO(null, null, null, null);
    }

    public EstudantePO getEstudante() {
        return estudante;
    }

    public void setEstudante(EstudantePO estudantePO) {
        this.estudante = estudantePO;
    }

    /**
     * verifica se o estudante já está cadastrado e se a senha é vazia, caso ele
     * nao seja cadastrado e sua senha exista, o estudante é inserido.
     *
     * @param estudante
     */
    private void cadastrarEstudante(EstudantePO estudante) {
        EstudanteDAO estudanteDAO = new EstudanteDAO();
        if (alreadyCadastrado(estudante)) {
            return;
        }
        if (estudante.getSenha().isEmpty()) {
            return;
        }
        estudanteDAO.insert(estudante);
    }

    /**
     * verifica se o estudante está cadastrado
     *
     * @param estudante
     * @return boolean
     */
    private boolean alreadyCadastrado(EstudantePO estudante) {
        EstudanteDAO estudanteDAO = new EstudanteDAO();
        return estudanteDAO.obter(estudante) != null;
    }

    /**
     * obtém o login e senha do estudante e, caso esteja autenticado, joga
     * mensagem na tela
     *
     * @param event
     */
    public void autenticar(ActionEvent event) {
        FacesMessage message = null;
        boolean loggedIn = false;
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("estudanteLogado", this.estudante);

        if (!alreadyCadastrado(this.estudante)) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Estudante não cadastrado!", null);
        }
        loggedIn = EstudanteBO.autenticar(this.estudante.getRa(), this.estudante.getSenha());

        if (loggedIn) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo!", estudante.getNome());

        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, message);

    }
}

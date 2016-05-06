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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author marco
 */
@Named(value = "estudanteMB")
@SessionScoped
@ManagedBean
public class EstudanteMB {

    private Estudante estudante;

    private EstudanteDAO estudanteDAO;

    private String nome;
    private String login;
    private String senha;

    public EstudanteMB() {
        this.estudanteDAO = new EstudanteDAO();
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(String login, String senha) {
        this.estudante = new Estudante(login, null, senha, null);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        System.out.println("Login:" + login);
    }

    public String getNome() {
        return estudanteDAO.obter(login).getNome();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
        System.out.println("Senha:" + senha);
    }

    /**
     * verifica se o estudante já está cadastrado e se a senha é vazia, caso ele
     * nao seja cadastrado e sua senha exista, o estudante é inserido.
     *
     * @param estudante
     */
    private void cadastrarEstudante(Estudante estudante) {
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
    private boolean alreadyCadastrado(Estudante estudante) {
        return estudanteDAO.obter(estudante) != null;
    }

    /**
     * Método que verifica a validade dos dados inseridos pelo úsuario através
     * de uma consulta ao banco de dados, retornando true ou false.
     *
     * @param login
     * @param senha
     * @return boolean (false caso não exista no banco e true caso exista)
     */
    public static boolean isAutentico(String login, String senha) {
        EstudanteDAO dao = new EstudanteDAO();
        Estudante estudante = dao.obter(login);
        if (estudante == null) {

            return false;
        }
        return estudante.getSenha().equals(senha);
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
//        FacesContext fc = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
//        session.setAttribute("estudanteLogado", this.idUsuario);

        setEstudante(login, senha);

//        if (!alreadyCadastrado()) {
//            menssage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Estudante não cadastrado!", null);
////            return false;
//        } else {
        loggedIn = estudanteDAO.obter(login).getSenha().equals(senha);

        if (loggedIn) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo!", getNome());

        }

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, message);

//            return true;
//        }
    }
}

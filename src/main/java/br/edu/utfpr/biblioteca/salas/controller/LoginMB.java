package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.bo.EstudanteBO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
//@Named(value = "")
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB {

    private EstudantePO estudante = null;
    FacesMessage message = null;

    public LoginMB() {
        this.estudante = new EstudantePO(null, null, null, null);
    }

    /**
     * verifica se o estudante está cadastrado
     *
     * @param estudante
     * @return boolean
     */
    private boolean alreadyCadastrado() {
        return EstudanteBO.alreadyCadastrado(this.estudante);
    }

    private boolean isLogado() {
        if (getEstudanteLogado() == null) {
            return false;
        }
        return true;
    }

    /**
     * Obtém o login e senha do estudante e, caso esteja autenticado, exibe
     * mensagem na tela, também cria uma nova sessão.
     *
     * @param event
     */
    public String doLogin(ActionEvent event) {
        boolean loggedIn;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext ec = facesContext.getExternalContext();

        if (!alreadyCadastrado()) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Estudante não cadastrado!", null);
        }
        estudante = EstudanteBO.isAutentico(estudante);
        if (estudante != null) {
//            SessionContext.getInstance().setAttribute("estudanteLogado", estudante);
            SessionContext.getInstance().setEstudanteLogado(estudante);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo!", estudante.getNome());
            facesContext.addMessage(null, message);
            try {
                ec.redirect("calendario.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
//            try {
//                ec.redirect("index.xhtml");
//            } catch (IOException ex) {
//                Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, ex);
//            }
            estudante = new EstudantePO(null, null, null, null);
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ra ou Senha incorretos! \n Por favor tente novamente.", null);
        }
        facesContext.addMessage(null, message);
        return "";
    }

    /**
     * Faz o logout do estudante, encerra a session
     *
     * @return Rediret to index.html
     */
    public void doLogout() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext ec = facesContext.getExternalContext();

        SessionContext.getInstance().encerrarSessao();
        try {
            ec.redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retorna estudante logado
     *
     * @return EstuantePO
     */
    public EstudantePO getEstudanteLogado() {
        return (EstudantePO) SessionContext.getInstance().getEstudanteLogado();
    }

    /**
     * Retorna nome do estudante logado
     *
     * @return EstuantePO
     */
    public String getNomeEstudanteLogado() {
        return SessionContext.getInstance().getNameEstudanteLogado();
    }

    public String exibirBtnLogout() {
        if (isLogado()) {
            return "true";
        }
        return "false";
    }
    
    public String exibirFormLogin(){
        if (isLogado()) {
            return "false";
        }
        return "true";
    }

    public EstudantePO getEstudante() {
        return estudante;
    }

    public void setEstudante(EstudantePO estudante) {
        this.estudante = estudante;
    }

}

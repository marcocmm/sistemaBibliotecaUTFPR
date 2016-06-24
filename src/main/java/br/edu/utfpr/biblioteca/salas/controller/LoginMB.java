package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.bo.UsuarioBO;
import br.edu.utfpr.biblioteca.salas.model.bo.ReservaBO;
import br.edu.utfpr.biblioteca.salas.model.entity.UsuarioPO;
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
@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB {

    private UsuarioPO usuario;
    FacesMessage message;

    public LoginMB() {
        this.usuario = new UsuarioPO(null, null, null, null);
    }

    /**
     * verifica se o usuario está cadastrado
     *
     * @param usuario
     * @return boolean
     */
    private boolean alreadyCadastrado() {
        return UsuarioBO.alreadyCadastrado(this.usuario);
    }

    private boolean isLogado() {
        if (getUsuarioLogado() == null) {
            return false;
        }
        return true;
    }

    /**
     * Obtém o login e senha do usuario e, caso esteja autenticado, exibe
     * mensagem na tela, também cria uma nova sessão.
     *
     * @param event
     */
    public String doLogin(ActionEvent event) {
        boolean loggedIn;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext ec = facesContext.getExternalContext();

        if (!alreadyCadastrado()) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario não cadastrado!", null);
        }
        usuario = UsuarioBO.isAutentico(usuario);
        if (usuario != null) {
//            SessionContext.getInstance().setAttribute("usuarioLogado", usuario);
            SessionContext.getInstance().setUsuarioLogado(usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo!", usuario.getNome());
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
            usuario = new UsuarioPO(null, null, null, null);
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ra ou Senha incorretos! \n Por favor tente novamente.", null);
        }
        facesContext.addMessage(null, message);
        return "";
    }

//    NOT WORKING
//    public void acessoNegado(){
//        String erro = (String) SessionContext.getInstance().getAttribute("acesso_negado");
//        if (erro.equals("negado")){
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Você precisa estar logado para ter acesso a está página.", null);
//            facesContext.addMessage(null, message);
//        }
//    }
    /**
     * Faz o logout do usuario, encerra a session
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
     * Retorna usuario logado
     *
     * @return EstuantePO
     */
    public UsuarioPO getUsuarioLogado() {
        return (UsuarioPO) SessionContext.getInstance().getUsuarioLogado();
    }

    /**
     * Retorna nome do usuario logado
     *
     * @return EstuantePO
     */
    public String getNomeUsuarioLogado() {
        return SessionContext.getInstance().getNameUsuarioLogado();
    }

    public String exibirBtnLogout() {
        if (isLogado()) {
            return "true";
        }
        return "false";
    }

    public String exibirFormLogin() {
        if (isLogado()) {
            return "false";
        }
        return "true";
    }

    public UsuarioPO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPO usuario) {
        this.usuario = usuario;
    }

    public void fazerCheckout() {
        throw new UnsupportedOperationException();
    }

    public boolean exibirBotaoCheckout() {
        UsuarioPO usuarioLogado = SessionContext.getInstance().getUsuarioLogado();
        if (usuarioLogado == null) {
            return false;
        }
        
        return false;
    }

    public void fazerCheckin() {
        UsuarioPO usuarioLogado = SessionContext.getInstance().getUsuarioLogado();
        try {
            if (usuarioLogado == null) {
                if (usuario.getRa() == null || usuario.getSenha() == null) {
                    throw new Exception("Campos login e senha não podem ser nulos!");
                }
                if (usuario.getRa().isEmpty() || usuario.getSenha().isEmpty()) {
                    throw new Exception("Informe o login e a senha!");
                }
            } else {
                this.usuario = usuarioLogado;
            }
            ReservaBO.fazerCheckin(usuario);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Checkin efetuado!", null);
        } catch (Exception ex) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null);
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public boolean exibirBotaoCheckin() {
        UsuarioPO usuarioLogado = SessionContext.getInstance().getUsuarioLogado();
        if (usuarioLogado == null) {
            return true;
        } else if (UsuarioBO.canDoChekin(usuarioLogado)) {
            return true;
        }
        return false;
    }
}

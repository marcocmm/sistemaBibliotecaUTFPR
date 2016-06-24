package br.edu.utfpr.biblioteca.salas.controller;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
import br.edu.utfpr.biblioteca.salas.model.entity.UsuarioPO;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class SessionContext {

    private static SessionContext instance;

    //Padrão de projeto singleton, só permite uma única instância por usuário
    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }

        return instance;
    }

    private SessionContext() {

    }

    //Retorna a instância corrente, se não existir uma retorna null
    private ExternalContext currentExternalContext() {
        if (FacesContext.getCurrentInstance() == null) {
            return null;
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    /**
     * Retorna a instância do estudandte logado, está instância está armazenada
     * na sessão
     *
     * @return UsuarioPO
     */
    public UsuarioPO getUsuarioLogado() {
        return (UsuarioPO) getAttribute("usuarioLogado");
    }

    /**
     * Retorna o nome do usuario,só para fins de view
     *
     * @return nomeUsuario
     */
    public String getNameUsuarioLogado() {
        UsuarioPO e = (UsuarioPO) getAttribute("usuarioLogado");

        return (e != null) ? "Bem vindo, " + e.getNome() : " ";
    }

    /**
     * Seta um novo usuario na sessão
     *
     * @param usuario
     */
    public void setUsuarioLogado(UsuarioPO usuario) {
        setAttribute("usuarioLogado", usuario);
    }
    

    /**
     * Invalida a sessão
     */
    public void encerrarSessao() {
        currentExternalContext().invalidateSession();
    }

    /**
     * Retorna um objeto armazenado na sessão dado o nome apelido do objeto
     *
     * @param nome
     * @return
     */
    public Object getAttribute(String nome) {
        return currentExternalContext().getSessionMap().get(nome);
    }

    /**
     * Armazena qualquer objeto na sessão
     *
     * @param nome
     * @param valor
     */
    public void setAttribute(String nome, Object valor) {
        currentExternalContext().getSessionMap().put(nome, valor);
    }

}

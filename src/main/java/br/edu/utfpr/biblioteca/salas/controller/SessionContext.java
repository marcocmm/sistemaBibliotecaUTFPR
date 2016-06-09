package br.edu.utfpr.biblioteca.salas.controller;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class SessionContext {

    private static SessionContext instance;

    //Padrão de projeto singleto, só permite uma única instância por usuário
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
     * @return EstudantePO
     */
    public EstudantePO getEstudanteLogado() {
        return (EstudantePO) getAttribute("estudanteLogado");
    }

    /**
     * Retorna o nome do estudante,só para fins de view
     *
     * @return nomeEstudante
     */
    public String getNameEstudanteLogado() {
        EstudantePO e = (EstudantePO) getAttribute("estudanteLogado");

        return (e != null) ? "Bem vindo, " + e.getNome() : " ";
    }

    /**
     * Seta um novo estudante na sessão
     *
     * @param estudante
     */
    public void setEstudanteLogado(EstudantePO estudante) {
        setAttribute("estudanteLogado", estudante);
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

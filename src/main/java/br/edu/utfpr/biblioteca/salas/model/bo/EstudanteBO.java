package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.dao.EstudanteDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;

public class EstudanteBO {

    static EstudanteDAO estudanteDAO = new EstudanteDAO();

    /**
     * Este método chama o método isAutentico do dao
     *
     * @param ra
     * @param senha
     * @return boolean
     */
    public static boolean isAutentico(String ra, String senha) {
        return estudanteDAO.isAutentico(ra, senha);
    }

    /**
     * Sobrecarga de método para permitir abstrações. Invoca isAutentico(ra,
     * senha)
     *
     * @param estudante
     * @return boolean
     */
    public static boolean isAutentico(EstudantePO estudante) {
        return isAutentico(estudante.getRa(), estudante.getSenha());
    }

    /**
     * Contata o dao para obter um estudante dado um ra.
     *
     * @param ra
     * @return
     */
    public static EstudantePO obterEstudante(String ra) {
        return estudanteDAO.obter(ra);
    }

    /**
     * verifica se o estudante está cadastrado
     *
     * @param estudante
     * @return boolean
     */
    public static boolean alreadyCadastrado(EstudantePO estudante) {
        return estudanteDAO.obter(estudante) != null;
    }

    /**
     * verifica se o estudante já está cadastrado e se a senha é vazia, caso ele
     * nao seja cadastrado e sua senha exista, o estudante é inserido.
     *
     * @param estudante
     */
    public static void cadastrarEstudante(EstudantePO estudante) {
        if (alreadyCadastrado(estudante)) {
            return;
        }
        if (estudante.getSenha().isEmpty()) {
            return;
        }
        estudanteDAO.insert(estudante);
    }
}

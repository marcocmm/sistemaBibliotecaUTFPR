package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.dao.EstudanteDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;

public class EstudanteBO {

    static EstudanteDAO estudanteDAO = new EstudanteDAO();

    /**
     * Este método chama o método autenticar do dao
     *
     * @param ra
     * @param senha
     * @return boolean
     */
    public static boolean autenticar(String ra, String senha) {
        return estudanteDAO.autenticar(ra, senha);
    }

    /**
     * Contata o dao para obter um estudante dado um ra.
     * @param ra
     * @return 
     */
    public static EstudantePO obterEstudante(String ra) {
        return estudanteDAO.obter(ra);
    }

}

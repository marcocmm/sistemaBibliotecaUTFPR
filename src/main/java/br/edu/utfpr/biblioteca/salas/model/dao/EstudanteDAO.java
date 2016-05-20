package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import javax.persistence.Query;

public class EstudanteDAO extends GenericDAO<EstudantePO> {

    public EstudanteDAO() {
        super(EstudantePO.class);
    }

    /**
     * Retorna um EstudantePO dado um ra
     *
     * @param ra
     * @return EstudantePO estudante
     */
    public EstudantePO obter(String ra) {
        entityManager.clear();
        return (EstudantePO) entityManager.find(EstudantePO.class, ra);
    }

    /**
     * Este método faz a autenticação do estudante no bd pelo ra e senha
     *
     * @param ra
     * @param senha
     * @return Boolean
     */
    public boolean autenticar(String ra, String senha) {
        EstudantePO estudante = obter(ra);
        if (estudante != null){
            if (estudante.getSenha().equals(senha)){
                return true;
            }
        }
        return false;
    }

}

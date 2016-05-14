package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;


public class EstudanteDAO extends GenericDAO<EstudantePO> {

    public EstudanteDAO() {
        super(EstudantePO.class);
    }

    /**
     * Retorna um EstudantePO dado um ra
     * @param ra
     * @return EstudantePO estudante
     */
    public EstudantePO obter(String ra) {
        entityManager.clear();
        return (EstudantePO) entityManager.find(EstudantePO.class, ra);
    }
    
    /**
     * Este método faz a busca do estudante no bd pelo ra e senha
     * @param ra
     * @param senha
     * @return 
     */
    public boolean autenticar(String ra, String senha){
        return false;
    }

}

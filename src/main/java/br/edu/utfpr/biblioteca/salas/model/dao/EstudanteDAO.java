package br.edu.utfpr.biblioteca.salas.model.dao;

import static br.edu.utfpr.biblioteca.salas.model.dao.GenericDAO.entityManager;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import br.edu.utfpr.biblioteca.salas.model.entity.StatusPO;
import java.util.Date;
import javax.persistence.Query;

public class EstudanteDAO extends GenericDAO<EstudantePO> {

    public EstudanteDAO() {
        super(EstudantePO.class);
    }
/**
 * Verifica se o limite diario de reserva foi atingido
 * @param estudante
 * @param dataInicial
 * @return 
 */
    public boolean canReservar(EstudantePO estudante, Date dataInicial) {

        long qtdReservas;
        Query q = entityManager.createQuery
        ("SELECT e FROM Reserva e WHERE e.status = :ativa AND e.estudante = :estudante AND e.dataInicial = :data");
        q.setParameter("ativa", new StatusPO("ativa"));
        q.setParameter("estudante", estudante);
        q.setParameter("data", dataInicial);
        qtdReservas = (long) q.getResultList().size();

        return qtdReservas < 2;
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
    public boolean isAutentico(String ra, String senha) {
        EstudantePO estudante = obter(ra);
        if (estudante != null) {
            if (estudante.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

}

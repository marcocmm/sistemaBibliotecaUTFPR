package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.StatusPO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author leonardo
 */
public class ReservaDAO extends GenericDAO<ReservaPO> implements Serializable {

    public ReservaDAO() {
        super(ReservaPO.class);
    }

    @Override
    public boolean insert(ReservaPO reserva) {
        StatusPO status;
        StatusDAO statusDAO;

        statusDAO = new StatusDAO();

        try {
            entityManager.getTransaction().begin();
            Query q = entityManager.createQuery("SELECT e FROM Reserva e WHERE e.dataInicial = :dataInicial AND e.sala = :sala AND e.status = :status");
            q.setParameter("dataInicial", reserva.getDataInicial());
            q.setParameter("sala", reserva.getSala());
            q.setParameter("status", new StatusPO("ativa"));
            q.getSingleResult();
            entityManager.getTransaction().rollback();
            System.out.println("Horário e sala já reservadas");
            return false;
        } catch (NoResultException ex) {
            status = statusDAO.obter("ativa");
            reserva.setStatus(status);
            entityManager.persist(reserva);
            entityManager.getTransaction().commit();
            System.out.println("Sucesso");
            return true;
        }
    }

    public List<ReservaPO> listByDate(Date date) {
        Query q = entityManager.createQuery("SELECT e FROM " + ReservaPO.class.getSimpleName() + " e "
                + "WHERE e.dataInicial=:dataInicial");
        q.setParameter("dataInicial", date);
        return q.getResultList();
    }
}

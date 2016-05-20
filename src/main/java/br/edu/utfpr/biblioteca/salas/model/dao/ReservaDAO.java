package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.StatusPO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ReservaDAO extends GenericDAO<ReservaPO> implements Serializable {

    public ReservaDAO() {
        super(ReservaPO.class);
    }

    /**
     * Insere uma reserva no BD, retorna true se a reserva foi inserida e false
     * se houver algum erro.
     *
     * @param reserva
     * @return Boolean
     */
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

    /**
     * Dado uma data, este método retorna um lista de reservas correspondente a
     * data.
     *
     * @param date
     * @return List<ReservaPO>
     */
    public List<ReservaPO> listByDateTime(Date date) {
        Query q = entityManager.createQuery("SELECT e FROM " + ReservaPO.class.getSimpleName() + " e "
                + "WHERE e.dataInicial=:dataInicial");
        q.setParameter("dataInicial", date);
        return q.getResultList();
    }

    /**
     * Dado uma data, este método retorna um lista de reservas correspondente a
     * data e o id da sala.
     *
     * @param date
     * @return List<ReservaPO>
     */
    public List<ReservaPO> listByDateAndIdSala(Date date, int idSala) {
        Query q = entityManager.createQuery("SELECT e FROM Reserva e WHERE e.dataInicial=:dataInicial AND e.id =:idSala");
        q.setParameter("dataInicial", date);
        q.setParameter("idSala", idSala);
        return q.getResultList();
    }

    public boolean isReservado(ReservaPO reserva) {
        try {
            Query q = entityManager.createQuery("SELECT e FROM Reserva e WHERE e.dataInicial = :dataInicial AND e.sala = :sala AND e.status = :status");
            q.setParameter("dataInicial", reserva.getDataInicial());
            q.setParameter("sala", reserva.getSala());
            q.setParameter("status", new StatusPO("ativa"));
            q.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }
}

package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.Reserva;
import br.edu.utfpr.biblioteca.salas.model.Status;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import tools.CalendarioHelper;

/**
 *
 * @author leonardo
 */
public class ReservaDAO extends GenericDAO<Reserva> {

    public ReservaDAO() {
        super(Reserva.class);
    }

    @Override
    public boolean insert(Reserva reserva) {
        Reserva alreadyReservado;
        Status status;
        StatusDAO statusDAO;

        statusDAO = new StatusDAO();

        try {
            entityManager.getTransaction().begin();
            Query q = entityManager.createQuery("SELECT e FROM Reserva e WHERE e.dataInicial = :dataInicial AND e.sala = :sala AND e.status = :status");
            q.setParameter("dataInicial", reserva.getDataInicial());
            q.setParameter("sala", reserva.getSala());
            q.setParameter("status", new Status("ativa"));
            alreadyReservado = (Reserva) q.getSingleResult();
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

    public List<Reserva> listByDate(Date date) {
        return entityManager.createQuery("SELECT e FROM " + Reserva.class.getSimpleName() + " e"
                + "WHERE e.data_inicial="
                + date).getResultList();
    }
    
    public List<Reserva> listByDate(Date date, Date hour){
        return null;
    }
}

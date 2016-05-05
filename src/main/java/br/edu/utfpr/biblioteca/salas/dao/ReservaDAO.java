package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.Reserva;
import br.edu.utfpr.biblioteca.salas.model.Status;
import java.util.Date;
import java.util.List;

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

        try {
            entityManager.getTransaction().begin();
            alreadyReservado = (Reserva) entityManager.createQuery(
                    "SELECT e FROM "
                    + Reserva.class.getSimpleName()
                    + " e WHERE e.data_inicial="
                    + reserva.getDataInicial()
                    + " AND sala_id="
                    + reserva.getSala().getId()
                    + " AND status_name=Ativa").getSingleResult();
            if (alreadyReservado != null) {
                entityManager.getTransaction().commit();
                return false;
            }
            statusDAO = new StatusDAO();
            status = statusDAO.obter("Ativa");
            reserva.setStatus(status);
            entityManager.persist(reserva);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().commit();
            return false;
        }
    }

    public List<Reserva> listByDate(Date date) {
        return entityManager.createQuery("SELECT e FROM " + Reserva.class.getSimpleName() + " e"
                + "WHERE e.data_inicial="
                + date).getResultList();
    }
}

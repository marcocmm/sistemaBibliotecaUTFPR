package br.edu.utfpr.biblioteca.salas.dao;

import br.edu.utfpr.biblioteca.salas.model.Reserva;
import br.edu.utfpr.biblioteca.salas.model.Status;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import tools.CalendarioController;

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
            alreadyReservado = null;
            
            Query q = entityManager.createQuery("SELECT e FROM Reserva e WHERE e.dataInicial = :dataInicial AND e.sala = :sala AND e.status = 'ativa'");
            q.setParameter("dataInicial", CalendarioController.getDatabaseDateFormat(reserva.getDataInicial()));
            q.setParameter("sala", reserva.getSala().getId());
            
            try{
                alreadyReservado = (Reserva) q.getSingleResult();
            }catch (Exception ex){
                System.err.println(ex.getMessage());
            }
            
//        alreadyReservado = (Reserva) entityManager.createQuery(
//                "SELECT e FROM "
//                + Reserva.class.getSimpleName()
//                + " e WHERE e.dataInicial='"
//                + CalendarioController.getDatabaseDateFormat(reserva.getDataInicial())
//                + "' AND e.sala="
//                + reserva.getSala().getId()
//                + " AND e.status='Ativa'").getSingleResult();
            if (alreadyReservado != null) {
                entityManager.getTransaction().commit();
                return false;
            }
            status = statusDAO.obter("ativa");
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

package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.StatusPO;
import java.util.Date;

import java.util.List;
import javax.persistence.Query;

public class SalaDAO extends GenericDAO<SalaPO> {

    public SalaDAO() {
        super(SalaPO.class);
    }

    /**
     * SELECT que busca no bd o status da sala dado uma data
     *
     * @param dataInicial
     * @param dataFinal
     * @return List<ReservaPO>
     */
    public List<ReservaPO> getStatusDaSala(Date dataInicial, Date dataFinal) {
        Query q = entityManager.createQuery("SELECT e.sala FROM Reserva e WHERE e.status = :status AND e.dataInicial > :dataInicial AND e.dataFinal < :dataFinal");
        q.setParameter("status", new StatusPO("inativa"));
        q.setParameter("dataInicial", dataInicial);
        q.setParameter("dataFinal", dataFinal);
        List<ReservaPO> reservas = null;
        try {
            reservas = (List<ReservaPO>) q.getResultList();
        } catch (Exception ex) {
            System.err.println("Erro SQL: " + ex.getMessage());
            return null;
        }
        return reservas;
    }

    /**
     * SELECT que busca no bd as salas disponíveis dado uma data com horário.
     *
     * @param dataInicial
     * @param dataFinal
     * @return List<SalaPO>
     */
    public List<SalaPO> getSalasDisponiveis(Date dataInicial, Date dataFinal) {
        Query q = entityManager.createQuery("SELECT e.sala FROM Reserva e WHERE e.status = :status AND e.dataInicial > :dataInicial AND e.dataFinal < :dataFinal");
        q.setParameter("status", new StatusPO("inativa"));
        q.setParameter("dataInicial", dataInicial);
        q.setParameter("dataFinal", dataFinal);
        List<SalaPO> salas = null;
        try {
            salas = (List<SalaPO>) q.getResultList();
        } catch (Exception ex) {
            System.err.println("Erro SQL: " + ex.getMessage());
            return null;
        }
        return salas;
    }

}

/* EXEMPLO DE DAO 
  Retorna um objeto do tipo Categoria  
  public Categoria buscaCategoria(String categoria) {
        Query q = em.createQuery("SELECT e FROM Categoria e WHERE e.categoria = :categoria");
        q.setParameter("categoria", categoria);
        Categoria c = null;
        try {

            c = (Categoria) q.getSingleResult();
            return c;

        } catch (Exception ex) {
            return null;
        }
    }
Retorna uma lista de Notas
public List<Notas> listaNotas(){
        Query q = em.createQuery("SELECT e FROM Notas e ORDER BY e.data DESC");
        List<Notas> notas;
        
        try{
            notas = (List<Notas>) q.getResultList();
            return notas;
        }catch (Exception ex){
            System.out.println("Erro: "+ ex.getMessage());
            return null;
        }
    }    
 */

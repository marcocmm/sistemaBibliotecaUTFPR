package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.StatusPO;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class SalaDAO extends GenericDAO<SalaPO> {

    public SalaDAO() {
        super(SalaPO.class);
    }

    /**
     * Obtém a disponibilidade de horários para serem reservados nos limites
     * (inclusive) passados por parâmetro
     *
     * @param dataInicial
     * @param dataFinal
     * @return
     */
    @Deprecated
    public HashMap<Date, Boolean> getHorarioIsDisponivelHash(Date dataInicial, Date dataFinal) {
        /**
         * SELECT COUNT(*) < (SELECT count(*) FROM Salas) FROM Reservas e WHERE
         * e.status_name != "inativa" AND e.data_inicial BETWEEN "2016-05-17
         * 08:00:00" AND "2016-05-17 22:00:00" GROUP BY e.data_inicial
         */

        Query q = entityManager.createQuery("SELECT e FROM Reserva e WHERE e.status != :status AND e.dataInicial BETWEEN :dataInicial AND :dataFinal GROUP BY e.dataInicial ORDER BY e.dataInicial");
        q.setParameter("status", new StatusPO("inativa"));
        q.setParameter("dataInicial", dataInicial);
        q.setParameter("dataFinal", dataFinal);
        List<ReservaPO> cabecalhosGruposReservas = null;
        try {
            cabecalhosGruposReservas = (List<ReservaPO>) q.getResultList();
            for (ReservaPO cabecalhoGrupo : cabecalhosGruposReservas) {

            }
        } catch (Exception ex) {
            System.err.println("Erro SQL: " + ex.getMessage());
            return null;
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Responde se uma sala está disponível para reserva na data dada.
     *
     * @param sala
     * @param datetime
     * @return
     */
    public boolean isDisponivel(SalaPO sala, Date datetime) {
        Query q = entityManager.createQuery("SELECT e FROM Reserva e WHERE e.status != :status AND e.dataInicial = :data AND e.sala = :sala");
        q.setParameter("status", new StatusPO("inativa"));
        q.setParameter("data", datetime);
        q.setParameter("sala", sala);
        try {
            q.getSingleResult();
            return false;
        } catch (NoResultException ex) {
            return true;
        }
    }

    /**
     * Obtém a lista de salas que estão disponíveis para reservas na data-hora
     * informada.
     *
     * @param datetime
     * @return
     */
    public List<SalaPO> getWhichSalasAreVagas(Date datetime) {
        Query q = entityManager.createQuery("SELECT s FROM Sala s WHERE s NOT IN (SELECT e.sala FROM Reserva e WHERE e.status != :status AND e.dataInicial = :data)");
        q.setParameter("status", new StatusPO("inativa"));
        q.setParameter("data", datetime);
        List<SalaPO> salasVagas = (List<SalaPO>) q.getResultList();
        return salasVagas;
    }

    /**
     * Responde se existe alguma sala vaga na data-hora dada. Verifica se o
     * número de salas reservadas é inferior ao número de salas disponíveis.
     *
     * @param datetime
     * @return
     */
    public boolean isAnyoneSalaVaga(Date datetime) {
        Query q = entityManager.createQuery("SELECT COUNT(e) FROM Reserva e WHERE e.status != :status AND e.dataInicial = :data");
        q.setParameter("status", new StatusPO("inativa"));
        q.setParameter("data", datetime);
        Long numeroReservas = (Long) q.getSingleResult();
        return numeroReservas < 5;
    }

    /**
     * SELECT que busca no bd as salas disponíveis dado uma data com horário.
     *
     * @param dataInicial
     * @param dataFinal
     * @return
     * @deprecated utilizar método getWhichSalasAreVagas
     */
    @Deprecated
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
        throw new UnsupportedOperationException();
    }

    /**
     * SELECT que busca a quantidade de salas no bd
     *
     * @return int
     */
    public int getQuantidadeSalas() {
        Query q = entityManager.createNativeQuery("SELECT COUNT(*) FROM Salas");
        long qtdeSalas = 0;

        try {
            qtdeSalas = (long) q.getSingleResult();
        } catch (Exception ex) {
            return 0;
        }
        return (int) qtdeSalas;
    }

    /**
     * Sobrescreve o método list pois seu nome de entidade difere o padrão
     *
     * @return
     */
    @Override
    public List<SalaPO> list() {
        return entityManager.createQuery("SELECT e FROM Sala e").getResultList();
    }

}
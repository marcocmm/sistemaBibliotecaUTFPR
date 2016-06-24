package br.edu.utfpr.biblioteca.salas.model.dao;

import static br.edu.utfpr.biblioteca.salas.model.dao.GenericDAO.entityManager;
import br.edu.utfpr.biblioteca.salas.model.entity.UsuarioPO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.StatusPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class UsuarioDAO extends GenericDAO<UsuarioPO> {

    public UsuarioDAO() {
        super(UsuarioPO.class);
    }

    /**
     * Verifica se o limite diario de reserva foi atingido
     *
     * @param usuario
     * @param dataInicial
     * @return
     */
    public boolean canReservar(UsuarioPO usuario, Date dataInicial) {
        Date primeiraHoraDodia = CalendarioHelper.getDateComHoraSete(dataInicial);
        Date ultimaHoraDodia = CalendarioHelper.getDateComHoraVinteUma(dataInicial);
        long qtdReservas;
        Query q = entityManager.createQuery("SELECT COUNT(e) FROM Reserva e WHERE e.status != :inativa AND e.usuario = :usuario AND "
                + "e.dataInicial BETWEEN :primeiraData AND :ultimaData");
        q.setParameter("inativa", new StatusPO("inativa"));
        q.setParameter("usuario", usuario);
        q.setParameter("primeiraData", primeiraHoraDodia);
        q.setParameter("ultimaData", ultimaHoraDodia);
        qtdReservas = (long) q.getSingleResult();
        return qtdReservas < 2;
    }
    
    
    public ReservaPO getReservaEmCurso(UsuarioPO usuario, Date date) {
        try {
            Query q = entityManager.createQuery("SELECT e FROM Reserva e WHERE e.status = :status AND e.usuario = :usuario AND e.dataInicial = :dataInicial");
            q.setParameter("status", new StatusPO("emCurso"));
            q.setParameter("usuario", usuario);
            q.setParameter("dataInicial", date);
            ReservaPO reserva = (ReservaPO) q.getSingleResult();
            return reserva;
        } catch (NoResultException ex) {
            return null;
        }
    }

    /**
     * Retorna um UsuarioPO dado um ra
     *
     * @param ra
     * @return UsuarioPO usuario
     */
    public UsuarioPO obter(String ra) {
        entityManager.clear();
        return (UsuarioPO) entityManager.find(UsuarioPO.class, ra);
    }

    /**
     * Este método faz a autenticação do usuario no bd pelo ra e senha
     *
     * @param ra
     * @param senha
     * @return Boolean
     */
    public UsuarioPO isAutentico(String ra, String senha) {
        UsuarioPO usuario = obter(ra);
        if (usuario != null) {
            
            if (usuario.getSenha().equals(senha)) {
                return usuario;
            }
        }
        return null;
    }
    
    public ReservaPO getReservaInTime(UsuarioPO usuario, Date date) {
        try {
            Query q = entityManager.createQuery("SELECT e FROM Reserva e WHERE e.status != :status AND e.usuario = :usuario AND e.dataInicial = :dataInicial");
            q.setParameter("status", new StatusPO("inativa"));
            q.setParameter("usuario", usuario);
            q.setParameter("dataInicial", date);
            ReservaPO reserva = (ReservaPO) q.getSingleResult();
            return reserva;
        } catch (NoResultException ex) {
            return null;
        }
    }

}
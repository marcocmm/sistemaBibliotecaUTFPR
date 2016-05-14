package br.edu.utfpr.biblioteca.salas.model.dao;

import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.sql.Date;
import java.util.List;


public class SalaDAO extends GenericDAO<SalaPO> {

    public SalaDAO() {
        super(SalaPO.class);
    }
    
    /**
     *  SELECT que busca no bd o status da sala dado uma data
     * @param date 
     * @return Definir o melhor!
     */
    public void getStatusDaSala(Date date){
       //implement the code here!
    }
    /**
     * SELECT que busca no bd as salas disponíveis dado uma data com horário.
     * @param dataHora
     * @return List<SalaPO>
     */
    public List<SalaPO> getSalasDisponiveis(Date dataHora){//Talvez teremos problemas em buscar com este tipo de data/hora
        //implement the code here!
        return null;
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
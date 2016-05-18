package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.dao.EstudanteDAO;


public class EstudanteBO {

    static EstudanteDAO daoEstudante;
    
    public EstudanteBO() {
        daoEstudante = new EstudanteDAO();
    }
    
    /**
     * Este método chama o método autenticar do dao 
     * @param ra
     * @param senha
     * @return boolean
     */
    public static boolean autenticar(String ra, String senha){
        return daoEstudante.autenticar(ra, senha);
    }
    
    
    
}

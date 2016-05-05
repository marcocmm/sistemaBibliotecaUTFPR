/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import br.edu.utfpr.biblioteca.salas.dao.SalaDAO;
import br.edu.utfpr.biblioteca.salas.model.Sala;
import java.util.HashMap;

/**
 *
 * @author leonardo
 */
public class testeMain {

    public static void main(String[] args) {
        SalaDAO salaDAO = new SalaDAO();
            System.out.println(" ----ola ------");
        HashMap<String, String> salas = new HashMap<>();
        for (Sala sala : salaDAO.list()) {
            System.out.println("Sala: " + sala.getId());
            salas.put(String.valueOf(sala.getId()), "Sala " + String.valueOf(sala.getId()));
        }
        if (salas == null) {
            System.out.println("é NULO ------");
        }
    }

}

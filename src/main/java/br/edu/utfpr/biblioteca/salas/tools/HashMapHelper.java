/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.tools;

import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author romulo
 */
public class HashMapHelper {

    /**
     * Clona um HashMap<SalaPO, ReservaPO> clonando devidamente os objetos
     * relacionados.
     *
     * @param map
     * @return
     */
    public static HashMap<SalaPO, ReservaPO> clone(HashMap<SalaPO, ReservaPO> map) {
        HashMap<SalaPO, ReservaPO> copy = new HashMap();

        for (Map.Entry<SalaPO, ReservaPO> entry : map.entrySet()) {
            try {
                copy.put((SalaPO) entry.getKey().clone(), (ReservaPO) entry.getValue().clone());
            } catch (NullPointerException ex) {
                copy.put((SalaPO) entry.getKey().clone(), null);
            }
        }
        return copy;
    }
}

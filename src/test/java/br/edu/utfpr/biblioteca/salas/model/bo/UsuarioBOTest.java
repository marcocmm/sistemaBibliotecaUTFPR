/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.Date;

/**
 *
 * @author mateus
 */
public class UsuarioBOTest {
    
    public UsuarioBOTest() {
    }
    
//    @Test
    public void test_Data(){
        Date data = CalendarioHelper.getHoraCheia(new Date());
        System.out.println("Data" +  CalendarioHelper.getData(data));
    }

}

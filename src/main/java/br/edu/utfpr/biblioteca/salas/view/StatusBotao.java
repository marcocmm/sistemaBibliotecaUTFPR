/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Armazena os atributos css de todos os botões da interface
 *
 * @author romulo
 * @deprecated Utilizar BotaoHorario ao invés desta classe
 */
@Deprecated
public class StatusBotao implements Serializable {

    private final String parametroUmAtivo;
    private final String parametroUmDesativado;
    private final String parametroUmSelecionado;
    private final String parametroDoisAtivo;
    private final String parametroDoisDesativado;
    private final List<String> parametrosBotoes;
    private final int numeroBotao;

    public StatusBotao() {
        parametroUmAtivo = "btn btn-success";
        parametroUmDesativado = "btn btn-danger";
        parametroUmSelecionado = "btn btn";
        parametroDoisAtivo = "false";
        parametroDoisDesativado = "true";

        numeroBotao = 0;
        parametrosBotoes = new ArrayList<>();
    }

    public void setParametrosBotoes(List<String> horasAtivas, Date date) {
        String hora;
        for (int i = 8; i < 22; i++) {
            hora = "";
            if (i < 10) {
                hora = "0";
            }
            hora += i;
            if (horasAtivas.contains(hora) || (new Date()).before(date)) {
                parametrosBotoes.add(parametroUmDesativado);
                parametrosBotoes.add(parametroDoisDesativado);
            } else {
                parametrosBotoes.add(parametroUmAtivo);
                parametrosBotoes.add(parametroDoisAtivo);
            }
        }
    }

    public String getParametro(int numeroBotao, int tipo) {
        String parametro;
        if (tipo == 0) {
            parametro = parametrosBotoes.get(numeroBotao * 2);
        } else {
            parametro = parametrosBotoes.get(numeroBotao * 2 + tipo);
        }
        return parametro;
    }
}

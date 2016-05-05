/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatusBotao {

    ReservaMB reservaMB;

    private final String parametroUmAtivo;
    private final String parametroUmDesativado;
    private final String parametroUmSelecionado;
    private final String parametroDoisAtivo;
    private final String parametroDoisDesativado;
    private List<String> parametrosBotoes;
    private int numeroBotao;

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

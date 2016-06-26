/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.view;

import java.io.Serializable;

/**
 * Armazena os atributos CSS de um botão da interface.
 *
 * @author romulo
 */
public final class BotaoHorario implements Serializable, Comparable<BotaoHorario> {

    private String value;
    private String classe;
    private boolean disabled;
    private int hora;

    public BotaoHorario(int horaInicial, String classe, boolean disabled) {
        setValue(horaInicial);
        setClasse(classe);
        this.disabled = disabled;
        this.hora = horaInicial;
    }

    public void setValue(int hora) {
        String strValue = "";
        switch (hora) {
            case 8:
                strValue += "0" + hora + " às 0" + (hora + 1);
                break;
            case 9:
                strValue += "0" + hora + " às " + (hora + 1);
                break;
            case 0:
                strValue = "               ";
                break;
            default:
                strValue += hora + " às " + (hora + 1);
                break;
        }
        this.value = strValue;
    }

    public void setClasse(String classe) {
        if (classe.equals("verde")) {
            this.classe = "btn btn-success";
        } else if (classe.equals("vermelho")) {
            this.classe = "btn btn-danger";
        } else if (classe.equals("branco")) {
            this.classe = "btn btn-small";
        } else {
            this.classe = "btn btn-primary";
        }
    }

    public boolean isDisabled() {
        return disabled;
    }

    public String getClasse() {
        return classe;
    }

    public int getHora() {
        return hora;
    }

    public String getHoraStr() {
        return Integer.toString(hora);
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(BotaoHorario o) {
        return Integer.compare(this.hora, o.hora);
    }

}

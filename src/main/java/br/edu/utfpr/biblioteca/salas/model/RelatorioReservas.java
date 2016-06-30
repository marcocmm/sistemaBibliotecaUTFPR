package br.edu.utfpr.biblioteca.salas.model;

import java.util.Date;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class RelatorioReservas {
    
    private Date data;
    private int quantidadeAlunosManha;
    private int quantidadeAlunosTarde;
    private int quantidadeAlunosNoite;

    public RelatorioReservas(Date data, int quantidadeAlunosManha, int quantidadeAlunosTarde, int quantidadeAlunosNoite) {
        this.data = data;
        this.quantidadeAlunosManha = quantidadeAlunosManha;
        this.quantidadeAlunosTarde = quantidadeAlunosTarde;
        this.quantidadeAlunosNoite = quantidadeAlunosNoite;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantidadeAlunosManha() {
        return quantidadeAlunosManha;
    }

    public void setQuantidadeAlunosManha(int quantidadeAlunosManha) {
        this.quantidadeAlunosManha = quantidadeAlunosManha;
    }

    public int getQuantidadeAlunosTarde() {
        return quantidadeAlunosTarde;
    }

    public void setQuantidadeAlunosTarde(int quantidadeAlunosTarde) {
        this.quantidadeAlunosTarde = quantidadeAlunosTarde;
    }

    public int getQuantidadeAlunosNoite() {
        return quantidadeAlunosNoite;
    }

    public void setQuantidadeAlunosNoite(int quantidadeAlunosNoite) {
        this.quantidadeAlunosNoite = quantidadeAlunosNoite;
    }

    public int getQuantidadeAlunosTotal() {
        return (this.quantidadeAlunosManha+this.quantidadeAlunosTarde+this.quantidadeAlunosNoite);
    }

}

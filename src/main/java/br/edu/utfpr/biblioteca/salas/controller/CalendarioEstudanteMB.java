/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.controller;

import br.edu.utfpr.biblioteca.salas.model.Dia;
import br.edu.utfpr.biblioteca.salas.model.bo.EstudanteBO;
import br.edu.utfpr.biblioteca.salas.model.bo.ReservaBO;
import br.edu.utfpr.biblioteca.salas.model.entity.EstudantePO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marco
 */
@Named(value = "calendarioEstudanteMB")
@SessionScoped
@ManagedBean
public class CalendarioEstudanteMB {

    private EstudantePO estudante;
    private Dia diaSelecionado;
    private Date mesEscolhido;
    private ReservaPO reserva;

    public CalendarioEstudanteMB() {
        this.estudante = new EstudantePO(null, null, null, null);
        mesEscolhido = new Date();
    }

    public EstudantePO getEstudante() {
        return estudante;
    }

    public void setEstudante(EstudantePO estudantePO) {
        this.estudante = estudantePO;
    }

    public Dia getDiaSelecionado() {
        return diaSelecionado;
    }

    public void setDiaSelecionado(Dia diaSelecionado) {
        this.diaSelecionado = diaSelecionado;
    }

    /**
     * Valida a entrada do usuário e cadastra um estudante.
     *
     * @param estudante
     */
    private void cadastrarEstudante() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            Long.parseLong(estudante.getRa());
            if (estudante.getNome().isEmpty()) {
                throw new Exception("Preencha todos os campos");
            }
            if (estudante.getEmail().isEmpty()) {
                throw new Exception("Preencha todos os campos");
            }
            if (estudante.getSenha().isEmpty()) {
                throw new Exception("Preencha todos os campos");
            }
            String[] split = estudante.getEmail().split("@");
            if (split[0].isEmpty() || split[1].isEmpty()) {
                throw new Exception("E-mail inválido");
            }
        } catch (NumberFormatException ex) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "RA inválido", "Inválido"));
        } catch (IndexOutOfBoundsException ex) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencher nome", "Inválido"));
        } catch (Exception ex) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), "Inválido"));
        }

        EstudanteBO.cadastrarEstudante(estudante);
    }

    /**
     * verifica se o estudante está cadastrado
     *
     * @param estudante
     * @return boolean
     */
    private boolean alreadyCadastrado() {
        return EstudanteBO.alreadyCadastrado(estudante);
    }

    /**
     * obtém o login e senha do estudante e, caso esteja autenticado, exibe
     * mensagem na tela
     *
     * @param event
     */
    public void autenticar(ActionEvent event) {
        FacesMessage message = null;
        boolean loggedIn;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        session.setAttribute("estudanteLogado", this.estudante);

        if (!alreadyCadastrado()) {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Estudante não cadastrado!", null);
        }
        loggedIn = EstudanteBO.isAutentico(this.estudante.getRa(), this.estudante.getSenha());
        if (loggedIn) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo!", estudante.getNome());
        }
        facesContext.addMessage(null, message);
    }

    /**
     * Obtém uma lista com todos os dias do mês dado. Cada dia é uma relação de
     * 14 horas cada qual com no máximo 6 reservas
     *
     * @param date
     * @return
     */
    public List<Dia> getMes(Date date) {
        return ReservaBO.descreverMes(date);
    }

    /**
     * Obtém lista com todos os dias do mês atual. Cada dia é uma relação de 14
     * horas cada qual com no máximo 6 reservas
     *
     * @return
     */
    public List<Dia> getMes() {
        return getMes(this.mesEscolhido);
    }

    /**
     * retorna o mes atual
     *
     * @return
     */
    public Date getDataAtual() {
        return new Date();
    }

    public Date getMesEscolhido() {
        return mesEscolhido;
    }

    public void setMesEscolhido(Date mesEscolhido) {
        this.mesEscolhido = mesEscolhido;
    }

    public Date getMesAnterior() {
        return CalendarioHelper.mesAnterior(mesEscolhido);
    }

    public Date getMesPosterior() {
        return CalendarioHelper.mesPosterior(mesEscolhido);
    }

    public String getNomeAtual() {
        return CalendarioHelper.getNomeMes(mesEscolhido);
    }

    public String getNomeAnterior() {
        return CalendarioHelper.getNomeMes(getMesAnterior());
    }

    public String getNomePosterior() {
        return CalendarioHelper.getNomeMes(getMesPosterior());
    }
}

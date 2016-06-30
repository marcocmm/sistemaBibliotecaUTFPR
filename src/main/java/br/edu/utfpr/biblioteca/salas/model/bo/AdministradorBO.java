/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.RelatorioReservas;
import br.edu.utfpr.biblioteca.salas.model.dao.ReservaDAO;
import br.edu.utfpr.biblioteca.salas.model.entity.ReservaPO;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author marco
 */
public class AdministradorBO {

    public void gerarRelatorio(String tipo, Date dataInicial, Date dataFinal) throws Exception {
        dataInicial = CalendarioHelper.setHoraMinutosSegundos(dataInicial, 8, 0, 0);
        dataFinal = CalendarioHelper.setHoraMinutosSegundos(dataFinal, 22, 0, 0);
        ReservaDAO reservaDAO = new ReservaDAO();
        List<ReservaPO> reservas = reservaDAO.getReservasPeriodo(dataInicial, dataFinal);
        List<RelatorioReservas> relatorioReservas = new ArrayList<>();
        for (ReservaPO reserva : reservas) {
            RelatorioReservas relatorioReserva;
            if (CalendarioHelper.getPeriodoDia(reserva.getDataInicial()).equals("manha")) {
                relatorioReserva = new RelatorioReservas(reserva.getDataInicial(), reserva.getQuantidadeAlunos(), 0, 0);

            } else if (CalendarioHelper.getPeriodoDia(reserva.getDataInicial()).equals("tarde")) {
                relatorioReserva = new RelatorioReservas(reserva.getDataInicial(), 0, reserva.getQuantidadeAlunos(), 0);

            } else {
                relatorioReserva = new RelatorioReservas(reserva.getDataInicial(), 0, 0, reserva.getQuantidadeAlunos());
            }
            relatorioReservas.add(relatorioReserva);
        }

        ParserCsvBO parserCsvBO = new ParserCsvBO(Integer.parseInt(tipo), relatorioReservas);
    }

}

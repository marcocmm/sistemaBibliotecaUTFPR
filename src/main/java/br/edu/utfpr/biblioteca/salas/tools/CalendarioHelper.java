/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.biblioteca.salas.tools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author romulo
 */
public class CalendarioHelper {

    /**
     * Obtém as datas do mês todo
     *
     * @param ano
     * @param mes
     * @return lista das datas do mês, incluido mês anterior, mês atual e mês
     * posterior
     * @deprecated Utilizar getCalendario(Date)
     */
    @Deprecated
    public static List<Date> getCalendario(Integer ano, Integer mes) {
        int primeiroDia, ultimoDia, diaPrimeiraSemana, i;
        List<Date> calendario = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        calendar.set(Calendar.YEAR, ano);

        calendar.set(Calendar.MONTH, mes - 1);
        ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.MONTH, mes);
        primeiroDia = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, primeiroDia);
        diaPrimeiraSemana = calendar.get(Calendar.DAY_OF_WEEK);
        if (diaPrimeiraSemana == 1) {
            diaPrimeiraSemana = 8;
        }

        calendar.set(Calendar.MONTH, mes - 1);
        for (i = ultimoDia; i > (ultimoDia - diaPrimeiraSemana) + 1; i--) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            calendario.add(0, calendar.getTime());
        }

        calendar.set(Calendar.MONTH, mes);
        ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (i = primeiroDia; i <= ultimoDia; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            calendario.add(calendar.getTime());
        }

        calendar.set(Calendar.MONTH, mes + 1);
        for (i = primeiroDia; calendario.size() < 42; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            calendario.add(calendar.getTime());
        }

        return calendario;
    }

    /**
     * Obtém as datas do mês todo. Observa apenas mês e ano
     *
     * @param date
     * @return return lista das datas do mês, incluido mês anterior, mês atual e
     * mês posterior
     */
    public static List<Date> getCalendario(Date date) {
        int primeiroDia, ultimoDia, diaPrimeiraSemana, i, ano, mes;
        List<Date> calendario;
        Calendar calendar;

        calendario = new ArrayList<>();
        calendar = Calendar.getInstance();

        calendar.setTime(date);
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);

        calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        calendar.set(Calendar.YEAR, ano);

        calendar.set(Calendar.MONTH, mes - 1);
        ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.MONTH, mes);
        primeiroDia = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, primeiroDia);
        diaPrimeiraSemana = calendar.get(Calendar.DAY_OF_WEEK);
        if (diaPrimeiraSemana == 1) {
            diaPrimeiraSemana = 8;
        }

        calendar.set(Calendar.MONTH, mes - 1);
        for (i = ultimoDia; i > (ultimoDia - diaPrimeiraSemana) + 1; i--) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            calendario.add(0, calendar.getTime());
        }

        calendar.set(Calendar.MONTH, mes);
        ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (i = primeiroDia; i <= ultimoDia; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            calendario.add(calendar.getTime());
        }

        calendar.set(Calendar.MONTH, mes + 1);
        for (i = primeiroDia; calendario.size() < 42; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            calendario.add(calendar.getTime());
        }

        return calendario;
    }

    /**
     * Obtém o calendário no formato de uma matriz
     *
     * @param ano
     * @param mes
     * @return
     * @deprecated Utilizar getCalendario(Date)
     */
    @Deprecated
    public static Date[][] getCalendarioMatriz(Integer ano, Integer mes) {
        int primeiroDia, ultimoDia, diaPrimeiraSemana, i;
        Date[][] calendario = new Date[5][7];

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        calendar.set(Calendar.YEAR, ano);

        calendar.set(Calendar.MONTH, mes - 1);
        ultimoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.set(Calendar.MONTH, mes);
        primeiroDia = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, primeiroDia);
        diaPrimeiraSemana = calendar.get(Calendar.DAY_OF_WEEK);
        if (diaPrimeiraSemana == 1) {
            diaPrimeiraSemana = 8;
        }

        calendar.set(Calendar.MONTH, mes - 1);
        i = ultimoDia;
        int j = 0;
        while (i > (ultimoDia - diaPrimeiraSemana) + 1) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            calendario[0][j] = calendar.getTime();
            i--;
        }

        calendar.set(Calendar.MONTH, mes);
        i = primeiroDia;
        for (j = 0; j < 7; j++) {
            for (int k = 1; k < 4; k++) {
                calendar.set(Calendar.DAY_OF_MONTH, i);
                calendario[j][i] = calendar.getTime();
                i++;
            }
        }

        calendar.set(Calendar.MONTH, mes + 1);
        for (i = 0; calendario[5][7] == null; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            calendario[5][i] = calendar.getTime();
        }

        return calendario;
    }

    /**
     * Obtém uma lista de horários (Date) ordenada com os horários em que a
     * biblioteca está em funcionamento.
     *
     * @param date
     * @return
     */
    public static List<Date> getHorarios(Date date) {
        List<Date> horarios = new ArrayList();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        for (int i = 8; i < 21; i++) {
            calendar.set(Calendar.HOUR_OF_DAY, i);
            horarios.add(calendar.getTime());
        }

        return horarios;
    }

    /**
     * Converte uma String em Date. Formato da String: dd-mm-yyyy
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        String dateParsed[] = date.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);

        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dateParsed[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(dateParsed[1]));
        calendar.set(Calendar.YEAR, Integer.valueOf(dateParsed[2]));
        return calendar.getTime();
    }

    /**
     * Converte uma String em Date.
     *
     * @param date formato dd-mm-yyy
     * @param time formato 23:59
     * @return
     */
    public static Date parseDateTime(String date, String time) {
        String timeParsed[] = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate(date));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeParsed[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(timeParsed[1]));
        return calendar.getTime();
    }

    /**
     * Obtém o formato que o banco de dados utiliza para inserir datas
     *
     * @param date
     * @return
     * @deprecated Não é necessário utilizar esse método, o banco grava o objeto
     * Date inteiro.
     */
    @Deprecated
    public static String getDatabaseDateFormat(Date date) {
        String databaseDateFormat = "";

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        databaseDateFormat += calendar.get(Calendar.YEAR);
        databaseDateFormat += "-";
        databaseDateFormat += calendar.get(Calendar.MONTH);
        databaseDateFormat += "-";
        databaseDateFormat += calendar.get(Calendar.DAY_OF_MONTH);
        databaseDateFormat += " ";
        databaseDateFormat += calendar.get(Calendar.HOUR_OF_DAY);
        databaseDateFormat += ":";
        databaseDateFormat += "00";
        databaseDateFormat += ":";
        databaseDateFormat += "00";
        return databaseDateFormat;
    }

}

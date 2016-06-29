package br.edu.utfpr.biblioteca.salas.model.bo;

import br.edu.utfpr.biblioteca.salas.model.RelatorioReservas;
import br.edu.utfpr.biblioteca.salas.tools.CalendarioHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ParserCsvBO {

    private String filePath;
    private FileWriter file;

    public ParserCsvBO(int tipoRelatorio) {
        String path = "";
        switch (tipoRelatorio){
            case 1:
                path = "./relatorios/RelatorioSemanal.csv";
                break;
            case 2:
                path = "./relatorios/RelatorioMensal.csv";
                break;
            case 3:
                path = "./relatorios/RelatorioAnual.csv";
                break;
        }
        try {
            filePath = new File(path).getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(ParserCsvBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeCsvReservasSemana(List<RelatorioReservas> relatorioReservas) {
        PrintWriter writeFile = null;
        try {
            writeFile = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath), Charset.forName("UTF-8")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParserCsvBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        writeFile.print("ESTATÍSTICA DO USO DAS SALAS DE ESTUDO");
        writeFile.println();
        writeFile.print("Data, Manhã, Tarde, Noite, Total,");
        writeFile.println();
        for (RelatorioReservas relatorio : relatorioReservas) {
            writeFile.print(CalendarioHelper.getDiaMesAno(relatorio.getData()) + ",");
            writeFile.print(relatorio.getQuantidadeAlunosManha() + ",");
            writeFile.print(relatorio.getQuantidadeAlunosTarde() + ",");
            writeFile.print(relatorio.getQuantidadeAlunosNoite() + ",");
            writeFile.print(relatorio.getQuantidadeAlunosTotal() + ",");
            writeFile.println();
        }
        writeFile.close();
    }

    public void writeCsvReservasMensalOrAnual(List<RelatorioReservas> relatorioReservas) {
        PrintWriter writeFile = null;
        try {
            writeFile = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath), Charset.forName("UTF-8")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParserCsvBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        writeFile.print("ESTATÍSTICA DO USO DAS SALAS DE ESTUDO");
        writeFile.println();
        writeFile.print("Mês, Manhã, Tarde, Noite, Total,");
        writeFile.println();
        HashMap<String, List<RelatorioReservas>> relatorioByMes = getMeses(relatorioReservas);
        for (Map.Entry<String, List<RelatorioReservas>> meses : relatorioByMes.entrySet()) {
            writeFile.print(meses.getKey() + ",");
            int quantidadeAlunosManha = 0;
            int quantidadeAlunosTarde = 0;
            int quantidadeAlunosNoite = 0;
            for (RelatorioReservas relatorio : meses.getValue()) {
                quantidadeAlunosManha += relatorio.getQuantidadeAlunosManha();
                quantidadeAlunosTarde += relatorio.getQuantidadeAlunosNoite();
                quantidadeAlunosNoite += relatorio.getQuantidadeAlunosNoite();
            }
            writeFile.print(quantidadeAlunosManha + ",");
            writeFile.print(quantidadeAlunosTarde + ",");
            writeFile.print(quantidadeAlunosTarde + ",");
            writeFile.print((quantidadeAlunosManha + quantidadeAlunosTarde + quantidadeAlunosNoite) + ",");
            writeFile.println();
        }
        writeFile.close();
    }

    private HashMap<String, List<RelatorioReservas>> getMeses(List<RelatorioReservas> relatorioReservas) {
        HashMap<String, List<RelatorioReservas>> relatorioByMes = new HashMap<>();
        for (RelatorioReservas relatorio : relatorioReservas) {
            String mes = CalendarioHelper.getNomeMes(relatorio.getData());
            if (!relatorioByMes.containsKey(mes)) {
                relatorioByMes.put(mes, new ArrayList<RelatorioReservas>());
                relatorioByMes.get(mes).add(relatorio);
            } else {
                relatorioByMes.get(mes).add(relatorio);
            }

        }
        return relatorioByMes;
    }

}

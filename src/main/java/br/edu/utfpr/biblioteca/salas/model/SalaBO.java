package br.edu.utfpr.biblioteca.salas.model;

import br.edu.utfpr.biblioteca.salas.model.entity.SalaPO;
import java.util.HashMap;
import java.util.List;


public class SalaBO {
    
    /**
     * Este método recebe uma data e retorna um hash contendo uma chave horário,
     * set true se alguma sala possui reservas disponíves ou false se todas as salas estão reservasdas naquele horário.
     * @param data
     * @return HashMap<Integer, Boolean>
     */
    public static HashMap<Integer, Boolean> getStatusDaSala(String data){
        //implement the code here!
        return null;
    }
    
    /**
     * Este método recebe uma data e uma hora,
     * e deve retornar uma lista de salas disponíveis.
     * @param data
     * @param hora
     * @return List<SalaPO>
     */
    public static List<SalaPO> getSalasDisponiveis(String data, String hora){
        //implement the code here!
        return null;
    }
    
    /**
     * Este método constrói o objeto ReservaPO com os atributos passado por parâmetro,
     * e faz os procedimentos para reservas uma sala, retorn true se a reserva foi feita e false quando não possível realizar.
     * @param idSala
     * @param qtdeAlunos
     * @param ra
     * @param senha
     * @param data
     * @param hora
     * @return boolean
     * Talvez retornar um boolean fique difícil de indentificar o erro (login, sala já reservada) para mandar a mensagem para o usuário.
     */
    public static boolean reservarSala(String idSala, String qtdeAlunos, String ra, String senha, String data, String hora){
        //implement the code here!
        //Construir o Objeto ReservaPO com os parâmentros
        //Chamar o insert do dao após as verificaçoes serem aprovasdas...
        if (!EstudanteBO.autenticar(ra, senha)){//verificando a autenticação do estudante
            return false;
        }
        return false;
    }
    
    
}

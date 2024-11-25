package cod;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuocientePartidario {

    private Map<String, Integer> votosPorPartido; // Votos por partido
    private Map<String, Integer> votosPorCandidato; // Votos por candidato
    private int quocienteEleitoral;

    // Construtor
    public QuocientePartidario(Map<String, Integer> votosPorPartido, Map<String, Integer> votosPorCandidato, int quocienteEleitoral) {
        this.votosPorPartido = votosPorPartido;
        this.votosPorCandidato = votosPorCandidato;
        this.quocienteEleitoral = quocienteEleitoral;
    }

    /**
     * Calcula o número de cadeiras que cada partido tem direito
     */
    public Map<String, Integer> calcularQuocientePartidario() {
        Map<String, Integer> cadeirasPorPartido = new HashMap<>();

        for (Map.Entry<String, Integer> entry : votosPorPartido.entrySet()) {
            String partido = entry.getKey();
            int votos = entry.getValue();

            int cadeiras = votos / quocienteEleitoral; // Calcula o número de cadeiras
            cadeirasPorPartido.put(partido, cadeiras);
        }

        return cadeirasPorPartido;
    }
    
    public void exibirQuocientePartidario() {
        if (quocienteEleitoral == 0) {
            System.out.println("Erro: O quociente eleitoral não pode ser zero.");
            return;
        }

        Map<String, Integer> cadeirasPorPartido = calcularQuocientePartidario();

        System.out.println("Quociente Partidário:");
        cadeirasPorPartido.forEach((partido, cadeiras) -> 
            System.out.println("Partido " + partido + ": " + cadeiras + " cadeira(s)")
        );
    }

    /**
     * Atribui cadeiras aos candidatos mais votados dentro de cada partido
     */
    public Map<String, List<String>> atribuirCadeiras() {
        Map<String, Integer> cadeirasPorPartido = calcularQuocientePartidario();
        Map<String, List<String>> cadeirasPorCandidatos = new HashMap<>();

        for (Map.Entry<String, Integer> entry : cadeirasPorPartido.entrySet()) {
            String partido = entry.getKey();
            int cadeiras = entry.getValue();

            // Filtra os candidatos do partido
            List<Map.Entry<String, Integer>> candidatosDoPartido = new ArrayList<>();
            for (Map.Entry<String, Integer> candidatoEntry : votosPorCandidato.entrySet()) {
                if (candidatoEntry.getKey().startsWith(partido)) { // Verifica pelo prefixo do partido
                    candidatosDoPartido.add(candidatoEntry);
                }
            }

            // Ordena os candidatos do partido por votos (decrescente)
            candidatosDoPartido.sort((a, b) -> b.getValue() - a.getValue());

            // Seleciona os candidatos mais votados para ocupar as cadeiras
            List<String> eleitos = new ArrayList<>();
            for (int i = 0; i < cadeiras && i < candidatosDoPartido.size(); i++) {
                eleitos.add(candidatosDoPartido.get(i).getKey()); // Adiciona o código do candidato eleito
            }

            cadeirasPorCandidatos.put(partido, eleitos);
        }

        return cadeirasPorCandidatos;
    }

    /**
     * Exibe as cadeiras atribuídas no console
     */
    public void exibirCadeirasAtribuidas() {
        Map<String, List<String>> cadeirasPorCandidatos = atribuirCadeiras();

        System.out.println("Cadeiras Atribuídas por Partido:");
        cadeirasPorCandidatos.forEach((partido, candidatos) -> {
            System.out.println("Partido " + partido + ": " + candidatos.size() + " cadeira(s)");
            System.out.println("Candidatos eleitos: " + candidatos);
        });
    }
}
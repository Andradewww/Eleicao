package cod;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Eleicao {

	private Map<String, Integer> votos;

    // Construtor que recebe os votos totais
    public Eleicao(Map<String, Integer> votos) {
        this.votos = votos;
    }

    // Método para exibir os 10 eleitos e o restante dos candidatos
    public void exibirCandidatosEleitos() {
        // Filtrar votos válidos (excluindo votos nulos e brancos)
        Map<String, Integer> votosValidos = votos.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("NULO") && !entry.getKey().equals("BRANCO"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Ordenar os candidatos por total de votos em ordem decrescente
        List<Map.Entry<String, Integer>> candidatosOrdenados = new ArrayList<>(votosValidos.entrySet());
        candidatosOrdenados.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Exibir os 10 primeiros eleitos
        System.out.println("Top 10 Eleitos:");
        for (int i = 0; i < Math.min(10, candidatosOrdenados.size()); i++) {
            Map.Entry<String, Integer> candidato = candidatosOrdenados.get(i);
            System.out.println("Candidato " + candidato.getKey() + ": " + candidato.getValue() + " votos");
        }

        // Exibir os candidatos restantes (ordenados por votos)
        System.out.println("\nOutros Candidatos:");
        for (int i = 10; i < candidatosOrdenados.size(); i++) {
            Map.Entry<String, Integer> candidato = candidatosOrdenados.get(i);
            System.out.println("Candidato " + candidato.getKey() + ": " + candidato.getValue() + " votos");
        }
    }
}
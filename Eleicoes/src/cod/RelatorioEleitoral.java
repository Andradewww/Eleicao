package cod;
import java.util.Map;

public class RelatorioEleitoral {

    private Map<String, Integer> votos;

    // Construtor que recebe os votos totais
    public RelatorioEleitoral(Map<String, Integer> votos) {
        this.votos = votos;
    }

    // Método para exibir o relatório
    public void exibirRelatorio() {
        System.out.println("Resumo Eleitoral");

        // Verificar se o mapa de votos não está vazio
        if (votos.isEmpty()) {
            System.out.println("Nenhum voto registrado.");
            return; // Se não houver votos, não prosseguir
        }

        // Calcular o total de votos válidos (excluindo NULO e BRANCO)
        int totalVotosValidos = votos.entrySet().stream()
            .filter(entry -> !entry.getKey().equals("NULO") && !entry.getKey().equals("BRANCO"))
            .mapToInt(Map.Entry::getValue)
            .sum();
        
        // Total de votos
        int totalVotos = votos.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("\nTotal de votos: " + totalVotos);

        // Votos por candidato com porcentagem
        System.out.println("\nVotos por Candidato:");
        votos.forEach((candidato, qtd) -> {
            if (!candidato.equals("NULO") && !candidato.equals("BRANCO")) {
                double percentualCandidato = (totalVotosValidos == 0) ? 0 : ((double) qtd / totalVotosValidos) * 100;
                System.out.println("Candidato " + candidato + ": " + qtd + " votos (" + String.format("%.2f", percentualCandidato) + "%)");
            }
        });

        // Votos brancos e nulos com percentual
        int votosBrancos = votos.getOrDefault("BRANCO", 0);
        int votosNulos = votos.getOrDefault("NULO", 0);

        double percentualBrancos = (totalVotos == 0) ? 0 : ((double) votosBrancos / totalVotos) * 100;
        double percentualNulos = (totalVotos == 0) ? 0 : ((double) votosNulos / totalVotos) * 100;

        System.out.println("\nVotos Brancos: " + votosBrancos + " (" + String.format("%.2f", percentualBrancos) + "%)");
        System.out.println("Votos Nulos: " + votosNulos + " (" + String.format("%.2f", percentualNulos) + "%)");
    }
}
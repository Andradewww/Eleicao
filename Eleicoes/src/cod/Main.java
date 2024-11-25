package cod;
import java.util.HashMap;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {
        String caminhoArquivo = "C:/Users/Thinkpad/OneDrive/Documentos/ELEICOES-POO/votos.txt"; //caminho arquivo
        int totalCadeiras = 10; //numero cadeiras

        LeituraDados leitor = new LeituraDados(caminhoArquivo);

        //obter contagem de votos
        Map<String, Integer> votos = leitor.contarVotos();

        //calcular total de votos válidos
        int totalVotosValidos = votos.entrySet().stream()
            .filter(entry -> !entry.getKey().equals("NULO") && !entry.getKey().equals("BRANCO"))
            .mapToInt(Map.Entry::getValue)
            .sum();

        // Calcular votos por partido e por candidato
        Map<String, Integer> votosPorPartido = new HashMap<>();
        Map<String, Integer> votosPorCandidato = new HashMap<>();
        votos.forEach((numero, qtd) -> {
            if (!numero.equals("NULO") && !numero.equals("BRANCO")) {
                String partido = numero.substring(0, 2); // Os dois primeiros números representam o partido
                votosPorPartido.put(partido, votosPorPartido.getOrDefault(partido, 0) + qtd);
                votosPorCandidato.put(numero, qtd);
            }
        });

        // Instanciar classe QuocienteEleitoral
        QuocienteEleitoral quocienteEleitoral = new QuocienteEleitoral(totalVotosValidos, totalCadeiras);
        int quociente = quocienteEleitoral.calcularQuocienteEleitoral();

        // Exibir o quociente eleitoral
        quocienteEleitoral.exibirQuocienteEleitoral();

        // Instanciar classe QuocientePartidario
        QuocientePartidario quocientePartidario = new QuocientePartidario(votosPorPartido, votosPorCandidato, quociente);

        // Exibir o quociente partidário
        quocientePartidario.exibirQuocientePartidario();

        // Atribuir cadeiras aos candidatos mais votados
        quocientePartidario.exibirCadeirasAtribuidas();

        // Gerar e exibir o relatório de votos por urna, votos totais, brancos e nulos
        RelatorioEleitoral relatorio = new RelatorioEleitoral(votos);
        relatorio.exibirRelatorio();

        // Instanciar a classe Eleicao e exibir os resultados
        Eleicao eleicao = new Eleicao(votos);
        eleicao.exibirCandidatosEleitos();
    }
}
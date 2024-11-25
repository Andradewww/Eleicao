package cod;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeituraDados {
    
	private String caminhoArquivo;

    //construtor caminho arquivo
    public LeituraDados(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    /**
     * Método para ler todas as linhas do arquivo
     * @return Lista de Strings com cada linha do arquivo
     * @throws IOException caso ocorra erro ao acessar o arquivo
     */
    public List<String> lerArquivo() throws IOException {
        List<String> linhas = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }
        }
        
        return linhas;
    }

    /**
     * Método para realizar a contagem dos votos no arquivo
     * @return Mapa com o número do candidato e sua quantidade de votos
     */
    public Map<String, Integer> contarVotos() {
        Map<String, Integer> votos = new HashMap<>();
        votos.put("NULO", 0); // Inicializando contagem para votos nulos
        votos.put("BRANCO", 0); // Inicializando contagem para votos em branco

        try {
            List<String> linhas = lerArquivo();
            
            for (String linha : linhas) {
                String[] partes = linha.split(";"); // Divide a linha no ";"
                if (partes.length < 2) continue; // Ignorar linhas inválidas
                
                String voto = partes[1]; // Obtém o número do candidato/voto

                if (voto.equals("0")) {
                    votos.put("NULO", votos.get("NULO") + 1); // Incrementa votos nulos
                } else if (voto.equals("1")) {
                    votos.put("BRANCO", votos.get("BRANCO") + 1); // Incrementa votos em branco
                } else {
                    votos.put(voto, votos.getOrDefault(voto, 0) + 1); // Incrementa votos do candidato
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return votos;
    }

    /**
     * Método para exibir a contagem de votos no console
     */
    public void exibirContagemVotos() {
        Map<String, Integer> votos = contarVotos();
        System.out.println("Contagem de Votos:");
        votos.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
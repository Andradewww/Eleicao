package cod;

public class QuocienteEleitoral {
	
	private int totalVotosValidos;
    private int totalCadeiras;

    // Construtor
    public QuocienteEleitoral(int totalVotosValidos, int totalCadeiras) {
        this.totalVotosValidos = totalVotosValidos;
        this.totalCadeiras = totalCadeiras;
    }

    /**
     * Método para calcular o quociente eleitoral
     * @return Valor do quociente eleitoral
     */
    public int calcularQuocienteEleitoral() {
        if (totalCadeiras <= 0) {
            throw new IllegalArgumentException("O número de cadeiras deve ser maior que zero.");
        }
        return totalVotosValidos / totalCadeiras;
    }

    /**
     * Método para exibir o quociente eleitoral no console
     */
    public void exibirQuocienteEleitoral() {
        try {
            int quociente = calcularQuocienteEleitoral();
            System.out.println("Quociente Eleitoral: " + quociente);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao calcular o quociente eleitoral: " + e.getMessage());
        }
    }

    // Getters e Setters
    public int getTotalVotosValidos() {
        return totalVotosValidos;
    }

    public void setTotalVotosValidos(int totalVotosValidos) {
        this.totalVotosValidos = totalVotosValidos;
    }

    public int getTotalCadeiras() {
        return totalCadeiras;
    }

    public void setTotalCadeiras(int totalCadeiras) {
        this.totalCadeiras = totalCadeiras;
    }
}

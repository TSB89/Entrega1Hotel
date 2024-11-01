package classesBase;

import java.time.LocalDate;

public class Reserva {

    private LocalDate entrada;
    private LocalDate saida;
    private String tipoDoQuarto;
    private int quantidadeDeHospedes;
    private String cpfHospede;
	private int numeroQuarto;
	private boolean checkInRealizado;
	public Reserva(LocalDate entrada, LocalDate saida, String tipoDoQuarto, int quantidadeDeHospedes, String cpfHospede,int numeroQuarto) {
		this.entrada = entrada;
		this.saida = saida;
		this.tipoDoQuarto = tipoDoQuarto;
		this.quantidadeDeHospedes = quantidadeDeHospedes;
		this.cpfHospede = cpfHospede;
		this.numeroQuarto = numeroQuarto;
		this.checkInRealizado = false;
		
	}

	public LocalDate getEntrada() {
		return entrada;
	}

	public LocalDate getSaida() {
		return saida;
	}
	public void setSaida(LocalDate saida) {
		this.saida = saida;
	}
	public String getTipoDoQuarto() {
		return tipoDoQuarto;
	}

	public int getQuantidadeDeHospedes() {
		return quantidadeDeHospedes;
	}

	public String getHospedeCpf() {
        return cpfHospede;
    }

	public int getNumeroQuarto() {
		return numeroQuarto;
	}

	public boolean isCheckInRealizado() {
		return checkInRealizado;
	}

	public void setCheckInRealizado(boolean checkInRealizado) {
		this.checkInRealizado = checkInRealizado;
	}

	@Override
	public String toString() {
		return "Entrada: " + entrada + "\n" +
				"Saida: " + saida + "\n" +
				"Tipo Do Quarto: " + tipoDoQuarto + "\n" +
				"Quantidade De Hospedes: " + quantidadeDeHospedes + "\n" +
				"Numero Do Quarto: " + numeroQuarto + "\n";
	}
}
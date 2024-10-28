package classesBase;

import java.time.LocalDate;
//vai ter uma associação com a classe Quarto
public class Historico {

    private LocalDate entrada;
    private LocalDate saida;
    private String tipoDoQuarto;
	
    public Historico(LocalDate entrada, LocalDate saida, String tipoDoQuarto) {
		this.entrada = entrada;
		this.saida = saida;
		this.tipoDoQuarto = tipoDoQuarto;
		
	}

	public LocalDate getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDate entrada) {
		this.entrada = entrada;
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

	public void setTipoDoQuarto(String tipoDoQuarto) {
		this.tipoDoQuarto = tipoDoQuarto;
	}
    
}
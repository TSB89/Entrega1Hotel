package classesBase;

import java.time.LocalDate;

//vai ter uma associação com a classe Quarto
public class Reserva {

    private LocalDate entrada;
    private LocalDate saida;
    private String tipoDoQuarto;
    private int quantidadeDeHospedes;
    private String cpfHospede;
	public Reserva(LocalDate entrada, LocalDate saida, String tipoDoQuarto, int quantidadeDeHospedes, String cpfHospede) {
		this.entrada = entrada;
		this.saida = saida;
		this.tipoDoQuarto = tipoDoQuarto;
		this.quantidadeDeHospedes = quantidadeDeHospedes;
		this.cpfHospede = cpfHospede;
		
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
	public int getQuantidadeDeHospedes() {
		return quantidadeDeHospedes;
	}
	public String getHospedeCpf() {
        return cpfHospede;
    }
	
	public void setQuantidadeDeHospedes(int quantidadeDeHospedes) {
		this.quantidadeDeHospedes = quantidadeDeHospedes;
	}
	
    
    

}

package classesBase;

public class Quarto {

    private int numero;
    private String tipo;
    private int capacidade;
    private double precoDiaria;
    private boolean disponivel;

    public Quarto(int numero, String tipo, int capacidade, double precoDiaria) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.precoDiaria = precoDiaria;
        this.disponivel = true;  
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setDisponivel(boolean disponivel) {
    	this.disponivel = disponivel;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "\nQuarto: " + numero + "\nTipo: " + tipo + " \nCapacidade: " + capacidade + " \nPreço: R$ " + String.format("%.2f", precoDiaria) + " \nDisponível: " + (disponivel ? "Sim" : "Não");
    }
}
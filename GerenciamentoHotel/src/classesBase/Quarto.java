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

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void atualizarStatus(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    @Override
    public String toString() {
        return "\nQuarto: " + numero + "\nTipo: " + tipo + " \nCapacidade: " + capacidade + " \nPreço: R$ " + precoDiaria + " \nDisponível: " + (disponivel ? "Sim" : "Não");
    }
}
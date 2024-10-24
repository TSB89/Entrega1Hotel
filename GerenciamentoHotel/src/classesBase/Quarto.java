package classesBase;

public class Quarto {

    private int numero;
    private String tipo;
    private int capacidade;
    private double diaria;
    private boolean disponibilidade;

    public Quarto(int capacidade, double diaria, int numero, String tipo) {
        this.capacidade = capacidade;
        this.diaria = diaria;
        this.disponibilidade = true;
        this.numero = numero;
        this.tipo = tipo;
    }
}
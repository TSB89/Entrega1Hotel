package Hotel1;
class Quarto implements Status {
    private int numero;
    private String tipo;
    private int capacidade;
    private double precoDiaria;
    private String status;

    public Quarto(int numero, String tipo, int capacidade, double precoDiaria) {
        this.numero = numero;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.precoDiaria = precoDiaria;
        this.status = "disponível";
    }

    public int getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public int getCapacidade() { return capacidade; }
    public double getPrecoDiaria() { return precoDiaria; }

    @Override
    public void atualizarStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "\nQuarto: " + numero + "\nTipo: " + tipo + " \nCapacidade: " + capacidade + " \nPreço: R$ " + precoDiaria + " \nStatus: " + status + "";
    }
}
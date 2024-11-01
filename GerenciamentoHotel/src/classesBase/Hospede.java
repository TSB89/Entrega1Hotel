package classesBase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Hospede extends Pessoa {

    private LocalDate dataDeNascimento;
    private String endereco;
    private String contato;
    private Reserva reserva;
    private List<Reserva> historico;

    public Hospede(String cpf, String nome, String contato, LocalDate dataDeNascimento, String endereco) {
        super(cpf, nome);
        this.contato = contato;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.historico = new ArrayList<>();
        this.reserva = null;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Reserva> getHistorico() {
        return historico;
    }

    public void setHistorico(Reserva historico) {
        this.historico.add(historico);
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public void retiraReserva () {
        this.reserva = null;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = getDataDeNascimento().format(formatarData);
        return "\nNome: " + getNome() +
                "\nCpf: " + getCpf() +
                "\nData de nascimento: " + data +
                "\nContato: " + getContato() +
                "\nEndereço: " + getEndereco() + "\n";
    }
}
package classesBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hospede extends Pessoa {

    private LocalDate dataDeNascimento;
    private String endereco;
    private String contato;
    private Reserva reserva;
    private List<Historico> historico;

    public Hospede(String cpf, String nome, String contato, LocalDate dataDeNascimento, String endereco, Reserva reserva) {
        super(cpf, nome);
        this.contato = contato;
        this.dataDeNascimento = dataDeNascimento;
        this.endereco = endereco;
        this.historico = new ArrayList<>();
        this.reserva = reserva;
    }
}
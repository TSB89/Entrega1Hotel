package gerenciamento;

import classesBase.Funcionario;
import classesBase.Hospede;
import classesBase.Quarto;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private GerenciamentoHospede gerenciamentoHospede;
    private GerenciamentoFuncionario gerenciamentoFuncionario;
    private GerenciamentoQuarto gerenciamentoQuarto;
    private GerenciamentoReserva gerenciamentoReserva;
    private List<Hospede> hospedes;
    private List<Funcionario> funcionarios;
    private List<Quarto> quartos;

    public Hotel() {
        quartos = new ArrayList<>();
        hospedes = new ArrayList<>();
        funcionarios = new ArrayList<>();
        gerenciamentoReserva = new GerenciamentoReserva(quartos,hospedes);
//        gerenciamentoHospede = new GerenciamentoHospede();
//        gerenciamentoFuncionario = new GerenciamentoFuncionario();
//        gerenciamentoQuarto = new GerenciamentoQuarto();
    }
}
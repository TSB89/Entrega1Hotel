package gerenciamento;

import classesBase.Funcionario;
import classesBase.Hospede;
import classesBase.Quarto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        gerenciamentoReserva = new GerenciamentoReserva(quartos, hospedes);
        gerenciamentoHospede = new GerenciamentoHospede(hospedes);
        gerenciamentoFuncionario = new GerenciamentoFuncionario(funcionarios);
        gerenciamentoQuarto = new GerenciamentoQuarto(quartos);
    }

    public void cadastrarQuarto() {
        gerenciamentoQuarto.cadastrar();
    }

    public void visualizarQuarto() {
        gerenciamentoQuarto.visualizar();
    }

    public void atualizarStatusQuarto() {
        gerenciamentoQuarto.editar();
    }

    public void cadastrarHospede() {
        gerenciamentoHospede.cadastrar();
    }

    public void visualizarHospede() {
        gerenciamentoHospede.visualizar();
    }

    public void visualizarHistoricoEstadias() {
        gerenciamentoHospede.visualizarHistorico();
    }

    public void editarHospede() {
        gerenciamentoHospede.editar();
    }

    public void cadastrarFuncionario() {
        gerenciamentoFuncionario.cadastrar();
    }

    public void visualizarFuncionario() {
        gerenciamentoFuncionario.visualizar();
    }

    public void editarFuncionario() {
        gerenciamentoFuncionario.editar();
    }

    public void criarReserva() {
        gerenciamentoReserva.cadastrar();
    }

    public void cancelarReserva() {
        gerenciamentoReserva.editar();
    }

    public void verificarDisponibilidadeQuarto() {
        gerenciamentoReserva.visualizar();
    }

    public void checkIn() {
        gerenciamentoReserva.checkIn();
    }

    public void checkOut() {
        gerenciamentoReserva.checkOut();
    }
}
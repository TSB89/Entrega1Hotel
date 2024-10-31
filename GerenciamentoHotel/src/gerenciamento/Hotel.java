package gerenciamento;

import classesBase.Funcionario;
import classesBase.Hospede;
import classesBase.Quarto;
import classesBase.Reserva;

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
    private List<Integer> numerosQuarto;
    private boolean checkInRealizado;
    private Scanner input = new Scanner(System.in);

    public Hotel() {
        quartos = new ArrayList<>();
        hospedes = new ArrayList<>();
        funcionarios = new ArrayList<>();
        numerosQuarto = new ArrayList<>();
        gerenciamentoReserva = new GerenciamentoReserva(quartos,hospedes);
        gerenciamentoHospede = new GerenciamentoHospede(hospedes);
        gerenciamentoFuncionario = new GerenciamentoFuncionario(funcionarios);
        gerenciamentoQuarto = new GerenciamentoQuarto(quartos);
        checkInRealizado = false;
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
        if (!gerenciamentoReserva.getReservas().isEmpty()) {
            System.out.println("\nInsira o numero do quarto que vai realizar o Check-In:");
            System.out.println("\nNumeros dos quartos:");
            numerosQuarto = gerenciamentoReserva.getNumeroQuartosReservados();
            for (int num:numerosQuarto) {
                System.out.printf("\nQuarto (Nº%d).", num);
            }
            System.out.println();
            boolean entradaValida = false;
            int numero = 0;
            while (!entradaValida) {
                if (!input.hasNextInt()) {
                    System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                    input.nextLine();
                    continue;
                }
                numero = input.nextInt();
                input.nextLine();
                entradaValida = true;
            }
            gerenciamentoReserva.ocuparQuarto(numero);
            checkInRealizado = true;
        }
        else {
            System.out.println("\nNão existem reservas para realizar Check-In");
        }
    }

    public void checkOut() {
        if (!gerenciamentoReserva.getReservas().isEmpty()) {
            System.out.println("\nInsira o numero do quarto que vai realizar o Check-Out:");
            System.out.println("\nNumeros dos quartos:");
            numerosQuarto = gerenciamentoReserva.getNumeroQuartosReservados();
            for (int num:numerosQuarto) {
                System.out.printf("\nQuarto (Nº%d).", num);
            }
            System.out.println();
            int numero = 0;
            boolean entradaValida = false;
            while (!entradaValida) {
                if (!input.hasNextInt()) {
                    System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                    input.nextLine();
                    continue;
                }
                numero = input.nextInt();
                input.nextLine();
                entradaValida = true;
            }
            gerenciamentoReserva.liberarQuarto(numero);
            if (numerosQuarto.size() == 1) {
                checkInRealizado = false;
            }
        }
        else  {
            System.out.println("\nNão existem reservas para realizar Check-Out");
        }
    }
}
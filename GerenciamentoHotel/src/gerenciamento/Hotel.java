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
    private List<Integer> numerosQuartosCheckIn;
    private List<Integer> numerosQuarto;
    private boolean checkInRealizado;
    private Scanner input = new Scanner(System.in);

    public Hotel() {
        quartos = new ArrayList<>();
        hospedes = new ArrayList<>();
        funcionarios = new ArrayList<>();
        numerosQuartosCheckIn = new ArrayList<>();
        numerosQuarto = new ArrayList<>();
        gerenciamentoReserva = new GerenciamentoReserva(quartos, hospedes);
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
            numerosQuartosCheckIn = gerenciamentoReserva.getNumeroQuartosReservadosCheckIn();
            if (!numerosQuartosCheckIn.isEmpty()) {
                boolean entradaValida = false;
                int numero = -1;
                while (!entradaValida) {
                    System.out.println("\nInsira o Número do Quarto que Deseja realizar o Check-In:");
                    System.out.println("\nNúmeros dos Quartos:");
                    for (int num : numerosQuartosCheckIn) {
                        System.out.printf("\nQuarto (Nº%d).", num);
                    }
                    System.out.println();
                    if (!input.hasNextInt()) {
                        System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                        input.nextLine();
                        continue;
                    }
                    numero = input.nextInt();
                    input.nextLine();
                    if (!numerosQuartosCheckIn.contains(numero)) {
                        System.out.println("\nNúmero de Quarto não existe!");
                        continue;
                    }
                    entradaValida = true;
                }
                gerenciamentoReserva.ocuparQuarto(numero);
                checkInRealizado = true;
            }
            else {
                System.out.println("\nNão Existem Reservas para Realizar Check-In");
            }
        }
        else {
            System.out.println("\nNão Existem Reservas para Realizar Check-In");
        }
    }

    //if (!gerenciamentoReserva.getReservas().isEmpty())
    public void checkOut() {
        if (checkInRealizado) {
            numerosQuarto = gerenciamentoReserva.getNumeroQuartosReservados();
            int numero = -1;
            boolean entradaValida = false;
            while (!entradaValida) {
                System.out.println("\nInsira o NÚmero do Quarto que vai realizar o Check-Out:");
                System.out.println("\nNumeros dos Quartos:");
                for (int num : numerosQuarto) {
                    System.out.printf("\nQuarto (Nº%d).", num);
                }
                System.out.println();
                if (!input.hasNextInt()) {
                    System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                    input.nextLine();
                    continue;
                }

                numero = input.nextInt();
                input.nextLine();
                if (!numerosQuarto.contains(numero)) {
                    System.out.println("\nNúmero de Quarto não existe!");
                    continue;
                }
                entradaValida = true;
            }
            if (numerosQuarto.size() == 1) {
                checkInRealizado = false;
            }
            gerenciamentoReserva.liberarQuarto(numero);

        }
        else if (gerenciamentoReserva.getReservas().isEmpty()) {
            System.out.println("\nNão existem reservas para realizar Check-Out");
        }
        else if (!checkInRealizado) {
            System.out.println("\nNão foi realizado nenhum Check-In");
        }
    }
}
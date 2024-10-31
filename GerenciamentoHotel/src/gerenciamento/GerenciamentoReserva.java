package gerenciamento;

import classesBase.Hospede;
import classesBase.Quarto;
import classesBase.Reserva;
import interfaces.GerenciamentoPadrao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoReserva implements GerenciamentoPadrao {
    private List<Reserva> reservas;
    private List<Quarto> quartos;
    private List<Hospede> hospedes;
    private List<Integer> numeroQuartosReservados;
    private int numeroDoQuarto;
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public GerenciamentoReserva(List<Quarto> quartos, List<Hospede> hospedes) {
        this.reservas = new ArrayList<>();
        this.numeroQuartosReservados = new ArrayList<>();
        this.quartos = quartos;
        this.hospedes = hospedes;
    }

    @Override
    public void cadastrar() {
        if (!hospedes.isEmpty()) {
            String cpf = "";
            ArrayList<String> cpfsValidos = new ArrayList<>();
            System.out.println("\nHospedes cadastrados:");
            for (Hospede hospede : hospedes) {
                System.out.println(hospede);
                cpfsValidos.add(hospede.getCpf());
            }
            boolean entradaValida = false;
            while (!entradaValida) {
                System.out.println("\nInsira o CPF do Hóspede que deseja Criar a Reserva:");
                cpf = scanner.nextLine();
                if (!cpfsValidos.contains(cpf)) {
                    System.out.println("\nInsira um CPF Válido!");
                    continue;
                }
                entradaValida = true;
            }

            Hospede hospede = buscarHospedePorCpf(cpf);
            String tipoQuarto = "";
            int numeroQuarto = 0;
            LocalDate dataEntrada = null;
            LocalDate dataSaida = null;
            do {
                System.out.println("\nDigite a data de entrada (DD/MM/AAAA):");
                dataEntrada = lerData();

                System.out.println("\nDigite a data de saída (DD/MM/AAAA):");
                dataSaida = lerData();

                boolean tipoValido = false;
                while (!tipoValido) {
                    System.out.println("\nEscolha o tipo de quarto:" +
                            "\n1) Solteiro" +
                            "\n2) Casal" +
                            "\n3) Suíte");

                    if (!scanner.hasNextInt()) {
                        scanner.nextLine();
                        System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                        continue;
                    }

                    int opcaoTipo = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcaoTipo) {
                        case 1 -> {
                            tipoQuarto = "Solteiro";
                            tipoValido = true;
                        }
                        case 2 -> {
                            tipoQuarto = "Casal";
                            tipoValido = true;
                        }
                        case 3 -> {
                            tipoQuarto = "Suíte";
                            tipoValido = true;
                        }
                        default -> System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2 ou 3.");
                    }
                }
                List<Integer> numerosDisponiveis = new ArrayList<>();

                for (Quarto quarto : quartos) {
                    if (quarto.getTipo().equals(tipoQuarto)) {
                        numerosDisponiveis.add(quarto.getNumero());
                    }
                }

                entradaValida = false;
                while (!entradaValida) {
                    for (int numero : numerosDisponiveis) {
                        System.out.printf("\nQuarto (Nº%d).", numero);
                    }
                    System.out.println("\nInsira o Número do Quarto de " + tipoQuarto + " que deseja reservar:");
                    if (!scanner.hasNextInt()) {
                        scanner.nextLine();
                        System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                    } else {
                        numeroQuarto = scanner.nextInt();
                        scanner.nextLine();
                        if (!numerosDisponiveis.contains(numeroQuarto)) {
                            System.out.println("\nNão existem Quartos com esse Número.");
                            continue;
                        }
                        entradaValida = true;
                    }
                }

                if (!quartoDisponivel(tipoQuarto, numeroQuarto, dataEntrada, dataSaida)) {
                    System.out.println("\nQuarto não está Disponivel nessa Data, Troque o Quarto ou Escolha outra Data.");
                }
            } while (!quartoDisponivel(tipoQuarto, numeroQuarto, dataEntrada, dataSaida));

            boolean valido = false;
            int quantidadeHospedes = 0;
            while (!valido) {
                System.out.println("\nDigite a Quantidade de Hóspedes:");
                if (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                } else {
                    quantidadeHospedes = scanner.nextInt();
                    scanner.nextLine();
                    valido = true;
                }
            }

            
            numeroQuartosReservados.add(numeroQuarto);
            Reserva reserva = new Reserva(dataEntrada, dataSaida, tipoQuarto, quantidadeHospedes, cpf, numeroQuarto);
            reservas.add(reserva);
            for (Hospede hospede1:hospedes) {
                if (hospede1.getCpf().equals(cpf)) {
                    hospede1.setReserva(reserva);
                }
            }
            System.out.println("\nReserva Criada com Sucesso para o Hóspede: " + hospede.getNome());
        }
        else {
            System.out.println("\nNenhum Hóspede Cadastrado para Criar Reserva.");
        }
    }

    @Override
    public void editar() {
        if (!hospedes.isEmpty() || !reservas.isEmpty()) {
            String cpf = "";
            ArrayList<String> cpfsValidos = new ArrayList<>();
            System.out.println("\nHospedes cadastrados:");
            for (Hospede hospede : hospedes) {
                System.out.println(hospede);
                cpfsValidos.add(hospede.getCpf());
            }
            boolean entradaValida = false;
            while (!entradaValida) {
                System.out.println("\nInsira o CPF do Hóspede que deseja Cancelar a Reserva:");
                cpf = scanner.nextLine();
                if (!cpfsValidos.contains(cpf)) {
                    System.out.println("\nInsira um CPF Válido!");
                    continue;
                }
                entradaValida = true;
            }

            for (Reserva reserva : reservas) {
                if (reserva.getHospedeCpf().equals(cpf)) {
                    reservas.remove(reserva);
                    System.out.println("\nReserva Cancelada com Sucesso!");
                    break;
                }
            }
            System.out.println("\nHóspede não Possui nenhuma Reserva para Cancelar.");
        }
        else if (hospedes.isEmpty()){
            System.out.println("\nNenhum Hóspede Cadastrado para Cancelar Reserva.");
        }
        else if (reservas.isEmpty()) {
            System.out.println("\nNenhuma Reserva foi feita para ser Cancelada.");
        }
    }

    @Override
    public void visualizar() {
        if (!quartos.isEmpty()) {
            System.out.println("\nDigite a data de entrada (DD/MM/AAAA):");
            LocalDate dataEntrada = lerData();

            System.out.println("\nDigite a data de saída (DD/MM/AAAA):");
            LocalDate dataSaida = lerData();

            String tipoQuarto = "";
            boolean tipoValido = false;
            while (!tipoValido) {
                System.out.println("\nEscolha o tipo de quarto:" +
                        "\n1) Solteiro" +
                        "\n2) Casal" +
                        "\n3) Suíte");

                if (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                    continue;
                }

                int opcaoTipo = scanner.nextInt();
                scanner.nextLine();

                switch (opcaoTipo) {
                    case 1 -> {
                        tipoQuarto = "Solteiro";
                        tipoValido = true;
                    }
                    case 2 -> {
                        tipoQuarto = "Casal";
                        tipoValido = true;
                    }
                    case 3 -> {
                        tipoQuarto = "Suíte";
                        tipoValido = true;
                    }
                    default -> System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2 ou 3.");
                }
            }

            List<Integer> numerosDisponiveis = new ArrayList<>();

            for (Quarto quarto : quartos) {
                if (quarto.getTipo().equals(tipoQuarto)) {
                    numerosDisponiveis.add(quarto.getNumero());
                }
            }
            int numeroQuarto = 0;
            boolean entradaValida = false;
            while (!entradaValida) {
                for (int numero : numerosDisponiveis) {
                    System.out.printf("\nQuarto (Nº%d).", numero);
                }
                System.out.println("\nInsira o Número do Quarto de " + tipoQuarto + ":");
                if (!scanner.hasNextInt()) {
                    scanner.nextLine();
                    System.out.println("\\nEntrada Inválida! Insira um Número Inteiro.");
                } else {
                    numeroQuarto = scanner.nextInt();
                    scanner.nextLine();
                    if (!numerosDisponiveis.contains(numeroQuarto)) {
                        System.out.println("\nNão Existem Quartos com esse Número.");
                        continue;
                    }
                    entradaValida = true;
                }
            }
            if (reservas.isEmpty()) {
            	System.out.println("\nQuarto Disponível para Reserva");
            }
            else if (quartoDisponivel(tipoQuarto, numeroQuarto, dataEntrada, dataSaida)) {
                System.out.println("\nQuarto Disponível para Reserva.");
            } else {
                System.out.println("\nNenhum Quarto Disponível para o Tipo e Datas selecionados.");
            }
        }
        else {
            System.out.println("\nNenhuma Reserva foi Criada até o Momento.");
        }
    }

    public void liberarQuarto(int numeroQuarto) {
        for (int i = 0; i < quartos.size(); i++) {
            if (quartos.get(i).getNumero() == numeroQuarto) {
                quartos.get(i).setDisponivel(true);
                break;
            }
        }
        for (Reserva reserva:reservas) {
            if (reserva.getNumeroQuarto() == numeroQuarto) {
                for (Hospede hospede:hospedes) {
                    if (reserva.getHospedeCpf().equals(hospede.getCpf())) {
                        hospede.setHistorico(reserva);
                        hospede.retiraReserva();
                        break;
                    }
                }
                this.numeroQuartosReservados.remove(Integer.valueOf(numeroQuarto));
                reservas.remove(reserva);
                System.out.println("\nCheck-Out Realizado com Sucesso!");
                break;
            }
        }
    }

    public void ocuparQuarto(int numeroQuarto) {
        for (int i = 0; i < quartos.size(); i++) {
            if (quartos.get(i).getNumero() == numeroQuarto) {
                quartos.get(i).setDisponivel(false);
                break;
            }
        }

        for (Reserva reserva:reservas) {
            if (reserva.getNumeroQuarto() == numeroQuarto) {
                for (Hospede hospede:hospedes) {
                    if (reserva.getHospedeCpf().equals(hospede.getCpf())) {
                        hospede.setReserva(reserva);
                        break;
                    }
                }
                reservas.remove(reserva);
                System.out.println("\nCheck-In Realizado com Sucesso!");
                break;
            }
        }
    }

    public int getNumeroDoQuarto() {
        return numeroDoQuarto;
    }

    public List<Integer> getNumeroQuartosReservados() {
        return numeroQuartosReservados;
    }

    public Reserva getReserva (int numeroQuarto)  {
        Reserva vazia = null;
        for (Reserva reserva:reservas) {
            if (reserva.getNumeroQuarto()==numeroQuarto) {
                return reserva;
            }
        }
        return vazia;
    }

    public List<Reserva>getReservas() {
        return this.reservas;
    }

    private LocalDate lerData() {
        LocalDate data = null;
        boolean dataValida = false;
        while (!dataValida) {
            try {
                String entrada = scanner.nextLine();
                data = LocalDate.parse(entrada, formatter);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("\nFormato de data inválido. Tente novamente (DD/MM/AAAA) digite a barra também (/):");
            }
        }
        return data;
    }

    private Hospede buscarHospedePorCpf(String cpf) {
        for (Hospede hospede : hospedes) {
            if (hospede.getCpf().equals(cpf)) {
                return hospede;
            }
        }
        return null;
    }

    private boolean quartoDisponivel(String tipoQuarto, int numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numeroQuarto) {
                if (!quarto.isDisponivel()) {
                    return false;
                }
            }
        }
        if (!reservas.isEmpty()) {
            for (Reserva reserva : reservas) {
                if ((reserva.getEntrada().equals(dataEntrada)) && (reserva.getSaida().equals(dataSaida)) && (reserva.getTipoDoQuarto().equals(tipoQuarto))) {
                    if (reserva.getNumeroQuarto() == numeroQuarto) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
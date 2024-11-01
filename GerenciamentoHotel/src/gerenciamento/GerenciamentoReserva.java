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
    private List<Integer> numeroQuartosReservadosCheckOut;
    private List<Integer> numeroQuartosReservadosCheckIn;
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public GerenciamentoReserva(List<Quarto> quartos, List<Hospede> hospedes) {
        this.reservas = new ArrayList<>();
        this.numeroQuartosReservadosCheckOut = new ArrayList<>();
        this.numeroQuartosReservadosCheckIn = new ArrayList<>();
        this.quartos = quartos;
        this.hospedes = hospedes;
    }

    @Override
    public void cadastrar() {
        if (!hospedes.isEmpty() && !quartos.isEmpty()) {
            String cpf = "";
            ArrayList<String> cpfsValidos = new ArrayList<>();
            boolean podeCadastrar = false;
            for (Hospede hospede : hospedes) {
                if (hospede.getReserva() == null) {
                    podeCadastrar = true;
                }
                if (podeCadastrar) {
                    System.out.println("\nHóspedes que Podem Realizar Reservas:");
                }
                System.out.println(hospede);
                cpfsValidos.add(hospede.getCpf());
            }
            if (podeCadastrar) {
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
                            }
                            case 2 -> {
                                tipoQuarto = "Casal";
                            }
                            case 3 -> {
                                tipoQuarto = "Suíte";
                            }
                            default -> System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2 ou 3.");
                        }

                        boolean existe = false;
                        for (Quarto quarto : quartos) {
                            if (quarto.getTipo().equals(tipoQuarto)) {
                                existe = true;
                                break;
                            }
                        }
                        if (!existe) {
                            System.out.println("\nNão existem quartos desse tipo cadastrados! Escolha outro tipo de quarto.");
                            continue;
                        }
                        tipoValido = true;

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

                numeroQuartosReservadosCheckIn.add(numeroQuarto);
                Reserva reserva = new Reserva(dataEntrada, dataSaida, tipoQuarto, quantidadeHospedes, cpf, numeroQuarto);
                reservas.add(reserva);
                for (int i = 0; i < hospedes.size(); i++) {
                    if (hospedes.get(i).getCpf().equals(cpf)) {
                        hospedes.get(i).setReserva(reserva);
                        break;
                    }
                }
                System.out.println("\nReserva Criada com Sucesso para o Hóspede: " + hospede.getNome());
            }
            else {
                System.out.println("Todos os Hóspedes já Possuem Reservas.");
            }
        }
        else if (hospedes.isEmpty()) {
            System.out.println("\nNenhum Hóspede Cadastrado para Criar Reserva.");
        } else if (quartos.isEmpty()) {
            System.out.println("\nNenhum Quarto Cadastrado para Criar Reserva.");
        }
    }

    @Override
    public void editar() {
        if (!hospedes.isEmpty() || !reservas.isEmpty()) {
            String cpf = "";
            int numeroQuarto = 0;
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
            boolean cancelada = false;
            for (Reserva reserva : reservas) {
                if (reserva.getHospedeCpf().equals(cpf)) {
                    if (!reserva.isCheckInRealizado()) {
                        numeroQuarto = reserva.getNumeroQuarto();
                        this.numeroQuartosReservadosCheckIn.remove(Integer.valueOf(numeroQuarto));
                        reservas.remove(reserva);
                        System.out.println("\nReserva Cancelada com Sucesso!");
                        for (int i = 0; i < hospedes.size(); i++) {
                            if (hospedes.get(i).getCpf().equals(cpf)) {
                                hospedes.get(i).retiraReserva();
                                break;
                            }
                        }
                        cancelada = true;
                        break;
                    }
                }
            }
            if (!cancelada) {
                System.out.println("\nHóspede não Possui nenhuma Reserva para Cancelar.");
            }
        } else if (hospedes.isEmpty()) {
            System.out.println("\nNenhum Hóspede Cadastrado para Cancelar Reserva.");
        } else if (reservas.isEmpty()) {
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
                    }
                    case 2 -> {
                        tipoQuarto = "Casal";
                    }
                    case 3 -> {
                        tipoQuarto = "Suíte";
                    }
                    default -> System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2 ou 3.");
                }
                boolean existe = false;
                for (Quarto quarto : quartos) {
                    if (quarto.getTipo().equals(tipoQuarto)) {
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    System.out.println("\nNão existem Quartos desse tipo Cadastrados! Escolha outro tipo de Quarto.");
                    continue;
                }
                tipoValido = true;
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
                    System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
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
            } else if (quartoDisponivel(tipoQuarto, numeroQuarto, dataEntrada, dataSaida)) {
                System.out.println("\nQuarto Disponível para Reserva.");
            } else {
                System.out.println("\nNenhum Quarto Disponível para o Tipo e Datas selecionados.");
            }
        } else {
            System.out.println("\nNenhum Quarto foi Criado até o Momento para ser Reservado.");
        }
    }

    private void liberarQuarto(int numeroQuarto) {
        for (int i = 0; i < quartos.size(); i++) {
            if (quartos.get(i).getNumero() == numeroQuarto) {
                quartos.get(i).setDisponivel(true);
                break;
            }
        }
        for (Reserva reserva : reservas) {
            if (reserva.getNumeroQuarto() == numeroQuarto) {
                for (int i = 0;i<hospedes.size();i++) {
                    if (reserva.getHospedeCpf().equals(hospedes.get(i).getCpf())) {
                        hospedes.get(i).setHistorico(reserva);
                        hospedes.get(i).retiraReserva();
                        break;
                    }
                }
                this.numeroQuartosReservadosCheckOut.remove(Integer.valueOf(numeroQuarto));
                reserva.setCheckInRealizado(false);
                reservas.remove(reserva);
                System.out.println("\nCheck-Out Realizado com Sucesso!");
                break;
            }
        }
    }

    private void ocuparQuarto(int numeroQuarto) {
        for (int i = 0; i < quartos.size(); i++) {
            if (quartos.get(i).getNumero() == numeroQuarto) {
                quartos.get(i).setDisponivel(false);
                break;
            }
        }

        for (int i = 0;i < reservas.size();i++) {
            if (reservas.get(i).getNumeroQuarto() == numeroQuarto) {
                for (int j = 0;j < hospedes.size();j++) {
                    if (reservas.get(i).getHospedeCpf().equals(hospedes.get(j).getCpf())) {
                        hospedes.get(j).getReserva().setCheckInRealizado(true);
                        hospedes.get(j).setReserva(reservas.get(i));
                        break;
                    }
                }
                reservas.get(i).setCheckInRealizado(true);
                this.numeroQuartosReservadosCheckIn.remove(Integer.valueOf(numeroQuarto));
                System.out.println("\nCheck-In Realizado com Sucesso!");
                break;
            }
        }
    }

    public void checkIn() {
        if (!numeroQuartosReservadosCheckIn.isEmpty()) {
            for (Hospede hospede : hospedes) {
                if (hospede.getReserva() != null) {
                    if (!hospede.getReserva().isCheckInRealizado()) {
                        System.out.println("Hóspede:");
                        System.out.println(hospede);
                        System.out.println("Reserva do Hóspede " + hospede.getNome() + ":");
                        System.out.println(hospede.getReserva());
                    }
                }
            }
            boolean entradaValida = false;
            int numero = -1;
            while (!entradaValida) {
                System.out.println("\nInsira o Número do Quarto que Deseja realizar o Check-In:");
                System.out.println("\nNúmeros dos Quartos:");
                for (int num : numeroQuartosReservadosCheckIn) {
                    System.out.printf("\nQuarto (Nº%d).", num);
                }
                System.out.println();
                if (!scanner.hasNextInt()) {
                    System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                    scanner.nextLine();
                    continue;
                }
                numero = scanner.nextInt();
                scanner.nextLine();
                if (!numeroQuartosReservadosCheckIn.contains(numero)) {
                    System.out.println("\nNúmero de Quarto não existe!");
                    continue;
                }
                entradaValida = true;
            }
            ocuparQuarto(numero);
            numeroQuartosReservadosCheckOut.add(numero);
        } else {
            System.out.println("\nNão Existem Reservas para Realizar Check-In");
        }
    }

    public void checkOut() {
        if (!numeroQuartosReservadosCheckOut.isEmpty()) {
            for (Hospede hospede : hospedes) {
                if (hospede.getReserva().isCheckInRealizado()) {
                    System.out.println(hospede);
                    System.out.println();
                    System.out.println("Reserva do Hóspede:");
                    System.out.println(hospede.getReserva());
                    System.out.println();
                }
            }
            int numero = -1;
            boolean entradaValida = false;
            while (!entradaValida) {
                System.out.println("\nInsira o Número do Quarto que vai realizar o Check-Out:");
                System.out.println("\nNúmeros dos Quartos:");
                for (int num : numeroQuartosReservadosCheckOut) {
                    System.out.printf("\nQuarto (Nº%d).", num);
                }
                System.out.println();
                if (!scanner.hasNextInt()) {
                    System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                    scanner.nextLine();
                    continue;
                }

                numero = scanner.nextInt();
                scanner.nextLine();
                if (!numeroQuartosReservadosCheckOut.contains(numero)) {
                    System.out.println("\nNúmero de Quarto não existe!");
                    continue;
                }
                entradaValida = true;
            }
            liberarQuarto(numero);

        } else if (reservas.isEmpty()) {
            System.out.println("\nNão existem reservas para realizar Check-Out");
        } else {
            System.out.println("\nNão foi realizado nenhum Check-In");
        }
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
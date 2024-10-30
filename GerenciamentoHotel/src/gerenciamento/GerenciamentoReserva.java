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
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public GerenciamentoReserva(List<Quarto> quartos, List<Hospede> hospedes) {
        this.reservas = new ArrayList<>();
        this.quartos = quartos;
        this.hospedes = hospedes;
    }

    @Override
    public void cadastrar() {
        try {
            System.out.println("\nDigite o CPF do hóspede:");
            String cpf = scanner.nextLine();
            Hospede hospede = buscarHospedePorCpf(cpf);

            if (hospede == null) {
                System.out.println("\nHóspede não encontrado.");
                return;
            }

            System.out.println("\nDigite a data de entrada (DD/MM/AAAA):");
            LocalDate dataEntrada = lerData();

            System.out.println("\nDigite a data de saída (DD/MM/AAAA):");
            LocalDate dataSaida = lerData();

            String tipoQuarto = "";
            boolean tipoValido = false;
            while (!tipoValido) {
            	System.out.println("Escolha o tipo de quarto:" +
                        "\n1) Solteiro" +
                        "\n2) Casal" +
                        "\n3) Suíte");

                int opcaoTipo = scanner.nextInt();
                scanner.nextLine();  

                switch (opcaoTipo) {
                    case 1 -> { tipoQuarto = "Solteiro"; tipoValido = true; }
                    case 2 -> { tipoQuarto = "Casal"; tipoValido = true; }
                    case 3 -> { tipoQuarto = "Suíte"; tipoValido = true; }
                    default -> System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2 ou 3.");
                }
            }

            Quarto quartoDisponivel = buscarQuartoDisponivel(tipoQuarto, dataEntrada, dataSaida);
            if (quartoDisponivel == null) {
                System.out.println("\nNenhum quarto disponível para o tipo e datas selecionados.");
                return;
            }

            System.out.println("\nDigite a quantidade de hóspedes:");
            int quantidadeHospedes = scanner.nextInt();
            scanner.nextLine();

            Reserva reserva = new Reserva(dataEntrada, dataSaida, tipoQuarto, quantidadeHospedes,cpf);
            reservas.add(reserva);
            quartoDisponivel.setDisponivel(false);

            System.out.println("\nReserva criada com sucesso para o hóspede: " + hospede.getNome());

        } catch (Exception e) {
            System.out.println("\nErro ao criar reserva: " + e.getMessage());
        }
    }

    @Override
    public void editar() {
        System.out.println("\nDigite o CPF do hóspede para cancelar a reserva:");
        String cpf = scanner.nextLine();
        Hospede hospede = buscarHospedePorCpf(cpf);

        if (hospede == null) {
            System.out.println("\nHóspede não encontrado.");
            return;
        }

        reservas.removeIf(reserva -> {
            if (reserva.getHospedeCpf().equals(hospede.getCpf())) {
                liberarQuarto(reserva.getTipoDoQuarto());
                System.out.println("\nReserva cancelada com sucesso.");
                return true;
            }
            return false;
        });
    }

    @Override
    public void visualizar() {
        System.out.println("\nDigite a data de entrada (DD/MM/AAAA):");
        LocalDate dataEntrada = lerData();

        System.out.println("\nDigite a data de saída (DD/MM/AAAA):");
        LocalDate dataSaida = lerData();

        System.out.println("\nSelecione o tipo de quarto (Solteiro, Casal, Suíte):");
        String tipoQuarto = scanner.nextLine();

        Quarto quartoDisponivel = buscarQuartoDisponivel(tipoQuarto, dataEntrada, dataSaida);
        if (quartoDisponivel == null) {
            System.out.println("\nNenhum quarto disponível para o tipo e datas selecionados.");
        } else {
            System.out.println("\nQuarto disponível para reserva: " + quartoDisponivel);
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
                System.out.println("\nFormato de data inválido. Tente novamente (DD/MM/AAAA):");
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

    private Quarto buscarQuartoDisponivel(String tipoQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
        for (Quarto quarto : quartos) {
            if (quarto.getTipo().equals(tipoQuarto) && quarto.isDisponivel()) {
                return quarto;
            }
        }
        return null;
    }

    private void liberarQuarto(String tipoQuarto) {
        for (Quarto quarto : quartos) {
            if (quarto.getTipo().equals(tipoQuarto)) {
                quarto.setDisponivel(true);
                break;
            }
        }
    }
}

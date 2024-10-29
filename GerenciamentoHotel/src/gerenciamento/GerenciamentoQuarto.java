package gerenciamento;

import classesBase.Quarto;
import interfaces.GerenciamentoPadrao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoQuarto implements GerenciamentoPadrao {

    private List<Quarto> quartos;

    public GerenciamentoQuarto() {
        this.quartos = new ArrayList<>();
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    public void cadastrar() {
        System.out.println("\nInsira o número do quarto:");
        int numero = scanner.nextInt();
        scanner.nextLine();  
        if (existeQuarto(numero)) {
            System.out.println("\nEsse número de quarto já está cadastrado. Tente novamente com um número diferente.");
            return;
        }

        String tipo = "";
        boolean tipoValido = false;
        while (!tipoValido) {
            System.out.println("Escolha o tipo de quarto:" +
                    "\n1) Solteiro" +
                    "\n2) Casal" +
                    "\n3) Suíte");

            int opcaoTipo = scanner.nextInt();
            scanner.nextLine();  

            switch (opcaoTipo) {
                case 1 -> {
                    tipo = "Solteiro";
                    tipoValido = true;
                }
                case 2 -> {
                    tipo = "Casal";
                    tipoValido = true;
                }
                case 3 -> {
                    tipo = "Suíte";
                    tipoValido = true;
                }
                default -> System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2 ou 3.");
            }
        }

        System.out.println("\nInsira a capacidade do quarto (número de hóspedes):");
        int capacidade = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            String capacidadeInput = scanner.nextLine();
            if (capacidadeInput.isEmpty()) {
                System.out.println("\nImpossível cadastrar quarto. Capacidade do quarto não foi informada.");
            } else {
                try {
                    capacidade = Integer.parseInt(capacidadeInput);
                    if (capacidade <= 0) {
                        System.out.println("\nCapacidade do quarto deve ser um número positivo.");
                    } else {
                        entradaValida = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida. Insira um número inteiro para a capacidade do quarto.");
                }
            }
        }

        System.out.println("\nInsira o preço da diária do quarto:");
        double precoDiaria = 0;
        entradaValida = false;

        while (!entradaValida) {
            String precoDiariaInput = scanner.nextLine();
            if (precoDiariaInput.isEmpty()) {
                System.out.println("\nImpossível cadastrar quarto. Preço da diária não foi informado.");
            } else {
                try {
                    precoDiaria = Double.parseDouble(precoDiariaInput);
                    if (precoDiaria <= 0) {
                        System.out.println("\nO preço da diária deve ser um valor positivo.");
                    } else {
                        entradaValida = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida. Insira um número válido para o preço da diária.");
                }
            }
        }
        }
    
    private boolean existeQuarto(int numero) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numero) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void visualizar() {
        System.out.println("\nQuartos disponíveis para reserva:");
        for (Quarto quarto : quartos) {
            if (quarto.isDisponivel()) {
                System.out.println(quarto);
            }
        }
    }

    @Override
    public void editar() {
    	System.out.println("\nQuartos Cadastrados:");
    	for (Quarto quarto : quartos) {
            if (quarto.isDisponivel()) {
                System.out.println(quarto);
            }
        }
        System.out.println("\nInsira o número do quarto que deseja alterar o status:");
        int numero = scanner.nextInt();
        scanner.nextLine();  

        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numero) {
                System.out.println("Escolha o novo status do quarto:" +
                        "\n1) Disponível" +
                        "\n2) Ocupado" +
                        "\n3) Em manutenção");

                int opcao = scanner.nextInt();
                scanner.nextLine();  

                switch (opcao) {
                    case 1 -> quarto.atualizarStatus(true);
                    case 2, 3 -> quarto.atualizarStatus(false);
                    default -> System.out.println("\nOpção inválida. Tente novamente.");
                }

                System.out.println("\nStatus do quarto atualizado com sucesso.");
                return;
            }
        }
        System.out.println("\nQuarto não encontrado.");
    }
}
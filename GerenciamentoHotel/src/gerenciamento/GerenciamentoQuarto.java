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
        boolean entradaValida = false;
        int numeroQuarto = 0;
        while (!entradaValida) {
            System.out.println("\nInsira o número do quarto:");

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("\nEntrada inválida, insira um número inteiro.");
                continue;
            }

            numeroQuarto = scanner.nextInt();
            scanner.nextLine();
            if (existeQuarto(numeroQuarto)) {
                System.out.println("\nEsse número de quarto já está cadastrado. Tente novamente com um número diferente.");
            } else {entradaValida = true;}
        }

        String tipoQuarto = "";
        entradaValida = false;
        while (!entradaValida) {
            System.out.println("Escolha o tipo de quarto:" +
                    "\n1) Solteiro" +
                    "\n2) Casal" +
                    "\n3) Suíte");

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("\nEntrada inválida, insira um número inteiro.");
                continue;
            }

            int opcaoTipo = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoTipo) {
                case 1 -> {
                    tipoQuarto = "Solteiro";
                    entradaValida = true;
                }
                case 2 -> {
                    tipoQuarto = "Casal";
                    entradaValida = true;
                }
                case 3 -> {
                    tipoQuarto = "Suíte";
                    entradaValida = true;
                }
                default -> System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2 ou 3.");
            }
        }

        int capacidade = 0;
        entradaValida = false;

        while (!entradaValida) {
            System.out.println("\nInsira a capacidade do quarto (número de hóspedes):");
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

        double precoDiaria = 0;
        entradaValida = false;

        while (!entradaValida) {
            System.out.println("\nInsira o preço da diária do quarto:");
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

        Quarto quarto = new Quarto(numeroQuarto,tipoQuarto,capacidade,precoDiaria);
        quartos.add(quarto);
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
        ArrayList<Integer> numerosQuartos = new ArrayList<>();
    	System.out.println("\nQuartos Cadastrados:");
    	for (Quarto quarto : quartos) {
            System.out.println(quarto);
            numerosQuartos.add(quarto.getNumero());
        }

        boolean entradaValida = false;
        int numero = 0;
        while (!entradaValida) {
            System.out.println("\nInsira o número do quarto que deseja alterar o status:");

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("\nEntrada inválida, insira um número inteiro.");
            }else {
                numero = scanner.nextInt();
                scanner.nextLine();
                if (!numerosQuartos.contains(numero)) {
                    System.out.println("\nNúmero de quarto não existe!");
                    continue;
                }
                entradaValida = true;
            }

        }

        entradaValida = false;
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numero) {
                int opcao = 0;
                while (!entradaValida) {
                    System.out.println("Escolha o novo status do quarto:" +
                            "\n1) Disponível" +
                            "\n2) Ocupado" +
                            "\n3) Em manutenção");

                    if (!scanner.hasNextInt()) {
                        scanner.nextLine();
                        System.out.println("\nEntrada inválida, insira um número inteiro.");
                        continue;
                    }
                    opcao = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcao) {
                        case 1 -> {
                            quarto.atualizarStatus(true);
                            entradaValida = true;
                        }
                        case 2, 3 -> {
                            quarto.atualizarStatus(false);
                            entradaValida = true;
                        }
                        default -> {
                            System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2 ou 3.");
                        }
                    }
                }
                System.out.println("\nStatus do quarto atualizado com sucesso.");
                break;
            }
        }
    }
}
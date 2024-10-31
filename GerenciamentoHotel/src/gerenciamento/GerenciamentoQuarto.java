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
            System.out.println("\nInsira o Número do quarto:");

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                continue;
            }

            numeroQuarto = scanner.nextInt();
            scanner.nextLine();
            if (existeQuarto(numeroQuarto)) {
                System.out.println("\nEsse número de quarto já está Cadastrado! Tente novamente com um número diferente.");
            } else {entradaValida = true;}
        }

        String tipoQuarto = "";
        entradaValida = false;
        while (!entradaValida) {
            System.out.println("\nEscolha o tipo de quarto:" +
                    "\n1) Solteiro" +
                    "\n2) Casal" +
                    "\n3) Suíte");

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("\nEntrada inválida! Insira um Número Inteiro.");
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
                default -> System.out.println("\nOpção inválida. Por favor! escolha entre 1, 2 ou 3.");
            }
        }

        int capacidade = 0;
        entradaValida = false;

        while (!entradaValida) {
            System.out.println("\nInsira a capacidade do Quarto (número de hóspedes):");
            String capacidadeInput = scanner.nextLine();
            if (capacidadeInput.isEmpty()) {
                System.out.println("\nImpossível Cadastrar Quarto! Capacidade do quarto não foi informada.");
            } else {
                try {
                    capacidade = Integer.parseInt(capacidadeInput);
                    if (capacidade <= 0) {
                        System.out.println("\nCapacidade do Quarto deve ser um número Positivo.");
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
                        System.out.println("\nO preço da diária deve ser um Valor Positivo.");
                    } else {
                        entradaValida = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida. Insira um Número Válido para o Preço da Diária.");
                }
            }
        }

        Quarto quarto = new Quarto(numeroQuarto,tipoQuarto,capacidade,precoDiaria);
        quartos.add(quarto);
        System.out.println("\nQuarto Cadastrado com Sucesso!");
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
        boolean existeDisponivel = false;
        for (Quarto quarto : quartos) {
            if (quarto.isDisponivel()) {
                if (!existeDisponivel) {
                    System.out.println("\nQuartos Disponíveis:");
                    existeDisponivel = true;
                }
                System.out.println(quarto);
            }
        }
        if (!existeDisponivel) {
            System.out.println("\nNão Existem Quartos Disponíveis no Momento.");
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
            System.out.println("\nInsira o Número do Quarto que deseja alterar o Status:");

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("\nEntrada inválida! Insira um Número Inteiro.");
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
                    System.out.println("\nEscolha o novo Status do Quarto:" +
                            "\n1) Disponível" +
                            "\n2) Ocupado" +
                            "\n3) Em manutenção");

                    if (!scanner.hasNextInt()) {
                        scanner.nextLine();
                        System.out.println("\nEntrada inválida! Insira um Número Inteiro.");
                        continue;
                    }
                    opcao = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcao) {
                        case 1 -> {
                            quarto.setDisponivel(true);
                            entradaValida = true;
                        }
                        case 2, 3 -> {
                            quarto.setDisponivel(false);
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
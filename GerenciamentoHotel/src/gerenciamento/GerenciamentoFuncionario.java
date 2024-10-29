package gerenciamento;

import classesBase.Funcionario;
import interfaces.GerenciamentoPadrao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoFuncionario implements GerenciamentoPadrao {

    private List<Funcionario> funcionarios;

    public GerenciamentoFuncionario() {
        this.funcionarios = new ArrayList<>();
    }

    Scanner input = new Scanner(System.in);

    @Override
    public void cadastrar() {
        System.out.println("\nInsira o nome do funcionário:");
        String nome = input.nextLine();

        System.out.println("\nInsira o cpf do funcionário;");
        String cpf = input.nextLine();

        int opcao = 0;
        boolean entradaValida = false;
        String cargo = "";
        while (!entradaValida) {
            System.out.println("\nDigite o número referente ao cargo do funcionário:" +
                    "\n1) Gerente." +
                    "\n2) Chefia." +
                    "\n3) Assistente." +
                    "\n4) Auxiliar.");

            if (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("\nEntrada inválida, insira um número inteiro.");
                continue;
            }
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1 -> {
                    cargo = "Gerente";
                    entradaValida = true;
                }
                case 2 -> {
                    cargo = "Chefia";
                    entradaValida = true;
                }
                case 3 -> {
                    cargo = "Assistente";
                    entradaValida = true;
                }

                case 4 -> {
                    cargo = "Auxiliar";
                    entradaValida = true;
                }

                default -> {
                    System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2, 3 ou 4.");
                }
            }
        }

        entradaValida = false;
        double salarioHora = 0.0;
        while (!entradaValida) {
            System.out.println("\nInsira o salário por hora do funcionário:");
            if (!input.hasNextDouble()) {
                System.out.println("\nEntrada inválida, insira um número inteiro.");
                input.nextLine();
                continue;
            }
            salarioHora = input.nextDouble();
            input.nextLine();
            entradaValida = true;
        }

        entradaValida = false;
        int horasDeTrabalho = 0;
        while (!entradaValida) {
            System.out.println("\nInsira a quantidade de horas de trabalho por mês do funcionário:");

            if (!input.hasNextInt()) {
                System.out.println("\nEntrada inválida, insira um número inteiro.");
                input.nextLine();
                continue;
            }
            horasDeTrabalho = input.nextInt();
            input.nextLine();
            entradaValida = true;
        }

        double salario = (horasDeTrabalho * salarioHora);

        Funcionario funcionario = new Funcionario(cpf,nome,cargo,horasDeTrabalho,salario);
        funcionarios.add(funcionario);
    }

    @Override
    public void editar() {
        ArrayList<String>cpfsValidos = new ArrayList<>();
        System.out.println("\nFuncionários cadastrados:");
        for (Funcionario funcionario:funcionarios) {
            System.out.println(funcionario);
            cpfsValidos.add(funcionario.getCpf());
        }

        boolean entradaValida = false;
        String cpf = "";
        while (!entradaValida) {
            System.out.println("\nInsira o cpf do funcionario que deseja alterar as informações:");
            cpf = input.nextLine();
            if (!cpfsValidos.contains(cpf)) {
                System.out.println("\nInsira um cpf válido.");
                continue;
            }
            entradaValida = true;
        }
        for (int i = 0;i < funcionarios.size();i++) {
            if (funcionarios.get(i).getCpf().equals(cpf)) {
                int opcao = 0;
                while (opcao!= 6) {
                    System.out.println("\nDigite o número referente a opção que deseja realizar:" +
                            "\n1) Alterar nome." +
                            "\n2) Alterar cpf." +
                            "\n3) Alterar cargo." +
                            "\n4) Alterar salário por hora." +
                            "\n5) Alterar horas trabalhadas por mês." +
                            "\n6) Sair do menu de alteração de dados.");

                    if (!input.hasNextInt()) {
                        System.out.println("\nEntrada inválida, insira um número inteiro.");
                        input.nextLine();
                        continue;
                    }

                    opcao = input.nextInt();
                    input.nextLine();

                    if (opcao < 0 || opcao > 6) {
                        System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2, 3, 4, 5 ou 6.");
                    }

                    else if (opcao == 1) {
                        System.out.println("\nInsira o novo nome do funcionário:");
                        String novoNome = input.nextLine();
                        funcionarios.get(i).setNome(novoNome);
                    }

                    else if (opcao == 2) {
                        System.out.println("\nInsira o cpf do funcionário;");
                        String novoCpf = input.nextLine();
                        funcionarios.get(i).setCpf(novoCpf);

                    }

                    else if (opcao == 3) {
                        int novaOpcao = 0;
                        boolean novaEntradaValida = false;
                        String novoCargo = "";
                        while (!novaEntradaValida) {
                            System.out.println("\nDigite o número referente ao novo cargo do funcionário:" +
                                    "\n1) Gerente." +
                                    "\n2) Chefia." +
                                    "\n3) Assistente." +
                                    "\n4) Auxiliar.");
                            if (!input.hasNextInt()) {
                                input.nextLine();
                                System.out.println("\nEntrada inválida, insira um número inteiro.");
                                continue;
                            }
                            novaOpcao = input.nextInt();
                            input.nextLine();

                            switch (novaOpcao) {
                                case 1 -> {
                                    novoCargo = "Gerente";
                                    novaEntradaValida = true;
                                }
                                case 2 -> {
                                    novoCargo = "Chefia";
                                    novaEntradaValida = true;
                                }
                                case 3 -> {
                                    novoCargo = "Assistente";
                                    novaEntradaValida = true;
                                }

                                case 4 -> {
                                    novoCargo = "Auxiliar";
                                    novaEntradaValida = true;
                                }

                                default -> {
                                    System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2, 3 ou 4.");
                                }
                            }
                        }
                        funcionarios.get(i).setCargo(novoCargo);
                    }

                    else if (opcao == 4) {
                        boolean novaEntradaValida = false;
                        double novoSalarioHora = 0.0;
                        while (!novaEntradaValida) {
                            System.out.println("\nInsira o novo salário por hora do funcionário:");
                            if (!input.hasNextDouble()) {
                                System.out.println("\nEntrada inválida, insira um número inteiro.");
                                input.nextLine();
                                continue;
                            }
                            novoSalarioHora = input.nextDouble();
                            input.nextLine();
                            novaEntradaValida = true;
                        }
                        double novoSalario = (novoSalarioHora * funcionarios.get(i).getHorasDeTrabalho());
                        funcionarios.get(i).setSalario(novoSalario);
                    }

                    else if (opcao == 5) {
                        boolean novaEntradaValida = false;
                        int novaHorasDeTrabalho = 0;
                        while (!novaEntradaValida) {
                            System.out.println("\nInsira a nova quantidade de horas de trabalho por mês do funcionário:");

                            if (!input.hasNextInt()) {
                                System.out.println("\nEntrada inválida, insira um número inteiro.");
                                input.nextLine();
                                continue;
                            }
                            novaHorasDeTrabalho = input.nextInt();
                            input.nextLine();
                            novaEntradaValida = true;
                        }
                        double salarioHora = (funcionarios.get(i).getSalario() / funcionarios.get(i).getHorasDeTrabalho());
                        double novoSalario = (salarioHora * novaHorasDeTrabalho);
                        funcionarios.get(i).setHorasDeTrabalho(novaHorasDeTrabalho);
                        funcionarios.get(i).setSalario(novoSalario);
                    }
                }
                break;
            }
        }
    }

    @Override
    public void visualizar() {
        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
        }
        else {
            for (Funcionario funcionario:funcionarios) {
                System.out.println(funcionario);
            }
        }
    }
}
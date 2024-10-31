package gerenciamento;

import classesBase.Funcionario;
import interfaces.GerenciamentoPadrao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoFuncionario implements GerenciamentoPadrao {

    private List<Funcionario> funcionarios;

    public GerenciamentoFuncionario(List<Funcionario>funcionarios) {
        this.funcionarios = funcionarios;
    }

    Scanner input = new Scanner(System.in);

    @Override
    public void cadastrar() {
        System.out.println("\nInsira o Nome do funcionário:");
        String nome = input.nextLine();

        String cpf;
        while (true) {
            System.out.println("\nInsira o CPF do funcionário (11 dígitos):");
            cpf = input.nextLine();
            if (cpf.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("\nPor favor, insira um CPF válido com 11 dígitos.");
            }
        }

        int opcao = 0;
        boolean entradaValida = false;
        String cargo = "";
        while (!entradaValida) {
            System.out.println("\nDigite o número referente ao cargo do funcionário:\n" +
                    "\n1) Gerente." +
                    "\n2) Chefia." +
                    "\n3) Assistente." +
                    "\n4) Auxiliar.");

            if (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("\\nEntrada Inválida! Insira um Número Inteiro.");
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
                System.out.println("\\nEntrada Inválida! Insira um Número Inteiro.");
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
            System.out.println("\nInsira a Quantidade de Horas de Trabalho por mês do funcionário:");

            if (!input.hasNextInt()) {
                System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
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
        System.out.println("\nFuncionário Cadastrado com Sucesso!");
    }

    @Override
    public void editar() {
        ArrayList<String>cpfsValidos = new ArrayList<>();
        System.out.println("\nFuncionários Cadastrados:");
        for (Funcionario funcionario:funcionarios) {
            System.out.println(funcionario);
            cpfsValidos.add(funcionario.getCpf());
        }

        boolean entradaValida = false;
        String cpf = "";
        while (!entradaValida) {
            System.out.println("\nInsira o CPF do Funcionário que Deseja alterar as Informações:");
            cpf = input.nextLine();
            if (!cpfsValidos.contains(cpf)) {
                System.out.println("\nInsira um CPF Válido!");
                continue;
            }
            entradaValida = true;
        }
        for (int i = 0;i < funcionarios.size();i++) {
            if (funcionarios.get(i).getCpf().equals(cpf)) {
                int opcao = 0;
                while (opcao!= 6) {
                    System.out.println("\nDigite o Número referente a Opção que deseja realizar:\n" +
                            "\n1) Alterar Nome" +
                            "\n2) Alterar CPF" +
                            "\n3) Alterar Cargo" +
                            "\n4) Alterar Salário por Hora" +
                            "\n5) Alterar Horas Trabalhadas por Mês" +
                            "\n6) Sair do Menu de Alteração de Dados");

                    if (!input.hasNextInt()) {
                        System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                        input.nextLine();
                        continue;
                    }

                    opcao = input.nextInt();
                    input.nextLine();

                    if (opcao < 0 || opcao > 6) {
                        System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2, 3, 4, 5 ou 6.");
                    }

                    else if (opcao == 1) {
                        System.out.println("\nInsira o novo Nome do funcionário:");
                        String novoNome = input.nextLine();
                        funcionarios.get(i).setNome(novoNome);
                        System.out.println("\nNome do Funcionário editado com Sucesso!");
                    }

                    else if (opcao == 2) {
                        System.out.println("\nInsira o novo CPF do funcionário;");
                        String novoCpf = input.nextLine();
                        funcionarios.get(i).setCpf(novoCpf);
                        System.out.println("\nCPF do funcionário editado com Sucesso!");

                    }

                    else if (opcao == 3) {
                        int novaOpcao = 0;
                        boolean novaEntradaValida = false;
                        String novoCargo = "";
                        while (!novaEntradaValida) {
                            System.out.println("\nDigite o Número referente ao Novo Cargo do funcionário:\n" +
                                    "\n1) Gerente" +
                                    "\n2) Chefia" +
                                    "\n3) Assistente" +
                                    "\n4) Auxiliar");
                            if (!input.hasNextInt()) {
                                input.nextLine();
                                System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
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
                        System.out.println("\nCargo do funcionário editado com Sucesso!");
                    }

                    else if (opcao == 4) {
                        boolean novaEntradaValida = false;
                        double novoSalarioHora = 0.0;
                        while (!novaEntradaValida) {
                            System.out.println("\nInsira o Novo Salário por Hora do funcionário:");
                            if (!input.hasNextDouble()) {
                                System.out.println("\nEntrada Inválida! Insira um Número Inteiro.");
                                input.nextLine();
                                continue;
                            }
                            novoSalarioHora = input.nextDouble();
                            input.nextLine();
                            novaEntradaValida = true;
                        }
                        double novoSalario = (novoSalarioHora * funcionarios.get(i).getHorasDeTrabalho());
                        funcionarios.get(i).setSalario(novoSalario);
                        System.out.println("\nSalário por hora do funcionário editado com Sucesso!");
                    }

                    else if (opcao == 5) {
                        boolean novaEntradaValida = false;
                        int novaHorasDeTrabalho = 0;
                        while (!novaEntradaValida) {
                            System.out.println("\nInsira a Nova Quantidade de horas de Trabalho por Mês do funcionário:");

                            if (!input.hasNextInt()) {
                                System.out.println("\nEntrada Inválida! Insira um Número inteiro.");
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
                        System.out.println("\nQuantidade de Horas de Trabalho do Funcionário editada com Sucesso!");
                    }
                }
                break;
            }
        }
    }

    @Override
    public void visualizar() {
        if (funcionarios.isEmpty()) {
            System.out.println("\nNenhum Funcionário Cadastrado.");
        }
        else {
            for (Funcionario funcionario:funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    public List<Funcionario> getFuncionarios () {
        return funcionarios;
    }
}
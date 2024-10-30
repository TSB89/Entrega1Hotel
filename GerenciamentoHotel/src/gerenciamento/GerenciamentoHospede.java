package gerenciamento;

import classesBase.Hospede;
import interfaces.GerenciamentoPadrao;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoHospede implements GerenciamentoPadrao {

    private List<Hospede> hospedes;

    public GerenciamentoHospede() {
        this.hospedes = new ArrayList<>();
    }

    Scanner input = new Scanner(System.in);
    DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void cadastrar() {
        System.out.println("\nInsira o Nome do Hóspede:");
        String nome = input.nextLine();

        String cpf;
        while (true) {
            System.out.println("\nInsira o CPF do Hóspede (11 dígitos):");
            cpf = input.nextLine();
            if (cpf.matches("\\d{11}")) {
                break;
            } else {
                System.out.println("\nPor favor, insira um CPF válido com 11 dígitos.");
            }
        }

        LocalDate dataNascimento = null;
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.println("\nInsira a Data de Nascimento do Hóspede (DD/MM/AAAA):");
                String data = input.nextLine();
                dataNascimento = LocalDate.parse(data, formatarData);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("\nInsira a Data de Nascimento de forma correta (DD/MM/AAAA)");
            }
        }

        System.out.println("\nInsira o Endereço do Hóspede (Rua):");
        String endereco = input.nextLine();
        if (endereco.equals("")) {
            System.out.println("\nImpossível cadastrar! Endereço do Hóspede não informado!");
            return;
        }

        System.out.println("\nInsira o Número de Contato do Hóspede:");
        String contato = input.nextLine();
        if (contato.equals("")) {
            System.out.println("\nImpossível cadastrar! Contato do Hóspede não informado.");
            return;
        }

        Hospede hospede = new Hospede(cpf, nome, contato, dataNascimento, endereco);
        hospedes.add(hospede);
        System.out.println("\nHóspede Cadastrado com Sucesso!");
    }

    @Override
    public void editar() {
        ArrayList<String> cpfsValidos = new ArrayList<>();
        System.out.println("\nHóspedes cadastrados:");
        for (Hospede hospede : hospedes) {
            System.out.println(hospede);
            cpfsValidos.add(hospede.getCpf());
        }
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.println("\nInsira o CPF do Hóspede que deseja alterar as informações:");
            String cpf = input.nextLine();
            if (!cpfsValidos.contains(cpf)) {
                System.out.println("\nInsira um CPF válido!");
                continue;
            }
            entradaValida = true;
            for (int i = 0; i < hospedes.size(); i++) {
                if (hospedes.get(i).getCpf().equals(cpf)) {
                    int opcao = 0;
                    while (opcao != 6) {
                        System.out.println("\nDigite o número referente a opção que deseja realizar:" +
                                "\n1) Alterar Nome." +
                                "\n2) Alterar CPF." +
                                "\n3) Alterar Data de Nascimento." +
                                "\n4) Alterar Endereço." +
                                "\n5) Alterar Contato." +
                                "\n6) Sair do Menu de Alteração de Dados.");

                        if (!input.hasNextInt()) {
                            input.nextLine();
                            System.out.println("\nEntrada inválida, insira um número inteiro!");
                            continue;
                        }

                        opcao = input.nextInt();
                        input.nextLine();

                        if (opcao < 1 || opcao > 6) {
                            System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2, 3, 4, 5 ou 6.");
                        } else if (opcao == 1) {
                            System.out.println("\nInsira o novo nome do Hóspede:");
                            String novoNome = input.nextLine();
                            hospedes.get(i).setNome(novoNome);
                        } else if (opcao == 2) {
                            String novoCpf;
                            while (true) {
                                System.out.println("\nInsira o novo CPF do Hóspede (11 dígitos):");
                                novoCpf = input.nextLine();
                                if (novoCpf.matches("\\d{11}")) {
                                    break;
                                } else {
                                    System.out.println("\nPor favor, insira um CPF válido com 11 dígitos.");
                                }
                            }
                            hospedes.get(i).setCpf(novoCpf);
                        } else if (opcao == 3) {
                            LocalDate novaDataNascimento = null;
                            boolean dataValida = false;
                            while (!dataValida) {
                                try {
                                    System.out.println("\nInsira a nova Data de Nascimento do Hóspede (DD/MM/AAAA):");
                                    String data = input.nextLine();
                                    novaDataNascimento = LocalDate.parse(data, formatarData);
                                    dataValida = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println("\nInsira a Data de Nascimento de forma correta (DD/MM/AAAA)");
                                }
                            }
                            hospedes.get(i).setDataDeNascimento(novaDataNascimento);
                        } else if (opcao == 4) {
                            System.out.println("\nInsira o Novo Endereço do Hóspede (Rua):");
                            String novoEndereco = input.nextLine();
                            hospedes.get(i).setEndereco(novoEndereco);
                        } else if (opcao == 5) {
                            System.out.println("\nInsira o Novo Número de contato do Hóspede:");
                            String novoContato = input.nextLine();
                            hospedes.get(i).setContato(novoContato);
                        }
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void visualizar() {
        System.out.println("\nEscolha uma opção:\n");
        System.out.println("1) Listar Todos os Hóspedes Cadastrados");
        System.out.println("2) Visualizar Hóspede Específico");

        int opcao = input.nextInt();
        input.nextLine(); 

        if (opcao == 1) {
            if (hospedes.isEmpty()) {
                System.out.println("Nenhum hóspede cadastrado.");
            } else {
                System.out.println("\nHóspedes Cadastrados:");
                for (Hospede hospede : hospedes) {
                    System.out.println(hospede);
                }
            }
        } else if (opcao == 2) {
        	System.out.println("\nDigite o CPF referente ao Hóspede que deseja visualizar:");
            String cpf = input.nextLine();
            if (cpf.length() != 11) {
                System.out.println("\nPor favor, insira um CPF válido com 11 dígitos.");
                return;
            }
            boolean hospedeEncontrado = false;
            for (Hospede hospede : hospedes) {
                if (hospede.getCpf().equals(cpf)) {
                    System.out.println(hospede);
                    hospedeEncontrado = true;
                    break;
                }
            }

            if (!hospedeEncontrado) {
                System.out.println("\nHóspede não encontrado.");
            }
        } else {
            System.out.println("\nOpção inválida. Por favor, escolha 1 ou 2.");
        }
    }

    public void visualizarHistorico() {

    }

    public void adicionarReserva() {

    }

    public void retirarReserva(){

    }
}
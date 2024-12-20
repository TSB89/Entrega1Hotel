package gerenciamento;

import classesBase.Hospede;
import classesBase.Reserva;
import interfaces.GerenciamentoPadrao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciamentoHospede implements GerenciamentoPadrao {

    private List<Hospede> hospedes;
    private Scanner input = new Scanner(System.in);

    public GerenciamentoHospede(List<Hospede>hospedes) {
        this.hospedes = hospedes;
    }

    DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void cadastrar() {
        String nome = "";
        while (true) {
            System.out.println("\nInsira o Nome do Hóspede:");
            nome = input.nextLine();
            if (nome.isEmpty()) {
                System.out.println("\nErro! Nome do Hóspede Não foi Informado.");
            }else {
                break;
            }
        }

        String cpf;
        while (true) {

            System.out.println("\nInsira o CPF do Hóspede (11 dígitos):");
            cpf = input.nextLine();
            if (cpf.matches("\\d{11}")) {
                if (cpfJaCadastrado(cpf)) {
                    System.out.println("\nCPF já cadastrado, insira outro CPF.");
                }
                else {
                    break;
                }
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
                dataNascimento = LocalDate.parse(data,formatarData);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("\nInsira a data de nascimento de forma correta (DD/MM/AAAA)");
            }
        }

        String endereco = "";
        while (endereco.isEmpty()) {
            System.out.println("\nInsira o Endereço do Hóspede (Rua):");
            endereco = input.nextLine();
            if (endereco.isEmpty()) {
                System.out.println("\nErro! Endereço do Hóspede Não foi Informado.");
            }
        }

        String contato = "";
        while (contato.isEmpty()) {
            System.out.println("\nInsira o Número de Contato do Hóspede:");
            contato = input.nextLine();
            if (contato.isEmpty()) {
                System.out.println("\nErro! Contato do Hóspede Não foi Informado.");
            }
        }

        Hospede hospede = new Hospede(cpf,nome,contato,dataNascimento,endereco);
        hospedes.add(hospede);
        System.out.println("\nHóspede Cadastrado com Sucesso!");
    }

    @Override
    public void editar() {
        if (!hospedes.isEmpty()) {
            ArrayList<String>cpfsValidos = new ArrayList<>();
            System.out.println("\nHóspedes cadastrados:");
            for (Hospede hospede:hospedes) {
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
                for (int i = 0;i < hospedes.size();i++) {
                    if (hospedes.get(i).getCpf().equals(cpf)) {
                        int opcao = 0;
                        while (opcao != 6) {
                            System.out.println("\nDigite o número referente a opção que deseja realizar:" +
                                    "\n1) Alterar Nome" +
                                    "\n2) Alterar CPF" +
                                    "\n3) Alterar Data de Nascimento" +
                                    "\n4) Alterar Endereço" +
                                    "\n5) Alterar Contato" +
                                    "\n6) Sair do Menu de Alteração de Dados");

                            if (!input.hasNextInt()) {
                                input.nextLine();
                                System.out.println("\nEntrada Inválida! Insira um Número Inteiro!");
                                continue;
                            }

                            opcao = input.nextInt();
                            input.nextLine();

                            if (opcao < 1 || opcao > 6) {
                                System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2, 3, 4, 5 ou 6.");
                            }

                            else if (opcao == 1) {
                                String novoNome = "";
                                while (true) {
                                    System.out.println("\nInsira o Novo Nome do Hóspede:");
                                    novoNome = input.nextLine();
                                    if (novoNome.isEmpty()) {
                                        System.out.println("\nErro! Nome do Hóspede Não foi Informado.");
                                    }else {
                                        break;
                                    }
                                }
                                hospedes.get(i).setNome(novoNome);
                                System.out.println("\nNome do Hóspede editado com Sucesso!");
                            }

                            else if (opcao == 2) {
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
                                System.out.println("\nCPF do Hóspede editado com Sucesso!");
                            }

                            else if (opcao == 3) {
                                LocalDate novaDataNascimento = null;
                                boolean dataValida = false;
                                while (!dataValida) {
                                    try {
                                        System.out.println("\nInsira a nova Data de Nascimento do Hóspede (DD/MM/AAAA):");
                                        String data = input.nextLine();
                                        novaDataNascimento = LocalDate.parse(data,formatarData);
                                        dataValida = true;
                                    } catch (DateTimeParseException e) {
                                        System.out.println("\nInsira a Data de Nascimento de forma correta (DD/MM/AAAA)");
                                    }
                                }
                                hospedes.get(i).setDataDeNascimento(novaDataNascimento);
                                System.out.println("\nData de Nascimento do Hóspede editado com Sucesso!");
                            }

                            else if (opcao == 4) {
                                String novoEndereco = "";
                                while (true) {
                                    System.out.println("\nInsira o Novo Endereço do Hóspede (Rua):");
                                    novoEndereco = input.nextLine();
                                    if (novoEndereco.isEmpty()) {
                                        System.out.println("\nErro! Endereço do Hóspede Não foi Informado.");
                                    }else {
                                        break;
                                    }
                                }
                                hospedes.get(i).setEndereco(novoEndereco);
                                System.out.println("\nEndereço do Hóspede editado com Sucesso!");
                            }

                            else if (opcao== 5) {
                                String novoContato = "";
                                while (true) {
                                    System.out.println("\nInsira o Novo Número de contato do Hóspede:");
                                    novoContato = input.nextLine();
                                    if (novoContato.isEmpty()) {
                                        System.out.println("\nErro! Número do Hóspede Não foi Informado.");
                                    }else {
                                        break;
                                    }
                                }
                                hospedes.get(i).setContato(novoContato);
                                System.out.println("\nNúmero do Hóspede editado com Sucesso!");
                            }
                        }
                        break;
                    }
                }
            }
        }
        else {
            System.out.println("\nNenhum Hóspede Cadastrado para ser Editado!");
        }
    }

    @Override
    public void visualizar() {
        if (!hospedes.isEmpty()) {
            System.out.println("\nHóspedes Cadastrados:");
            for (Hospede hospede:hospedes) {
                System.out.println(hospede);
            }
        }
        else {
            System.out.println("\nNenhum Hóspede Cadastrado!");
        }
    }

    public void visualizarHistorico() {
        if (!hospedes.isEmpty()) {
            ArrayList<String>cpfsValidos = new ArrayList<>();
            System.out.println("\nHóspedes Cadastrados:");
            for (Hospede hospede:hospedes) {
                System.out.println(hospede);
                cpfsValidos.add(hospede.getCpf());
            }
            boolean entradaValida = false;
            String cpf = "";
            while (!entradaValida) {
                System.out.println("\nInsira o CPF do Hóspede que deseja Visualizar o Histórico de Estadias:");
                cpf = input.nextLine();
                if (!cpfsValidos.contains(cpf)) {
                    System.out.println("\nInsira um CPF válido!");
                    continue;
                }
                entradaValida = true;
            }

            for (Hospede hospede:hospedes) {
                if (hospede.getCpf().equals(cpf)) {
                    if (hospede.getHistorico().isEmpty()) {
                        System.out.println("\nHóspede não realizou Nenhuma Estadia no Hotel até o Momento!");
                        break;
                    }
                    else {
                        List<Reserva> historico = hospede.getHistorico();
                        System.out.println("\nHistórico de estadia do hóspede:" + hospede.getNome());
                        System.out.println();
                        for (Reserva reserva:historico) {
                            System.out.println("Data de Check-In:" + reserva.getEntrada());
                            System.out.printf("Número do quarto: %d\n" +
                                              "Tipo do quarto: %s\n",reserva.getNumeroQuarto(),reserva.getTipoDoQuarto());
                            System.out.println("Data de Check-Out:" + reserva.getSaida() + "\n");
                        }
                        break;
                    }
                }
            }
        }
        else {
            System.out.println("\nNenhum Hóspede Cadastrado!");
        }
    }

    private boolean cpfJaCadastrado(String cpf) {
        if (!hospedes.isEmpty()) {
            for (Hospede hospede:hospedes) {
                if (hospede.getCpf().equals(cpf)) {
                    return true;
                }
            }
        }
        return false;
    }
}
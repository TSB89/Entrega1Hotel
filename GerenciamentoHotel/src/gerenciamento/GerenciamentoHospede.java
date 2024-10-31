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
        System.out.println("\nInsira o nome do hospede:");
        String nome = input.nextLine();

        System.out.println("\nInsira o CPF do hospede:");
        String cpf = input.nextLine();

        LocalDate dataNascimento = null;
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.println("\nInsira a data de nascimento do hospede (DD/MM/AAAA) digite a barra também (/)::");
                String data = input.nextLine();
                dataNascimento = LocalDate.parse(data,formatarData);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("\nInsira a data de nascimento de forma correta (DD/MM/AAAA)");
            }
        }

        System.out.println("\nInsira o endereço do hospede (rua):");
        String endereco = input.nextLine();

        System.out.println("\nInsira o número de contato do hospede:");
        String contato = input.nextLine();

        Hospede hospede = new Hospede(cpf,nome,contato,dataNascimento,endereco);
        hospedes.add(hospede);
    }

    @Override
    public void editar() {
        ArrayList<String>cpfsValidos = new ArrayList<>();
        System.out.println("\nHospedes cadastrados:");
        for (Hospede hospede:hospedes) {
            System.out.println(hospede);
            cpfsValidos.add(hospede.getCpf());
        }
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.println("\nInsira o cpf do hospede que deseja alterar as informações:");
            String cpf = input.nextLine();
            if (!cpfsValidos.contains(cpf)) {
                System.out.println("\nInsira um cpf válido.");
                continue;
            }
            entradaValida = true;
            for (int i = 0;i < hospedes.size();i++) {
                if (hospedes.get(i).getCpf().equals(cpf)) {
                    int opcao = 0;
                    while (opcao != 6) {
                        System.out.println("\nDigite o número referente a opção que deseja realizar:" +
                                "\n1) Alterar nome." +
                                "\n2) Alterar cpf." +
                                "\n3) Alterar data de nascimento." +
                                "\n4) Alterar endereço." +
                                "\n5) Alterar contato." +
                                "\n6) Sair do menu de alteração de dados.");

                        if (!input.hasNextInt()) {
                            input.nextLine();
                            System.out.println("\nEntrada inválida, insira um número inteiro.");
                            continue;
                        }

                        opcao = input.nextInt();
                        input.nextLine();

                        if (opcao < 1 || opcao > 6) {
                            System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2, 3, 4, 5 ou 6.");
                        }

                        else if (opcao == 1) {
                            System.out.println("\nInsira o novo nome do hospede:");
                            String novoNome = input.nextLine();
                            hospedes.get(i).setNome(novoNome);
                        }

                        else if (opcao == 2) {
                            System.out.println("\nInsira o novo CPF do hospede:");
                            String novoCpf = input.nextLine();
                            hospedes.get(i).setCpf(novoCpf);
                        }

                        else if (opcao == 3) {
                            LocalDate novaDataNascimento = null;
                            boolean dataValida = false;
                            while (!dataValida) {
                                try {
                                    System.out.println("\nInsira a nova data de nascimento do hospede (DD/MM/AAAA) digite a barra também (/):");
                                    String data = input.nextLine();
                                    novaDataNascimento = LocalDate.parse(data,formatarData);
                                    dataValida = true;
                                } catch (DateTimeParseException e) {
                                    System.out.println("\nInsira a data de nascimento de forma correta (DD/MM/AAAA)");
                                }
                            }
                            hospedes.get(i).setDataDeNascimento(novaDataNascimento);
                        }

                        else if (opcao == 4) {
                            System.out.println("\nInsira o novo endereço do hospede (rua):");
                            String novoEndereco = input.nextLine();
                            hospedes.get(i).setEndereco(novoEndereco);
                        }

                        else if (opcao== 5) {
                            System.out.println("\nInsira o novo número de contato do hospede:");
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
        System.out.println("\nDigite o cpf referente ao hospede que deseja visualizar:");
        String cpf = input.nextLine();
        for (Hospede hospede:hospedes) {
            if (hospede.getCpf().equals(cpf)) {
                System.out.println(hospede);
                break;
            }
        }
    }

    public void visualizarHistorico() {

    }

    public void adicionarReserva() {

    }

    public void retirarReserva(){

    }
}
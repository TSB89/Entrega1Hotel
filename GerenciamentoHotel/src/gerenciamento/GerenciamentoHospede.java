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
        System.out.println("Insira o nome do hospede:");
        String nome = input.nextLine();

        System.out.println("Insira o CPF do hospede:");
        String cpf = input.nextLine();

        LocalDate dataNascimento = null;
        boolean dataValida = false;
        while (!dataValida) {
            try {
                System.out.println("Insira a data de nascimento do hospede (DD/MM/AAAA) digite a barra também (/)::");
                String data = input.nextLine();
                dataNascimento = LocalDate.parse(data,formatarData);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Insira a data de nascimento de forma correta (DD/MM/AAAA)");
            }
        }

        System.out.println("Insira o endereço do hospede (rua):");
        String endereco = input.nextLine();

        System.out.println("Insira o número de contato do hospede:");
        String contato = input.nextLine();

        Hospede hospede = new Hospede(cpf,nome,contato,dataNascimento,endereco);
        hospedes.add(hospede);
    }

    @Override
    public void editar() {
        System.out.println("Insira o cpf do hospede que deseja alterar as informações:");
        String cpf = input.nextLine();
        for (int i = 0;i < hospedes.size();i++) {
            if (hospedes.get(i).getCpf().equals(cpf)) {
                int opcao = 0;
                while (opcao != 6) {
                    System.out.println("Digite o número referente a opção que deseja realizar:" +
                            "1) Alterar nome." +
                            "2) Alterar cpf." +
                            "3) Alterar data de nascimento." +
                            "4) Alterar endereço." +
                            "5) Alterar contato." +
                            "6) Sair do menu de alteração de dados.");

                    opcao = input.nextInt();
                    input.nextLine();

                    if (opcao == 1) {
                        System.out.println("Insira o novo nome do hospede:");
                        String novoNome = input.nextLine();
                        hospedes.get(i).setNome(novoNome);
                    }

                    else if (opcao == 2) {
                        System.out.println("Insira o CPF do hospede:");
                        String novoCpf = input.nextLine();
                        hospedes.get(i).setCpf(novoCpf);
                    }

                    else if (opcao == 3) {
                        LocalDate novaDataNascimento = null;
                        boolean dataValida = false;
                        while (!dataValida) {
                            try {
                                System.out.println("Insira a nova data de nascimento do hospede (DD/MM/AAAA) digite a barra também (/):");
                                String data = input.nextLine();
                                novaDataNascimento = LocalDate.parse(data,formatarData);
                                dataValida = true;
                            } catch (DateTimeParseException e) {
                                System.out.println("Insira a data de nascimento de forma correta (DD/MM/AAAA)");
                            }
                        }
                        hospedes.get(i).setDataDeNascimento(novaDataNascimento);
                    }

                    else if (opcao == 4) {
                        System.out.println("Insira o endereço do hospede (rua):");
                        String novoEndereco = input.nextLine();
                        hospedes.get(i).setEndereco(novoEndereco);
                    }

                    else if (opcao== 5) {
                        System.out.println("Insira o número de contato do hospede:");
                        String novoContato = input.nextLine();
                        hospedes.get(i).setContato(novoContato);
                    }
                }
                break;
            }
        }
    }

    @Override
    public void visualizar() {
        System.out.println("Digite o cpf referente ao hospede que deseja visualizar:");
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
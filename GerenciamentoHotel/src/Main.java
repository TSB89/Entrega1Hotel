import gerenciamento.Hotel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Hotel hotel = new Hotel();

        System.out.println("-------------------------------------------------------------");
        System.out.println("\nBem Vindo(a) ao nosso Sistema de Gerenciamento de Hotel!");
        System.out.println();
        int opcao = 0;
        while (opcao != 16) {
        	System.out.println("-------------------------------------------------------------");
            System.out.println("\nDigite o Número referente a Opção que deseja realizar:\n" +
                               "\n1  = Cadastrar Novo Funcionário" +
                               "\n2  = Cadastrar Novo Hóspede" +
                               "\n3  = Cadastrar Novo Quarto" +
                               "\n4  = Visualizar Funcionários Cadastrados" +
                               "\n5  = Visualizar Hóspedes Cadastrados" +
                               "\n6  = Visualizar Quartos Cadastrados" +
                               "\n7  = Atualizar Status do Quarto" +
                               "\n8  = Editar Informações do Funcionário" +
                               "\n9  = Editar Informações do Hóspede" +
                               "\n10 = Visualizar Histórico de Estadias do Hóspede" +
                               "\n11 = Verificar Quartos disponiveis para Reserva em Período Específico" +
                               "\n12 = Criar uma Reserva" +
                               "\n13 = Cancelar uma Reserva" +
                               "\n14 = Realizar Check-In" +
                               "\n15 = Realizar Check-Out" +
                               "\n16 = Sair do Sistema de Gerenciamento de Hotel");


            if (!input.hasNextInt()) {
                System.out.println("\nEntrada inválida, insira um número inteiro!");
                input.nextLine();
                continue;
            }

            opcao = input.nextInt();
            input.nextLine();

            if (opcao < 0 || opcao > 16) {
                System.out.println("\nOpção inválida. Por favor, escolha entre 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 ou 16.");
            }

            else if (opcao == 1) {
                hotel.cadastrarFuncionario();
            }

            else if (opcao == 2) {
                hotel.cadastrarHospede();
            }

            else if (opcao == 3) {
                hotel.cadastrarQuarto();
            }

            else if (opcao == 4) {
                hotel.visualizarFuncionario();
            }

            else if (opcao == 5) {
                hotel.visualizarHospede();
            }

            else if (opcao == 6) {
                hotel.visualizarQuarto();
            }

            else if (opcao == 7) {
                hotel.atualizarStatusQuarto();
            }

            else if (opcao == 8) {
                hotel.editarFuncionario();
            }

            else if (opcao == 9) {
                hotel.editarHospede();
            }

            else if (opcao == 10) {
                hotel.visualizarHistoricoEstadias();
            }

            else if (opcao == 11) {
                hotel.verificarDisponibilidadeQuarto();
            }

            else if (opcao == 12) {
                hotel.criarReserva();
            }

            else if (opcao == 13) {
                hotel.cancelarReserva();
            }

            else if (opcao == 14) {
                hotel.checkIn();
            }

            else if (opcao == 15) {
                hotel.checkOut();
            }
        }
        System.out.println("\nObrigado por usar nosso sistema!!!");
    }
}
import gerenciamento.Hotel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Hotel hotel = new Hotel();

        System.out.println("\nSistema de Gerneciamento de Hotel");
        int opcao = 0;
        while (opcao != 16) {
            System.out.println("\nDigite o número referente a opção que deseja realizar:" +
                               "\n1) Cadastrar novo funcionário." +
                               "\n2) Cadastrar novo hóspede." +
                               "\n3) Cadastrar novo quarto." +
                               "\n4) Visualizar funcionários cadastrados." +
                               "\n5) Visualizar hóspedes cadastrados." +
                               "\n6) Visualizar quartos cadastrados." +
                               "\n7) Atualizar status do quarto." +
                               "\n8) Editar informações do funcionário." +
                               "\n9) Editar informações do hóspede." +
                               "\n10) Visualizar histórico de estadias do hóspede." +
                               "\n11) Verificar quartos disponiveis para reserva em periodo especifico." +
                               "\n12) Criar uma reserva." +
                               "\n13) Cancelar uma reserva." +
                               "\n14) Realizar Check-In." +
                               "\n15) Realizar Check-Out." +
                               "\n16) Sair do sistema de gerenciamento de hotel.");

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
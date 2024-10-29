package gerenciamento;

import classesBase.Funcionario;
import interfaces.GerenciamentoPadrao;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GerenciamentoFuncionario implements GerenciamentoPadrao {
	public List<Funcionario> funcionarios = new ArrayList<>();
	public Scanner sc = new Scanner(System.in);

	@Override
	public void cadastrar() {

		try {

			System.out.println("Cadastro de Funcionário:");
			System.out.print("Nome: ");
			String nome = sc.nextLine();
			System.out.print("CPF: ");
			String cpf = sc.nextLine();
			System.out.print("Cargo: ");
			String cargo = sc.nextLine();
			System.out.print("Salário base: ");
			double salario = sc.nextDouble();
			System.out.print("Horas de trabalho semanais: ");
			int horasDeTrabalho = sc.nextInt();
			sc.nextLine();

			Funcionario funcionario = new Funcionario(cpf, nome, cargo, horasDeTrabalho, salario);
			funcionarios.add(funcionario);
			System.out.println("Funcionário cadastrado com sucesso!");

		} catch (InputMismatchException e) {
			System.out.println(
					"Erro: Entrada inválida. Certifique-se de inserir valores numéricos para salário e horas de trabalho.");
		}
	}

	@Override
	public void editar() {

		try {

			System.out.print("Informe o CPF do funcionário que deseja editar: ");
			String cpf = sc.nextLine();
			Funcionario funcionario = buscarFuncionarioPorCpf(cpf);

			if (funcionario == null) {
				throw new NoSuchElementException("Funcionário com CPF " + cpf + " não encontrado.");
			}

			System.out.println("Editar informações do funcionário:");
			System.out.print("Novo nome: ");
			funcionario.setNome(sc.nextLine());
			System.out.print("Novo cargo: ");
			funcionario.setCargo(sc.nextLine());
			System.out.print("Novo salário: ");
			funcionario.setSalario(sc.nextDouble());
			System.out.print("Novas horas de trabalho semanais: ");
			funcionario.setHorasDeTrabalho(sc.nextInt());
			sc.nextLine();

			System.out.println("Informações do funcionário atualizadas com sucesso!");
		} catch (InputMismatchException e) {
			System.out.println(
					"Erro: Entrada inválida. Certifique-se de inserir valores numéricos para salário e horas de trabalho.");
		}
	}

	@Override
	public void visualizar() {
		if (funcionarios.isEmpty()) {
			System.out.println("Nenhum funcionário cadastrado.");
		} else {
			System.out.println("Lista de Funcionários:");
			for (Funcionario funcionario : funcionarios) {
				System.out.println("Nome: " + funcionario.getNome());
				System.out.println("CPF: " + funcionario.getCpf());
				System.out.println("Cargo: " + funcionario.getCargo());
				System.out.println("Salário: " + funcionario.getSalario());
				System.out.println("Horas de Trabalho: " + funcionario.getHorasDeTrabalho());
				System.out.println("--------------");
			}
		}
	}

	public void registrarHoras(String cpf, int horasTrabalhadas) {

		try {
			Funcionario funcionario = buscarFuncionarioPorCpf(cpf);

			if (funcionario == null) {
				throw new NoSuchElementException("Funcionário com CPF " + cpf + " não encontrado.");
			}

			funcionario.setHorasDeTrabalho(funcionario.getHorasDeTrabalho() + horasTrabalhadas);
			System.out.println("Horas registradas com sucesso.");
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado ao registrar horas: " + e.getMessage());
		}
	}

	public void calcularSalario(String cpf) {

		try {
			Funcionario funcionario = buscarFuncionarioPorCpf(cpf);

			if (funcionario == null) {
				throw new NoSuchElementException("Funcionário com CPF " + cpf + " não encontrado.");
			}

			double salarioCalculado = funcionario.getSalario() * (funcionario.getHorasDeTrabalho() / 40.0);
			System.out.println("Salário calculado para " + funcionario.getNome() + ": R$" + salarioCalculado);
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado ao calcular salário: " + e.getMessage());
		}
	}

	private Funcionario buscarFuncionarioPorCpf(String cpf) {
		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getCpf().equals(cpf)) {
				return funcionario;
			}
		}
		return null;
	}
}

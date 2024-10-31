package classesBase;

public class Funcionario extends Pessoa{

    private String cargo;
    private double salario;
    private int horasDeTrabalho;

    public Funcionario(String cpf, String nome, String cargo, int horasDeTrabalho, double salario) {
        super(cpf, nome);
        this.cargo = cargo;
        this.horasDeTrabalho = horasDeTrabalho;
        this.salario = salario;
    }

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getHorasDeTrabalho() {
		return horasDeTrabalho;
	}

	public void setHorasDeTrabalho(int horasDeTrabalho) {
		this.horasDeTrabalho = horasDeTrabalho;
	}

	@Override
	public String toString() {
		return "\nNome: " + getNome() +
				"\nCpf: " + getCpf() +
				"\nCargo: " + getCargo() +
				"\nHoras de trabalho: " + getHorasDeTrabalho() +
				"\nSalário: R$" + getSalario() + "\n";
	}
}

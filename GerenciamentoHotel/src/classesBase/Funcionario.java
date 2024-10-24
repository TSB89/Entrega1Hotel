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
}

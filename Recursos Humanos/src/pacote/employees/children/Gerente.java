package pacote.employees.children;

import java.util.Scanner;
import pacote.employees.father.*;
import pacote.Bigdata;

public class Gerente extends Funcionario implements Administraçao {
    private Bigdata bigdata;
    private transient Scanner input = new Scanner(System.in);
    private int senha;

    public Gerente(String nome, String email, Departamento departamento, int admissao, int salario, long fone,
            Bigdata bigdata, int id) {
        super(nome, email, departamento, admissao, salario, fone, id);
        System.out.println("Digite a senha do gerente:");
        setSenha(senha = input.nextInt());
        this.bigdata = bigdata;
    }

    public int getSenha() {
        return this.senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public void aumentarSalario(Funcionario funcionario, int aumento) {
        funcionario.setSalario(funcionario.getSalario() + aumento);
    }

    public void diminuirSalario(Funcionario funcionario, int diminui) {
        funcionario.setSalario(funcionario.getSalario() - diminui);
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        bigdata.adicionarFuncionario(funcionario); // Adiciona o funcionário no ArrayList de funcionários
        System.out.println("Funcionário adicionado com sucesso!");

    }

    public void removerFuncionario(Funcionario funcionario) {
        System.out.println("Funcionário removido com sucesso!");
    }

    public void listarFuncionarios(Departamento departamento) {
        System.out.println("Funcionários:");
        bigdata.listarFuncionarios(departamento);
    }

    public String toString() {
        return "Nome: " + this.getNome() + "\nEmail: " + this.getEmail() + "\nDepartamento: "
                + this.getDepartamento().toString() + "\nAdmissão: " + this.getAdmissao() + "\nSalário: "
                + this.getSalario() + "\nFone: " + this.getFone() + "\nSenha: " + this.getSenha()
                + "\nID: " + this.getId();
    }

}

package pacote.employees.children;

import pacote.employees.father.*;
import java.util.*;

public class Temporario extends Funcionario {
    private int duracao;

    public Temporario(String nome, String email, Departamento departamento, int admissao, int salario, long fone) {
        super(nome, email, departamento, admissao, salario, fone);
        System.out.println("Digite a duração do contrato em anos:");
        this.duracao = input.nextInt();
    }

    public int getDuracao() {
        return this.duracao;
    }

    public String toString() {
        return "Nome: " + this.getNome() + "\nEmail: " + this.getEmail() + "\nDepartamento: "
                + this.getDepartamento().toString() + "\nAdmissão: " + this.getAdmissao() + "\nSalário: "
                + this.getSalario() + "\nFone: " + this.getFone() + "\nDuração: " + this.getDuracao();

    }

    Scanner input = new Scanner(System.in);
}

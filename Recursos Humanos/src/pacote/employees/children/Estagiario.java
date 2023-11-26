package pacote.employees.children;

import pacote.employees.father.*;

import java.util.Scanner;

public class Estagiario extends Funcionario {
    Scanner input = new Scanner(System.in);
    private String curso;

    public Estagiario(String nome, String email, Departamento departamento, int admissao, int salario, long fone,
            int id) {
        super(nome, email, departamento, admissao, salario, fone, id);
        System.out.println("Digite o curso do estagiário:");
        this.curso = input.nextLine();
        System.out.println("Estagiário cadastrado com sucesso!");
    }

    public String getCurso() {
        return this.curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String toString() {
        return "Nome: " + this.getNome() + "\nEmail: " + this.getEmail() + "\nCurso: " + this.getCurso()
                + "\nDepartamento: " + this.getDepartamento().toString() + "\nAdmissão: " + this.getAdmissao()
                + "\nSalário: " + this.getSalario() + "\nFone: " + this.getFone()
                + "\nID: " + this.getId();
    }

}

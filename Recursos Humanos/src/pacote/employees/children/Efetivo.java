package pacote.employees.children;

import pacote.employees.father.*;
import java.util.*;

public class Efetivo extends Funcionario {
    private int contrato;

    public Efetivo(String nome, String email, Departamento departamento, int admissao, int salario, long fone) {
        super(nome, email, departamento, admissao, salario, fone);
        System.out.println("Digite o tempo até a renovação do contrato(Em anos):");
        this.contrato = input.nextInt();
    }

    public int getContrato() {
        return this.contrato;
    }

    public void renovarContrato(int contrato) {
        this.contrato = contrato;
    }

    public String toString() {
        return "Nome: " + this.getNome() + "\nEmail: " + this.getEmail() + "\nDepartamento: "
                + this.getDepartamento().toString() + "\nAdmissão: " + this.getAdmissao() + "\nSalário: "
                + this.getSalario() + "\nFone: " + this.getFone() + "\nContrato: " + this.getContrato();
    }

    Scanner input = new Scanner(System.in);
}

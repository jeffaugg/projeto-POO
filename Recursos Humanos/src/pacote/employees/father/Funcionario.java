package pacote.employees.father;

import java.io.Serializable;

public abstract class Funcionario implements Serializable {
    private String nome;
    private String email;
    private Departamento departamento;
    private int id;
    private int admissao;
    private int salario;
    private long fone;

    public Funcionario(String nome, String email, Departamento departamento, int admissao, int salario, long fone,
            int id) {
        this.nome = nome;
        this.email = email;
        this.departamento = departamento;
        this.admissao = admissao;
        this.salario = salario;
        this.fone = fone;
        this.id = id;

    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public int getAdmissao() {
        return this.admissao;
    }

    public int getSalario() {
        return this.salario;
    }

    public long getFone() {
        return this.fone;
    }

    public int getId() {
        return this.id;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
}

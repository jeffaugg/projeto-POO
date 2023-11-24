package pacote.employees.father;

import java.io.Serializable;

public interface Administraçao extends Serializable {
    public void adicionarFuncionario(Funcionario funcionario);

    public void removerFuncionario(Funcionario funcionario);

    public void listarFuncionarios(Departamento departamento);

    public void aumentarSalario(Funcionario funcionario, int aumento);

    public void diminuirSalario(Funcionario funcionario, int diminuiçao);
}
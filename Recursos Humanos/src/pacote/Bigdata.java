package pacote;

import java.io.File;
import pacote.employees.father.*;
import pacote.employees.children.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Bigdata implements Serializable {
    ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
    private String arquivoSerializacao = "dadosBigData.ser"; // Variável de instância que guarda o nome do arquivo de
                                                             // serialização

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void removerFuncionario(Funcionario funcionario) {
        funcionarios.remove(funcionario);
    }

    public void listarFuncionarios(Departamento departamento) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDepartamento().equals(departamento)) {
                System.out.println(funcionario.toString());
            }
        }
    }

    public void listarFuncionarios() {
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.toString());
        }
    }

    public boolean temGerente(Departamento departamento) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario instanceof Gerente) {
                if (funcionario.getDepartamento().equals(departamento)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Gerente verificarSenha(int senha, Departamento departamento) {
        for (Funcionario funcionario : funcionarios) {
            // Verifica se o funcionário pertence ao departamento fornecido
            if (funcionario.getDepartamento().equals(departamento)) {
                // Verifica se o funcionário é um Gerente
                if (funcionario instanceof Gerente) {
                    Gerente gerente = (Gerente) funcionario; // Cast para Gerente
                    if (gerente.getSenha() == senha) {
                        return gerente;
                    }
                }
            }
        }
        return null;
    }

    public Funcionario buscarFuncionario(int id) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getId() == id) {
                return funcionario;
            }
        }
        return null;
    }

    // public void carregarDados() {
    // try {
    // FileInputStream arquivoInput = new FileInputStream(arquivoSerializacao);
    // ObjectInputStream input = new ObjectInputStream(arquivoInput);

    // // Desserializando o objeto e atribuindo ao objeto atual
    // Bigdata objetoSalvo = (Bigdata) input.readObject();
    // this.funcionarios = objetoSalvo.funcionarios;

    // input.close();
    // arquivoInput.close();
    // } catch (IOException | ClassNotFoundException e) {
    // // Tratamento de exceções
    // e.printStackTrace();
    // }
    // }

    public void carregarDados() {
        try {
            File f = new File(arquivoSerializacao);
            if (!f.exists()) {
                f.createNewFile();
            }

            // Verifica se o arquivo está vazio
            if (f.length() == 0) {
                // O arquivo está vazio, então criamos e salvamos um objeto padrão
                this.funcionarios = new ArrayList<Funcionario>();
                this.salvarDados();
            } else {
                FileInputStream arquivoInput = new FileInputStream(arquivoSerializacao);
                ObjectInputStream input = new ObjectInputStream(arquivoInput);

                // Desserializando o objeto e atribuindo ao objeto atual
                Bigdata objetoSalvo = (Bigdata) input.readObject();
                this.funcionarios = objetoSalvo.funcionarios;

                input.close();
                arquivoInput.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            // Tratamento de exceções
            e.printStackTrace();
        }
    }

    public void salvarDados() {
        try {
            FileOutputStream arquivoOutput = new FileOutputStream(arquivoSerializacao);
            ObjectOutputStream output = new ObjectOutputStream(arquivoOutput);

            // Salvando o objeto atual
            output.writeObject(this);

            output.close();
            arquivoOutput.close();
        } catch (IOException e) {
            // Tratamento de exceções
            e.printStackTrace();
        }
    }
}
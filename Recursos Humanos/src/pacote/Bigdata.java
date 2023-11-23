package pacote;

import java.io.*;
import java.util.ArrayList;
import pacote.employees.father.*;
import pacote.employees.children.*;

class PersistenciaDados {
    public static void salvar(Object obj, String arquivo) {
        try {
            FileOutputStream fileOut = new FileOutputStream(arquivo);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Object carregar(String arquivo) {
        Object obj = null;
        try {
            FileInputStream fileIn = new FileInputStream(arquivo);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            obj = in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Classe não encontrada");
            c.printStackTrace();
        }
        return obj;
    }
}

public class Bigdata implements Serializable {
    ArrayList<Funcionario> funcionarios;

    public Bigdata() {
        // Carregar o estado salvo da lista de funcionários
        File f = new File("funcionarios.ser");
        if (f.exists() && !f.isDirectory()) {
            funcionarios = (ArrayList<Funcionario>) PersistenciaDados.carregar("funcionarios.ser");
        } else {
            funcionarios = new ArrayList<Funcionario>();
        }
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
        // Salvar o estado atual da lista de funcionários
        PersistenciaDados.salvar(funcionarios, "funcionarios.ser");
    }

    public void removerFuncionario(Funcionario funcionario) {
        funcionarios.remove(funcionario);
        // Salvar o estado atual da lista de funcionários
        PersistenciaDados.salvar(funcionarios, "funcionarios.ser");
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
}

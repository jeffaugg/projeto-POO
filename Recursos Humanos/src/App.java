import java.util.ArrayList;
import java.util.Scanner;
import pacote.employees.children.*;
import pacote.employees.father.*;
import pacote.*;

public class App {
    public static void main(String[] args) throws Exception {
        Opcoes opcoes = new Opcoes();
        opcoes.carregarDados();
        while (true) {
            char[] linha = new char[80];
            for (int i = 0; i < 80; i++) {
                linha[i] = '-';
            }
            for (int i = 0; i < 80; i++) {
                System.out.print(linha[i]);
            }

            System.out.println("Sistema de Gerenciamento de Recursos Humanos");
            System.out.println("1 - Adicionar Funcionario");
            System.out.println("2 - Remover Funcionario");
            System.out.println("3 - Listar Funcionarios");
            System.out.println("4 - Aumentar Salario");
            System.out.println("5 - Diminuir Salario");
            System.out.println("6 - Sair");
            System.out.println("Digite a opção desejada: ");

            int opcao = Integer.parseInt(System.console().readLine());

            try {
                switch (opcao) {
                    case 1:
                        opcoes.adicionarfun(); // recebe os parametros para o construtor de cada classe e adiciona o
                                               // funcionario na lista
                        break;
                    case 2:
                        opcoes.removerfun();
                        break;
                    case 3:
                        opcoes.listarfun();
                        break;

                    case 4:
                        opcoes.aumentarsal();
                        break;

                    case 5:
                        opcoes.diminuirsal();
                        break;

                    case 6:
                        opcoes.salvarDados();
                        System.out.println("Saindo...");
                        return;

                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

}

// classe opcao que vai receber os parametros para o construtor de cada classe

class Opcoes {
    Scanner in = new Scanner(System.in);
    Bigdata bigdata = new Bigdata();

    public void carregarDados() {
        bigdata.carregarDados();
    }

    public void salvarDados() {
        bigdata.salvarDados();
    }

    // nome, email, departamento, admissao, salario, fone
    public void adicionarfun() throws Exception {
        ArrayList<Object> parametros = new ArrayList<Object>();
        System.out.println("Digite o nome do funcionário: ");
        parametros.add(System.console().readLine());
        // recebeo nome do funcionario e adiciona na lista de parametros para o
        // construtor

        Clear.clearScreen(); // limpa a tela

        System.out.println("Digite o email do funcionário: ");
        parametros.add(System.console().readLine());
        // recebeo email do funcionario e adiciona na lista de parametros para o
        // construtor

        Clear.clearScreen(); // limpa a tela

        System.out.println("Digite o departamento do funcionário: ");
        System.out.println("1 - Financeiro"
                + "\n2 - TI"
                + "\n3 - Jurídico"
                + "\n4 - Limpeza"
                + "\n5 - Vendas");
        int aux = Integer.parseInt(System.console().readLine());
        switch (aux) {
            case 1:
                parametros.add(Departamento.Financeiro);
                break;
            case 2:
                parametros.add(Departamento.TI);
                break;
            case 3:
                parametros.add(Departamento.Jurídico);
                break;
            case 4:
                parametros.add(Departamento.Limpeza);
                break;
            case 5:
                parametros.add(Departamento.Vendas);
                break;
            default:
                throw new Exception("Departamento não encontrado");
        }

        Clear.clearScreen(); // limpa a tela
        // recebeo departamento do funcionario e adiciona na lista de parametros para o
        // construtor
        System.out.println("Digite a data de admissão do funcionário(dd/mm/aaaa): ");
        parametros.add(in.nextLine());

        Clear.clearScreen(); // limpa a tela
        // recebeo admissão do funcionario e adiciona na lista de parametros para o
        // construtor
        System.out.println("Digite o salário do funcionário: ");
        parametros.add(in.nextLine());

        Clear.clearScreen(); // limpa a tela

        // recebeo salario do funcionario e adiciona na lista de parametros para o
        // construtor
        System.out.println("Digite o fone do funcionário: ");
        parametros.add(in.nextLine());

        Clear.clearScreen(); // limpa a tela

        // parametros.add(Integer.parseInt(System.console().readLine()));
        // recebeo fone do funcionario e adiciona na lista de parametros para o
        // construtor
        System.out.println("Digite o tipo de funcionário: ");

        System.out.println("1 - Efetivo"
                + "\n2 - Estagiário"
                + "\n3 - Temporário"
                + "\n4 - Gerente");

        aux = Integer.parseInt(System.console().readLine());

        if (aux < 1 || aux > 4) {
            throw new Exception("Tipo de funcionário não encontrado");
        }

        String admissao = (String) parametros.get(3);
        String salario = (String) parametros.get(4);
        String fone = (String) parametros.get(5);
        int aux3 = Integer.parseInt(admissao);
        int aux4 = Integer.parseInt(salario);
        long aux5 = Long.parseLong(fone);

        Clear.clearScreen();// limpa a tela

        if (aux >= 1 && aux < 4) {
            if (!bigdata.temGerente((Departamento) parametros.get(2))) {
                System.out.println("É necessário ter um gerente no departamento para adicionar um novo funcionário");
                return;
            }

            System.out.println("Digite o id do gerente: ");
            int auxidger = in.nextInt();

            if (bigdata.buscarFuncionario(auxidger) == null) {
                throw new Exception("Gerente não encontrado");
            }

            if (bigdata.buscarFuncionario(auxidger).getDepartamento() != bigdata.buscarFuncionario(auxidger)
                    .getDepartamento()) {
                throw new Exception("O gerente não pode adicionar um funcionário de outro departamento");
            }

            while (true) {
                System.out.println("Digite a senha do gerente: ");
                int auxsenha = in.nextInt();
                if (bigdata.verificarSenha(auxsenha, (Departamento) parametros.get(2)) == null) {
                    System.out.println("Senha incorreta"
                            + "\n 1 - Digite novamente"
                            + "\n 2 - Sair");
                    int aux2 = in.nextInt();

                    if (aux2 == 2) {
                        return;
                    }

                } else {
                    break;
                }

            }

        }

        switch (aux) {
            case 1:
                Efetivo efetivo = new Efetivo((String) parametros.get(0), (String) parametros.get(1),
                        (Departamento) parametros.get(2), aux3, aux4, aux5);
                // nome //email //departamento //admissao //salario //fone
                bigdata.adicionarFuncionario(efetivo);

                break;
            case 2:
                Estagiario estagiario = new Estagiario((String) parametros.get(0), (String) parametros.get(1),
                        (Departamento) parametros.get(2), aux3, aux4, aux5);
                // nome //email //departamento //admissao //salario //fone
                bigdata.adicionarFuncionario(estagiario);
                break;
            case 3:
                Temporario temporario = new Temporario((String) parametros.get(0), (String) parametros.get(1),
                        (Departamento) parametros.get(2), aux3, aux4, aux5);
                // nome //email //departamento //admissao //salario / /fone
                bigdata.adicionarFuncionario(temporario);
                break;
            case 4:
                Gerente gerente = new Gerente((String) parametros.get(0), (String) parametros.get(1),
                        (Departamento) parametros.get(2), aux3, aux4, aux5, this.bigdata);
                // nome //email //departamento //admissao //salario //fone
                bigdata.adicionarFuncionario(gerente);
                break;
            default:
                break;
        }

    }

    public void removerfun() throws Exception {
        System.out.println("Digite o id do funcionário que deseja remover: ");
        int auxidfunc = in.nextInt();
        Clear.clearScreen();
        if (bigdata.buscarFuncionario(auxidfunc) == null) {
            System.out.println("Funcionário não encontrado");
            return;
        }

        System.out.println("Digite o id do gerente: ");
        int auxidger = in.nextInt();

        if (bigdata.buscarFuncionario(auxidger).getDepartamento() != bigdata.buscarFuncionario(auxidfunc)
                .getDepartamento()) {
            throw new Exception("O gerente não pode demitir um funcionário de outro departamento");
        }

        if (bigdata.buscarFuncionario(auxidger) == null) {
            throw new Exception("Gerente não encontrado");
        }

        Clear.clearScreen();

        while (true) {
            System.out.println("Digite a senha do gerente: ");
            int auxsenha = in.nextInt();
            if (bigdata.verificarSenha(auxsenha, bigdata.buscarFuncionario(auxidfunc).getDepartamento()) == null) {
                System.out.println("Senha incorreta"
                        + "\n 1 - Digite novamente"
                        + "\n 2 - Sair");
                int aux2 = in.nextInt();

                if (aux2 == 2) {
                    return;
                }

            } else {
                break;
            }

        }
        Clear.clearScreen();
        bigdata.removerFuncionario(bigdata.buscarFuncionario(auxidfunc));
    }

    public void listarfun() {
        System.out.println("1 - Listar todos os funcionários"
                + "\n2 - Listar funcionários por departamento");

        int aux = in.nextInt();
        Clear.clearScreen();
        if (aux == 1) {
            bigdata.listarFuncionarios();
        } else if (aux == 2) {
            System.out.println("Digite o departamento: ");
            System.out.println("1 - Financeiro"
                    + "\n2 - TI"
                    + "\n3 - Jurídico"
                    + "\n4 - Limpeza"
                    + "\n5 - Vendas");
            int aux2 = in.nextInt();
            Clear.clearScreen();
            switch (aux2) {
                case 1:
                    bigdata.listarFuncionarios(Departamento.Financeiro);
                    break;
                case 2:
                    bigdata.listarFuncionarios(Departamento.TI);
                    break;
                case 3:
                    bigdata.listarFuncionarios(Departamento.Jurídico);
                    break;
                case 4:
                    bigdata.listarFuncionarios(Departamento.Limpeza);
                    break;
                case 5:
                    bigdata.listarFuncionarios(Departamento.Vendas);
                    break;
                default:
                    System.out.println("Departamento não encontrado");
                    break;
            }

        }
    }

    public void aumentarsal() throws Exception {
        System.out.println("Digite o id do funcionário que deseja aumentar o salário: ");
        int auxidfunc = in.nextInt();
        Clear.clearScreen();

        if (bigdata.buscarFuncionario(auxidfunc) == null) {
            throw new Exception("Funcionário não encontrado");
        }

        System.out.println("Digite o id do gerente: ");
        int auxidger = in.nextInt();

        if (bigdata.buscarFuncionario(auxidfunc).getId() == bigdata.buscarFuncionario(auxidger).getId()) {
            throw new Exception("O gerente não pode aumentar o próprio salário");
        }

        if (bigdata.buscarFuncionario(auxidger).getDepartamento() != bigdata.buscarFuncionario(auxidfunc)
                .getDepartamento()) {
            throw new Exception("O gerente não pode aumentar o salário de um funcionário de outro departamento");
        }

        if (bigdata.buscarFuncionario(auxidger) == null) {
            throw new Exception("Gerente não encontrado");
        }

        Clear.clearScreen();

        while (true) {
            System.out.println("Digite o senha do gerente: ");
            int auxsenha = in.nextInt();
            Clear.clearScreen();
            if (bigdata.verificarSenha(auxsenha, bigdata.buscarFuncionario(auxidfunc).getDepartamento()) == null) {
                System.out.println("Senha incorreta"
                        + "\n 1 - Digite novamente"
                        + "\n 2 - Sair");
                int aux2 = in.nextInt();

                if (aux2 == 2) {
                    return;
                }

            } else {
                break;
            }

        }

        System.out.println("O salário atual do funcionário é:");
        bigdata.buscarFuncionario(auxidfunc).getSalario();
        System.out.println("Digite o valor do aumento: ");
        int auxaumento = in.nextInt();

        if (auxaumento < 0) {
            throw new Exception("Valor inválido");
        }

        if (auxaumento > bigdata.buscarFuncionario(auxidger).getSalario() * 0.5) {
            throw new Exception("O aumento não pode ser maior que 50% do salário atual");

        }
        Clear.clearScreen();

        bigdata.buscarFuncionario(auxidger).setSalario(bigdata.buscarFuncionario(auxidfunc).getSalario() + auxaumento); // chama
                                                                                                                        // a
                                                                                                                        // funçao
                                                                                                                        // do
                                                                                                                        // gerente
                                                                                                                        // e
                                                                                                                        // modifica
                                                                                                                        // o
                                                                                                                        // salario
                                                                                                                        // do
                                                                                                                        // funcionario
                                                                                                                        // no
                                                                                                                        // banco
                                                                                                                        // de
                                                                                                                        // dados

        System.out.println("O salário foi atualizado para:");
        bigdata.buscarFuncionario(auxidfunc).getSalario();
    }

    public void diminuirsal() throws Exception {
        System.out.println("Digite o id do funcionário que deseja diminuir o salário: ");
        int auxidfunc = in.nextInt();
        Clear.clearScreen();

        if (bigdata.buscarFuncionario(auxidfunc) == null) {
            throw new Exception("Funcionário não encontrado");
        }

        System.out.println("Digite o id do gerente: ");
        int auxidger = in.nextInt();

        if (bigdata.buscarFuncionario(auxidfunc).getId() == bigdata.buscarFuncionario(auxidger).getId()) {
            throw new Exception("O gerente não pode diminuir o próprio salário");
        }

        if (bigdata.buscarFuncionario(auxidger).getDepartamento() != bigdata.buscarFuncionario(auxidfunc)
                .getDepartamento()) {
            throw new Exception("O gerente não pode diminuir o salário de um funcionário de outro departamento");
        }

        if (bigdata.buscarFuncionario(auxidger) == null) {
            throw new Exception("Gerente não encontrado");
        }

        while (true) {
            System.out.println("Digite a senha do gerente: ");
            int auxsenha = in.nextInt();
            if (bigdata.verificarSenha(auxsenha, bigdata.buscarFuncionario(auxidfunc).getDepartamento()) == null) {
                System.out.println("Senha incorreta"
                        + "\n 1 - Digite novamente"
                        + "\n 2 - Sair");
                int aux2 = in.nextInt();

                if (aux2 == 2) {
                    return;
                }

            } else {
                break;
            }

        }

        System.out.println("O salário atual do funcionário é:");
        bigdata.buscarFuncionario(auxidfunc).getSalario();
        System.out.println("Digite o valor da redução: ");
        int auxred = in.nextInt();

        if (auxred < 0) {
            throw new Exception("Valor inválido");
        }

        if (auxred > bigdata.buscarFuncionario(auxidger).getSalario() * 0.5) {
            throw new Exception("A redução não pode ser maior que 50% do salário atual");
        }
        Clear.clearScreen();

        bigdata.buscarFuncionario(auxidger).setSalario(bigdata.buscarFuncionario(auxidfunc).getSalario() + auxred); // chama
                                                                                                                    // a
                                                                                                                    // funçao
                                                                                                                    // do
                                                                                                                    // gerente
                                                                                                                    // e
                                                                                                                    // modifica
                                                                                                                    // o
                                                                                                                    // salario
                                                                                                                    // do
                                                                                                                    // funcionario
                                                                                                                    // no
                                                                                                                    // banco
                                                                                                                    // de
                                                                                                                    // dados
        System.out.println("O salário foi atualizado para:");
        bigdata.buscarFuncionario(auxidfunc).getSalario();
        Clear.clearScreen(); // limpa a tela
    }

}

class Clear {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

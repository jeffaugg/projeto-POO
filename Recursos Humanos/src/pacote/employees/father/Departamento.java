package pacote.employees.father;

public enum Departamento {
    Financeiro,
    TI,
    Jurídico,
    Limpeza,
    Vendas;

    public String toString() {
        switch (this) {
            case Financeiro:
                return "Financeiro";
            case TI:
                return "TI";
            case Jurídico:
                return "Jurídico";
            case Limpeza:
                return "Limpeza";
            case Vendas:
                return "Vendas";
            default:
                return "Departamento não encontrado";
        }
    }
}
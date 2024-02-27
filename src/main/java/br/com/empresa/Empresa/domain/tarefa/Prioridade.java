package br.com.empresa.Empresa.domain.tarefa;

public enum Prioridade {

    ALTA(1),
    MEDIA(2),
    BAIXA(3);

    private final int valorDeRisco;

    Prioridade(int valorDeRisco) {
        this.valorDeRisco = valorDeRisco;
    }

    public int getValue() {
        return valorDeRisco;
    }
}

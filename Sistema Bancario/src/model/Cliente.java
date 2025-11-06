package model;

public class Cliente implements Comparable<Cliente> {
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private String endereco;
    private Conta conta;

    public Cliente(String nome, String sobrenome, String rg, String cpf, String endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    // Getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }

    public double getSalario() {
        if (this.conta == null) {
            return 0.0;
        }
        return this.conta.getSaldo();
    }

    @Override
    public int compareTo(Cliente outro) {
        // 1. Comparação por Nome (Ordem Alfabética)
        int comparacaoNome = this.nome.compareToIgnoreCase(outro.nome);

        if (comparacaoNome != 0) {
            return comparacaoNome;
        }

        // 2. Se os nomes forem iguais, compara pelo Sobrenome (Ordem Alfabética)
        return this.sobrenome.compareToIgnoreCase(outro.sobrenome);
    }

}

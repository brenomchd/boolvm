package objects;

public class Names {
    private String nome;
    private int nome_id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNome_id() {
        return nome_id;
    }

    public void setNome_id(int nome_id) {
        this.nome_id = nome_id;
    }

    public Names(String nome, int nome_id) {
        this.nome = nome;
        this.nome_id = nome_id;
    }

    public Names(String nome) {
        this.nome = nome;
    }
}

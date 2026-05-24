package objects;

public class Lhs {
    private Names name;
    private Names nomePonto; // opcional

    public Lhs(Names name) {
        this.name = name;
    }

    public Lhs(Names name, Names nomePonto) {
        this.name = name;
        this.nomePonto = nomePonto;
    }

    public Names getName() {
        return name;
    }

    public void setName(Names name) {
        this.name = name;
    }

    public Names getNomePonto() {
        return nomePonto;
    }

    public void setNomePonto(Names nomePonto) {
        this.nomePonto = nomePonto;
    }
}
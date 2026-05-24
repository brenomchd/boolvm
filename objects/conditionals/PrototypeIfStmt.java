package objects.conditionals;

import generators.Generator;
import objects.Names;

import static compiler.BoolCompiler.addLinha;

public class PrototypeIfStmt extends IfStmt {
    private Names nome;
    private Names valor;

    public PrototypeIfStmt(Names prototype, Names valor) {
        this.nome = prototype;
        this.valor = valor;
    }

    public void append_result() {
        String newLine = "load " + this.nome.getNome() + "\nload " + valor.getNome() + "\nset _.prototype";
        Generator.linhas += 3;

        addLinha(newLine);
    }

    public Names getNome() {
        return nome;
    }

    public void setNome(Names nome) {
        this.nome = nome;
    }

    public Names getValor() {
        return valor;
    }

    public void setValor(Names valor) {
        this.valor = valor;
    }
}

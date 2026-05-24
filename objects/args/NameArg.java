package objects.args;

import generators.Generator;
import objects.Names;

import static compiler.BoolCompiler.addLinha;

public class NameArg extends Arg{
    private Names nome, nome2; //name.name ou name

    public NameArg(Names name) {
        this.nome = name;
        append_result();
    }

    private void append_result() {
        String newLine = "load " + this.nome.getNome();
        Generator.linhas += 1;
        addLinha(newLine);
    }

    public NameArg(Names name, Names nomes2) {
        this.nome = name;
        this.nome2 = nomes2;
    }

    public Names getNome() {
        return nome;
    }

    public void setNome(Names name) {
        this.nome = name;
    }

    public Names getNome2() {
        return nome2;
    }

    public void setNome2(Names nomes2) {
        this.nome2 = nomes2;
    }
}

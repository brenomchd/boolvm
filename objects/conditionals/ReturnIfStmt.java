package objects.conditionals;

import generators.Generator;
import objects.Names;

import static generators.Generator.gerarArg;
import static compiler.BoolCompiler.addLinha;

public class ReturnIfStmt extends  IfStmt {
    private Names nome;

    public ReturnIfStmt(Names nome) {
        this.nome = nome;
    }


    public void append_result() {
        String newLine;
//        newLine = "load " + this.nome.getNome() + "\nret";
        gerarArg(this.nome.getNome());
        addLinha("ret");
        Generator.linhas += 1;
    }

}

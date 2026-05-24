package objects.methods;

import generators.Generator;
import objects.Names;
import objects.NameList;

import static compiler.BoolCompiler.addLinha;

public class MethodCall {
    private Names nomeObjetos;
    private Names nomeMethod;
    private NameList parameters;

    public MethodCall(Names nomeObjetos, Names nomeMethod) {
        this.nomeObjetos = nomeObjetos;
        this.nomeMethod = nomeMethod;
        this.parameters = new NameList();
    }

    public MethodCall(Names nomeObjetos, Names nomeMethod, NameList parameters) {
        this.nomeObjetos = nomeObjetos;
        this.nomeMethod = nomeMethod;
        this.parameters = parameters;
    }

    public void append_result() {
        String newLine = "";
        if(this.parameters.getNomes().isEmpty()){
            newLine = "load " + this.nomeObjetos.getNome() + "\ncall " + this.nomeMethod.getNome();
            Generator.linhas += 2;
        } else {
            for(Names nome : this.parameters.getNomes()){
                newLine += "load " + nome.getNome() + "\n";
                Generator.linhas += 1;
            }
            newLine += "load " + this.nomeObjetos.getNome() + "\ncall " + this.nomeMethod.getNome();
            Generator.linhas += 2;
        }

        if(!Generator.metodoAtribuido){
            newLine += "\npop";
            Generator.linhas += 1;
        }

        addLinha(newLine);
    }

    public Names getNomeObjetos() {
        return nomeObjetos;
    }

    public Names getNomeMethod() {
        return nomeMethod;
    }

    public NameList getParameters() {
        return parameters;
    }
}

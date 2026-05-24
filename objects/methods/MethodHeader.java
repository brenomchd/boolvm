package objects.methods;

import objects.Names;
import objects.NameList;

public class MethodHeader {
    private Names nome;
    private NameList parameters;

    public MethodHeader(Names nome) {
        this.nome = nome;
        this.parameters = new NameList();
    }

    public MethodHeader(Names nome, NameList parameters) {
        this.nome = nome;
        this.parameters = parameters;
    }
}

package objects.classes;

import objects.AttrsDef;
import objects.methods.MethodsDef;
import objects.Names;

public class ClassDef {
    private Names name;
    private AttrsDef attrsDef; // opcional
    private MethodsDef methodsDef; // opcional

    public ClassDef(Names name, AttrsDef attrsDef, MethodsDef methodsDef) {
        this.name = name;
        this.attrsDef = attrsDef;
        this.methodsDef = methodsDef;
    }

    public ClassDef(Names name, AttrsDef attrsDef) {
        this.name = name;
        this.attrsDef = attrsDef;
    }

    public ClassDef(Names name, MethodsDef methodsDef) {
        this.name = name;
        this.methodsDef = methodsDef;
    }

    public ClassDef(Names name) {
        this.name = name;
    }

    public Names getName() {
        return name;
    }

    public void setName(Names name) {
        this.name = name;
    }

    public AttrsDef getAttrsDef() {
        return attrsDef;
    }

    public void setAttrsDef(AttrsDef attrsDef) {
        this.attrsDef = attrsDef;
    }

    public MethodsDef getMethodsDef() {
        return methodsDef;
    }

    public void setMethodsDef(MethodsDef methodsDef) {
        this.methodsDef = methodsDef;
    }
}

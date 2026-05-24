package objects.conditionals;

import objects.Attr;
import objects.Lhs;
import objects.Names;

public class AttrIfStmt extends IfStmt {
    private Attr attr;
    int tipo; // 1 = com objeto, 2 = com operacao, 3 = return

    public AttrIfStmt(Attr attr) {
        this.attr = attr;
    }

    public AttrIfStmt(Names nome, Names atributo, Names valor) {
        super();
    }

    public AttrIfStmt(Attr attr, Lhs lhs) {
        super();
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    public void append_result() {
        if(this.tipo == 1){
            Attr attr = this.attr;



        }

    }
}

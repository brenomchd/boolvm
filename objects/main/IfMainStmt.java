package objects.main;

import objects.conditionals.If;

public class IfMainStmt extends MainStmt{
    private If ifStatement;

    public IfMainStmt(If ifStatement) {
        this.ifStatement = ifStatement;
    }
}

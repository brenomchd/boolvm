package objects.main;

import objects.VarsDef;

import java.util.List;

public class MainBody {
    private VarsDef varsDef; // opcional
    private List<MainStmt> mainStmts;

    public MainBody(VarsDef varsDef, List<MainStmt> mainStmts) {
        this.varsDef = varsDef;
        this.mainStmts = mainStmts;
    }

    public MainBody(List<MainStmt> mainStmts) {
        this.mainStmts = mainStmts;
    }
}

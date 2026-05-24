package objects.conditionals;

import objects.methods.MethodCall;

public class MethodCallIfStmt extends IfStmt {
    private MethodCall methodCall;

    public MethodCallIfStmt(MethodCall methodCall) {
        this.methodCall = methodCall;
    }
}

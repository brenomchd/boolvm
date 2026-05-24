package objects.main;

import objects.methods.MethodCall;

public class MethodCallMainStmt extends MainStmt{
    private MethodCall methodCall;

    public MethodCallMainStmt(MethodCall methodCall) {
        this.methodCall = methodCall;
    }
}

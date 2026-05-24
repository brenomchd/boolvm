package objects.args;


import objects.methods.MethodCall;

public class MethodCallArg extends Arg {
    private MethodCall methodCall;

    public MethodCallArg(MethodCall methodCall) {
        this.methodCall = methodCall;
    }

    public MethodCall getMethodCall() {
        return methodCall;
    }
}

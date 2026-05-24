package objects.args;

import generators.Generator;
import objects.Numbers;

import static compiler.BoolCompiler.addLinha;

public class NumberArg extends Arg{
    private Numbers number;

    public NumberArg(Numbers number) {
        this.number = number;
        append_result();
    }


    public Numbers getNumber() {
        return number;
    }


    public void append_result(){
        String newLine = "const " + this.number.getNumero();
        Generator.linhas += 1;
        addLinha(newLine);
    }
}

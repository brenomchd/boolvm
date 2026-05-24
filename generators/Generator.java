package generators;

import compiler.BoolCompiler;
import enums.Cmp;
import objects.*;
import objects.conditionals.*;
import objects.methods.MethodBody;
import objects.methods.MethodCall;
import objects.args.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static compiler.BoolCompiler.addLinha;
import static compiler.BoolCompiler.updateLineByIndex;

public class Generator {
    public static int linhas = 0;
    public static boolean metodoAtribuido = false;

    public static MethodBody gerarMethodBody(String methodBody) {
        MethodBody method =null;
        boolean dentroIf = false;
        int contador = 0;
        for(String line : methodBody.split("\n")){
            if(line.contains("end-if")){
                dentroIf = false;
                contador = 0;
            }
            else if(line.trim().startsWith("if")) dentroIf = true;
            else{
                if(dentroIf && contador == 0){
                    contador++;
                    Pattern ifPattern = Pattern.compile("(?s)\\bif\\b.*?\\bend-if\\b");
                    Matcher ifMatcher = ifPattern.matcher(methodBody);
                    if(ifMatcher.find()) {
                        If ifStatement = gerarIf(ifMatcher.group());
                    }

                }
                else if (line.contains("return") && contador == 0) {
                    gerarReturn(line);
                }
                else if (line.contains("=") && contador == 0) {
                    gerarAtribuicao(line);
                }
                else if(line.contains("(") && line.contains(")") && !line.contains("=") && contador == 0){
                    gerarMethodCall(line);
                }
            }


        }
        return method;
    }

    public static If gerarIf(String texto){
        String linha = texto.split("\n")[0];

        If IfPai = null;
        Cmp comparador = null;
        Names nomeEsquerda, nomeDireita;
        List<IfStmt> thenStmts = new ArrayList<>();
        List<IfStmt> elseStmts = new ArrayList<>();

        String[] split = linha.trim().split(" ");
        comparador = Cmp.valueOf(split[2]);
        nomeEsquerda = new Names(split[1]);
        nomeDireita = new Names(split[3]);

        String ifThenBody = texto.split("then")[1].split("end-if")[0];
        String ifElseBody = "";
        if(ifThenBody.contains("else")){
            ifElseBody = texto.split("else")[1];
            ifThenBody = ifThenBody.split("else")[0];
        }


        ///processar if header
        gerarArg(nomeEsquerda.getNome());
        gerarArg(nomeDireita.getNome());
        addLinha(split[2]);

        //aquiaquiaqui

        String newLine = "if " + -1 ;
        int ifIndex = addLinha(newLine);


        Generator.linhas = 0;


        for(String line : ifThenBody.split("\n")){

            if(!line.trim().isEmpty()){
                thenStmts.add(gerarIfStmt(line));
            }

        }
        updateLineByIndex(BoolCompiler.boolcFile, ifIndex, "if " + Generator.linhas);


        if(!(ifElseBody.isEmpty())){


            newLine = "else " + -1;
            int elseIndex = addLinha(newLine);

            Generator.linhas = 0;
            for (String line : ifElseBody.split("\n")) {
                if (!line.trim().isEmpty() && !line.trim().startsWith("else") && !line.trim().startsWith("end-if")){
                    elseStmts.add(gerarIfStmt(line));
                }
            }
            updateLineByIndex(BoolCompiler.boolcFile, elseIndex, "else " + Generator.linhas);
        }
        addLinha("end-if");

        IfPai = new If(nomeEsquerda, comparador, nomeDireita, thenStmts, elseStmts);



        return IfPai;
    }

    private static IfStmt gerarIfStmt(String Texto){
        for (String line : Texto.split("\n")) {
            if (line.trim().contains("prototype")) {
                return gerarPrototype(line);
            } else if (line.trim().contains("(") && line.trim().contains(")") && !line.trim().contains("=")) {
                MethodCall methodCall = gerarMethodCall(line);
                return new MethodCallIfStmt(methodCall);
            } else if (line.trim().startsWith("return")) {
                return gerarReturn(line);
            }
            else{
                return gerarAtribuicao(line);

            }
        }
        return null;
    }

    private static AttrIfStmt gerarAtribuicao(String line) {
        String separado[] = line.split("=");
        Generator.metodoAtribuido = true;

        if(separado[0].contains(".")){ // se tiver setando att de objetp
            String[] partes = separado[0].split("\\.");
            Names nome = new Names(partes[0]);
            Names atributo = new Names(partes[1]);
            Lhs lhs = new Lhs(nome, atributo);
            Attr attr = gerarLinhaAttr(separado[1]);
            attr.setLhs(lhs);
            attr.append_result_store(1);
            Generator.metodoAtribuido = false;


        }
        else{

            Names nome = new Names(separado[0]);
            Attr attr = gerarLinhaAttr(separado[1]);
            attr.setLhs(new Lhs(nome));
            AttrIfStmt attrIfStmt = new AttrIfStmt(attr);
            addLinha("store " + nome.getNome().trim());
            Generator.linhas += 1;
            Generator.metodoAtribuido = false;
            return attrIfStmt;
//            if(attr.getArg() instanceof MethodCallArg){
//                MethodCallArg arg = (MethodCallArg) attr.getArg();
//                MethodCall method = arg.getMethodCall();
//
//                System.out.println(attr.getLhs().getName().getNome() + " = " + method.getNomeMethod().getNome() + "()");
//            }
//            if(attr.getArg() instanceof NameArg){
//                NameArg arg = (NameArg) attr.getArg();
//                System.out.println(attr.getLhs().getName().getNome() + " = " + arg.getNome().getNome() );
//            }
//            if(attr.getArg() instanceof NumberArg) {
//                NumberArg arg = (NumberArg) attr.getArg();
//                System.out.println(attr.getLhs().getName().getNome() + " = " + arg.getNumber().getNumero());
//            }
//
//
        }




        return null;
    }

    private static Attr gerarLinhaAttr(String linha) {
        String[] split = linha.trim().split(" ");
        if(linha.contains("+") || linha.contains("-") || linha.contains("*") || linha.contains("/")) {
            Names nomeEsquerdo = new Names(split[0]);
            String op = (split[1]);
            Names nomeDireito = new Names(split[2]);
            Arg argGerado1 = gerarArg(split[0]);
            Arg argGerado2 = gerarArg(split[2]);

            Attr attr = new Attr(argGerado1, op, argGerado2);
            //como foi soma tem que mostrar
            attr.append_result();
            return attr;
        }
        else{
            Arg arg = gerarArg(linha);
            return new Attr(arg);


        }

    }

    public static Arg gerarArg(String linha) {
        if(linha.trim().contains("(") && linha.trim().contains(")")){

            MethodCall methodCall = gerarMethodCall(linha);
            return new MethodCallArg(methodCall);
        }
        else if(linha.trim().contains(".")){
            String[] partes = linha.split("\\.");
            Names nome = new Names(partes[0]);
            Names atributo = new Names(partes[1]);
            NameArg nameArg = new NameArg(nome, atributo);
            addLinha("load " + nome.getNome().trim() + "\nget " + atributo.getNome().trim());
            Generator.linhas += 2;
            return nameArg;
        }

        else if(linha.trim().contains("new")){
            String[] split = linha.trim().split(" ");
            Names nome = new Names(split[1]);
            ObjectCreationArg objectCreationArg = new ObjectCreationArg(new ObjectCreation(nome));
            addLinha("new " + nome.getNome());
            Generator.linhas += 1;
            return objectCreationArg;

        }
        else{
            try {
                int valor = Integer.parseInt(linha.trim());
                return new NumberArg(new Numbers(valor));
            } catch (NumberFormatException e) {
                return new NameArg(new Names(linha.trim()));
            }
        }




    }

    private static ReturnIfStmt gerarReturn(String line) {
        String[] split = line.trim().split(" ");
        Names nome = new Names(split[1]);
        ReturnIfStmt ifStmt = new ReturnIfStmt(nome);
        ifStmt.append_result();
        return ifStmt;
    }

    private static MethodCall  gerarMethodCall(String line) {
        String[] formated = line.trim().split("\\(");
        String[] params = formated[1].replace(")", "").split(",");
        List<Names> parametros = new ArrayList<>();
        for (String param : params) {
            if(param.equals("")){
                continue;
            }
            parametros.add(new Names(param));

        }

        String[] partes = formated[0].split("\\.");


        MethodCall methodCall = new MethodCall(new Names(partes[0]), new Names(partes[1]), new NameList(parametros));
        methodCall.append_result();
        return  methodCall;


    }

    private static PrototypeIfStmt gerarPrototype(String line) {
        String[] split = line.trim().split(" ");
        Names nome = new Names(split[0].replace("._prototype", ""));

        Names valor = new Names(split[2]);
        PrototypeIfStmt proto = new PrototypeIfStmt(nome, valor);
        proto.append_result();
        return proto;
    }
}

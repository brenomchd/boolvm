package objects;

import java.util.ArrayList;
import java.util.List;

public class NameList {
    private List<Names> nomes;

    public NameList() {
        this.nomes = new ArrayList<>();
    }

    public NameList(List<Names> nomes) {

        this.nomes = nomes;
    }

    public void addNomes(Names nomes) {
       this.nomes.add(nomes);
    }

    public List<Names> getNomes() {
        return nomes;
    }

    public void setNomes(List<Names> nomes) {
        this.nomes = nomes;
    }
}

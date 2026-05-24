package objects;

import objects.classes.ClassDef;
import objects.main.MainBody;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private List<ClassDef> classDefs;
    private MainBody mainBody;

    public Program(MainBody mainBody) {
        this.mainBody = mainBody;
        this.classDefs = new ArrayList<>();
    }

    public Program(List<ClassDef> classDefs, MainBody mainBody) {
        this.classDefs = classDefs;
        this.mainBody = mainBody;
    }
}

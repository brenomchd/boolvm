package objects.args;

import objects.ObjectCreation;

public class ObjectCreationArg extends Arg {
    private ObjectCreation objectCreation;

    public ObjectCreationArg(ObjectCreation objectCreation) {
        this.objectCreation = objectCreation;
    }

    public ObjectCreation getObjectCreation() {
        return objectCreation;
    }
}

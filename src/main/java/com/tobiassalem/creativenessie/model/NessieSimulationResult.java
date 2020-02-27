package com.tobiassalem.creativenessie.model;

import java.io.IOException;
import java.io.Serializable;

/**
 * Class for keeping result data from one Nessie simulation
 *
 * @author Tobias
 */
public class NessieSimulationResult implements Serializable {

    private static final long serialVersionUID = 4034947051840408996L;
    private boolean nessieFound;
    private boolean lakeChoiceSwitched;

    /**
     * Default constructor needed for serialization
     */
    @SuppressWarnings("unused")
    public NessieSimulationResult() {
    }

    public NessieSimulationResult(boolean nessieFound, boolean lakeChoiceSwitched) {
        this.nessieFound = nessieFound;
        this.lakeChoiceSwitched = lakeChoiceSwitched;
    }

    public boolean isNessieFound() {
        return nessieFound;
    }

    public boolean isLakeChoiceSwitched() {
        return lakeChoiceSwitched;
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeBoolean(nessieFound);
        stream.writeBoolean(lakeChoiceSwitched);
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        nessieFound = stream.readBoolean();
        lakeChoiceSwitched = stream.readBoolean();
    }

}

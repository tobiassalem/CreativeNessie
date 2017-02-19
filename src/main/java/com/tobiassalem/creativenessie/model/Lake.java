package com.tobiassalem.creativenessie.model;

/**
 * Model class representing a Lake in the Loch Ness are
 *
 * @author Tobias
 */
public class Lake {

    private Integer number;
    private String name;

    private boolean nessieInThisLake;

    public Lake(Integer lakeNumber) {
        this.number = lakeNumber;
    }

    public Integer getNumber() {
        return this.number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNessieInThisLake() {
        return nessieInThisLake;
    }

    public void setNessieInThisLake(boolean nessieInThisLake) {
        this.nessieInThisLake = nessieInThisLake;
    }


}

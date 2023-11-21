package com.felipesantos.warmine.entities;

import net.minecraft.util.math.BlockPos;

import java.util.UUID;

public abstract class AbstractCityBlock {
    private int RANGE_AREA;
    protected Coordinate coordinate;
    protected WarTeam warTeam;
    protected String name;
    protected UUID nameUUID;

    protected void defineRange(int range){
        RANGE_AREA = range;
    }

    public int getRANGE_AREA(){
        return this.RANGE_AREA;
    }

    public UUID getNameUUID() {
        return nameUUID;
    }

    public void setNameUUID(UUID nameUUID) {
        this.nameUUID = nameUUID;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public WarTeam getWarTeam() {
        return warTeam;
    }

    public void setWarTeam(WarTeam warTeam) {
        this.warTeam = warTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

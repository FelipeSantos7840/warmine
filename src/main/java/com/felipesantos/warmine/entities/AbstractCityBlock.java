package com.felipesantos.warmine.entities;

import net.minecraft.util.math.BlockPos;

public abstract class AbstractCityBlock {
    private int RANGE_AREA;
    protected Coordinate coordinate;
    protected WarTeam warTeam;

    protected void defineRange(int range){
        RANGE_AREA = range;
    }

    public int getRANGE_AREA(){
        return this.RANGE_AREA;
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

}

package com.felipesantos.warmine.entities;

public class CrownDataBlock extends AbstractCityBlock {
    private Coordinate coordinate;
    private WarTeam warTeam;
    private String name;

    public CrownDataBlock(Coordinate coordinate, WarTeam warTeam, String name) {
        defineRange(WarMineData.CROWN_RANGE);
        this.coordinate = coordinate;
        this.warTeam = warTeam;
        this.name = name;
    }

    public CrownDataBlock(Coordinate coordinate, String name) {
        defineRange(WarMineData.CROWN_RANGE);
        this.coordinate = coordinate;
        this.name = name;
    }

    public CrownDataBlock(Coordinate coordinate, WarTeam warTeam) {
        defineRange(WarMineData.CROWN_RANGE);
        this.coordinate = coordinate;
        this.warTeam = warTeam;
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

package com.felipesantos.warmine.entities;

public class CrownDataBlock extends AbstractCityBlock {
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

}

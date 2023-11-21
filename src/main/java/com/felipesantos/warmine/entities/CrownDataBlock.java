package com.felipesantos.warmine.entities;

import java.util.UUID;

public class CrownDataBlock extends AbstractCityBlock {

    public CrownDataBlock(Coordinate coordinate, WarTeam warTeam, UUID nameUUID){
        defineRange(WarMineData.CROWN_RANGE);
        this.coordinate = coordinate;
        this.warTeam = warTeam;
        this.nameUUID = nameUUID;
    }

    public CrownDataBlock (Coordinate coordinate, WarTeam warTeam, String name){
        this.coordinate = coordinate;
        this.warTeam = warTeam;
        this.name = name;
    }

    public CrownDataBlock(Coordinate coordinate, WarTeam warTeam) {
        defineRange(WarMineData.CROWN_RANGE);
        this.coordinate = coordinate;
        this.warTeam = warTeam;
    }

    public CrownDataBlock(Coordinate coordinate, String name) {
        defineRange(WarMineData.CROWN_RANGE);
        this.coordinate = coordinate;
        this.name = name;
    }

}

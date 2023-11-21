package com.felipesantos.warmine.entities;

import java.util.UUID;

public class CrownDataBlock extends AbstractCityBlock {

    public CrownDataBlock(Coordinate coordinate,String name,WarTeam warTeam, UUID nameUUID){
        defineRange(WarMineData.CROWN_RANGE);
        this.coordinate = coordinate;
        this.name = name;
        this.warTeam = warTeam;
        this.nameUUID = nameUUID;
    }

    public CrownDataBlock(Coordinate coordinate, WarTeam warTeam, UUID nameUUID){
        defineRange(WarMineData.CROWN_RANGE);
        this.coordinate = coordinate;
        this.warTeam = warTeam;
        this.nameUUID = nameUUID;
    }

    public CrownDataBlock(Coordinate coordinate,String name, UUID nameUUID){
        defineRange(WarMineData.CROWN_RANGE);
        this.coordinate = coordinate;
        this.name = name;
        this.nameUUID = nameUUID;
    }

    public CrownDataBlock(Coordinate coordinate, String name) {
        defineRange(WarMineData.CROWN_RANGE);
        this.coordinate = coordinate;
        this.name = name;
    }

}

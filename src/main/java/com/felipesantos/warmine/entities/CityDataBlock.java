package com.felipesantos.warmine.entities;

import java.util.UUID;

public class CityDataBlock extends AbstractCityBlock{

    public CityDataBlock(Coordinate coordinate,String name,WarTeam warTeam,UUID nameUUID) {
        defineRange(WarMineData.CITY_RANGE);
        this.coordinate = coordinate;
        this.warTeam = warTeam;
        this.name =name;
        this.nameUUID = nameUUID;
    }

    public CityDataBlock(Coordinate coordinate, WarTeam warTeam, UUID nameUUID){
        defineRange(WarMineData.CROWN_RANGE);
        this.coordinate = coordinate;
        this.warTeam = warTeam;
        this.nameUUID = nameUUID;
    }

    public CityDataBlock(Coordinate coordinate,String name,UUID nameUUID) {
        defineRange(WarMineData.CITY_RANGE);
        this.coordinate = coordinate;
        this.warTeam = warTeam;
        this.name =name;
        this.nameUUID = nameUUID;
    }

    public CityDataBlock(Coordinate coordinate, String name) {
        defineRange(WarMineData.CITY_RANGE);
        this.coordinate = coordinate;
        this.name = name;
    }

}

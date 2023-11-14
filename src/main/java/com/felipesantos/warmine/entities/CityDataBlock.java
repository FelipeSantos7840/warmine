package com.felipesantos.warmine.entities;

public class CityDataBlock extends AbstractCityBlock{
    private Coordinate coordinate;
    private WarTeam warTeam;
    private String name;

    public CityDataBlock(Coordinate coordinate, WarTeam warTeam, String name) {
        defineRange(WarMineData.CITY_RANGE);
        this.coordinate = coordinate;
        this.warTeam = warTeam;
        this.name = name;
    }

    public CityDataBlock(Coordinate coordinate, WarTeam warTeam) {
        defineRange(WarMineData.CITY_RANGE);
        this.coordinate = coordinate;
        this.warTeam = warTeam;
    }

    public CityDataBlock(Coordinate coordinate, String name) {
        defineRange(WarMineData.CITY_RANGE);
        this.coordinate = coordinate;
        this.name = name;
    }
}

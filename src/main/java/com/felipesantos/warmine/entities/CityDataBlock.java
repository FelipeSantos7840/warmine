package com.felipesantos.warmine.entities;

public class CityDataBlock extends AbstractCityBlock{
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

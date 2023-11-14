package com.felipesantos.warmine.entities;

public abstract class AbstractCityBlock {
    private int RANGE_AREA;

    protected void defineRange(int range){
        RANGE_AREA = range;
    }

    public int getRANGE_AREA(){
        return this.RANGE_AREA;
    }

}

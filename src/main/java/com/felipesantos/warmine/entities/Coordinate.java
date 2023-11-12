package com.felipesantos.warmine.entities;

public class Coordinate {
    private int x;
    private int y;
    private int z;

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate() {
    }

    public boolean isSamePosition(Coordinate coordinate){
        boolean validate = false;
        if(this.getX() == coordinate.getX() && this.getY() == coordinate.getY() && this.getZ() == coordinate.getZ()){
            validate = true;
        }
        return validate;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}

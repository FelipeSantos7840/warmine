package com.felipesantos.warmine.entities;

import net.minecraft.util.math.BlockPos;

public class Coordinate {
    private int x;
    private int y;
    private int z;

    public Coordinate(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate(int x, int z) {
        this.x = x;
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

    public boolean inArea(int range,Coordinate testCoord){
        boolean validate = false;
        if(testCoord.getX() >= (this.getX()-range) && testCoord.getX() <= (this.getX()+range)){
            if(testCoord.getZ() >= (this.getZ()-range) && testCoord.getZ() <= (this.getZ()+range)){
                validate = true;
            }
        }
        return validate;
    }

    public static Coordinate getCoordinateOfPos(BlockPos pos){
        return new Coordinate(pos.getX(),pos.getY(),pos.getZ());
    }

    public BlockPos toBlockPos(){
        return new BlockPos(x,y,z);
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

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}

package com.codecool.marsexploration.data.surface;

import com.codecool.marsexploration.data.Coordinate;

public class Tile {
    private final Coordinate coordinate;
    private SurfaceType surfaceType;

    public Tile(Coordinate coordinate, SurfaceType surfaceType){
        this.coordinate = coordinate;
        this.surfaceType = surfaceType;
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }
    public SurfaceType getSurfaceType(){
        return surfaceType;
    }
    public void setSurfaceType(SurfaceType surfaceType){
        this.surfaceType  =surfaceType;
    }
}

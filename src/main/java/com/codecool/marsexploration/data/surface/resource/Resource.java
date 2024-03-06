package com.codecool.marsexploration.data.surface.resource;


import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.surface.SurfaceType;
import com.codecool.marsexploration.data.surface.terrain.TerrainType;

public class Resource implements SurfaceType {
    private final ResourceType resourceType;
    private final Coordinate coordinate;

    public Resource(ResourceType resourceType){
        this.resourceType = resourceType;
        this.coordinate = null;
    }

    public ResourceType getResourceType(){
        return resourceType;
    }
    public Coordinate getCoordinate(){
        return coordinate;
    }
    public String getPreferredTerrainSymbol(){
        return resourceType.getPreferredTerrainSymbol();
    }

    @Override
    public String getSymbol() {
        return resourceType.getSymbol();
    }

    @Override
    public String getName() {
        return this.resourceType.name();
    }
}

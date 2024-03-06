package com.codecool.marsexploration.data.surface.resource;


import com.codecool.marsexploration.data.surface.terrain.TerrainType;

public enum ResourceType {

    WATER(TerrainType.VALLEY,"~"),
    MINERAL(TerrainType.HILL,"*");

    private final String symbol;
    private final TerrainType preferredTerrain;

    ResourceType(TerrainType terrainType,String symbol){
        this.symbol = symbol;
        this.preferredTerrain = terrainType;
    }
    public String getSymbol(){
        return symbol;
    }
    public TerrainType getPreferredTerrain(){
        return preferredTerrain;
    }
    public String getPreferredTerrainSymbol(){
        return preferredTerrain.getSymbol();
    }
}

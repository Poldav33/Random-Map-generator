package com.codecool.marsexploration.data.surface.terrain;

public enum TerrainType {
    HILL("^"),
    VALLEY("#");

    private final String symbol;

    TerrainType(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }
}

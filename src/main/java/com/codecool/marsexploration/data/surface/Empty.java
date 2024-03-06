package com.codecool.marsexploration.data.surface;

public class Empty implements SurfaceType {
    private final String symbol;
    public Empty(){
        this.symbol = " ";
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getName() {
        return "EMPTY";
    }
}

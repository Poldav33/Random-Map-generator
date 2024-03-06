package com.codecool.marsexploration.data.map;

import com.codecool.marsexploration.data.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class MapElement {
    String symbol;
    String name;
    int area;

    public MapElement(String symbol, String name, int area) {
        this.symbol = symbol;
        this.name = name;
        this.area = area;
    }


    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public int getArea() {
        return area;
    }


    void generateCoordinates(Map map) {}
}

package com.codecool.marsexploration.data.surface.terrain;



import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.surface.SurfaceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Terrain implements SurfaceType {
    private final Random random = new Random();

    private final TerrainType terrainType;
    private  int size;
    private List<Coordinate> shape;
    private List<Coordinate> mapCoordinates;

    public Terrain(TerrainType terrainType, int area){
        this.terrainType = terrainType;
        this.shape = generateShape();
        this.mapCoordinates = new ArrayList<>();
        this.size = area;
    }

    private List<Coordinate> generateShape(){
        List<Coordinate> shape = new ArrayList<>(List.of(new Coordinate(0, 0)));

        Coordinate coordinate = shape.get(random.nextInt(0, shape.size()));
        int X = coordinate.x();
        int Y = coordinate.y();

        while (shape.size() < size) {
            Coordinate[] neighbours = {
                    new Coordinate(X - 1, Y),
                    new Coordinate(X, Y - 1),
                    new Coordinate(X + 1, Y),
                    new Coordinate(X, Y + 1)
            };

            Coordinate addToShape = neighbours[random.nextInt(0, neighbours.length)];

            if (shape.contains(addToShape)) {
                coordinate = shape.get(random.nextInt(0, shape.size()));
            } else shape.add(addToShape);

        }
        return shape;
    }

    public void addMapCoordinates(List<Coordinate> mapCoordinates){
        this.mapCoordinates = mapCoordinates;
    }
    public TerrainType getTerrainType(){
        return terrainType;
    }

    @Override
    public String getSymbol() {
        return terrainType.getSymbol();
    }

    @Override
    public String getName() {
        return terrainType.name();
    }

    public void findCoordinatesOnMap(List<Coordinate> coordinates){

    }
    public List<Coordinate> getMapCoordinates(){
        return mapCoordinates;
    }
    public int getSize(){
        return size;
    }
    public List<Coordinate> getShape(){
        return shape;
    }
}

package com.codecool.marsexploration.Service.Placement;

import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.map.Map;
import com.codecool.marsexploration.data.surface.terrain.Terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TerrainPlacement extends MapElementPlacer{
    @Override
    void placeElement(Map map) {
        Random random = new Random();
        for (Terrain terrainToPlace : map.getTerrains()) {
            List<Coordinate> reservedCoords = new ArrayList<>();
            for (Terrain x : map.getTerrains()){
                if (x.getMapCoordinates()!=null)
                    reservedCoords.addAll(x.getMapCoordinates());
            }

            Coordinate startingCoordinate = new Coordinate(random.nextInt(map.getSize()),random.nextInt(map.getSize()));
            List<Coordinate> newCoordinates = new ArrayList<>();

            while(newCoordinates.size()<terrainToPlace.getSize()){
                for (Coordinate shapeCoordinate : terrainToPlace.getShape()){
                    int X = startingCoordinate.x() + shapeCoordinate.x();
                    int Y = startingCoordinate.y() + shapeCoordinate.y();
                    Coordinate onMapCoordinate = new Coordinate(X,Y);
                    if(X>=0 && X< map.getSize() && Y>=0 && Y< map.getSize()){
                        if (reservedCoords.contains(onMapCoordinate)){
                            newCoordinates.clear();
                            startingCoordinate = new Coordinate(random.nextInt(map.getSize()),random.nextInt(map.getSize()));
                            break;
                        }else newCoordinates.add(onMapCoordinate);
                    }else {
                        newCoordinates.clear();
                        startingCoordinate = new Coordinate(random.nextInt(map.getSize()),random.nextInt(map.getSize()));
                        break;
                    }
                }
                terrainToPlace.addMapCoordinates(newCoordinates);
        }
        }
    }
}

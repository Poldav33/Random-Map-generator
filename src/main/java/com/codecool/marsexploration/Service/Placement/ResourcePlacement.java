package com.codecool.marsexploration.Service.Placement;

import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.map.Map;
import com.codecool.marsexploration.data.surface.resource.Resource;
import com.codecool.marsexploration.data.surface.terrain.Terrain;

import java.util.List;
import java.util.Set;

public class ResourcePlacement {

    public void placeElement(Map map) {

        for (Resource resource : map.getResources()) {
            String requiredSymbol = resource.getPreferredTerrainSymbol();
            for (Terrain terrain : map.getTerrains()) {
                if (requiredSymbol.equals(terrain.getSymbol())) {
                    Set<Coordinate> freeNeighbours = map.getFreeCellsAround(terrain.getMapCoordinates());
                    int requiredAmount = map.getRequiredResourceAmount(resource.getSymbol());
                    for (Coordinate coordinate : freeNeighbours) {
                        if (requiredAmount > 0) {
                            map.setCoordinateValue(coordinate, resource.getSymbol());
                            requiredAmount--;
                        } else {
                            break;
                        }
                    }
                    map.setRemainingResourceAmount(resource.getSymbol(), requiredAmount);
                }
            }
        }
    }
}

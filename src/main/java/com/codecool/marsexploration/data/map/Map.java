package com.codecool.marsexploration.data.map;

import com.codecool.marsexploration.Service.MapConfiguration.MapConfiguration;
import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.surface.resource.Resource;
import com.codecool.marsexploration.data.surface.terrain.Terrain;

import java.util.*;

public class Map {
    private final List<Resource> resources;
    private List<Terrain> terrains;
    private List<List<String>> map;
    private int size;
    Random random = new Random();

    private int requiredWaterAmount;
    private int requiredMineralAmount;

    public Map(MapConfiguration config) {
        this.size = config.getSize();
        this.terrains = config.getTerrains();
        this.resources = config.getResources();
        generateEmptyMap(size);
        calculateRequiredResourceAmount(config.getResources());
    }

    public void setRemainingResourceAmount(String symbol, int amount) {
        if (symbol.equals("~")) {
            this.requiredWaterAmount = amount;
        } else if (symbol.equals("*")) {
            this.requiredMineralAmount = amount;
        }
    }
    private void generateEmptyMap(int size) {
        List<List<String>> map = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < size; j ++) {
                row.add(" ");
            }
            map.add(row);
        }
        this.map = map;
    }

    public int getSize() {
        return size;
    }

    public String getCoordinateValue(Coordinate coordinate) {
        return map.get(coordinate.x()).get(coordinate.y());
    }
    public void setCoordinateValue(Coordinate coordinate ,String nextValue) {
        map.get(coordinate.x()).set(coordinate.y(), nextValue);
    }


    public boolean isInMap(Coordinate coordinate) {
        if (coordinate.x() >= 0 && coordinate.x() < size
            && coordinate.y() >= 0 && coordinate.y() < size) {
            return true;
        }
        return false;
    }

    public Set<Coordinate> getNeighbouringCells(List<Coordinate> coordinates) {
        Set<Coordinate> neighborCoordinates = new HashSet<>();
        for (Coordinate coordinate : coordinates) {
            Set<Coordinate> surroundingCells = getSurroundingCells(coordinate);
            for (Coordinate neighbour : surroundingCells) {
                if (isInMap(neighbour)) {
                    neighborCoordinates.add(neighbour);
                }
            }
        }
        return neighborCoordinates;
    }

    public Set<Coordinate> getSurroundingCells(Coordinate coordinate) {
        Set<Coordinate> surroundingCells = new HashSet<>();
        surroundingCells.add(new Coordinate(coordinate.x() - 1, coordinate.y()));
        surroundingCells.add(new Coordinate(coordinate.x() + 1, coordinate.y()));
        surroundingCells.add(new Coordinate(coordinate.x(), coordinate.y() - 1));
        surroundingCells.add(new Coordinate(coordinate.x(), coordinate.y() - 1));
        return surroundingCells;
    }

    public Set<Coordinate> getFreeCellsAround(List<Coordinate> coordinates) {
        Set<Coordinate> freeCells = new HashSet<>();
        Set<Coordinate> neighbouringCells = getNeighbouringCells(coordinates);
        for (Coordinate coordinate : neighbouringCells) {
            for (Coordinate neighBourCoordinate : neighbouringCells) {
                if (getCoordinateValue(coordinate).equals(" ")) {
                    freeCells.add(coordinate);
                }
            }
        }
        return freeCells;
    }
    public int getRequiredResourceAmount(String symbol) {
        if (symbol.equals("~")) {
            return requiredWaterAmount;
        } else if (symbol.equals("*")) {
            return requiredMineralAmount;
        }
        return 0;
    }
    private void calculateRequiredResourceAmount(List<Resource> resources) {
        int requiredWater = 0;
        int requiredMineral = 0;
        for (Resource resource : resources) {
            if (resource.getSymbol().equals("~")) {
                requiredWater += resource.getArea();
            } else if (resource.getSymbol().equals("*")) {
                requiredMineral += resource.getArea();
            }
        }
        this.requiredWaterAmount = requiredWater;
        this.requiredMineralAmount = requiredMineral;
    }

    public List<Terrain> getTerrains(){
        return terrains;
    }

    public void putOnTheMap(){
        for(Terrain terrain : terrains){
            setCoordinates(terrain);
            for (Coordinate coord : terrain.getMapCoordinates()){
                map.get(coord.x()).set(coord.y(), terrain.getSymbol());
            }
        }
        System.out.println("terrain added successfully");

    }

    private void setCoordinates(Terrain terrain){
        List<Coordinate> reservedCoords = new ArrayList<>();
        for (Terrain x : terrains){
            if (x.getMapCoordinates()!=null)
                reservedCoords.addAll(x.getMapCoordinates());
        }
        Coordinate startingCoordinate = new Coordinate(random.nextInt(size),random.nextInt(size));
        List<Coordinate> newCoordinates = new ArrayList<>();
        while(newCoordinates.size()<terrain.getSize()){
            for (Coordinate shapeCoordinate : terrain.getShape()){
                int X = startingCoordinate.x() + shapeCoordinate.x();
                int Y = startingCoordinate.y() + shapeCoordinate.y();
                Coordinate onMapCoordinate = new Coordinate(X,Y);
                if(X>=0 && X<size && Y>=0 && Y<size){
                    if (reservedCoords.contains(onMapCoordinate)){
                        newCoordinates.clear();
                        startingCoordinate = new Coordinate(random.nextInt(size),random.nextInt(size));
                        break;
                    }else newCoordinates.add(onMapCoordinate);
                }else {
                    newCoordinates.clear();
                    startingCoordinate = new Coordinate(random.nextInt(size),random.nextInt(size));
                    break;
                }
            }
            terrain.addMapCoordinates(newCoordinates);
        }
    }

    public List<List<String>> getMap(){
        return map;
    }

    public void generateResources() {
        generateCoordinates("~");
        generateCoordinates("*");
    }

    public void generateCoordinates(String symbol) {
        String requiredSymbol = getRequiredSymbol(symbol);
        for (Terrain terrain : terrains) {
            if (requiredSymbol.equals(terrain.getSymbol())) {
                Set<Coordinate> freeNeighbours = getFreeCellsAround(terrain.getMapCoordinates());
                int requiredAmount = getRequiredResourceAmount(symbol);
                for (Coordinate coordinate : freeNeighbours) {
                    if (requiredAmount > 0) {
                        setCoordinateValue(coordinate, symbol);
                        requiredAmount--;
                    } else {
                        break;
                    }
                }
                setRemainingResourceAmount(symbol, requiredAmount);
            }
        }
    }

    public String getRequiredSymbol(String symbol) {
        String requiredSymbol = "";
        if (symbol.equals("~")) {
            requiredSymbol = "#";
        } else {
            requiredSymbol = "^";
        }
        return requiredSymbol;
    }

    public List<Resource> getResources() {
        return resources;
    }
}

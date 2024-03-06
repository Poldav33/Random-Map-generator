package com.codecool.marsexploration.Service.MapConfiguration;

import com.codecool.marsexploration.Service.Generator.ShapeGenerator;
import com.codecool.marsexploration.Service.Placement.ResourcePlacement;
import com.codecool.marsexploration.Service.Placement.TerrainPlacement;
import com.codecool.marsexploration.data.map.MapElement;
import com.codecool.marsexploration.data.surface.resource.Resource;
import com.codecool.marsexploration.data.surface.terrain.Terrain;

import java.util.ArrayList;
import java.util.List;

public class MapConfiguration {
    private String fileName;
    private int size;
    private List<MapElement> mapElements;
    private List<Terrain> terrains;
    private List<Resource> resources;
    private final ResourcePlacement resourcePlacement;
    private final TerrainPlacement terrainPlacement;


    public MapConfiguration(ShapeGenerator shapeGenerator, ResourcePlacement resourcePlacement, TerrainPlacement terrainPlacement) {
        this.terrains = new ArrayList<Terrain>();
        this.resources = new ArrayList<Resource>();
        this.resourcePlacement = resourcePlacement;
        this.terrainPlacement = terrainPlacement;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void addTerrain(Terrain terrain) {
        this.terrains.add(terrain);
    }
    public void addResource(Resource resource){
        this.resources.add(resource);
    }




    public String getFileName() {
        return fileName;
    }

    public int getSize() {
        return size;
    }
    public List<MapElement> getMapElements() {
        return new ArrayList<>(mapElements);
    }


    public List<Terrain> getTerrains(){
        return terrains;
    }
    public List<Resource> getResources(){
        return resources;
    }
}

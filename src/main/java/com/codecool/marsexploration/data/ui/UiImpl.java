package com.codecool.marsexploration.data.ui;

import com.codecool.marsexploration.Service.MapConfiguration.MapConfiguration;
import com.codecool.marsexploration.data.map.*;
import com.codecool.marsexploration.data.surface.resource.Resource;
import com.codecool.marsexploration.data.surface.resource.ResourceType;
import com.codecool.marsexploration.data.surface.terrain.Terrain;
import com.codecool.marsexploration.data.surface.terrain.TerrainType;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UiImpl implements Ui{
    private MapConfiguration mapConfiguration;
    private Map map;


    public UiImpl(MapConfiguration mapConfiguration) {
        this.mapConfiguration = mapConfiguration;
    }

    public void displayText(String text) {
        System.out.println(text);
    }
    public String getTextInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
    public int getIntInput(String text) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                displayText(text);
                int intInput = scanner.nextInt();
                return intInput;
            } catch(InputMismatchException e) {
                displayText("Please try again, enter an integer");
            }
        }
    }
    public void start() {
        displayText("Welcome in our MapSimulator!\nPlease enter the configuration for your map!");
        while(true) {

            int size = getIntInput("Please enter the map area: ");
            mapConfiguration.setSize(size);

            getMapElements();

            this.map = new Map(mapConfiguration);
            map.putOnTheMap();
            map.generateResources();
            String startAndEndRow = "";
            for (int i = 0; i < size; i++) {
                startAndEndRow += "___";
            }
            System.out.println(startAndEndRow);
            for (List<String> row : map.getMap()){
                String newRow = "|";
                for (String symbol : row) {
                    newRow += " " + symbol + " ";
                }
                newRow += "|";
                System.out.println(newRow);
            }
            System.out.println(startAndEndRow);
        }
    }
    private void getTerrainInputs(TerrainType terrainType) {
        int size = getIntInput("Please enter how many " + terrainType.name() +" you want to generate: ");

        for (int i = 0; i < size; i++) {
            int terrainSize = getIntInput("Please enter " + (i + 1)+ ". " + terrainType.name() + "'s area: ");
            mapConfiguration.addTerrain((new Terrain(terrainType,terrainSize)));
        }
    }

    private List<Terrain> mergePitsAndHills(List<Terrain> hills, List<Terrain> pits) {
        List<Terrain> terrains = new ArrayList<>();
        terrains.addAll(hills);
        terrains.addAll(pits);
        return terrains;
    }

    public void restart() {
    }

    private void getResources(ResourceType resourceType){
        int amount = getIntInput(resourceType.name());
        for (int i = 0; i < amount; i++) {
            mapConfiguration.addResource(new Resource(resourceType));
        }
    }
    private void getMapElements(){
        for (TerrainType terrainType : TerrainType.values()){
            getTerrainInputs(terrainType);
        }

        for (ResourceType resource : ResourceType.values()){
            getResources(resource);
        }
    }
}

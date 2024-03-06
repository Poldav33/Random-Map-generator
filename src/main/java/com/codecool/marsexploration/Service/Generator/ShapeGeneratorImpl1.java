package com.codecool.marsexploration.Service.Generator;



import com.codecool.marsexploration.data.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShapeGeneratorImpl1 implements ShapeGenerator{
    Random random = new Random();
    @Override
    public List<Coordinate> generateShape(int size, int growth) {
        List<Coordinate> shape = new ArrayList<>(List.of(new Coordinate(0,0)));
        Coordinate coor = shape.get(random.nextInt(shape.size()));
        List<Coordinate> neighbours = shapeGrowthCalculator(growth);

        while (shape.size() < size) {
            Coordinate add = getRandomNeighbour(coor,neighbours);
            if (shape.contains(add)) {
                coor = shape.get(random.nextInt(0, shape.size()));
            } else shape.add(add);
        }
        return shape;
    }

    private List<Coordinate> shapeGrowthCalculator(int growth){
        List<Coordinate> shapeCalculator = new ArrayList<>();
        int X = 0;
        int Y = 0;
        for (int i = 0; i < 5; i++) {
            shapeCalculator.addAll(List.of(new Coordinate(X-1, Y),new Coordinate(X+1,Y)));
        }
        for (int i = 0; i <= 1+(growth*5); i++) {
            shapeCalculator.addAll(List.of(new Coordinate(X,Y-1),new Coordinate(X,Y+1)));
        }
        return shapeCalculator;
    }
    private Coordinate getRandomNeighbour(Coordinate coordinate, List<Coordinate> coordinates){
        Coordinate randomNeighbour = coordinates.get(random.nextInt(coordinates.size()));
        return new Coordinate(randomNeighbour.x()+coordinate.x(),randomNeighbour.y()+coordinate.y());
    }
}

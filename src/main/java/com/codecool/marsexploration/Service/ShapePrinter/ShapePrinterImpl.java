package com.codecool.marsexploration.Service.ShapePrinter;

import com.codecool.marsexploration.data.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class ShapePrinterImpl implements ShapePrinter{
    @Override
    public void print(List<Coordinate> shape) {
        List<List<String>> matrix = generateEmptyMatrix(getDimensions(shape));
        int shiftX = getShiftX(shape);
        int shiftY = getShiftY(shape);

        for(Coordinate coordinate : shape){
            matrix.get(coordinate.x()-shiftX).set(coordinate.y()-shiftY,"#");
        }
        for (List<String> row : matrix){
            for (String symbol : row){
                System.out.print(symbol+" ");
            }
            System.out.print("\n");
        }
        System.out.println("");

    }

    private static int getShiftX(List<Coordinate> shape) {
        return shape.stream().map(Coordinate::x).min(Integer::compareTo).get();
    }

    private static int getShiftY(List<Coordinate> shape) {
        return shape.stream().map(Coordinate::y).min(Integer::compareTo).get();
    }

    private static List<List<String>> generateEmptyMatrix(int[] dimensions){
        List<List<String>> matrix = new ArrayList<>();

        for (int i = 0; i <= dimensions[0]; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j <= dimensions[1]; j++) {
                row.add(" ");
            }
            matrix.add(row);
        }
        return matrix;
    }
    private static int[] getDimensions(List<Coordinate> shape){
        return new int[]{getHeight(shape),getWidth(shape)};
    }
    private static int getHeight(List<Coordinate> shape){
        return shape.stream().map(Coordinate::x).max(Integer::compareTo).get() - getShiftX(shape);
    }
    private static int getWidth(List<Coordinate> shape){
        return shape.stream().map(Coordinate::y).max(Integer::compareTo).get() - getShiftY(shape);
    }

}

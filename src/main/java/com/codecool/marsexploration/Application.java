package com.codecool.marsexploration;

import com.codecool.marsexploration.Service.Generator.ShapeGenerator;
import com.codecool.marsexploration.Service.Generator.ShapeGeneratorImpl1;
import com.codecool.marsexploration.Service.MapConfiguration.MapConfiguration;
import com.codecool.marsexploration.Service.ShapePrinter.ShapePrinter;
import com.codecool.marsexploration.Service.ShapePrinter.ShapePrinterImpl;
import com.codecool.marsexploration.data.Coordinate;
import com.codecool.marsexploration.data.ui.Ui;
import com.codecool.marsexploration.data.ui.UiImpl;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {


        //MapConfiguration mapConfiguration = new MapConfiguration();
        //Ui ui = new UiImpl(mapConfiguration);
        //ui.start();

        ShapePrinter printer = new ShapePrinterImpl();
        ShapeGenerator shapeGenerator = new ShapeGeneratorImpl1();


        List<Coordinate> shape1 = shapeGenerator.generateShape(30,0);
        List<Coordinate> shape2 = shapeGenerator.generateShape(30,5);
        List<Coordinate> shape3 = shapeGenerator.generateShape(30,10);
        List<List<Coordinate>> shapes = new ArrayList<>(List.of(shape1,shape2,shape3));

        shapes.forEach(printer::print);




    }

    public static void printList(List list){
        for (var x : list){
            System.out.println(x);
        }
    }
}

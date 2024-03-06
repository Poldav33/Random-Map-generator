package com.codecool.marsexploration.Service.Generator;

import com.codecool.marsexploration.data.Coordinate;

import java.util.List;

public interface ShapeGenerator {

    List<Coordinate> generateShape(int size, int growth);
}

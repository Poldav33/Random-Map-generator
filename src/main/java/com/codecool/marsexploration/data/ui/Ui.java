package com.codecool.marsexploration.data.ui;

import com.codecool.marsexploration.data.map.MapElement;

public interface Ui {
    void displayText(String text);
    String getTextInput();
    int getIntInput(String text);
    void start();
    void restart();
}

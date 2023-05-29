package controller;

import java.awt.Color;

public interface MenuSubscriber {
    void updateDie(int newValue);
    void updateTurn(Color color);
}

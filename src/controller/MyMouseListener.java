package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.ModelAPI;

public class MyMouseListener extends MouseAdapter {
    private final ModelAPI modelAPI = ModelAPI.getInstance();

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        modelAPI.handleClick(convertCoords(x, y));
    }

    int convertCoords(int x, int y){

        if (x >= 240 && x <= 280 && y>= 0 && y <= 240){
            return 49 - (y/40); 
        } 
        else if (x >= 280 && x <= 360 && y >= 0 && y <= 40 ){
            return 43 + (x/40);
        } 
        else if (x >= 320 && x <= 360 && y >= 40 && y <= 240){
            return -1 + (y/40);
        } 
        else if ( x >= 360 && x <= 600 && y >= 240 && y <= 280){
            return -4 + (x/40);
        } 
        else if (x >= 560 && x <= 600 && y >= 280 && y <= 360){
            return 4 + (y/40);
        } 
        else if (x >= 360 && x <= 560 && y >= 320 && y <= 360 ){
            return 26 - (x/40);
        } 
        else if (x >= 320 && x <= 360 && y >= 360 && y <= 600){
            return 9 + (y/40);
        } 
        else if (x >= 280 && x <= 320 && y >= 560 && y <= 600){
            return 24;
        } 
        else if ( x >= 240 && x <= 280 && y >= 360 && y <= 600){
            return 39 - (y/40);
        } 
        else if ( x >= 0 && x <= 240 && y >= 320 && y <= 360){
            return 36 - (x/40);
        } 
        else if ( x >= 0 && x <= 40 && y >= 280 && y <= 320){
            return 37;
        } 
        else if ( x >= 0 && x <= 240 && y >= 240 && y <= 280){
            return 38 + (x/40);
        } 
        else if ( x >= 40 && x <= 280 && y >= 280 && y <= 320){
            return 399 + (x/40);
        } 
        else if ( x >= 320 && x <= 560 && y >= 280 && y <= 320){
            return 213 - (x/40);
        } 
        else if ( x >= 280 && x <= 320 && y >= 40 && y <= 280){
            return 99 + (y/40);
        } 
        else if ( x >= 280 && x <= 320 && y >= 320 && y <= 560){
            return 313 - (y/40);
        } 
        else if (x >= 0 && x <= 240 && y >= 0 && y <= 240){
            return -4;
        } 
        else if (x >= 0 && x <= 240 && y >= 360 && y <= 600){
            return -3;
        } 
        else if (x >= 360 && x <= 600 && y >= 0 && y <= 240){
            return -1;
        } 
        else if (x >= 360 && x <= 600 && y >= 360 && y <= 600){
            return -2;
        }


        return 99999;
    }
    
}

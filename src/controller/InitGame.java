package controller;

import model.ModelAPI;
import view.ViewAPI;

class InitGame {
    public static void main(String[] args) {
        ModelAPI modelAPI = new ModelAPI();
        ViewAPI viewAPI = new ViewAPI();
        modelAPI.createGame();
        viewAPI.createView();

        modelAPI.addSubscriber(viewAPI.getMenuSubscriber());

        while (true){
            viewAPI.redraw();
            modelAPI.rollDie();
        }
    }
}

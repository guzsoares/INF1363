package controller;

import model.ModelAPI;
import view.ViewAPI;

class InitGame {
    public static void main(String[] args) {
        ModelAPI modelAPI = ModelAPI.getInstance();
        ViewAPI viewAPI = ViewAPI.getInstance();
        modelAPI.createGame();
        viewAPI.createView();

        modelAPI.addSubscriber(viewAPI.getMenuSubscriber());
        modelAPI.addBoardSubscriber(viewAPI.getBoardSubscriber());

    }
}

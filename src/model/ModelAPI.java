package model;

import controller.Subscriber;

public class ModelAPI {
    private Game game;
    

    public void createGame(){
        game = new Game();
    }

    public void rollDie(){
        game.rollDie();
    }

    public void addSubscriber(Subscriber subscriber){
        game.addSubscriber(subscriber);
    }
}

package model;

import controller.Subscriber;

public class ModelAPI {
    private static ModelAPI instance;
    private Game game;

    public ModelAPI(){
        instance = this;
    }
    
    public static ModelAPI getInstance() {
        if (instance == null) {
            instance = new ModelAPI();
        }
        return instance;
    }

    public void createGame(){
        game = new Game();
    }

    public void rollDie(){
        game.rollDie();
    }

    public void addSubscriber(Subscriber subscriber){
        game.addSubscriber(subscriber);
    }

    public int getDieNumber(){
        return game.getDieNumber();
    }
}

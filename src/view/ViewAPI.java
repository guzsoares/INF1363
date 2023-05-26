package view;

import controller.Subscriber;

public class ViewAPI {
    private GameScreen gameView;

    public void createView(){
        gameView = new GameScreen();
    }

    public void redraw(){
        gameView.redraw();
    }

    public Subscriber getMenuSubscriber() {
        return gameView.getGameMenu().getSubscriber();
    }
}

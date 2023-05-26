package view;

import controller.Subscriber;

public class ViewAPI {
    private static ViewAPI instance;
    private GameScreen gameView;
    
    public ViewAPI(){
        instance = this;
    }

    public static ViewAPI getInstance() {
        if (instance == null) {
            instance = new ViewAPI();
        }
        return instance;
    }

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

package view;

import controller.MenuSubscriber;
import controller.BoardSubscriber;

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

    public MenuSubscriber getMenuSubscriber() {
        return gameView.getGameMenu().getSubscriber();
    }

    public BoardSubscriber getBoardSubscriber() {
        return gameView.getGameBoard().getBoardSubscriber();
    }

    public void endGameMessage(String winner){
        gameView.showMessage(winner);
    }
}

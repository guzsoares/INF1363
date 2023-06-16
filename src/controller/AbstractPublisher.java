package controller;

import java.util.*;

public class AbstractPublisher implements MenuPublisher, BoardPublisher{
    private List<MenuSubscriber> subscribers;
    private List<BoardSubscriber> boardSubscribers;

    public AbstractPublisher(){
        subscribers = new ArrayList<>();    
        boardSubscribers = new ArrayList<>();    
    }

    @Override
    public void addSubscriber(MenuSubscriber subscriber){
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(MenuSubscriber subscriber){
        subscribers.remove(subscriber);
    }

    @Override
    public void addBoardSubscriber(BoardSubscriber subscriber){
        boardSubscribers.add(subscriber);
    }

    @Override
    public void removeBoardSubscriber(BoardSubscriber subscriber){
        boardSubscribers.remove(subscriber);
    }

    protected void notifySubscribersDie(int newValue){
        for (MenuSubscriber subscriber : subscribers) {
            subscriber.updateDie(newValue);
        }
    }

    protected void notifySubscribersTurn(java.awt.Color color){
        for (MenuSubscriber subscriber : subscribers) {
            subscriber.updateTurn(color);
        }
    }

    protected void notifyBoardUpdate(){
        for (BoardSubscriber boardSubscriber : boardSubscribers) {
            boardSubscriber.updateBoard();
        }
    }
}

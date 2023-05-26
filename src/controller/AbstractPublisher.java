package controller;

import java.util.*;
import java.awt.Color;

public class AbstractPublisher implements Publisher{
    private List<Subscriber> subscribers;

    public AbstractPublisher(){
        subscribers = new ArrayList<>();    
    }

    @Override
    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    protected void notifySubscribersDie(int newValue){
        for (Subscriber subscriber : subscribers) {
            subscriber.updateDie(newValue);
        }
    }

    protected void notifySubscribersTurn(java.awt.Color color){
        for (Subscriber subscriber : subscribers) {
            subscriber.updateTurn(color);
        }
    }
}

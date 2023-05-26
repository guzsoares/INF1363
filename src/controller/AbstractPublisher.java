package controller;

import java.util.*;

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

    protected void notifySubscribers(int newValue){
        for (Subscriber subscriber : subscribers) {
            subscriber.update(newValue);
        }
    }
}

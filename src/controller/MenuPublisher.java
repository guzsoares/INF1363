package controller;

public interface MenuPublisher {
    void addSubscriber(MenuSubscriber subscriber);
    void removeSubscriber(MenuSubscriber subscriber);
}

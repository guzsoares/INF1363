package controller;

public interface BoardPublisher {
    void addBoardSubscriber(BoardSubscriber subscriber);
    void removeBoardSubscriber(BoardSubscriber subscriber);
}

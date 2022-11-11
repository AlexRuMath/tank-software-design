package ru.mipt.bit.platformer.publisher;


public interface Publisher {
    void addSubscriber(Subscriber sub);
    void removeSubscriber(Subscriber sub);
    void publish();
}
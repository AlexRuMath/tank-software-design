package ru.mipt.bit.platformer.publisher;

public interface Subscriber {
    void send(String event);
}

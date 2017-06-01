package com.hubelias.fifatournament;

public class DomainEventPublisher {
    private static DomainEventPublisher instance = null;

    public static DomainEventPublisher getInstance() {
        if(instance == null) {
            return new DomainEventPublisher();
        }

        return instance;
    }

    public void publish(Object event) {}
}

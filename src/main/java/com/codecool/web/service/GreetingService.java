package com.codecool.web.service;

import com.codecool.web.model.Greeting;

import java.util.ArrayList;
import java.util.List;

public final class GreetingService {

    private final List<Greeting> greetings = new ArrayList<>();

    public GreetingService() {
        greetings.add(new Greeting("en", "Hi!"));
        greetings.add(new Greeting("hu", "Szia!"));
        greetings.add(new Greeting("fr", "Bonjour!"));
        greetings.add(new Greeting("pl", "Cześć!"));
    }

    public List<Greeting> getGreetings() {
        return greetings;
    }
}

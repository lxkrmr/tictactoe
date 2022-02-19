package com.example.tictactoe.adapter.outgoing;

import com.example.tictactoe.domain.event.EventRepository;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.event.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class SimpleEventRepository implements EventRepository {

    private final HashMap<GameName, List<Event>> repository = new HashMap<>();

    public SimpleEventRepository() {
    }

    public void addEventByGameName(GameName gameName, Event event) {
        var events = loadEventsByGameName(gameName);
        events.add(event);

        repository.put(gameName,
                       events);
    }

    public List<Event> loadEventsByGameName(GameName gameName) {
        return repository.getOrDefault(gameName, new ArrayList<>());
    }

}

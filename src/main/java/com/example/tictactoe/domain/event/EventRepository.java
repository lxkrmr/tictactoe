package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.GameName;

import java.util.List;

public interface EventRepository {

    void addEventByGameName(GameName gameName, Event event);

    List<Event> loadEventsByGameName(GameName gameName);

}

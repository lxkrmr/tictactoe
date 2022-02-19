package com.example.tictactoe.adapter.outgoing;

import com.example.tictactoe.domain.event.EventRepository;
import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.game.GameRepository;
import org.springframework.stereotype.Repository;

import static com.example.tictactoe.domain.game.Game.initGame;

@Repository
public class SimpleGameRepository implements GameRepository {

    private final EventRepository eventRepository;

    public SimpleGameRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Game load(GameName gameName) {
        var game = initGame(gameName);
        eventRepository.loadEventsByGameName(gameName)
                       .forEach(event -> event.updateGame(game));
        return game;
    }
}

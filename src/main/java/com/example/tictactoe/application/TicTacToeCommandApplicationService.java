package com.example.tictactoe.application;

import com.example.tictactoe.application.model.PlayerJoinsGameCommand;
import com.example.tictactoe.application.model.PlayerMakesMoveCommand;
import com.example.tictactoe.domain.event.Event;
import com.example.tictactoe.domain.event.EventRepository;
import com.example.tictactoe.domain.game.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicTacToeCommandApplicationService {

    private final GameRepository gameRepository;
    private final EventRepository eventRepository;

    public TicTacToeCommandApplicationService(GameRepository gameRepository,
                                              EventRepository eventRepository) {
        this.gameRepository = gameRepository;
        this.eventRepository = eventRepository;
    }

    public void playerJoinsTheGame(PlayerJoinsGameCommand command) {
        // get the domain object
        var game = gameRepository.load(command.gameName());

        // perform the command
        List<Event> events = game.playerJoinsTheGame(command.gameName(), command.playerName());

        // persist changes
        events.forEach(event -> eventRepository.addEventByGameName(command.gameName(), event));
    }

    public void playerIsMakingAMove(PlayerMakesMoveCommand command) {
        // get the domain object
        var game = gameRepository.load(command.gameName());

        // perform the command
        List<Event> events = game.playerMakesMove(command.gameName(), command.playerName(), command.getGridCellNumber());

        // persist changes
        events.forEach(event -> eventRepository.addEventByGameName(command.gameName(), event));
    }
}

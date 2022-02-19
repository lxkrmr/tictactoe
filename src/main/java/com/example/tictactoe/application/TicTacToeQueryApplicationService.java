package com.example.tictactoe.application;

import com.example.tictactoe.application.model.LoadGameQuery;
import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeQueryApplicationService {

    private final GameRepository gameRepository;

    public TicTacToeQueryApplicationService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game loadGame(LoadGameQuery command) {
        // get the domain object
        return gameRepository.load(command.gameName());
    }
}

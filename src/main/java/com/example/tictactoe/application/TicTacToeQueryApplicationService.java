package com.example.tictactoe.application;

import com.example.tictactoe.application.model.LoadGameQuery;
import com.example.tictactoe.domain.game.GameView;
import com.example.tictactoe.domain.game.GameViewRepository;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeQueryApplicationService {

    private final GameViewRepository gameViewRepository;

    public TicTacToeQueryApplicationService(GameViewRepository gameViewRepository) {
        this.gameViewRepository = gameViewRepository;
    }

    public GameView loadGame(LoadGameQuery command) {
        // get the domain object
        return gameViewRepository.load(command.gameName());
    }
}

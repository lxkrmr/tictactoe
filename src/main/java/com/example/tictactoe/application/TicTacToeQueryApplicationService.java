package com.example.tictactoe.application;

import com.example.tictactoe.application.model.LoadGameQuery;
import com.example.tictactoe.domain.game.GameView;
import com.example.tictactoe.domain.game.GameViewRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicTacToeQueryApplicationService {

    private final GameViewRepository gameViewRepository;

    public TicTacToeQueryApplicationService(GameViewRepository gameViewRepository) {
        this.gameViewRepository = gameViewRepository;
    }

    public String loadIntroduction() {
        var randomName = UUID.randomUUID().toString();

        return String.join("\n",
                           "So you want to play a game of Tic Tac Toe?",
                           "Great!",
                           "All you need is a great name,",
                           "because in this digital world we want to appreciate things by naming them.",
                           "",
                           "But be careful, because if someone already named her/his game the same, then all you can do is",
                           "(a) joining her/him",
                           "(b) watching other players enjoy their game of tic tac toe",
                           "(c) or even studying an already finished game.",
                           "",
                           "As you can see the name matters,",
                           "but as a computer I know that picking unique things is quiet difficult for you humans.",
                           "Therefore let me generate a unique name for you:",
                           "... this over here ... yes ... nice ... done",
                           "",
                           "My unique name for you is:",
                           randomName,
                           "",
                           "To create a new game, make sure your url looks like this:",
                           "http://localhost:8080/tictactoe/{nameForTheGameEitherYoursOrMine}",
                           "",
                           "So if you would like to take my funny suggestion, you can go to",
                           String.format("http://localhost:8080/tictactoe/%s", randomName)
        );
    }

    public GameView loadGame(LoadGameQuery command) {
        // get the domain object
        return gameViewRepository.load(command.gameName());
    }
}

package com.example.tictactoe.adapter.incoming;

import com.example.tictactoe.application.TicTacToeCommandApplicationService;
import com.example.tictactoe.application.TicTacToeQueryApplicationService;
import com.example.tictactoe.domain.game.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.tictactoe.application.model.LoadGameQuery.loadGameQuery;
import static com.example.tictactoe.application.model.PlayerJoinsGameCommand.playerJoinsGameCommand;
import static com.example.tictactoe.application.model.PlayerMakesMoveCommand.playerMakesMoveCommand;
import static com.example.tictactoe.domain.game.GameName.gameName;
import static com.example.tictactoe.domain.grid.GridCellNumber.gridCellNumber;
import static com.example.tictactoe.domain.player.PlayerName.playerName;

@RestController
@RequestMapping("/tictactoe")
public class TicTacToeController {

    private final TicTacToeQueryApplicationService queryService;
    private final TicTacToeCommandApplicationService commandService;

    public TicTacToeController(TicTacToeQueryApplicationService queryService,
                               TicTacToeCommandApplicationService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping(produces = "text/plain")
    public String welcome() {
        return "So you want to play a game of Tic Tac Toe?";
    }

    @GetMapping("/{nameOfTheGame}")
    public Game loadTheGameByItsName(@PathVariable String nameOfTheGame) {
        return queryService.loadGame(loadGameQuery(gameName(nameOfTheGame)));
    }

    @GetMapping("/{nameOfTheGame}/{nameOfThePlayer}")
    public Game aPlayerIsJoiningTheGame(@PathVariable String nameOfTheGame,
                                        @PathVariable String nameOfThePlayer) {
        commandService.playerJoinsTheGame(playerJoinsGameCommand(gameName(nameOfTheGame),
                                                                 playerName(nameOfThePlayer)));

        return queryService.loadGame(loadGameQuery(gameName(nameOfTheGame)));
    }

    @GetMapping("/{nameOfTheGame}/{nameOfThePlayer}/{numberOfTheCellPickedByThePlayer}")
    public Game aPlayerIsMakingAMove(@PathVariable String nameOfTheGame,
                                     @PathVariable String nameOfThePlayer,
                                     @PathVariable int numberOfTheCellPickedByThePlayer) {
        commandService.playerIsMakingAMove(playerMakesMoveCommand(gameName(nameOfTheGame),
                                                                  playerName(nameOfThePlayer),
                                                                  gridCellNumber(numberOfTheCellPickedByThePlayer)));

        return queryService.loadGame(loadGameQuery(gameName(nameOfTheGame)));
    }

}
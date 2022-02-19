package com.example.tictactoe.domain.game;

import lombok.Value;

import static com.example.tictactoe.domain.event.EventsView.eventsView;
import static com.example.tictactoe.domain.grid.GridView.gridView;
import static com.example.tictactoe.domain.player.PlayerView.playerView;
import static com.example.tictactoe.domain.player.PlayersTurnView.playersTurnView;
import static com.example.tictactoe.domain.player.SpectatorsView.spectatorsView;

@Value
public class GameView {

    public static final String TEMPLATE = String.join("\n",
                                                      "Name of the Game: %s",
                                                      "Status of the Game: %s",
                                                      "",
                                                      "Players turn: %s",
                                                      "",
                                                      "The 3x3 grid:",
                                                      "",
                                                      "%s",
                                                      "",
                                                      " Player One: %s",
                                                      " Player Two: %s",
                                                      "",
                                                      "Need some help?",
                                                      "jfakljfljda",
                                                      "jksajdfklöjsda",
                                                      "jkajflöjasklf",
                                                      "jkajfölajdöfl",
                                                      "",
                                                      "Spectators:",
                                                      "%s",
                                                      "",
                                                      "Events:",
                                                      "%s");
    String view;

    public static GameView gameView(Game game) {
        var view = String.format(TEMPLATE,
                                 game.getGameName().getValue(),
                                 game.getGameStatus(),
                                 playersTurnView(game.getPlayersTurn()).getView(),
                                 gridView(game.getGrid()).getView(),
                                 playerView(game.getPlayerOne()).getView(),
                                 playerView(game.getPlayerTwo()).getView(),
                                 spectatorsView(game.getSpectators()).getView(),
                                 eventsView(game.getEvents()).getView()
        );

        return new GameView(view);
    }


}

package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.Value;

import java.time.Instant;

import static com.example.tictactoe.domain.player.Player.initPlayerOne;
import static com.example.tictactoe.domain.player.Player.initPlayerTwo;
import static com.example.tictactoe.domain.player.Player.initSpectator;

@Value
public class PlayerJoinedTheGameEvent implements Event {

    Instant created;
    GameName gameName;
    PlayerName playerName;

    public static PlayerJoinedTheGameEvent playerJoinedTheGameEvent(GameName gameName,
                                                                    PlayerName playerName) {
        return new PlayerJoinedTheGameEvent(Instant.now(),
                                            gameName,
                                            playerName);
    }

    @Override
    public void updateGame(Game game) {
        game.getEvents().add(this);

        if (game.getPlayerOne() == null) {
            game.setPlayerOne(initPlayerOne(playerName));
            game.setPlayersTurn(playerName);
            return;
        }

        if (game.getPlayerTwo() == null) {
            game.setPlayerTwo(initPlayerTwo(playerName));
            return;
        }

        game.getSpectators().add(initSpectator(playerName));
    }
}

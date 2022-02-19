package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.game.GameStatus;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.Value;

import java.time.Instant;

import static com.example.tictactoe.domain.game.GameStatus.PLAYER_ONE_WON;

@Value
public class PlayerWonEvent implements Event {

    Instant created;
    GameName gameName;
    PlayerName playerName;

    public static PlayerWonEvent playerWonEvent(GameName gameName,
                                                       PlayerName playerName) {
        return new PlayerWonEvent(Instant.now(),
                                  gameName,
                                  playerName);
    }

    @Override
    public void updateGame(Game game) {
        game.getEvents().add(this);
        game.wonByPlayerName(playerName);

    }
}

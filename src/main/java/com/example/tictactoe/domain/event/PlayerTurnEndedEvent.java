package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.grid.GridCellNumber;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.Value;

import java.time.Instant;

@Value
public class PlayerTurnEndedEvent implements Event {

    Instant created;
    GameName gameName;
    PlayerName playerName;

    public static PlayerTurnEndedEvent playerTurnEndedEvent(GameName gameName,
                                                            PlayerName playerName) {
        return new PlayerTurnEndedEvent(Instant.now(),
                                        gameName,
                                        playerName);
    }

    @Override
    public void updateGame(Game game) {
        game.getEvents().add(this);
        game.playerTurnEnded(playerName);
    }
}

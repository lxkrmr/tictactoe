package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.grid.GridCellNumber;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.Value;

import java.time.Instant;

@Value
public class PlayerMarkedCellEvent implements Event {

    Instant createdAt;
    GameName gameName;
    PlayerName playerName;
    GridCellNumber gridCellNumber;

    public static PlayerMarkedCellEvent playerMarkedCellEvent(GameName gameName,
                                                              PlayerName playerName,
                                                              GridCellNumber gridCellNumber) {
        return new PlayerMarkedCellEvent(Instant.now(),
                                         gameName,
                                         playerName,
                                         gridCellNumber);
    }

    @Override
    public void updateGame(Game game) {
        game.getEvents().add(this);
        game.playerMakesMove(gameName, playerName, gridCellNumber);
    }

    @Override
    public String toString() {
        return String.format("%s: %s put his marke into cell %s! Was it a genius or a dumm move?",
                             this.getCreatedAt(),
                             playerName.getValue(),
                             gridCellNumber.getValue());
    }
}

package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.grid.GridCellNumber;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.Value;

import java.time.Instant;

@Value
public class IllegalMoveEvent implements Event {

    Instant createdAt;
    GameName gameName;
    PlayerName playerName;
    GridCellNumber gridCellNumber;

    public static IllegalMoveEvent illegalMoveEvent(GameName gameName,
                                                    PlayerName playerName,
                                                    GridCellNumber gridCellNumber) {
        return new IllegalMoveEvent(Instant.now(),
                                    gameName,
                                    playerName,
                                    gridCellNumber);
    }

    @Override
    public void updateGame(Game game) {
        game.getEvents().add(this);
    }

    @Override
    public String toString() {
        return String.format("%s: Hey %s you are not allowed to put your mark into cell number %s, shame shame shame!",
                             this.getCreatedAt(),
                             playerName.getValue(),
                             gridCellNumber.getValue());
    }
}

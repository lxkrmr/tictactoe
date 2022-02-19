package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.Value;

import java.time.Instant;

@Value
public class PlayerHasAlreadyJoinedTheGameEvent implements Event {

    Instant createdAt;
    GameName gameName;
    PlayerName playerName;

    public static PlayerHasAlreadyJoinedTheGameEvent playerHasAlreadyJoinedTheGameEvent(GameName gameName,
                                                                                 PlayerName playerName) {
        return new PlayerHasAlreadyJoinedTheGameEvent(Instant.now(),
                                                      gameName,
                                                      playerName);
    }

    @Override
    public void updateGame(Game game) {
        game.getEvents().add(this);
    }

    @Override
    public String toString() {
        return String.format("%s: Hey %s you have already joined the game. Chill or make your move!",
                             this.getCreatedAt(),
                             playerName.getValue());
    }
}

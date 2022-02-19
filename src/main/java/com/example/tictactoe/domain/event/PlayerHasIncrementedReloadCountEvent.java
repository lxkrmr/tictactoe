package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.Value;

import java.time.Instant;

@Value
public class PlayerHasIncrementedReloadCountEvent implements Event {

    Instant createdAt;
    GameName gameName;
    PlayerName playerName;

    public static PlayerHasIncrementedReloadCountEvent playerHasIncrementedReloadCountEvent(GameName gameName,
                                                                                            PlayerName playerName) {
        return new PlayerHasIncrementedReloadCountEvent(Instant.now(),
                                                        gameName,
                                                        playerName);
    }

    @Override
    public void updateGame(Game game) {
        game.getEvents().add(this);
        game.incrementNumberOfReloadsByPlayerName(playerName);
    }

    @Override
    public String toString() {
        return String.format("%s: %s has reloaded the page! It is getting dangerous...",
                             this.getCreatedAt(),
                             playerName.getValue());
    }
}

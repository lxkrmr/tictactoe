package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.Value;

import java.time.Instant;

@Value
public class PlayerWonEvent implements Event {

    Instant createdAt;
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

    @Override
    public String toString() {
        return String.format("%s: No way! %s has won the game. No one saw this coming, no one!",
                             this.getCreatedAt(),
                             playerName.getValue());
    }
}

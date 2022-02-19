package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import lombok.Value;

import java.time.Instant;

import static com.example.tictactoe.domain.game.GameStatus.DRAW;

@Value
public class GameIsDrawEvent implements Event {

    Instant createdAt;
    GameName gameName;

    public static GameIsDrawEvent gameIsDrawEvent(GameName gameName) {
        return new GameIsDrawEvent(Instant.now(),
                                   gameName);
    }

    @Override
    public void updateGame(Game game) {
        game.getEvents().add(this);
        game.setGameStatus(DRAW);
    }

    @Override
    public String toString() {
        return String.format("%s: The game ended in a draw",
                             this.getCreatedAt());
    }
}

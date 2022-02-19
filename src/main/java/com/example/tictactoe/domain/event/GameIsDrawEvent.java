package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.game.GameStatus;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.Value;

import java.time.Instant;

import static com.example.tictactoe.domain.game.GameStatus.DRAW;

@Value
public class GameIsDrawEvent implements Event {

    Instant created;
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
}

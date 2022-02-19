package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.game.GameStatus;
import lombok.Value;

import java.time.Instant;

import static com.example.tictactoe.domain.game.GameStatus.IN_PROGRESS;

@Value
public class GameIsInProgressEvent implements Event {

    Instant created;
    GameName gameName;

    public static GameIsInProgressEvent gameIsInProgressEvent(GameName gameName) {
        return new GameIsInProgressEvent(Instant.now(),
                                         gameName);
    }

    @Override
    public void updateGame(Game game) {
        game.getEvents().add(this);
        game.setGameStatus(IN_PROGRESS);
    }
}

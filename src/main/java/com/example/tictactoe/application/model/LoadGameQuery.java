package com.example.tictactoe.application.model;

import com.example.tictactoe.domain.game.GameName;
import lombok.Value;

@Value
public class LoadGameQuery {

    GameName gameName;

    public static LoadGameQuery loadGameQuery(GameName gameName) {
        return new LoadGameQuery(gameName);
    }

    public GameName gameName() {
        return gameName;
    }
}

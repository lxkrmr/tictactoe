package com.example.tictactoe.domain.game;

import lombok.Value;

@Value
public class GameName {

    String value;

    public static GameName gameName(String name) {
        return new GameName(name);
    }
}

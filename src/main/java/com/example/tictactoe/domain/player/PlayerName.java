package com.example.tictactoe.domain.player;

import lombok.Value;

@Value
public class PlayerName {

    String value;

    public static PlayerName playerName(String name) {
        return new PlayerName(name);
    }
}

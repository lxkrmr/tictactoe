package com.example.tictactoe.application.model;

import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.Value;

@Value
public class PlayerJoinsGameCommand {

    GameName gameName;
    PlayerName playerName;

    public static PlayerJoinsGameCommand playerJoinsGameCommand(GameName gameName,
                                                                PlayerName playerName) {
        return new PlayerJoinsGameCommand(gameName,
                                          playerName);
    }

    public GameName gameName() {
        return gameName;
    }

    public PlayerName playerName() {
        return playerName;
    }
}

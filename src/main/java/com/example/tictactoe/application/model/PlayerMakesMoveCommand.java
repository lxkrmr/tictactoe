package com.example.tictactoe.application.model;

import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.grid.Grid;
import com.example.tictactoe.domain.grid.GridCellNumber;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.Value;

@Value
public class PlayerMakesMoveCommand {

    GameName gameName;
    PlayerName playerName;
    GridCellNumber gridCellNumber;

    public static PlayerMakesMoveCommand playerMakesMoveCommand(GameName gameName,
                                                                PlayerName playerName,
                                                                GridCellNumber gridCellNumber) {
        return new PlayerMakesMoveCommand(gameName,
                                          playerName,
                                          gridCellNumber);
    }

    public GameName gameName() {
        return gameName;
    }

    public PlayerName playerName() {
        return playerName;
    }
}

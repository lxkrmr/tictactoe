package com.example.tictactoe.domain.player;

import com.example.tictactoe.domain.game.GameStatus;
import lombok.Value;

import static com.example.tictactoe.domain.player.NumberOfReloads.initNumberOfReloads;
import static com.example.tictactoe.domain.player.PlayersMark.O;
import static com.example.tictactoe.domain.player.PlayersMark.X;

@Value
public class Player {

    PlayerName playerName;
    NumberOfReloads numberOfReloads;
    PlayersMark playersMark;

    public static Player initPlayerOne(PlayerName playerName) {
        return new Player(playerName,
                          initNumberOfReloads(),
                          X);
    }

    public static Player initPlayerTwo(PlayerName playerName) {
        return new Player(playerName,
                          initNumberOfReloads(),
                          O);
    }

    public static Player initSpectator(PlayerName playerName) {
        return new Player(playerName,
                          null,
                          null);
    }

    public GameStatus toWonStatus() {
        return playersMark.toGameStatusWon();
    }
}

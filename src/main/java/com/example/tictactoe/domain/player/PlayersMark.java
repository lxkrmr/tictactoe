package com.example.tictactoe.domain.player;

import com.example.tictactoe.domain.game.GameStatus;

import static com.example.tictactoe.domain.game.GameStatus.*;
import static com.example.tictactoe.domain.game.GameStatus.PLAYER_TWO_WON;

public enum PlayersMark {
    X, O;

    public GameStatus toGameStatusWon() {
        return X.equals(this) ? PLAYER_ONE_WON : PLAYER_TWO_WON;
    }
}

package com.example.tictactoe.domain.game;

public enum GameStatus {
    CREATED,
    IN_PROGRESS,
    PLAYER_ONE_WON_BY_RELOADS,
    PLAYER_ONE_WON_BY_GETTING_A_TIC_TAC_TOE,
    PLAYER_TWO_WON_BY_RELOADS,
    PLAYER_TWO_WON_BY_GETTING_A_TIC_TAC_TOE,
    DRAW
}

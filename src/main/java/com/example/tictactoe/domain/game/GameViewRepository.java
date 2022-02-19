package com.example.tictactoe.domain.game;

public interface GameViewRepository {

    GameView load(GameName gameName);

}

package com.example.tictactoe.domain.game;

public interface GameRepository {

    Game load(GameName gameName);

}

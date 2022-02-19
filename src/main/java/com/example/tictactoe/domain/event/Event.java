package com.example.tictactoe.domain.event;

import com.example.tictactoe.domain.game.Game;

public interface Event {

    void updateGame(Game game);
}

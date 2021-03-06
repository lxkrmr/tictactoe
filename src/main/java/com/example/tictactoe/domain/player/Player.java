package com.example.tictactoe.domain.player;

import com.example.tictactoe.domain.event.Event;
import com.example.tictactoe.domain.event.PlayerWonEvent;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.game.GameStatus;
import lombok.Value;

import java.util.Optional;

import static com.example.tictactoe.domain.event.PlayerWonEvent.playerWonEvent;
import static com.example.tictactoe.domain.game.GameStatus.PLAYER_ONE_WON_BY_RELOADS;
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

    public Optional<Event> maybeHasEnoughReloadsToWin(GameName gameName, int numberOfReloadToWin) {
        if(numberOfReloadToWin == numberOfReloads.getValue()) {
            return Optional.of(playerWonEvent(gameName, playerName, PLAYER_ONE_WON_BY_RELOADS));
        }

        return Optional.empty();
    }
}

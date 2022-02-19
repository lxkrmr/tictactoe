package com.example.tictactoe.domain.grid;

import com.example.tictactoe.domain.event.Event;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.player.Player;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.example.tictactoe.domain.event.IllegalMoveEvent.illegalMoveEvent;
import static com.example.tictactoe.domain.event.PlayerMarkedCellEvent.playerMarkedCellEvent;
import static com.example.tictactoe.domain.player.PlayersMark.O;
import static com.example.tictactoe.domain.player.PlayersMark.X;

@Data
@AllArgsConstructor
public class GridCell {

    String value;

    public static GridCell initCell(String value) {
        return new GridCell(value);
    }

    public Event updateCell(GameName gameName,
                            Player player,
                            GridCellNumber gridCellNumber) {
        if (isInvalidUpdate()) {
            return illegalMoveEvent(gameName, player.getPlayerName(), gridCellNumber);
        }

        this.value = player.getPlayersMark().name();
        return playerMarkedCellEvent(gameName,
                                     player.getPlayerName(),
                                     gridCellNumber);
    }

    private boolean isInvalidUpdate() {
        return value.equals(X.name()) || value.equals(O.name());
    }
}

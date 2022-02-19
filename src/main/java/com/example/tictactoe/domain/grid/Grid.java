package com.example.tictactoe.domain.grid;

import com.example.tictactoe.domain.event.Event;
import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.player.Player;
import com.example.tictactoe.domain.player.PlayerName;
import com.example.tictactoe.domain.player.PlayersMark;
import lombok.Value;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.tictactoe.domain.event.GameIsDrawEvent.gameIsDrawEvent;
import static com.example.tictactoe.domain.event.IllegalMoveEvent.illegalMoveEvent;
import static com.example.tictactoe.domain.event.PlayerWonEvent.playerWonEvent;
import static com.example.tictactoe.domain.grid.GridCell.initCell;
import static com.example.tictactoe.domain.player.PlayersMark.O;
import static com.example.tictactoe.domain.player.PlayersMark.X;
import static java.lang.Boolean.TRUE;

@Value
public class Grid {

    List<GridCell> cells;

    public static Grid initGrid() {
        var cells = Stream.of(
            initCell("1"),
            initCell("2"),
            initCell("3"),
            initCell("4"),
            initCell("5"),
            initCell("6"),
            initCell("7"),
            initCell("8"),
            initCell("9")
        ).collect(Collectors.toList());

        return new Grid(cells);
    }

    public Event updateGridCell(GameName gameName, Player player, GridCellNumber gridCellNumber) {
        if (isInvalid(gridCellNumber)) {
            return illegalMoveEvent(gameName, player.getPlayerName(), gridCellNumber);
        }

        return cells.get(gridCellNumber.toIndex()).updateCell(gameName, player, gridCellNumber);
    }

    private boolean isInvalid(GridCellNumber gridCellNumber) {
        return !gridCellNumber.isValidIndex(cells.size());
    }

    public Optional<Event> maybeGameIsOver(GameName gameName, PlayerName playerName) {
        if (playerOneWon()) {
            return Optional.of(playerWonEvent(gameName, playerName));
        }

        if (playerTwoWon()) {
            return Optional.of(playerWonEvent(gameName, playerName));
        }

        if (isDraw()) {
            return Optional.of(gameIsDrawEvent(gameName));
        }

        return Optional.empty();
    }

    private boolean playerOneWon() {
        return playerWon(X);
    }

    private boolean playerTwoWon() {
        return playerWon(O);
    }

    private boolean isDraw() {
        return cells.stream()
                    .map(GridCell::getValue)
                    .allMatch(gridCellValue -> X.name().equals(gridCellValue) || O.name().equals(gridCellValue));
    }

    private boolean playerWon(PlayersMark playersMark) {
        return List.of(checkTriplet(firstRow(), playersMark),
                       checkTriplet(secondRow(), playersMark),
                       checkTriplet(thirdRow(), playersMark),
                       checkTriplet(firstColumn(), playersMark),
                       checkTriplet(secondColumn(), playersMark),
                       checkTriplet(thirdColumn(), playersMark),
                       checkTriplet(firstDiagonal(), playersMark),
                       checkTriplet(secondDiagonal(), playersMark))
                   .contains(TRUE);
    }

    private boolean checkTriplet(List<GridCell> triplet, PlayersMark playersMark) {
        return triplet.stream()
                      .map(GridCell::getValue)
                      .allMatch(gridCellValue -> playersMark.name().equals(gridCellValue));
    }

    private List<GridCell> firstRow() {
        return triplet(0, 1, 2);
    }

    private List<GridCell> secondRow() {
        return triplet(3, 4, 5);
    }

    private List<GridCell> thirdRow() {
        return triplet(6, 7, 8);
    }

    private List<GridCell> firstColumn() {
        return triplet(0, 3, 6);
    }

    private List<GridCell> secondColumn() {
        return triplet(1, 4, 7);
    }

    private List<GridCell> thirdColumn() {
        return triplet(2, 5, 8);
    }

    private List<GridCell> firstDiagonal() {
        return triplet(0, 4, 8);
    }

    private List<GridCell> secondDiagonal() {
        return triplet(2, 4, 6);
    }

    private List<GridCell> triplet(int firstIndex,
                                   int secondIndex,
                                   int thirdIndex) {
        return List.of(cells.get(firstIndex),
                       cells.get(secondIndex),
                       cells.get(thirdIndex));
    }
}

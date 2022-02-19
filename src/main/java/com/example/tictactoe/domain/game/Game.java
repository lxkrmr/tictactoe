package com.example.tictactoe.domain.game;

import com.example.tictactoe.domain.event.Event;
import com.example.tictactoe.domain.event.IllegalMoveEvent;
import com.example.tictactoe.domain.grid.Grid;
import com.example.tictactoe.domain.grid.GridCellNumber;
import com.example.tictactoe.domain.player.NumberOfReloads;
import com.example.tictactoe.domain.player.Player;
import com.example.tictactoe.domain.player.PlayerName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static com.example.tictactoe.domain.event.GameIsInProgressEvent.gameIsInProgressEvent;
import static com.example.tictactoe.domain.event.IllegalMoveEvent.illegalMoveEvent;
import static com.example.tictactoe.domain.event.PlayerHasAlreadyJoinedTheGameEvent.playerHasAlreadyJoinedTheGameEvent;
import static com.example.tictactoe.domain.event.PlayerHasIncrementedReloadCountEvent.playerHasIncrementedReloadCountEvent;
import static com.example.tictactoe.domain.event.PlayerJoinedTheGameEvent.playerJoinedTheGameEvent;
import static com.example.tictactoe.domain.event.PlayerTurnEndedEvent.playerTurnEndedEvent;
import static com.example.tictactoe.domain.game.GameStatus.CREATED;
import static com.example.tictactoe.domain.game.GameStatus.IN_PROGRESS;
import static com.example.tictactoe.domain.grid.Grid.initGrid;

@Data
@AllArgsConstructor
public class Game {

    public static final int NUMBER_OF_RELOAD_TO_WIN = 60;

    GameName gameName;
    GameStatus gameStatus;
    PlayerName playersTurn;
    Grid grid;
    Player playerOne;
    Player playerTwo;
    List<Event> events;
    List<Player> spectators;

    public static Game initGame(GameName gameName) {
        return new Game(gameName,
                        CREATED,
                        null,
                        initGrid(),
                        null,
                        null,
                        new ArrayList<>(),
                        new ArrayList<>());
    }

    public List<Event> playerJoinsTheGame(GameName gameName, PlayerName playerName) {
        if (playerHasAlreadyJoined(playerName)) {
            return List.of(playerHasAlreadyJoinedTheGameEvent(gameName, playerName));
        }

        if (hasGameStarted()) {
            return List.of(playerJoinedTheGameEvent(gameName, playerName),
                           gameIsInProgressEvent(gameName));
        }

        return List.of(playerJoinedTheGameEvent(gameName,
                                                playerName));
    }

    private boolean playerHasAlreadyJoined(PlayerName playerName) {
        return maybePlayerByPlayerName(playerName).isPresent()
            || spectators.stream()
                         .map(Player::getPlayerName)
                         .anyMatch(spectatorName -> spectatorName.equals(playerName));
    }

    private boolean hasGameStarted() {
        return playerOne != null && playerTwo != null;
    }

    private Optional<Player> maybePlayerByPlayerName(PlayerName playerName) {
        return Stream.of(playerOne, playerTwo)
                     .filter(Objects::nonNull)
                     .filter(player -> playerName.equals(player.getPlayerName()))
                     .findFirst();
    }

    public List<Event> playerMakesMove(GameName gameName, PlayerName playerName, GridCellNumber gridCellNumber) {
        var maybePlayerOneOrTwo = maybePlayerByPlayerName(playerName);
        if (maybePlayerOneOrTwo.isEmpty() || isGameNotPlayable()) {
            return List.of(illegalMoveEvent(gameName,
                                            playerName,
                                            gridCellNumber));
        }

        if (isNotPlayersTurn(playerName)) {
            var playerHasIncrementedReloadCountEvent = playerHasIncrementedReloadCountEvent(gameName,
                                                                                            playerName);
            return maybeGameOverByReloads(gameName).map(gameEndedEvent -> List.of(playerHasIncrementedReloadCountEvent, gameEndedEvent))
                                                   .orElseGet(() -> List.of(playerHasIncrementedReloadCountEvent));
        }

        var resultOfMakeMove = makeMove(gameName, maybePlayerOneOrTwo.get(), gridCellNumber);
        if (resultOfMakeMove instanceof IllegalMoveEvent) {
            return List.of(resultOfMakeMove);
        }

        return maybeGameOver(gameName, playerName).map(gameEndedEvent -> List.of(resultOfMakeMove, gameEndedEvent))
                                                  .orElseGet(() -> List.of(resultOfMakeMove, playerTurnEndedEvent(gameName, playerName)));
    }

    private boolean isGameNotPlayable() {
        return gameStatus.equals(IN_PROGRESS);
    }

    private boolean isNotPlayersTurn(PlayerName playerNameFromCommand) {
        return Optional.ofNullable(playersTurn)
                       .filter(playerName -> playerName.equals(playerNameFromCommand))
                       .isEmpty();
    }

    private Optional<Event> maybeGameOverByReloads(GameName gameName) {
        return playerOne.maybeHasEnoughReloadsToWin(gameName, NUMBER_OF_RELOAD_TO_WIN)
                        .or(() -> playerTwo.maybeHasEnoughReloadsToWin(gameName, NUMBER_OF_RELOAD_TO_WIN));
    }

    private Event makeMove(GameName gameName, Player player, GridCellNumber gridCellNumber) {
        return grid.updateGridCell(gameName, player, gridCellNumber);
    }

    private Optional<Event> maybeGameOver(GameName gameName, PlayerName playerName) {
        return maybeGameOverByReloads(gameName).or(() -> grid.maybeGameIsOver(gameName, playerName));
    }

    public void incrementNumberOfReloadsByPlayerName(PlayerName playerName) {
        maybePlayerByPlayerName(playerName).map(Player::getNumberOfReloads)
                                           .ifPresent(NumberOfReloads::increment);
    }

    public void playerTurnEnded(PlayerName playerName) {
        if (playerOne.getPlayerName().equals(playerName)) {
            playersTurn = playerTwo.getPlayerName();
            playerTwo.getNumberOfReloads().reset();
            return;
        }

        playersTurn = playerOne.getPlayerName();
        playerOne.getNumberOfReloads().reset();
    }


}

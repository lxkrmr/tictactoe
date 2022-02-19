package com.example.tictactoe.domain.game;

import lombok.Value;

import static com.example.tictactoe.domain.event.EventsView.eventsView;
import static com.example.tictactoe.domain.game.GameStatus.CREATED;
import static com.example.tictactoe.domain.game.GameStatus.IN_PROGRESS;
import static com.example.tictactoe.domain.grid.GridView.gridView;
import static com.example.tictactoe.domain.player.PlayerView.playerView;
import static com.example.tictactoe.domain.player.PlayersTurnView.playersTurnView;
import static com.example.tictactoe.domain.player.SpectatorsView.spectatorsView;

@Value
public class GameView {

    public static final String TEMPLATE = String.join("\n",
                                                      "Name of the Game: %s",
                                                      "Status of the Game: %s",
                                                      "",
                                                      "Players turn: %s",
                                                      "",
                                                      "The 3x3 grid:",
                                                      "",
                                                      "%s",
                                                      "",
                                                      " Player One: %s",
                                                      " Player Two: %s",
                                                      "",
                                                      "Need some help?",
                                                      "%s",
                                                      "",
                                                      "Spectators:",
                                                      "%s",
                                                      "",
                                                      "Events:",
                                                      "%s");
    String view;

    public static GameView gameView(Game game) {
        var view = String.format(TEMPLATE,
                                 game.getGameName().getValue(),
                                 game.getGameStatus(),
                                 playersTurnView(game.getPlayersTurn()).getView(),
                                 gridView(game.getGrid()).getView(),
                                 playerView(game.getPlayerOne()).getView(),
                                 playerView(game.getPlayerTwo()).getView(),
                                 giveAHelpingHand(game),
                                 spectatorsView(game.getSpectators()).getView(),
                                 eventsView(game.getEvents()).getView()
        );

        return new GameView(view);
    }

    private static String giveAHelpingHand(Game game) {
        if (game.getPlayerOne() == null || game.getPlayerTwo() == null) {
            return timeToJoinTheGame();
        }

        if (CREATED.equals(game.getGameStatus())) {
            return timeToMakeTheFirstMove();
        }

        if (!IN_PROGRESS.equals(game.getGameStatus())) {
            return justPlayAnotherRound();
        }

        return keepInMind();
    }

    private static String timeToJoinTheGame() {
        return String.join("\n",
                           "Nice, great job creating this awesome new game.",
                           "Now it is time to introduce yourself and invite either a friend or nemesis to play with you.",
                           "For this we are going to extend the url even further by adding your name like this:",
                           "http://localhost:8080/tictactoe/{nameForTheGameEitherYoursOrMine}/{yourName}",
                           "",
                           "and your friend / nemesis can join this game by doing the same with her/his name:",
                           "http://localhost:8080/tictactoe/{nameForTheGameEitherYoursOrMine}/{nameOfFriendOrNemesis}",
                           "",
                           "And if you like to be watched, then don't be shy and share your link with other as well.",
                           "Then will be visible in the spectators section,",
                           "but please make sure that they will enter after your friend / nemesis.",
                           "Otherwise I get confused and you have to play against someone else.",
                           "",
                           "Important: Please make sure to press F5 to reload the page,",
                           "otherwise you won't see that your friends nemesis has already joined",
                           "and that I am ready for your first move");
    }

    private static String timeToMakeTheFirstMove() {
        return String.join("\n",
                           "Wonderful! Now Player One can make his first move.",
                           "Most likely you already guest it, for this you will extend the url again.",
                           "But first look at the 3x3 grid.",
                           "As both of you can see, each cell has a number.",
                           "So if you want to put your mark into a cell, then just provide this number like this",
                           "http://localhost:8080/tictactoe/{nameForTheGameEitherYoursOrMine}/{yourName}/{numberOfTheCell}",
                           "",
                           "For example if you want to put your mark into the middle of the grid, then you would pick number 5:",
                           "http://localhost:8080/tictactoe/{nameForTheGameEitherYoursOrMine}/{nameOfFriendOrNemesis}/5");
    }

    private static String justPlayAnotherRound() {
        return String.join("\n",
                           "Perfect!",
                           "Hopefully this game made you hate each other even more.",
                           "This is how a friend becomes a nemesis!",
                           "Hopefully you still don't have enough, so go to this link to just have another match:",
                           "http://localhost:8080/tictactoe/");
    }

    private static String keepInMind() {
        return String.join("\n",
                           "Yes, now we are talking.",
                           "Keep in mind that you url should look like this from now on",
                           "http://localhost:8080/tictactoe/{nameForTheGameEitherYoursOrMine}/{yourName}/{numberOfTheCell}",
                           "",
                           "And to make thinks even more exciting, their is an additional twist waiting for you.",
                           "Instead of getting bored if it is not your turn, you can press F5 to reload the page.",
                           "This will increment your 'number of reloads' counter.",
                           "And as soon as you'll hit the threshold - YOU WIN!",
                           "Yes, you heard correct. Even if you are dum as ... a human, you can still win press smashing the button.",
                           "So act fast when it is your turn!");
    }

}

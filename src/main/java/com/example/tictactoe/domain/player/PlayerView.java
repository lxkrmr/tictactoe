package com.example.tictactoe.domain.player;

import lombok.Value;

import static com.example.tictactoe.domain.game.Game.NUMBER_OF_RELOAD_TO_WIN;

@Value
public class PlayerView {

    public static final String TEMPLATE_PLAYER_GIVEN = "%s (number of reloads %s / %s)";
    public static final String TEMPLATE_PLAYER_MISSING = "waiting for player to join ...";
    String view;

    public static PlayerView playerView(Player player) {
        if (player == null) {
            return new PlayerView(TEMPLATE_PLAYER_MISSING);
        }

        return new PlayerView(String.format(TEMPLATE_PLAYER_GIVEN,
                                            player.getPlayerName().getValue(),
                                            player.getNumberOfReloads().getValue(),
                                            NUMBER_OF_RELOAD_TO_WIN));
    }

}

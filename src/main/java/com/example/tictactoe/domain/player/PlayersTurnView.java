package com.example.tictactoe.domain.player;

import lombok.Value;

import static org.springframework.util.StringUtils.hasText;

@Value
public class PlayersTurnView {

    public static final String TEMPLATE_PLAYER_NAME_GIVEN = "%s it is your turn";
    public static final String TEMPLATE_PLAYER_NAME_MISSING = "No player has joined the game yet.";

    String view;

    public static PlayersTurnView playersTurnView(PlayerName playerName) {
        if (playerName == null || !hasText(playerName.getValue())) {
            return new PlayersTurnView(TEMPLATE_PLAYER_NAME_MISSING);
        }

        return new PlayersTurnView(String.format(TEMPLATE_PLAYER_NAME_GIVEN, playerName.getValue()));
    }

}

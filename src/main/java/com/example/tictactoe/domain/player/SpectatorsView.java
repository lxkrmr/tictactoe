package com.example.tictactoe.domain.player;

import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class SpectatorsView {

    public static final String TEMPLATE_PLAYER_MISSING = "waiting for spectators to join ...";
    String view;

    public static SpectatorsView spectatorsView(List<Player> spectators) {
        if (spectators == null || spectators.isEmpty()) {
            return new SpectatorsView(TEMPLATE_PLAYER_MISSING);
        }

        var view = spectators.stream()
                             .map(Player::getPlayerName)
                             .map(PlayerName::getValue)
                             .collect(Collectors.joining("\n"));
        return new SpectatorsView(view);
    }
}

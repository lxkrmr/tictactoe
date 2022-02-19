package com.example.tictactoe.domain.event;

import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class EventsView {

    public static final String TEMPLATE_PLAYER_MISSING = "nothing did happen yet ...";
    String view;

    public static EventsView eventsView(List<Event> events) {
        if (events == null || events.isEmpty()) {
            return new EventsView(TEMPLATE_PLAYER_MISSING);
        }

        var view = events.stream()
                         .map(Event::toString)
                         .collect(Collectors.joining("\n"));
        return new EventsView(view);
    }
}

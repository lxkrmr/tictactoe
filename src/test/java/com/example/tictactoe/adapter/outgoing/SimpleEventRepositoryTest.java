package com.example.tictactoe.adapter.outgoing;

import com.example.tictactoe.domain.event.Event;
import org.junit.jupiter.api.Test;

import static com.example.tictactoe.domain.game.GameName.gameName;
import static org.assertj.core.api.Assertions.assertThat;


class SimpleEventRepositoryTest {

    private final SimpleEventRepository repository = new SimpleEventRepository();

    @Test
    void givenExistingEvent_whenLoadEvent_thenShouldFindExistingEvent() {
        // given
        var gameName = gameName("<gameName>");
        var event = new DummyEvent();
        repository.addEventByGameName(gameName, event);

        // when
        var result = repository.loadEventsByGameName(gameName);

        // then
        assertThat(result).containsExactly(event);
    }

    @Test
    void givenNoExistingEvent_whenLoadEvent_thenFindNothing() {
        // given
        var gameName = gameName("<gameName>");

        // when
        var result = repository.loadEventsByGameName(gameName);

        // then
        assertThat(result).isEmpty();
    }

    private static class DummyEvent implements Event {
    }
}

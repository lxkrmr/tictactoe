package com.example.tictactoe.domain.player;

import org.junit.jupiter.api.Test;

import static com.example.tictactoe.domain.player.NumberOfReloads.initNumberOfReloads;
import static org.assertj.core.api.Assertions.assertThat;


class NumberOfReloadsTest {

    @Test
    void whenInitializeNumberOfReloads_thenValueIsZero() {
        // when
        var result = initNumberOfReloads();

        // then
        assertThat(result.value()).isEqualTo(0);
    }

    @Test
    void givenNumberOfReloads_whenIncrement_thenValueIsOne() {
        // given
        var numberOfReloads = initNumberOfReloads();

        // when
        numberOfReloads.increment();

        // then
        assertThat(numberOfReloads.value()).isEqualTo(1);
    }

    @Test
    void givenIncrementedNumberOfReloads_whenReset_thenValueIsZero() {
        // given
        var numberOfReloads = initNumberOfReloads();
        numberOfReloads.increment();

        // when
        numberOfReloads.reset();

        // then
        assertThat(numberOfReloads.value()).isEqualTo(0);
    }
}

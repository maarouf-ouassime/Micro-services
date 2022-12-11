package com.example.bowlinggame.bowtest;

import com.example.bowlinggame.bow.BowlingGame;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {
    static BowlingGame game = new BowlingGame();

    @Test
    void worstGame(){
        rollZeros(20);
        int score = game.score();
        assertThat(score).isEqualTo(0);
    }

    private static void rollZeros(int x) {
        for (int i = 0; i < x; i++) {
            game.roll(0);
        }
    }

    @Test
    void onePin(){
        game.roll(1);
        rollZeros(19);
        int score = game.score();
        assertThat(score).isEqualTo(1);
    }

    @Test
    void tooLargeRoll(){
        assertThrows(IllegalArgumentException.class, () -> game.roll(11));

    }

    @Test
    void negativeRoll(){
        assertThrows(IllegalArgumentException.class, () -> game.roll(-1));

    }

    @Test
    void spare(){
        roll(5,5,1);
        rollZeros(17);
        assertThat(game.score()).isEqualTo(12);
    }

    @Test
    void twoSpare(){
        roll(5,5,5,5,1);
        rollZeros(15);
        assertThat(game.score()).isEqualTo(27);
    }

    private void roll(int ... pinsArray) {
        for (int pins : pinsArray) {
            game.roll(pins);
        }
    }

    @Test
    void nonSpare(){
        roll(0,5,5,1);
        rollZeros(16);
        assertThat(game.score()).isEqualTo(11);
    }

    @Test
    void strike(){
        roll(10,1,1);
        rollZeros(16);
        assertThat(game.score()).isEqualTo(12+2);
    }

    @Test
    void goodGame(){
        for (int i = 0; i < 10; i++) {
            game.roll(10);
        }
        assertThat(game.score()).isEqualTo(300);
    }



}

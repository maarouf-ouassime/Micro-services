package com.example.bowlinggame.bow2test;

import com.example.bowlinggame.bow2.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
         game = new Game();
    }

    // @Test
    public void canRollBall(){
        game.roll(0);
    }

    @Test
    public void canScoreGutterGame(){
        game.roll(0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0 ,0,0);
        assertEquals(0, game.score());
    }


    @Test
    public void canScoreGameOfOnes(){
        game.roll(1,1 ,1,1 ,1,1 ,1,1 ,1,1 ,1,1 ,1,1 ,1,1 ,1,1 ,1,1);
        assertEquals(20, game.score());
    }

    @Test
    public void canScoreSpareFollowedByThree(){
        game.roll(5,5 , 3,0 , 0,0 , 0,0 , 0,0 , 0,0 , 0,0 , 0,0 , 0,0 , 0,0);
        assertEquals(16, game.score());
    }


    @Test
    public void canScoreStrikeFollowedByThreeThenThree(){
        game.roll(10 , 3,3 , 0,0 , 0,0 , 0,0 , 0,0 , 0,0 , 0,0 , 0,0 , 0,0);
        assertEquals(22, game.score());
    }

    @Test
    public void canScorePerfectGame(){
        game.roll( 10 , 10 , 10 , 10 , 10 , 10 , 10 , 10 , 10 , 10 , 10 , 10);
        assertEquals(300, game.score());
    }

}

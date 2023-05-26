package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameTest {


    @Test
    public void test_legal_move(){
        Game game = new Game();
        game.rollDie();
        boolean moved = game.players[0].makeMove(0, game);
        assertTrue("Erro ao mover peão", moved);
    }

    @Test
    public void test_illegal_move(){
        Game game = new Game();
        game.rollDie();
        boolean moved = game.players[0].makeMove(1, game);
        assertFalse("Peão realizou movimento ilegal", moved);
    }

    @Test
    public void test_move_out_initial(){
        Game game = new Game();
        game.die.setDieNumber(5);
        game.players[0].makeMove(0, game);
        boolean moved = game.players[0].makeMove(1, game);
        System.out.println(game.players[0].getPlayerPawns()[1].getPosition());
        assertTrue("Peão não conseguiu mover fora da casa inicial", moved);
    }
}
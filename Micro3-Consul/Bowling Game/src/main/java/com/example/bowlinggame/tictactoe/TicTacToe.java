package com.example.bowlinggame.tictactoe;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TicTacToe {

    /*public void play(int x, int y) {
        if (x < 1 || x > 3) {
            throw new RuntimeException("X is outside board");
        } else if (y < 1 || y > 3) {
            throw new RuntimeException("Y is outside board");
        }
    }*/

    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};
    /*public void play(int x, int y) {
        if (x < 1 || x > 3) {
            throw
                    new RuntimeException("X is outside board");
        } else if (y < 1 || y > 3) {
            throw
                    new RuntimeException("Y is outside board");
        }
        if (board[x - 1][y - 1] != '\0') {
            throw
                    new RuntimeException("Box is occupied");
        } else {
            board[x - 1][y - 1] = 'X';
        }
    }*/

  /*  public void play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        setBox(x, y);
    }*/
    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException("X is outside board");
        }
    }
   /* private void setBox(int x, int y) {
        if (board[x - 1][y - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[x - 1][y - 1] = 'X';
        }
    }*/

    /*public char nextPlayer() {
        return 'X';
    }*/
    private char lastPlayer = '\0';
   /* public void play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        setBox(x, y);
        lastPlayer = nextPlayer();
    }*/
    public char nextPlayer() {
        if (lastPlayer == 'X') {
            return 'O';
        }
        return 'X';
    }
   /* public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        setBox(x, y);
        lastPlayer = nextPlayer();
        return "No winner";
    }*/

    /*public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(x, y, lastPlayer);
        for (int index = 0; index < 3; index++) {
            if (board[0][index] == lastPlayer &&
                    board[1][index] == lastPlayer &&
                    board[2][index] == lastPlayer) {
                return lastPlayer + " is the winner";
            }
        }
        return "No winner";
    }*/
    private void setBox(int x, int y, char lastPlayer)
    {
        if (board[x - 1][y - 1] != '\0') {
            throw new RuntimeException("Box is occupied");
        } else {
            board[x - 1][y - 1] = lastPlayer;
        }
    }
    private static final int SIZE = 3;
   /* public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(x, y, lastPlayer);
        if (isWin()) {
            return lastPlayer + " is the winner";
        }
        return "No winner";
    }*/
    /*private boolean isWin() {
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] + board[1][i] + board[2][i] == (lastPlayer * SIZE)) {
                return true;
            }
        }
        return false;
    }*/
   /* private boolean isWin() {
        int playerTotal = lastPlayer * 3;
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] + board[1][i] + board[2][i] == playerTotal) {
                return true;
            } else if (board[i][0] + board[i][1] + board[i][2] == playerTotal) {
                return true;
            }
        }
        return false;
    }*/
   /* private boolean isWin() {
        int playerTotal = lastPlayer * 3;
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] + board[1][i] + board[2][i] == playerTotal) {
                return true;
            } else if (board[i][0] + board[i][1] + board[i][2] == playerTotal)
            {
                return true;
            }
        }
        if ((board[0][0] + board[1][1] + board[2][2])
                == playerTotal) {
            return true;
        }
        return false;
    }*/
    /*private boolean isWin() {
        int playerTotal = lastPlayer * 3;
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] + board[1][i] + board[2][i] == playerTotal) {
                return true;
            } else if (board[i][0] + board[i][1] + board[i][2] == playerTotal)
            {
                return true;
            }
        }
        if ((board[0][0] + board[1][1] + board[2][2])
                == playerTotal) {
            return true;
        } else if (playerTotal == (board[0][2] + board[1][1] +
                board[2][0])) {
            return true;
        }
        return false;
    }*/
    private boolean isWin() {
        int playerTotal = lastPlayer * 3;
        char diagonal1 = '\0';
        char diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
            if (board[0][i] + board[1][i] + board[2][i] == playerTotal) {
                return true;
            } else if (board[i][0] + board[i][1] + board[i][2] == playerTotal) {
                return true;
            }
        }
        if (diagonal1 == playerTotal || diagonal2 == playerTotal) {
            return true;
        }
        return false;
    }
    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(x, y, lastPlayer);
        if (isWin()) {
            return lastPlayer + " is the winner";
        } else if (isDraw()) {
            return "The result is draw";
        } else {
            return "No winner";
        }
    }
    private boolean isDraw() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == '\0') {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isWin(int x, int y) {
        int playerTotal = lastPlayer * 3;
        char horizontal, vertical, diagonal1, diagonal2;
        horizontal = vertical = diagonal1 = diagonal2 = '\0';
        for (int i = 0; i < SIZE; i++) {
            horizontal += board[i][y - 1];
            vertical += board[x - 1][i];
            diagonal1 += board[i][i];
            diagonal2 += board[i][SIZE - i - 1];
        }
        if (horizontal == playerTotal
                || vertical == playerTotal
                || diagonal1 == playerTotal
                || diagonal2 == playerTotal) {
            return true;
        }
        return false;
    }


}

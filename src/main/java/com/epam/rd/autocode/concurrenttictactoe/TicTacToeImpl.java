package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;

public class TicTacToeImpl implements TicTacToe {

    final static char xMark ='X';
    final static char oMark ='O';
    char prevMark = oMark;
    char[][] board;

    public TicTacToeImpl() {
        newBoard();
    }

    @Override
    public void setMark(int x, int y, char mark) {
        if(x>2||y>2||(mark!= xMark &&mark!= oMark))
            throw new IllegalArgumentException();
        if (board[x][y]==' ') {
            board[x][y] = mark;
            prevMark = mark;
        }
        else
            throw new IllegalArgumentException();
    }

    @Override
    public char[][] table() {
        return Arrays.stream(board).map(char[]::clone).toArray(char[][]::new);
    }

    @Override
    public char lastMark() {
        return prevMark;
    }

    private void newBoard() {
        board = new char[][]{{' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '},
        };
    }
}
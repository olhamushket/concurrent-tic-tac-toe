package com.epam.rd.autocode.concurrenttictactoe;

public class PlayerImpl implements Player {

    final TicTacToe ticTacToe;
    final char mark;
    PlayerStrategy strategy;

    public PlayerImpl(TicTacToe ticTacToe, char mark, PlayerStrategy strategy) {
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy = strategy;
    }

    @Override
    public boolean wonBoard(char[][] table) {
        final char xMark = 'X';
        final char oMark = 'O';

        for(int i=0; i<=2;i++){
            if(table[0][i]==xMark&&table[1][i]==xMark&&table[2][i]==xMark||table[0][i]==oMark&&table[1][i]==oMark&&table[2][i]==oMark)
                return true;
            if(table[i][0]==xMark&&table[i][1]==xMark&&table[i][2]==xMark||table[i][0]==oMark&&table[i][1]==oMark&&table[i][2]==oMark)
                return true;
        }
        return table[0][0] == xMark && table[1][1] == xMark && table[2][2] == xMark
                || table[0][2] == xMark && table[1][1] == xMark && table[2][0] == xMark
                || table[0][0] == oMark && table[1][1] == oMark && table[2][2] == oMark
                || table[0][2] == oMark && table[1][1] == oMark && table[2][0] == oMark;
    }

    @Override
    public void run() {
        synchronized (ticTacToe) {
            try {
                while (!wonBoard(ticTacToe.table())) {
                    while (mark == ticTacToe.lastMark()) {
                        ticTacToe.wait();
                    }
                    if ((!wonBoard(ticTacToe.table())))
                        ticTacToe.setMark(strategy.computeMove(mark, ticTacToe).row, strategy.computeMove(mark, ticTacToe).column, mark);
                    ticTacToe.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }}}
}
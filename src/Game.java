public class Game {

    char[][] mat = new char[3][3];

    int turn;
    boolean gameEnd;
    int winner;


    public Game() {
        init();
    }

    public int getWinner() {
        return winner;
    }

    public int getTurn() {
        return turn;
    }

    public void init() {
        gameEnd = false;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                mat[i][j] = '-';
            }
        }
        turn = 0;
        winner = 2;//None, -1 - tiko, 0 - x , 1 -circle
    }

    public char play(boolean isFirstPlayer, int i, int j) {
        if (gameEnd) {
            return '-';
        }
        if (isFirstPlayer) {
            if (turn == 0)
                if (mat[i][j] == '-') {
                    mat[i][j] = 'X';
                    printMatrix();
                    gameEnd = isWin();
                    if(gameEnd) {
                        System.out.println("45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,");
                        winner = turn;
                    }
                    turn++;
                    turn %= 2;
                    return 'X';
                }
        } else {
            if (turn == 1)
                if (mat[i][j] == '-') {
                    mat[i][j] = 'O';
                    printMatrix();
                    gameEnd = isWin();
                    if(gameEnd) {
                        System.out.println("59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,59,");

                        winner = turn;
                    }

                    turn++;
                    turn %= 2;
                    return 'O';
                }
        }
        printMatrix();
        return '-';
    }

//    private void checkWin() {
//        checkRows();
//        checkCols();
//        checkMainDiagonale();
//        chackSecondDiagonale();
//    }

//    private void checkRows() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 1; j < 3; j++) {
//                if (mat[i][0] == '-') {
//                    break;
//                } else {
//                    if (mat[i][j] != mat[i][0])
//                        break;
//                    else {
//                        if (j == 2) {
//                            gameEnd = true;
//                            turn++;
//                            turn %= 2;
//                            winner = turn;
//                            return;
//                        }
//                    }
//                }
//            }
//        }
//    }

    private void checkCols() {
    }

    private void checkMainDiagonale() {
    }

    private void chackSecondDiagonale() {
    }

    public void printMatrix() {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    public char[][] getMat() {
        return mat;
    }

    public boolean isWin() {
        boolean result = true;
        //check rows
        for (int i = 0; i < mat.length; i++) {
            result = true;
            for (int j = 0; j < mat.length - 1; j++) {
                if (mat[i][j] != mat[i][j + 1])
                    result = false;
                if (mat[i][j] == '-')
                    result = false;
            }
            if (result) {
                System.out.println("-------------------------rows");
                return true;
            }
        }

        //check cols
        for (int i = 0; i < mat.length; i++) {
            result = true;

            for (int j = 0; j < mat.length-1 ; j++) {
                if (mat[j][i] != mat[j+1][i])
                    result = false;
                if (mat[j][i] == '-') {
                    result = false;
                }
            }
            if (result) {
                System.out.println("-------------------------cols");
                return true;
            }
        }

        //check main diagonal
        result = true;
        char first = mat[0][0];
        if (first != '-') {
            for (int i = 0; i < mat.length - 1; i++)
                if (mat[i][i] != mat[i + 1][i + 1])
                    result = false;
        } else {
            result = false;
        }
        if (result) {
            System.out.println("-------------------------rows");
            return true;
        }

//check secondary diagonal
        result = true;
        for(int i=1;i<mat.length;i++)
        {
            if(mat[i][mat.length-i-1]!=mat[i-1][mat.length-i])
                result = false;
            if(mat[i][mat.length-i-1]=='-')
                result = false;
        }



        if(result)
        System.out.println("-------------------------secondary diagonal");

        return result;
    }
}

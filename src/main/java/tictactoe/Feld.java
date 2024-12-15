package tictactoe;

public class Feld {
    private String[][] gameBoard;
    final String[][] displayBoard = {
            {" 1 ", " 2 ", " 3 "},
            {" 4 ", " 5 ", " 6 "},
            {" 7 ", " 8 ", " 9 "}
    };

    Feld() {
        gameBoard = new String[][]{
                {"   ", "   ", "   "},
                {"   ", "   ", "   "},
                {"   ", "   ", "   "}
        };
    }

    public void printBoard() {
        System.out.println("Anhand der linken Graphik das Spiel steuern");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + displayBoard[i][0] + " | " + displayBoard[i][1] + " | " + displayBoard[i][2]
                    + "\t\t" + " " + gameBoard[i][0] + " | " + gameBoard[i][1] + " | " + gameBoard[i][2]);
            if (i < 2) {
                System.out.println("---+---+---+----" + "\t\t" + "---+---+---+----");
            }
        }
    }

    private boolean isMarkerValid(Position markerPos){
        // TODO: ...
        return false;
    }
    // TODO: eigene Funktion schreiben um die Umrechnung der Position zu machen
    // 5
    // feld[1][1]
    // 5 / 3 = 1.666667
    // Als Integer: Alles nach dem Komma fällt weg !
    // (int) 5/3 = 1
    // 8 / 3 = 2

    // Vor Dezimalzahlen Teilen:;
    // 5 / 3 = 1 R 2
    // Modulo:
    // 5 % 3 = 2
    // 8 % 3 = 2
    // falls die zahlen 3, 6, 9 vorkommen .... -> sonderpositionierung ?
    // TODO: Test schreiben ob position richtig gesetzt wird
    public void setMarker(String marker, Integer position) {
        String[][] feld = new String[3][3];
        if (position < 1 || position > 9) {
            System.out.println("Die Position muss zwischen 1 und 9 liegen");
        }
        int row = (int) (position - 1) / 3;
        int column = (position - 1) % 3;

        if (position % 3 == 0) {
            row = (position - 1);
            column = 2;
        }

        if (feld[row][column] == null) {
            feld[row][column] = marker;
        } else {
            System.out.println("Die Position ist bereits belegt");
        }
    }

    public Boolean unentschieden(){
        int tmp = 0;
        // TODO: überprüfe pbgame
        gameBoard[0][0].contains("X");
        for (int i = 0; i < gameBoard.length; ++i){
            for(int j = 0; j < gameBoard.length; ++j){
                if (gameBoard[i][j].contains("X") || (gameBoard[i][j].contains("O"))) {
                    ++tmp;
                }
            }
        }
        return tmp == 9;
    }
}

package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class Feld {
    private String[][] gameBoard;
    public int zähler = 0;
    public String lastMarker = "";
    final String[][] displayBoard = {
            {" 1 ", " 2 ", " 3 "},
            {" 4 ", " 5 ", " 6 "},
            {" 7 ", " 8 ", " 9 "}
    };
    //( 0, [0,0] [1,1] [2,2]]
    private final Map<Integer, List<Position>> gewinnPositionen =
            Map.ofEntries(
                    // Diagonalen
                    entry(0, Arrays.asList(new Position(0, 0), new Position(1, 1), new Position(2, 2))),
                    entry(1, Arrays.asList(new Position(0, 2), new Position(1, 1), new Position(2, 0))),
                    // Waagerechten
                    entry(2, Arrays.asList(new Position(0, 0), new Position(0, 1), new Position(0, 2))),
                    entry(3, Arrays.asList(new Position(1, 0), new Position(1, 1), new Position(1, 2))),
                    entry(4, Arrays.asList(new Position(2, 0), new Position(2, 1), new Position(2, 2))),
                    // Senkrechten
                    entry(5, Arrays.asList(new Position(0, 0), new Position(1, 0), new Position(2, 0))),
                    entry(6, Arrays.asList(new Position(0, 1), new Position(1, 1), new Position(2, 1))),
                    entry(7, Arrays.asList(new Position(0, 2), new Position(1, 2), new Position(2, 2)))
            );


    public Feld() {
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
    public void setMarker(String marker, int position) {
        // Überprüfen, ob die Position zwischen 1 und 9 liegt
        if (position < 1 || position > 9) {
            throw new IllegalArgumentException("Die Position muss zwischen 1 und 9 liegen.");
        }

        // Berechne die Zeile und Spalte für die Position
        int row = (position - 1) / 3;
        int column = (position - 1) % 3;

        // Überprüfen, ob die Position bereits belegt ist -> ist dort ein X oder ein O
        if (gameBoard[row][column].contains("X")
                || gameBoard[row][column].contains("O")) {
            return;
        }

        // Marker auf das Spielfeld setzen
        gameBoard[row][column] = " "+ marker + " ";
        lastMarker = marker;
        // Zähler erhöhen
        zähler++;
    }


    public Boolean unentschieden(){
        if(zähler < 9){
            return false;
        }
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
        if(tmp == 9){
            System.out.println("UNENTSCHIEDEN");
        }
        return tmp == 9;
    }
    public Boolean vorbei(){
        if(zähler<5){
            return false;
        }
        for (Map.Entry<Integer, List<Position>> ee : gewinnPositionen.entrySet()) {
            List<Position> positions = ee.getValue();
            int tmp = 0;
            for(Position pos : positions){
                if(gameBoard[pos.getX()][pos.getY()].contains(lastMarker)){
                    ++tmp;
                }
                if(tmp == 3){
                    System.out.println("Spiel wurde gewonnen von "+  lastMarker);
                    return true;
                }
            }
        }
        return false;
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }
    public int getZähler() {
        return zähler;
    }
    public void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
    public Boolean positionKorrekt(Integer pos) {

        if (pos < 1 || pos > 9) {
            return false;
        }


        int row = (pos - 1) / 3;
        int col = (pos - 1) % 3;


        return gameBoard[row][col].isBlank();
    }



}

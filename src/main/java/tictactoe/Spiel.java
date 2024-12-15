package tictactoe;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class Spiel {
    private Feld feld;
    private Integer playerScore;
    private Integer computerScore;
    private Boolean gameOn = true;
    private final String regex = "^[1-9]$";
    private Random rand;

    Scanner scan;

    private Boolean spielGewonnen(){
        // TODO: überprüfe ob ein Gewinner existiert
        return true;
    }

    private Boolean unentschieden(){
        return feld.
    }

    // TODO: Logik aufräumen, so dass nur eine Funktion benötigt wird
    private void computerZug() {
        Boolean inEingabe  = true;
        Integer pos;
        while (inEingabe) {
            pos = rand.nextInt(10);
            if(positionKorrekt(pos)) {
                feld.setMarker("O", pos);
                inEingabe = false  ;
            }
        }
    }

    private void spielerZug(){
        Boolean inEingabe  = true;
        Integer pos;
        while(inEingabe){
            pos = Integer.valueOf(scan.next(regex));
            if (positionKorrekt(Integer.valueOf(pos))) {
                feld.setMarker("X", pos);
                inEingabe = false;
            }
        }
    }

    Boolean positionKorrekt(Integer pos){
        return true;
        // TODO: delegieren ans Feld
    }



    void zug(){
        spielerZug();
        feld.unentschieden();
        computerZug();
    }

    Spiel(){
        feld = new Feld();
        scan = new Scanner(System.in);
        rand = new Random(System.currentTimeMillis() / 1000L);
        while(gameOn){
            feld.printBoard();
            zug();
        }
    }
}

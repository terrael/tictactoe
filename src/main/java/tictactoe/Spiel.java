package tictactoe;

import java.util.*;

public class Spiel {
    private Feld feld;
    private Integer playerScore;
    private Integer computerScore;
    private Boolean gameOn = true;
    private final String regex = "^[1-9]$";
    private Random rand;
    Scanner scan;
    private Boolean spielGewonnen(){
        return feld.vorbei();
    }

    private Boolean unentschieden(){
        return feld.unentschieden();
    }

    private void Zug(SpielerTyp spielerTyp) {
        if (feld.vorbei() || feld.unentschieden()) {
            return;
        }
        Boolean inEingabe = true;
        Integer pos = 0;
        // (Kondition) ? "X" : "O"
        // (Kondition) falls dann sonst
        // if(spielerTyp == SpielerTyp.SPIELER){"X"}else{"O"}
        String marker = (spielerTyp == SpielerTyp.SPIELER)
                ? "X"
                : "O";
        while (inEingabe) {
            switch(spielerTyp){
                case COMPUTER -> {
                    pos = rand.nextInt(10);
                    inEingabe = !positionKorrekt(pos);
                }
                case SPIELER -> {
                    // TODO: Bug bei falscher Eingab
                    pos = Integer.valueOf(scan.next(regex));
                    inEingabe = !positionKorrekt(pos);
                }
            }
        }feld.setMarker( marker, pos);
    }

    // TODO: XXO -> game over aus soll nur bei XXX oder OOO passieren
    Boolean positionKorrekt(Integer pos){
        if (pos < 1 || pos > 9) {
            System.out.println("Die Position muss zwischen 1 und 9 liegen.");
            return false;
        }

        if (!feld.positionKorrekt(pos)) {
            System.out.println("Die Position ist bereits belegt. Bitte wähle eine andere.");
            return false;
        }

        return true;
    }



    Boolean spielLäuft(){
        return !feld.unentschieden() && !feld.vorbei();
    }

    Spiel(){
        feld = new Feld();
        rand = new Random(System.currentTimeMillis() / 1000L);
        feld.printBoard();
        while(gameOn){
            scan = new Scanner(System.in);
            Zug(SpielerTyp.SPIELER);
            Zug(SpielerTyp.COMPUTER);
            gameOn = spielLäuft();
            feld.printBoard();
        }
        // TODO: Gewinn oder Verlust bzw Unentschieden sollen geprinted werden
    }
}

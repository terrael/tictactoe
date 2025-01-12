package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.Feld;
import tictactoe.Position;
import tictactoe.Spiel;
import static org.junit.jupiter.api.Assertions.*;

class FeldTest {
    private Feld feld;

    @BeforeEach
    void setUp() {
        feld = new Feld();
    }
    @Test
    void testSetMarkerInvalidPosition() {
        assertThrows(IllegalArgumentException.class, () -> feld.setMarker("X", 0));
        assertThrows(IllegalArgumentException.class, () -> feld.setMarker("X", 10));
    }

    @Test
    void testSetMarkerAlreadyOccupied() {
        feld.setMarker("X", 1);


        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            feld.setMarker("O", 1);
        });


        assertEquals("Die Position ist bereits belegt.", exception.getMessage());


        assertEquals(" X ", feld.getGameBoard()[0][0]);
        assertEquals(1, feld.getZähler());
    }
    @Test
    void testUnentschiedenFalse() {
        feld.zähler = 8;
        assertFalse(feld.unentschieden());
    }

    @Test
    void testUnentschiedenTrue() {
        feld.zähler = 9;
        feld.getGameBoard()[0] = new String[]{"X", "O", "X"};
        feld.getGameBoard()[1] = new String[]{"O", "X", "O"};
        feld.getGameBoard()[2] = new String[]{"O", "X", "O"};

        assertTrue(feld.unentschieden());
    }

    @Test
    void testVorbeiFalse() {
        feld.zähler = 4;
        assertFalse(feld.vorbei());
    }

    @Test
    void testVorbeiTrueHorizontalWin() {
        feld.zähler = 5;
        feld.lastMarker = "X";
        feld.getGameBoard()[0] = new String[]{"X", "X", "X"};
        feld.getGameBoard()[1] = new String[]{" ", " ", " "};
        feld.getGameBoard()[2] = new String[]{" ", " ", " "};

        assertTrue(feld.vorbei());
    }

    @Test
    void testVorbeiTrueDiagonalWin() {
        feld.zähler = 5;
        feld.lastMarker = "O";
        feld.getGameBoard()[0] = new String[]{"O", " ", " "};
        feld.getGameBoard()[1] = new String[]{" ", "O", " "};
        feld.getGameBoard()[2] = new String[]{" ", " ", "O"};

        assertTrue(feld.vorbei());
    }

    @Test
    void testVorbeiTrueVerticalWin() {
        feld.setMarker("O", 2);
        feld.setMarker("O", 6);
        feld.setMarker("O", 8);

        feld.setMarker("X", 1);
        feld.setMarker("X", 4);
        feld.setMarker("X", 7);

        boolean isGameOver = feld.vorbei();


        assertTrue(isGameOver);
    }

    @Test
    void testVorbeiFalseNoWin() {
        feld.zähler = 5;
        feld.lastMarker = "X";
        feld.getGameBoard()[0] = new String[]{"X", "O", "X"};
        feld.getGameBoard()[1] = new String[]{"O", "X", "O"};
        feld.getGameBoard()[2] = new String[]{"O", "X", "O"};

        assertFalse(feld.vorbei());
    }

}

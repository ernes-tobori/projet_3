package ocr.ernestine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() { System.setOut(new PrintStream (outContent)); }

    @AfterEach
    public void restoreStreams() { System.setOut(System.out); }

    Menu menu = new Menu ();

    @Test
    public void Given_ModeChallenger_When_DisplaySelectedMenu_Then_DisplayChallengerSentence () {
        menu.displaySelectedMenu(1);
        assertEquals("Vous avez choisi comme menu : Mode Challenger \n", outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_ModeDefenseur_When_DisplaySelectedMenu_Then_DisplayDefenseurSentence () {
        menu.displaySelectedMenu(2);
        assertEquals("Vous avez choisi comme menu : Mode Defenseur \n", outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_ModeDuel_When_DisplaySelectedMenu_Then_DisplayDuelSentence () {
        menu.displaySelectedMenu(3);
        assertEquals("Vous avez choisi comme menu : Mode Duel \n", outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_QuitterLeJeu_When_DisplaySelectedMenu_Then_DisplayQuitentence () {
        menu.displaySelectedMenu(4);
        assertEquals("Vous avez choisi de quitter le jeu \n", outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_TooBigValue_When_DisplaySelectedMenu_Then_DisplayErrorSentence () {
        menu.displaySelectedMenu(18);
        assertEquals("Vous avez saisi un nombre hors du menu \n", outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_NegativeValue_When_DisplaySelectedMenu_Then_DisplayErrorSentence () {
        menu.displaySelectedMenu(-4);
        assertEquals("Vous avez saisi un nombre hors du menu \n", outContent.toString().replace("\r\n", "\n"));
    }

}

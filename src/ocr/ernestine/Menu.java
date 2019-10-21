package ocr.ernestine;


import java.util.Scanner;

public class Menu {

    /**
     * Display available menu to play the game
     */

    public void displayAvailableMenu() {
        System.out.println("Nous vous proposons 4 Choix possibles");
        System.out.println("1- Mode Challenger");
        System.out.println("2- Mode Defenseur");
        System.out.println("3- Mode Duel");
        System.out.println("4- Quitter le jeu");
        System.out.println("Veuillez saisir le nombre correspondant à votre choix");
    }

    public void displaySelectedMenu(int nbMenu) {
        switch (nbMenu) {
            case 1 :
                System.out.println("Vous avez choisi comme menu : Mode Challenger ");
                break;
            case 2 :
                System.out.println("Vous avez choisi comme menu : Mode Defenseur ");
                break;
            case 3 :
                System.out.println("Vous avez choisi comme menu : Mode Duel ");
                break;
            case 4 :
                System.out.println("Vous avez choisi de quitter le jeu ");
                break;
            default:
                System.out.println("Vous avez saisi un nombre hors du menu ");
                break;
        }
        
    }
}

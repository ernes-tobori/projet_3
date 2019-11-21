package ocr.ernestine;


import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
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

    /**
     * Display selected menu to start the game
     * @param nbMenu is the value written
     */

    public void displaySelectedMenu(int nbMenu) {
        ActionJoueurInterface joueur;


        switch (nbMenu) {
            case 1 :
                System.out.println("Vous avez choisi comme menu : Mode Challenger ");
                joueur =new Challenger();
                joueur.saisir();
                break;
            case 2 :
                System.out.println("Vous avez choisi comme menu : Mode Defenseur ");
                joueur=new Defenseur();
                joueur.saisir();
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

       /**
     * Display Correct Number is a method which ask to write a number and not a letter
     * @param nb is the value of the choice selected to play the game
     */

    public void displayCorrectNumber(String nb) {
        boolean isChoice=true;
        String modeNumber=nb;
        while(isChoice) {
            try{
                int number=Integer.parseInt(modeNumber) ;
                this.displaySelectedMenu((number));
                System.out.println("Que souhaiteriez-vous faire ? \n 1- Rejouer \n 2- Changer de mode, ou \n 3- Quitter le jeu");
                Scanner   scReplay = new Scanner(System.in);
               String nbReplay = scReplay.next();
               boolean isContinu=Boolean.valueOf(nbReplay);
                if("1".equals(nbReplay)){
                    modeNumber=nb;
                }else if ("2".equals(nbReplay)){
                    System.out.println("Saisissez un mode");
                    Scanner   sc = new Scanner(System.in);
                    nb = sc.next();
                }else{
                    System.out.println("Au revoir et A bientôt");
                    isChoice=false;
                }

            }catch (NumberFormatException exe) {
                System.out.println("Saisissez un chiffre");
                Scanner   sc = new Scanner(System.in);
                nb = sc.next();
            }
        }
    }
    //Ajout du mode defenseur



}

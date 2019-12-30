package ocr.ernestine;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class Menu {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(Menu.class);
    /**
     * Display available menu to play the game
     */

    public void displayAvailableMenu() {
        System.out.println(Affichage.MENU_CHOIX);
        System.out.println(Affichage.CHOIX_CHALLENGER);
        System.out.println(Affichage.CHOIX_DEFENSEUR);
        System.out.println(Affichage.CHOIX_DUEL);
        System.out.println(Affichage.CHOIX_QUITTER);
        System.out.println(Affichage.CHOIX_VEUILLEZ_CHOISIR);
    }

    /**
     * Display selected menu to start the game
     *
     * @param nbMenu is the value written
     */

    public void displaySelectedMenu(int nbMenu) {

        ActionJoueurInterface joueur;

        switch (nbMenu) {
            case 1:
                System.out.println(Affichage.MENU_CHALLENGER_CHOISI);
                joueur = new Challenger();
                boolean endGameChallenger = false;
                joueur.saisirCle();
                int nombreCoupChallenger=Integer.valueOf(PropertiesReader.readPropertiesFile().getProperty("nombre.coup.defaut"));
//                Boolean isModeDevChallenger =Boolean.valueOf(PropertiesReader.readPropertiesFile().getProperty("mode.dev"));
                do {
                    joueur.saisirProposition();
                    endGameChallenger = joueur.saisirIndice();
                    nombreCoupChallenger+=-1;
//                    System.out.println("nombre de Coups : "+nombreCoupChallenger);
                    if(nombreCoupChallenger==0){
                        System.out.println(Affichage.RAPPEL_CLE_ORDINATEUR_CHALLENGER +((Challenger) joueur).computerKey);
                        System.out.println(Affichage.DESOLE_UTILISATEUR);
                        break;
                    }
                } while (endGameChallenger == false);
                break;
            case 2:
                System.out.println(Affichage.MENU_DEFENSEUR_CHOISI);
                joueur = new Defenseur();
                boolean endGameDefenseur = false;
                joueur.saisirCle();
                int nombreCoupDefenseur=Integer.valueOf(PropertiesReader.readPropertiesFile().getProperty("nombre.coup.defaut"));
//                Boolean isModeDevDefenseur = Boolean.valueOf(PropertiesReader.readPropertiesFile().getProperty("mode.dev"));
                do {
                    joueur.saisirProposition();
                    endGameDefenseur = joueur.saisirIndice();
                    nombreCoupDefenseur+=-1;
//                    System.out.println("nombre de Coups : "+nombreCoupDefenseur);
                    if(nombreCoupDefenseur==0){
                        System.out.println(Affichage.RAPPEL_CLE_ORDINATEUR_DEFENSEUR +((Defenseur) joueur).combinaison);
                        System.out.println(Affichage.DESOLE_ORDINATEUR);
                        break;
                    }
                } while (endGameDefenseur == false);
                break;
            case 3:
                System.out.println(Affichage.MENU_DUEL_CHOISI);
                ActionJoueurInterface joueur2;
                boolean endGameChallengerDuel = false;
                boolean endGameDefenseurDuel = false;
                joueur = new Challenger();
                joueur2 = new Defenseur();
                joueur.saisirCle();
                joueur2.saisirCle();
                //int nombreCoupDuel=Integer.valueOf(PropertiesReader.readPropertiesFile().getProperty("nombre.coup.defaut"));
//                Boolean isModeDevDuel = Boolean.valueOf(PropertiesReader.readPropertiesFile().getProperty("mode.dev"));
                do {
                    joueur.saisirProposition();
                    endGameChallengerDuel = joueur.saisirIndice();
                    /*nombreCoupDuel+=-1;
//                    System.out.println("nombre de Coups : "+nombreCoupDuel);
                    if(nombreCoupDuel==0){
                        System.out.println(Affichage.RAPPEL_CLE_ORDINATEUR_CHALLENGER +((Challenger) joueur).computerKey);
                        System.out.println(Affichage.RAPPEL_CLE_ORDINATEUR_DEFENSEUR +((Defenseur) joueur2).combinaison);
                        System.out.println(Affichage.DESOLE_UTILISATEUR);
                        break;
                    }*/
                    // c'est l'équivalent de endGameChallengerDuel == false;
                    if (!endGameChallengerDuel) {
                        joueur2.saisirProposition();
                        endGameDefenseurDuel = joueur2.saisirIndice();
                    }
                } while (!endGameChallengerDuel && !endGameDefenseurDuel);
                break;
            case 4:
                System.out.println(Affichage.MENU_QUITTER_CHOISI);
                break;
            default:
                System.out.println(Affichage.HORS_MENU_CHOISI);
                break;
        }

    }

    /**
     * Display Correct Number is a method which ask to write a number and not a letter
     *
     * @param nb is the value of the choice selected to play the game
     */

    public void displayCorrectNumber(String nb) {
        boolean isChoice = true;
        String modeNumber = nb;
        while (isChoice) {

            try {
                int number = Integer.parseInt(modeNumber);
                this.displaySelectedMenu((number));
                System.out.println(Affichage.QUE_SOUHAITERIEZ_VOUS);
                Scanner scReplay = new Scanner(System.in);
                String nbReplay = scReplay.next();
                boolean isContinu = Boolean.valueOf(nbReplay);
                if ("1".equals(nbReplay)) {
                    continue;
                } else if ("2".equals(nbReplay)) {
                    System.out.println(Affichage.SAISISSEZ_UN_MODE);
                    Scanner sc = new Scanner(System.in);
                    modeNumber = sc.next();
                    logger.info(Affichage.NUMERO_DU_MODE +modeNumber);
                } else {
                    System.out.println(Affichage.AU_REVOIR);
                    isChoice = false;
                }

            } catch (NumberFormatException exe) {
                logger.error(Affichage.SAISISSEZ_DES_CHIFFRES);
                Scanner sc = new Scanner(System.in);
                modeNumber = sc.next();
                logger.info(Affichage.SAISISSEZ_UN_NOUVEAU_NUMERO_DE_MODE +modeNumber);
            }
        }
    }
}

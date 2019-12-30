package ocr.ernestine;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class Challenger extends  Joueur implements ActionJoueurInterface {
    private static final Logger logger = Logger.getLogger(Challenger.class);

    /**
     * Saisir is a method which ask the key length
     */
    @Override
    public void saisirCle() {

        System.out.println(Affichage.MODE_CHALLENGER);
        System.out.println(Affichage.COMMENTAIRE);
        System.out.println(Affichage.DEVINETTE_DE_LA_COMBINAISON_PAR_L_UTILISATEUR);
        System.out.println(Affichage.UTILISATEUR_SAISIT_TAILLE);
        System.out.println(Affichage.COMMENTAIRE);
        System.out.println(Affichage.BONJOUR_UTILISATEUR);

        sc = new Scanner(System.in);
        keyLength = sc.nextLine();
        if(keyLength==null || keyLength.trim().equals(("") ))
        {
          keyLength=PropertiesReader.readPropertiesFile().getProperty("taille.cle.defaut");
        }
        logger.info(Affichage.TAILLE +keyLength);
        boolean isChoice=true;
        int nb = -1;
        while(isChoice) {
            try {
                nb = Integer.valueOf(keyLength);
                isChoice = false;
            }catch (NumberFormatException exe) {
                logger.error(Affichage.SAISISSEZ);
                sc = new Scanner(System.in);
                keyLength = sc.next();
                logger.info(Affichage.NOUVELLE_SAISIE +keyLength);
            }
        }

        computerKey = createComputerKey(String.valueOf(nb));
        logger.info(Affichage.CLE_GENEREE +computerKey);


        this.keyLength=keyLength;

        /*boolean gameFinished = false;
        while (gameFinished == false) {
            gameFinished = this.guessTheKey();
        }
        System.out.println("Le Jeu est terminé !");*/

    }

    @Override
    public void saisirProposition() {
        // boolean gameFinished = false;
        // while (gameFinished == false) {
        this.guessTheKey(riddle);
        //}
        //System.out.println("Le Jeu est terminé !");
    }

    @Override
    public boolean saisirIndice() {

        String result = generateNewIndicatorFromComputerProposal(riddleUser, computerKey);

        //System.out.println("Petit rappel de la clé de l'utilisateur saisie précedemment: " +riddleUser);
        //System.out.println("Petit rappel de la clé de l'ordinateur : " +computerKey);

        if (result.equals(generateValue(computerKey.length()))) {
            System.out.println(Affichage.COMPARAISON_DES_DEUX_COMBINAISONS + result);
            System.out.println(Affichage.BRAVO_UTILISATEUR + computerKey + Affichage.POINT_EXCLAMATION);
            return true;
        } else {
            System.out.println(Affichage.COMPARAISON_DES_DEUX_COMBINAISONS + result);
            return false;
        }
    }
}

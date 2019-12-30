package ocr.ernestine;

import org.apache.log4j.Logger;

import java.util.*;

public class Defenseur extends Joueur implements ActionJoueurInterface {
    private static final Logger logger = Logger.getLogger(Defenseur.class);
    @Override
    public void saisirCle() {
        System.out.println(Affichage.MODE_DEFENSEUR);
        System.out.println(Affichage.COMMENTAIRE);
        System.out.println(Affichage.DEVINETTE_DE_LA_COMBINAISON);
        System.out.println(Affichage.ORDINATEUR_ENREGISTRE);
        System.out.println(Affichage.COMMENTAIRE);
        System.out.println(Affichage.BONJOUR_UTILISATEUR_CLE_A_DEVINER);
        sc = new Scanner(System.in);
        size = sc.nextLine();
        logger.info("Clé : " +size);
        this.size = size;
//      int nombreEssai = 4;
        number = size.length();
        intArrayMin = new int[number];
        intArrayMax = new int[number];
        System.out.println(Affichage.UTILISATEUR + number);
        combinaison = createComputerKey(String.valueOf(number));
        for (int j = 0; j < combinaison.length(); j++) {
            if (tour == 0) {
                intArrayMax[j] = 9;
                intArrayMin[j] = 0;
            }
        }
    }


    @Override
    public void saisirProposition() {
        // boolean isChoice = true;
        // int nombreEssai = 4;
        System.out.println(Affichage.MODE_DEFENSEUR);
        System.out.println(Affichage.COMMENTAIRE);
        System.out.println(Affichage.DEVINETTE_COMBINAISON);
        System.out.println(Affichage.ORDINATEUR_ENREGISTRE);
        System.out.println(Affichage.COMMENTAIRE);

        System.out.println(Affichage.NUMERO_MAX+ tour + Affichage.DEUX_POINTS+ Arrays.toString(intArrayMax));
        System.out.println(Affichage.NUMERO_MIN + tour + Affichage.DEUX_POINTS+ Arrays.toString(intArrayMin));
        System.out.println( Affichage.ORDINATEUR+ number + Affichage.CHIFFRES + combinaison);
    }

    @Override
    public boolean saisirIndice() {

        boolean isChoice = true;
        try {
            while (isChoice) {
                System.out.println(Affichage.DONNEZ_QUELQUES_INDICES);
                System.out.println(Affichage.PETIT_RAPPEL_CLE_ORDINATEUR + combinaison);
                System.out.println(Affichage.PETIT_RAPPEL_CLE_UTILISATEUR + size);
                sc = new Scanner(System.in);
                resultUser = sc.nextLine();
                logger.info(Affichage.INDICES +resultUser);

                if (generateValue(number).equals(resultUser)) {
                    System.out.println(Affichage.BRAVO_ORDINATEUR);
                    return true;
                } else {
                    combinaison = generateNewValueFromUserIndicator(resultUser, combinaison);
                    //combinaison = this.combinaison;
                    System.out.println(Affichage.NUMERO_MAX + tour + Affichage.DEUX_POINTS+ Arrays.toString(intArrayMax));
                    System.out.println(Affichage.NUMERO_MIN + tour + Affichage.DEUX_POINTS + Arrays.toString(intArrayMin));
                    //System.out.println("\n L'ordinateur a choisi la combinaison suivante : " + combinaison +"\nElle est comprise entre le tableau min n°" + tour +" :" + Arrays.toString(intArrayMin) + "\nEt le tableau max n°" + tour +" :" + Arrays.toString(intArrayMax)) ;

                    tour += 1;
                    isChoice = false;
                }
            }
        } catch (NumberFormatException exe) {
            logger.error(Affichage.SAISISSEZ + number + Affichage.CHIFFRES);
            sc = new Scanner(System.in);
            key = sc.next();
        }
        return false;
    }
}

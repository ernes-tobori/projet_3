package ocr.ernestine;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;

public class Duel  extends Joueur implements ActionJoueurInterface{
    private static final Logger logger = Logger.getLogger(Duel.class);
    @Override
    public void saisirCle() {
        /* Debut de la devinette de la combinaison par l'ordinateur!*/
        System.out.println(Affichage.COMMENTAIRE);
        System.out.println(Affichage.DEBUT_DEVINETTE);
        System.out.println(Affichage.UTILISATEUR_SAISIT);
        System.out.println(Affichage.COMMENTAIRE);
        System.out.println(Affichage.BONJOUR_UTILISATEUR_CLE_A_DEVINER);
        sc = new Scanner(System.in);
        String size = sc.nextLine();
        logger.info("Clé : " +size);
        String combinaison = null;
        String resultUser = null;
        int nombreEssai = 4;
        boolean isChoice = true;
        int k=0;
        try {
            number = size.length();
            intArrayMin=new int[number] ;
            intArrayMax =new int[number] ;
            System.out.println(Affichage.SAISIT_TAILLE + number);
            combinaison = createComputerKey(String.valueOf(number));
            System.out.println(Affichage.MOI_ORDINATEUR + number + Affichage.CHIFFRES + combinaison);
            while (isChoice) {
                System.out.println(Affichage.QUELQUES_INDICES);
                sc = new Scanner(System.in);
                resultUser = sc.nextLine();
                logger.info(Affichage.INDICES +resultUser);
                System.out.println(Affichage.DONNEZ_MOI_UN_PEU_DE_TEMPS);
                if (generateValue(number).equals(resultUser)) {
                    System.out.println(Affichage.BRAVO_UTILISATEUR);
                    System.out.println(Affichage.GAGNE_ORDINATEUR);
                    break;
                } else {

                    /* Fin de la devinette de la combinaison par l'ordinateur!*/
                    /* Debut de la devinette de la combinaison par l'utilsateur!*/
                    System.out.println(Affichage.TOUR_UTILISATEUR);
                    System.out.println(Affichage.COMMENTAIRE);
                    System.out.println(Affichage.DEVINETTE_COMBINAISON);
                    System.out.println(Affichage.UTILISATEUR_SAISI_TAILLE);
                    System.out.println(Affichage.COMMENTAIRE);
                    if(k==0) {
                        System.out.println(Affichage.COMPRISE_UN_CHIFFRE_UN_NEUF);
                        scUser = new Scanner(System.in);
                        keyLength = scUser.nextLine();
                        logger.info(Affichage.TAILLE+keyLength);
                        boolean isChoiceUser=true;
                        int nb = -1;
                        while(isChoiceUser) {
                            try {
                                nb = Integer.valueOf(keyLength);
                                isChoiceUser = false;
                            }catch (NumberFormatException exe) {
                                logger.error(Affichage.CHIFFRES);
                                sc = new Scanner(System.in);
                                keyLength = sc.next();
                                logger.info(Affichage.NOUVELLE_SAISIE +keyLength);
                            }
                        }
                        //L'ordinateur génère la combinaison une seule fois
                        computerKey = createComputerKey(String.valueOf(nb));
                        k++;
                    }
                    else{
                        System.out.println(Affichage.RAPPEL_CLE_ORDINATEUR_CHALLENGER +   riddleUser+ Affichage.RESULT_ORDINATEUR+ globalResult);
                    }
                    System.out.println(Affichage.CLE_GENERE + computerKey+ Affichage.CONSERVER);
                    isChoice=!this.guessTheKey(riddleUser);
                    /* Fin de la devinette de la combinaison par l'utilsateur!*/
                    //Si le nombre d'essai dépasse 5 alors on arrête le jeu

                    //L'ordinateur génère une nouvelle clée
                    if( isChoice) {
                        System.out.println(Affichage.MODE_DEFENSEUR);
                        System.out.println(Affichage.COMMENTAIRE);
                        System.out.println(Affichage.DEVINETTE);
                        System.out.println(Affichage.ORDINATEUR_ENREGISTRE);
                        System.out.println(Affichage.COMMENTAIRE);
                        combinaison = generateNewValueFromUserIndicator(resultUser, combinaison);
                        System.out.println(Affichage.ORDINATEUR_COMBINAISON + combinaison);
                        System.out.println(Affichage.NUMERO_MAX + tour + Affichage.DEUX_POINTS + Arrays.toString(intArrayMin));
                        System.out.println(Affichage.NUMERO_MIN + tour + Affichage.DEUX_POINTS + Arrays.toString(intArrayMax));
                        tour += 1;
                    }else{
                        System.out.println(Affichage.GAGNE_UTILSATEUR );
                    }
                }

                if (nombreEssai == 0) {
                    System.out.println(Affichage.DESOLE);
                    isChoice = false;
                    break;
                }
                nombreEssai += -1;
            }
        } catch (NumberFormatException exe) {
            logger.error(Affichage.SAISISSEZ_DES_CHIFFRES);
            sc = new Scanner(System.in);
            key = sc.next();
            logger.info(Affichage.NOUVELLE_SAISIE +size);
        }
    }

    @Override
    public void saisirProposition() {

    }

    @Override
    public boolean saisirIndice() {
        return false;
    }
}

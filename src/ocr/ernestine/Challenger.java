package ocr.ernestine;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Challenger extends  Joueur implements ActionJoueurInterface {


    /**
     * Saisir is a method which ask the key length
     */
    @Override
    public void saisir() {
        System.out.println("\n Bonjour\n Je suis l'ordinateur : Veuillez saisir la taille de la combinaison souhaitée");
        sc = new Scanner(System.in);
        keyLength = sc.nextLine();
        boolean isChoice=true;
        int nb = -1;
        while(isChoice) {
            try {
                nb = Integer.valueOf(keyLength);
                isChoice = false;
            }catch (NumberFormatException exe) {
                System.out.println("Saisissez un chiffre");
                sc = new Scanner(System.in);
                keyLength = sc.next();
            }
        }

        computerKey = createComputerKey(String.valueOf(nb));
        System.out.println(computerKey);
        this.keyLength=keyLength;
        boolean gameFinished = false;
        while (gameFinished == false) {
            gameFinished = this.guessTheKey(riddle);
        }
        System.out.println("Le Jeu est terminé !");
    }


    /**
     * Display Computer Key is a method in which the computer generate a key
     * @param length is the number of elements of the key
     * @return the computer key
     */

    public static String createComputerKey(String length) {
        String randomNumeric = RandomStringUtils.randomNumeric(Integer.valueOf(length));
        return randomNumeric;
    }
    public boolean guessTheKey(String riddleUser) {
        System.out.println("\n Veuillez deviner ma clé et la saisir \n");
        scUser = new Scanner(System.in);
        riddleUser = scUser.nextLine();
        boolean isChoice=true;
        int number = -1;
        int riddleLength = -1;
        int tailleCle = -1;
        while (isChoice) {
            try {
                number = Integer.parseInt(riddleUser);
                riddleLength = riddleUser.length();
                System.out.println("Vous avez saisi " +riddleLength + " chiffres");
                tailleCle = Integer.parseInt(keyLength);
                while (riddleLength != tailleCle) {
                    System.out.println("Veuillez saisir " + keyLength + " chiffres");
                    sc = new Scanner(System.in);
                    riddleUser = sc.next();
                    riddleLength = riddleUser.length();
                }
                boolean isWithoutLetter=true;
                while (isWithoutLetter) {
                    try {
                        int nombre = Integer.parseInt(riddleUser);
                        System.out.println("C'est bien ! Votre clé ne contient pas de lettre mais exactement " + keyLength + " chiffres \n Maintenant, comparons nos clés");
                        isWithoutLetter = false;
                    }catch (NumberFormatException exe) {
                        System.out.println("Votre clé contient des lettres! Veuillez corriger SVP! et saisir " + keyLength + " chiffres");
                        sc = new Scanner(System.in);
                        riddleUser = sc.next();
                    }
                }
                isChoice = false;
            }catch (NumberFormatException exe) {
                System.out.println("Veuillez saisir " + keyLength + " chiffres");
                sc = new Scanner(System.in);
                riddleUser = sc.next();
            }
        }


        String result = compareValue(riddleUser, computerKey);

        if (result.equals(generateValue(computerKey.length()))) {
            System.out.println("comparaison des deux combinaisons : " + result);
            System.out.println("Bravo vous avez trouvé!!!" + computerKey);
            return true;
        } else {
            System.out.println("comparaison des deux combinaisons : " + result);
            return false;
        }
    }

    private String compareValue(String user, String computer) {
        String result = "";
        ArrayList<Character> listcharacterComputer = new ArrayList<Character>();
        ArrayList<Character> listcharacterUser = new ArrayList<Character>();
        for (Character characterComputer : computer.toCharArray()) {
            listcharacterComputer.add(characterComputer);
        }

        for (Character characterUser : user.toCharArray()) {
            listcharacterUser.add(characterUser);
        }

        // Modify objects being iterated
        ListIterator iterateListcharacterComputer = listcharacterComputer.listIterator();

        ListIterator iterateListcharacterUser = listcharacterUser.listIterator();

        while (iterateListcharacterComputer.hasNext()) {
            Object elementComputer = iterateListcharacterComputer.next();

            while (iterateListcharacterUser.hasNext()) {
                Object elementUser = iterateListcharacterUser.next();
                iterateListcharacterUser.remove();
                System.out.println("elementUser :" + elementUser);
                System.out.println("elementComputer :" + elementComputer);
                if (Integer.valueOf(elementUser.toString()) < Integer.valueOf(elementComputer.toString())) {
                    result = result + "+";
                    break;

                } else if (Integer.valueOf(elementUser.toString()) > Integer.valueOf(elementComputer.toString())) {
                    result += "-";
                    break;
                } else {
                    result += "=";
                    break;
                }
            }
            iterateListcharacterComputer.remove();
        }
        return result;
    }
}

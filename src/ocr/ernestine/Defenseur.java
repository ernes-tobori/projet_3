package ocr.ernestine;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;

public class Defenseur extends Joueur implements ActionJoueurInterface {

    @Override
    public void saisir() {
            System.out.println("\n Bonjour\n Je suis l'ordinateur : Veuillez saisir la clé à deviner :" );
            sc = new Scanner(System.in);
            String  size = sc.nextLine();
            String combinaison=null;
            String   resultUser=null;
            int nombreEssai=4;
            boolean isChoice=true;
            int number = size.length();
            combinaison=createComputerKey(String.valueOf(number));
            System.out.println("L'ordinateur a choisi la combinaison : " +combinaison) ;

            try {
                while(isChoice) {
                    System.out.println("Quel est le resultat ? " ) ;
                    scUser = new Scanner(System.in);
                    resultUser = sc.nextLine();
                    if(generateValue(number).equals(resultUser)){
                        System.out.println("Bravo vous avez réussi");
                        break;
                    }else {
                        combinaison = generateNewValueFromUserIndicator(resultUser, combinaison);
                        System.out.println("L'ordinateur a choisit la combinaison : " + combinaison);
                        if (nombreEssai == 0) {
                            System.out.println("Désolé vous ne pouvez plus continuer le nombre d'essai est terminé ");
                            isChoice = false;
                            break;
                        }
                        nombreEssai += -1;
                    }
                }
            }catch (NumberFormatException exe) {
                System.out.println("Saisissez des chiffres");
                sc = new Scanner(System.in);
                key = sc.next();
            }

        }
    public String generateNewValueFromUserIndicator(String user, String computer) {
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

                if ("+".equals(elementUser.toString())) {
                    result += getRandomNumberInRange(Integer.valueOf(elementComputer.toString())+1,9);
                    break;
                } else if ("-".equals(elementUser.toString())) {
                    if(Integer.valueOf(elementComputer.toString())-1<0) {
                        result += getRandomNumberInRange(0, 1);
                    }else{
                        result += getRandomNumberInRange(0,Integer.valueOf(elementComputer.toString())-1);
                    }

                    break;
                } else {
                    result += Integer.valueOf(elementComputer.toString());
                    break;
                }
            }
            iterateListcharacterComputer.remove();
        }
        return result;
    }
}

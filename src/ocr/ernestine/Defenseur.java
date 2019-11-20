package ocr.ernestine;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

public class Defenseur extends Joueur implements ActionJoueurInterface {
        int i=0;
    int[] intArrayMin ;
    int[] intArrayMax ;
    @Override
    public void saisir() {
        System.out.println("\n Bonjour\n Je suis l'ordinateur : Veuillez saisir la clé à deviner :");
        sc = new Scanner(System.in);
        String size = sc.nextLine();
        String combinaison = null;
        String resultUser = null;
        int nombreEssai = 4;
        boolean isChoice = true;
        number = size.length();
        intArrayMin=new int[number] ;
        intArrayMax =new int[number] ;
        System.out.println("\n Vous avez saisi une clé de taille : " + number);
        combinaison = createComputerKey(String.valueOf(number));
        System.out.println(" Je vous propose cette combinaison de " + number + " chiffres : " + combinaison);

        try {
            while (isChoice) {
                System.out.println("\n Veuillez me donner quelques indices afin de me rapprocher de votre résultat!");
                sc = new Scanner(System.in);
                resultUser = sc.nextLine();
                if (generateValue(number).equals(resultUser)) {
                    System.out.println("Bravo vous avez réussi");
                    break;
                } else {

                    combinaison = generateNewValueFromUserIndicator(resultUser, combinaison);
                    System.out.println("\n Tableau min n°" +i +" :" + Arrays.toString(intArrayMin));
                    System.out.println("\n Tableau max n°" +i +" :" + Arrays.toString(intArrayMax));
                    System.out.println("\n L'ordinateur a choisit la combinaison : " + combinaison);

                    i+=1;
                    if (nombreEssai == 0) {
                        System.out.println("Désolé vous ne pouvez plus continuer le nombre d'essai est terminé ");
                        isChoice = false;
                        break;
                    }
                    nombreEssai += -1;
                }
            }
        } catch (NumberFormatException exe) {
            System.out.println("Saisissez des chiffres");
            sc = new Scanner(System.in);
            key = sc.next();
        }

    }

    public String generateNewValueFromUserIndicator(String user, String computer) {
        String result = "";
// 'j' c'est l'index
        int j=0;
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
                int valueGenerate=0;
                while (iterateListcharacterUser.hasNext()) {
                    Object elementUser = iterateListcharacterUser.next();
                    iterateListcharacterUser.remove();

                    if ("+".equals(elementUser.toString())) {
                        if(i==0) {
                            intArrayMax[j] = 9;
                        }
                        intArrayMin[j] = (Integer.parseInt(elementComputer.toString()))+1;
                        valueGenerate=getRandomNumberInRange(intArrayMin[j] , intArrayMax[j]);
                        result += valueGenerate;
                        break;
                    } else if ("-".equals(elementUser.toString())) {
                        if(i==0){
                            intArrayMin[j] =0;
                        }
                        intArrayMax[j]=(Integer.parseInt(elementComputer.toString()))-1 ;
                        valueGenerate=getRandomNumberInRange(intArrayMin[j] , intArrayMax[j]);
                        result += valueGenerate;
                        break;
                    } else {
                        result += Integer.valueOf(elementComputer.toString());
                        intArrayMax[j]=Integer.parseInt(elementComputer.toString()) ;
                        intArrayMin[j] =Integer.parseInt(elementComputer.toString());
                        break;
                    }
                }
                j+=1;
                iterateListcharacterComputer.remove();
            }
        return result;
    }

}

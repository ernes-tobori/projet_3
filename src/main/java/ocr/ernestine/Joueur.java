package ocr.ernestine;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import java.util.*;

public class Joueur {
    private static final Logger logger = Logger.getLogger(Joueur.class);
    Scanner sc = new Scanner(System.in);
    Scanner scUser = new Scanner(System.in);
    String keyLength = "";
    String key = "";
    String riddle = "";
   public String computerKey = "";
    String  riddleUser;
    int number;
    int tour =0;
    int[] intArrayMin ;
    int[] intArrayMax ;
    int[] tabUser;
    int[] tabComputer;
    String combinaison = null;
    String resultUser = null;
    String globalResult = "";
    String size;
    //char[] tabResult;

    public String generateValue(int taille) {
        String result="";
        for(int i=0;i<taille;i++ ) {
            result+="=";
        }
        return result;
    }
    
    public int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();
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
                    if (tour == 0) {
                        intArrayMax[j] = 9;
                    }
                    if (intArrayMax[j] > Integer.parseInt(elementComputer.toString())) {
                        intArrayMin[j] = (Integer.parseInt(elementComputer.toString())) + 1;
                        valueGenerate = getRandomNumberInRange(intArrayMin[j], intArrayMax[j]);
                        result += valueGenerate;
                    } else {
                        logger.error(Affichage.MESSAGE_ERREUR_PLUS + (j+1));
                        result += Integer.parseInt(elementComputer.toString());
                        break;
                    }
                    break;
                } else if ("-".equals(elementUser.toString())) {
                    if (tour == 0) {
                        intArrayMin[j] = 0;
                    }
                    if (intArrayMin[j] < Integer.parseInt(elementComputer.toString())) {
                        intArrayMax[j] = (Integer.parseInt(elementComputer.toString())) - 1;
                        valueGenerate = getRandomNumberInRange(intArrayMin[j], intArrayMax[j]);
                        result += valueGenerate;
                    } else {
                        logger.error(Affichage.MESSAGE_ERREUR_MOINS + (j+1));
                        result += Integer.parseInt(elementComputer.toString());
                        break;
                    }
                    break;
                } else {
                    result += Integer.valueOf(elementComputer.toString());
                    intArrayMax[j] = Integer.parseInt(elementComputer.toString());
                    intArrayMin[j] = Integer.parseInt(elementComputer.toString());
                    break;
                }
            }
            j+=1;
            iterateListcharacterComputer.remove();
        }
        return result;
    }

    public String generateNewIndicatorFromComputerProposal(String user, String computer) {
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
//                System.out.println("elementUser :" + elementUser);
//                System.out.println("elementComputer :" + elementComputer);
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
            globalResult = result;
            iterateListcharacterComputer.remove();
        }
        return globalResult;
    }

    public boolean guessTheKey(String riddleUser) {

        boolean isChoice=true;
        int number = -1;
        int riddleLength = -1;
        int tailleCle = -1;

        System.out.println(Affichage.MODE_CHALLENGER);
        System.out.println(Affichage.COMMENTAIRE);
        System.out.println(Affichage.DEVINETTE_DE_LA_COMBINAISON_PAR_L_UTILISATEUR);
        System.out.println(Affichage.UTILISATEUR_SAISI_TAILLE);
        System.out.println(Affichage.COMMENTAIRE);

        Boolean isModeDev =Boolean.valueOf(PropertiesReader.readPropertiesFile().getProperty("mode.dev"));
        if(isModeDev==true){
         System.out.println(Affichage.RAPPEL_CLE_ORDINATEUR_CHALLENGER +computerKey);
         }
//        PropertiesReader.readPropertiesFile().setProperty("mode.dev","true");
        if(this.riddleUser!= null && this.riddleUser.trim() != "") {
        System.out.println(Affichage.PETIT_RAPPEL_CLE_UTILISATEUR + this.riddleUser);
        }
        if(globalResult!= null && globalResult.trim() != "") {
        System.out.println(Affichage.COMPARAISON_DES_DEUX_COMBINAISONS + globalResult);
        }
        System.out.println(Affichage.VEUILLEZ_DEVINER);
        //intArrayMin = new int [computerKey.length()];
        //intArrayMax = new int [computerKey.length()];


        //for (int j=0; j<computerKey.length(); j++) {

        // intArrayMin[j] = 0;
        //intArrayMax[j] = 9;

            /*char[] tabResult = new char[computerKey.length()];
                for (int e=0; e<computerKey.length(); e++) {
                    tabResult [e] = globalResult.charAt(e);
               }

            if ("+".equals(String.valueOf(tabResult[j]))) {
                if (tour == 0) {
                    intArrayMax[j] = 9;
                }
                intArrayMin[j] = tabUser[j] + 1;

            } else if ("-".equals(String.valueOf(tabResult[j]))) {
                if (tour == 0) {
                    intArrayMin[j] = 0;
                }
                intArrayMax[j] = tabUser[j] - 1;

            } else {
                intArrayMin[j] = tabUser[j];
                intArrayMax[j] = tabUser[j];
            }*/
        // }

        //System.out.println("Valeurs max : " + Arrays.toString(intArrayMax));
        //System.out.println("Valeurs min : " + Arrays.toString(intArrayMin) + "\n");

        scUser = new Scanner(System.in);
        riddleUser = scUser.nextLine();
        logger.info(Affichage.CLE_DEVINEE +riddleUser);

        while (isChoice) {
            try {
                number = Integer.parseInt(riddleUser);
                riddleLength = riddleUser.length();
                //System.out.println("L'utilisateur a saisi " + riddleLength + " chiffres");

                tabUser = new int[riddleUser.length()];
                tabComputer = new int[computerKey.length()];
                for (int j = 0; j < riddleUser.length(); j++) {
                    tabUser[j] = Integer.parseInt(Affichage.GUILLEMETS + riddleUser.charAt(j));
                    tabComputer[j] = Integer.parseInt(Affichage.GUILLEMETS + computerKey.charAt(j));
                    // System.out.println("La saisie de l'utilisateur n°" + j + " est : " + tabUser[j]);
                }
                // System.out.println("Tableau de la saisie de l'utilisateur pour le tour " + tour + " :" + Arrays.toString(tabUser));
                // System.out.println("Tableau de la saisie de l'ordinateur pour le tour " + tour + " :" + Arrays.toString(tabComputer));

                tailleCle = Integer.parseInt(keyLength);
                while (riddleLength != tailleCle) {
                    System.out.println(Affichage.VEUILLEZ_SAISIR + keyLength + Affichage.CHIFFRES);
                    sc = new Scanner(System.in);
                    riddleUser = sc.next();
                    logger.info("Clé : " +riddleUser);
                    riddleLength = riddleUser.length();
                }
                boolean isWithoutLetter = true;
                while (isWithoutLetter) {
                    try {
                        int nombre = Integer.parseInt(riddleUser);
                        //System.out.println("C'est bien ! Votre clé ne contient pas de lettre mais exactement " + keyLength + " chiffres \n Maintenant, comparons nos clés");
                        isWithoutLetter = false;
                    } catch (NumberFormatException exe) {
                        logger.error(Affichage.VOTRE_CLE_CONTIENT_DES_LETTRES + keyLength + Affichage.CHIFFRES);
                        sc = new Scanner(System.in);
                        riddleUser = sc.next();
                        logger.info(Affichage.NOUVELLE_SAISIE_DE_LA_CLE +riddleUser);
                    }
                }
                isChoice = false;
            } catch (NumberFormatException exe) {
                logger.error(Affichage.VOTRE_CLE_CONTIENT_DES_LETTRES + keyLength + Affichage.CHIFFRES);
                sc = new Scanner(System.in);
                riddleUser = sc.next();
                logger.info(Affichage.NOUVELLE_SAISIE_DE_LA_CLE  +riddleUser);
            }
        }


        this.riddleUser = riddleUser;
        tour +=1;
        return false;
    }
}

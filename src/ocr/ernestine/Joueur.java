package ocr.ernestine;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.Scanner;

public class Joueur {

    Scanner sc = new Scanner(System.in);
    Scanner scUser = new Scanner(System.in);
    String keyLength = "";
    String key = "";
    String riddle = "";
    String computerKey = "";
    int number;

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
}

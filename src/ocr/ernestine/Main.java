package ocr.ernestine;

import java.util.Scanner;

public class Main {

    public static void main(String [ ] args)
    {
        Menu menu = new Menu();
        menu.displayAvailableMenu();
        Scanner sc = new Scanner(System.in);
        int nb = sc.nextInt();
        menu.displaySelectedMenu(nb);
    }
}

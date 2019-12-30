package ocr.ernestine;

        import org.apache.log4j.Logger;

        import java.util.Scanner;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String [ ] args)
    {
        logger.info(Affichage.TEXTE_DEBUT);

        Menu menu = new Menu();
        menu.displayAvailableMenu();
        Scanner sc = new Scanner(System.in);
        String nb = sc.nextLine();
        logger.info(Affichage.MENU+ nb);
        menu.displayCorrectNumber(nb);

        logger.info(Affichage.TEXTE_FIN);
    }
}

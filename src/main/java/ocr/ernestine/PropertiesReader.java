package ocr.ernestine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    static Properties readPropertiesFile () {
        Properties prop = new Properties();
        try (InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return null;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
//            System.out.println(prop.getProperty("taille.cle.defaut"));
//            System.out.println(prop.getProperty("nombre.coup.defaut"));
//            System.out.println(prop.getProperty("mode.dev"));


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

}

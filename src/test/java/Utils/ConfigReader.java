package Utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    //to read config.properties
    static Properties prop;
    public static Properties readProperties(String filePath){

        try {
            FileInputStream fis=new FileInputStream(filePath); //to locate the file
            prop=new Properties();
            prop.load(fis); //to load properties file in java

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getPropertyValue(String key){
        return prop.getProperty(key); //get property is a method which will read the value as per the key provided
    }

}

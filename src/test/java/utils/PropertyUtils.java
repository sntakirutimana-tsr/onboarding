package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    public static Properties propertyLoader(String filePath){
        Properties properties = new Properties();
        try(FileReader reader = new FileReader(filePath)){
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}

package utils;



import constants.EnvType;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private ConfigLoader(){
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch (EnvType.valueOf(env)){
            case PROD   -> properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
            case STAGE -> properties = PropertyUtils.propertyLoader("src/test/resources/stage_config.properties");
            default -> throw new IllegalStateException("Invalid Environment: " + env);
        }
    }

    // This is Thread Safe by default. Because class initialization is synchronized by JVM
    private static class Holder{
        private static final ConfigLoader INSTANCE = new ConfigLoader();
    }

    public  static ConfigLoader getInstance(){
        return Holder.INSTANCE;
    }

    public String getBaseUrl(){
        return properties.getProperty("baseUrl");
    }
}

package ask.ask1.test;

import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;

public class ConfigReader {
    public class ConfigReader {
        public static void main(String[] args) {
            String configPath = "config.json";
            JSONObject config = readConfig(configPath);
            JSONObject extensions = config.getJSONObject("extensions");
            String defaultFolder = config.getString("default");
            // Use extensions and defaultFolder to move files
        }
    
        private static JSONObject readConfig(String path) {
            try (FileAsker reader = new FileAsker(path)) {
                StringBuilder builder = new StringBuilder();
                int character;
                while ((character = reader.read()) != -1) {
                    builder.append((char) character);
                }
                return new JSONObject(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }  
}

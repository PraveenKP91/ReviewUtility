package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppPropertyReader {
  /**
   *
   * @author billion
   */
  public static String getProperty(String property) {
    String result = null;
    try (InputStream input =
        AppPropertyReader.class.getClassLoader().getResourceAsStream("Application.properties")) {
      Properties prop = new Properties();
      prop.load(input);
      result = prop.getProperty(property);
    } catch (IOException ex) {
      ex.printStackTrace();
      result = null;
    }
    return result;
  }
}

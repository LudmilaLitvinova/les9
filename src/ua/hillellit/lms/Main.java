package ua.hillellit.lms;

import java.io.IOException;
import ua.hillellit.lms.exceptions.FileMaxSizeReachedException;
import ua.hillellit.lms.model.FileLogger;
import ua.hillellit.lms.model.FileLoggerConfigurationLoader;

public class Main {

  public static void main(String[] args) {
    FileLoggerConfigurationLoader flcl = new FileLoggerConfigurationLoader();
    FileLogger fl = new FileLogger(
        flcl.load("src\\ua\\hillellit\\lms\\resources\\configuration.properties"));

    try {
      fl.info("Привіт! Тут має бути INFO текст про логування");
      fl.debug("Привіт! Тут має бути DEBUG текст про логування");
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (FileMaxSizeReachedException e) {
      throw new RuntimeException(e);
    }

  }

}

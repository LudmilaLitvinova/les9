package ua.hillellit.lms;

import java.io.IOException;
import ua.hillellit.lms.model.FileLogger;
import ua.hillellit.lms.model.FileLoggerConfigurationLoader;

public class Main {

  public static void main(String[] args) {
    FileLoggerConfigurationLoader flcl = new FileLoggerConfigurationLoader();
    FileLogger fl = new FileLogger(flcl.load("src\\ua\\hillellit\\lms\\resources\\configurationFile.txt"));

    try {
      fl.log("Привіт! Тут має бути текст про логування");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

}

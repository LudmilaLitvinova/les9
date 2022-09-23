package ua.hillellit.lms.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileLoggerConfigurationLoader {

  public FileLoggerConfiguration load(String path) {
    FileLoggerConfiguration flc;
    try (InputStream is = new FileInputStream(path)) {
      byte[] res = new byte[is.available()];
      int s;
      int i = 0;
      while ((s = is.read()) >= 0) {
        res[i++] = (byte) s;
      }
      String[] input = (new String(res)).split("\n");

      flc = new FileLoggerConfiguration(input[0].split(": ")[1].replaceAll("\\p{Cntrl}", ""),
          LoggingLevel.valueOf(input[1].split(": ")[1].replaceAll("\\p{Cntrl}", "")),
          Integer.parseInt(input[2].split(": ")[1].replaceAll("\\p{Cntrl}", "")),
          input[3].split(": ")[1].replaceAll("\\p{Cntrl}", ""));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return flc;
  }
}

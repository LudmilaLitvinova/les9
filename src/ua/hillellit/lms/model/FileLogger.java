package ua.hillellit.lms.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import ua.hillellit.lms.exceptions.FileMaxSizeReachedException;

public class FileLogger implements Logable {

  public FileLogger(FileLoggerConfiguration configuration) {
    this.configuration = configuration;
  }
  private FileLoggerConfiguration configuration;

  long currentSize;

  @Override
  public void log(String message) throws IOException {
    Path path = Paths.get(configuration.getDestinationFile() + "." + configuration.getFileFormat());

    try {
      currentSize = Files.size(path);

    }catch (NoSuchFileException e) {
      File file = new File(configuration.getDestinationFile() + "." + configuration.getFileFormat());
      file.createNewFile();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    switch (configuration.currentLoggingLevel) {
      case DEBUG:
        try {
          debug(message);
        } catch (FileMaxSizeReachedException e) {
          throw new RuntimeException(e);
        }
        break;
      case INFO:
        try {
          info(message);
        } catch (FileMaxSizeReachedException e) {
          throw new RuntimeException(e);
        }
        break;
    }
  }

  private void debug(String message) throws FileMaxSizeReachedException {
    File file = new File(configuration.getDestinationFile() + "." + configuration.getFileFormat());
    try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true))) {
      String text = "[" + LocalTime.now().toString() + "][DEBUG] Повідомлення: [" + message + "]\n";
      byte[] textSize = text.getBytes("UTF-8");
      checkFileSize(currentSize, textSize.length);
      bufferWriter.write(text);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void info(String message) throws FileMaxSizeReachedException {
    File file = new File(configuration.getDestinationFile() + "." + configuration.getFileFormat());
    try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true))) {
      String text = "[" + LocalTime.now().toString() + "][INFO] Повідомлення: [" + message + "]\n";
      byte[] textSize = text.getBytes("UTF-8");
      checkFileSize(currentSize, textSize.length);
      bufferWriter.write(text);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void checkFileSize(long currentSize, long textSize) throws FileMaxSizeReachedException {
    if (currentSize + textSize > configuration.getMaxSizeFile()) {
      throw new FileMaxSizeReachedException(
          "Over max size " + configuration.getMaxSizeFile() + " Size of file will be " + (
              currentSize + textSize));
    }
  }


}

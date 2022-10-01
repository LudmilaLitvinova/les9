package ua.hillellit.lms.model;

import java.io.IOException;
import ua.hillellit.lms.exceptions.FileMaxSizeReachedException;

public interface Logable {

  public void debug(String message) throws FileMaxSizeReachedException, IOException;
  public void info(String message) throws FileMaxSizeReachedException, IOException;

}

package ua.hillellit.lms.model;

import java.io.IOException;

public interface Logable {

  void log(String message) throws IOException;

}

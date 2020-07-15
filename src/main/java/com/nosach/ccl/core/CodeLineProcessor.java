package com.nosach.ccl.core;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Defines processing lines of code
 */
public interface CodeLineProcessor {

  /**
   * Count number of lines of code
   *
   * @param path path to file
   * @return number of code's lines
   * @throws IOException if issue with file processing
   */
  int processFile(Path path) throws IOException;
}

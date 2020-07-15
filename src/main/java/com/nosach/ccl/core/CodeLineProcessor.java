package com.nosach.ccl.core;

import java.util.stream.Stream;

/**
 * Defines processing lines of code
 */
public interface CodeLineProcessor {

  /**
   * Count number of lines of code
   *
   * @param stream stream of Strings of the file
   * @return number of code's lines
   */
  int processFileLines(Stream<String> stream);
}

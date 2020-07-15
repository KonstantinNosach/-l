package com.nosach.ccl.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CodeLineProcessorImpl implements CodeLineProcessor {

  /**
   * {@inheritDoc}
   */
  @Override
  public int processFile(Path path) throws IOException {
    List<String> lines = null;
    try (Stream<String> linesStream = Files.lines(path)) {
      lines = linesStream.map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
    }
    int count = 0;
    boolean multilineComment = false;
    for (String line : lines) {
      if (line.startsWith("/*")) {
        // need to check where is end, can be on on the same line
        int index = line.lastIndexOf("*/");
        if (index == -1) {
          // this is multiline comment started
          multilineComment = true;
        } else {
          // need to check do we have code here on this line
          String subString = line.substring(index + 2);
          if (!subString.isEmpty() && !subString.startsWith("//")) {
            count++;
          }
        }
      } else if (!multilineComment && !line.startsWith("//")) {
        count++;
        //after code we can have started multiline comment
        if (containsStartMultiline(line)) {
          multilineComment = true;
        }
      } else {
        int index = line.lastIndexOf("*/");
        if (index != -1) {
          multilineComment = false;
          // need to check do we have code here on this line
          String subString = line.substring(index + 2);
          if (!subString.isEmpty() && !subString.startsWith("//")) {
            count++;
          }
        }
      }
    }
    return count;
  }

  private boolean containsStartMultiline(String line) {
    // after code we can have started multiline comment
    int startIndex = line.lastIndexOf("/*");
    if (startIndex != -1) {
      int endIndex = line.lastIndexOf("*/");
      int quoteEnd = line.lastIndexOf("\"");
      int quoteStart = line.substring(0, quoteEnd).lastIndexOf("\"");
      if (quoteStart != -1 && quoteEnd != -1 && startIndex < quoteEnd && startIndex > quoteStart) {
        return false;
      } else if (endIndex == -1 || startIndex > endIndex) {
        return true;
      }
    }
    return false;
  }
}

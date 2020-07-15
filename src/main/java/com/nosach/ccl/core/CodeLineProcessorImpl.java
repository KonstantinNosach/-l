package com.nosach.ccl.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class CodeLineProcessorImpl implements CodeLineProcessor {

  /**
   * {@inheritDoc}
   */
  @Override
  public int processFile(Path path) throws IOException {
    AtomicInteger count = new AtomicInteger(0);
    AtomicBoolean multilineComment = new AtomicBoolean(false);
    try (Stream<String> linesStream = Files.lines(path)) {
      linesStream.map(String::trim).filter(s -> !s.isEmpty()).forEach(line -> {
        if (line.startsWith("/*")) {
          // need to check where is end, can be on on the same line
          int index = line.lastIndexOf("*/");
          if (index == -1) {
            // this is multiline comment started
            multilineComment.set(true);
          } else {
            // need to check do we have code here on this line
            String subString = line.substring(index + 2);
            if (!subString.isEmpty() && !subString.startsWith("//")) {
              count.getAndIncrement();
            }
          }
        } else if (!multilineComment.get() && !line.startsWith("//")) {
          count.getAndIncrement();
          //after code we can have started multiline comment
          if (containsStartMultiline(line)) {
            multilineComment.set(true);
          }
        } else {
          int index = line.lastIndexOf("*/");
          if (index != -1) {
            multilineComment.set(false);
            // need to check do we have code here on this line
            String subString = line.substring(index + 2);
            if (!subString.isEmpty() && !subString.startsWith("//")) {
              count.getAndIncrement();
            }
          }
        }
      });
    }
    return count.get();
  }

  private boolean containsStartMultiline(String line) {
    // after code we can have started multiline comment
    int startIndex = line.lastIndexOf("/*");
    if (startIndex != -1) {
      int endIndex = line.lastIndexOf("*/");
      int quoteEnd = line.lastIndexOf("\"");
      int quoteStart = line.substring(0, quoteEnd).lastIndexOf("\"");
      // skip case when start comment /* inside quotes
      if (quoteStart != -1 && quoteEnd != -1 && startIndex < quoteEnd && startIndex > quoteStart) {
        return false;
      } else if (endIndex == -1 || startIndex > endIndex) {
        return true;
      }
    }
    return false;
  }
}

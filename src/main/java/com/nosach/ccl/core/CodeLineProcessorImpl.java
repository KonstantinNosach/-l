package com.nosach.ccl.core;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CodeLineProcessorImpl implements CodeLineProcessor {

  /**
   * {@inheritDoc}
   */
  @Override
  public int processFileLines(Stream<String> stream) {
    int count = 0;
    boolean commentStarted = false;
    List<String> lines = stream.collect(Collectors.toList());
    for (String line : lines) {
      line = line.trim();
      if (line.isEmpty() || (line.startsWith("//") && !commentStarted)) {
        continue;
      } else {
        if (commentStarted) {
          if (commentEnded(line)) {
            line = line.substring(line.indexOf("*/") + 2).trim();
            commentStarted = false;
            if (line.isEmpty() || line.startsWith("//")) {
              continue;
            }
          } else {
            continue;
          }
        }
        if (isSourceCodeLine(line)) {
          count++;
        }
        if (commentStarted(line)) {
          commentStarted = true;
        }
      }
    }
    return count;
  }

  private boolean commentStarted(String line) {
    int commentStartIndex = line.indexOf("/*");
    if (commentStartIndex == -1) {
      return false;
    }
    int quoteIndex = line.indexOf("\"");
    if (quoteIndex != -1 && quoteIndex < commentStartIndex) {
      while (quoteIndex != -1) {
        line = line.substring(quoteIndex + 1);
        int quoteEndIndex = line.indexOf("\"");
        line = line.substring(quoteEndIndex + 1);
        quoteIndex = line.indexOf("\"");
      }
      return commentStarted(line);
    }
    return !commentEnded(line.substring(commentStartIndex + 2));
  }

  private boolean commentEnded(String line) {
    int index = line.indexOf("*/");
    if (index == -1) {
      return false;
    } else {
      String subString = line.substring(index + 2).trim();
      if (subString.isEmpty() || subString.startsWith("//")) {
        return true;
      }
      if (commentStarted(subString)) {
        return false;
      } else {
        return true;
      }
    }
  }

  private boolean isSourceCodeLine(String line) {
    boolean isSourceCodeLine = false;
    line = line.trim();
    if (line.isEmpty() || line.startsWith("//")) {
      return isSourceCodeLine;
    }
    if (line.length() == 1) {
      return true;
    }
    int index = line.indexOf("/*");
    if (index != 0) {
      return true;
    } else {
      while (line.length() > 0) {
        line = line.substring(index + 2);
        int endCommentIndex = line.indexOf("*/");
        if (endCommentIndex < 0 || endCommentIndex == line.length() - 2) {
          return false;
        } else {
          String subString = line.substring(endCommentIndex + 2).trim();
          if (subString.isEmpty() || subString.startsWith("//")) {
            return false;
          } else {
            if (subString.startsWith("/*")) {
              line = subString;
              continue;
            }
            return true;
          }
        }
      }
    }
    return isSourceCodeLine;
  }
}

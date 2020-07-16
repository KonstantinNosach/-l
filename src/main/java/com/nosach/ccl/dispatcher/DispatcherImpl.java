package com.nosach.ccl.dispatcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.nosach.ccl.core.CodeLineProcessor;
import com.nosach.ccl.core.CodeLineProcessorImpl;
import com.nosach.ccl.output.OutputProcessor;
import com.nosach.ccl.output.OutputProcessorImpl;
import com.nosach.ccl.path.PathProcessor;
import com.nosach.ccl.path.PathProcessorImpl;

public class DispatcherImpl implements Dispatcher {

  private PathProcessor pathProcessor = new PathProcessorImpl();
  private CodeLineProcessor codeProcessor = new CodeLineProcessorImpl();
  private OutputProcessor outputProcessor = new OutputProcessorImpl();

  /**
   * {@inheritDoc}
   */
  @Override
  public void processInputData(String input) {
    Map<String, Integer> output = new LinkedHashMap<>();
    System.out.println("Will check path: " + input);
    List<Path> paths = pathProcessor.getListOfPaths(input);
    if (paths.isEmpty()) {
      System.out.println("Cannot find files by path: " + input + ". Please try another one");
      return;
    }
    boolean singleFile = paths.size() == 1;
    for (Path path : paths) {
      try {
        String fileName = path.getFileName().toString();
        if (fileName.endsWith(".java")) {
          int number = codeProcessor.processFile(path);
          String folderName = Paths.get(path.toString().replace(File.separator + fileName, ""))
              .getFileName().toString();
          Integer value = output.computeIfPresent(folderName, (k, v) -> v += number);
          if (value == null && !singleFile) {
            output.put(folderName, number);
          }
          output.put(fileName, number);
        }
      } catch (IOException e) {
        System.err.println("Cannot process: " + input + " Error : " + e.getMessage());
      }
    }
    outputProcessor.printOut(output);
  }

}

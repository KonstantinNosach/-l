package com.nosach.ccl.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathProcessorImpl implements PathProcessor {

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Path> getListOfPaths(String pathString) {
    List<Path> paths = new ArrayList<>();
    Path path = Paths.get(pathString);
    if (Files.exists(path)) {
      if (Files.isDirectory(path)) {
        System.out.println("process directory");
        try (Stream<Path> s = Files.find(Paths.get(pathString), Integer.MAX_VALUE,
            (filePath, fileAttr) -> fileAttr.isRegularFile())) {
          paths.addAll(s.collect(Collectors.toList()));
        } catch (IOException e) {
          System.err.println("Cannot get files from path: " + pathString);
        }
      } else {
        System.out.println("process file");
        paths.add(path);
      }
    }
    return paths;
  }
}

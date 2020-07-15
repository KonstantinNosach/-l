package com.nosach.ccl.path;

import java.nio.file.Path;
import java.util.List;

/**
 * Defines processing path to folder/file
 */
public interface PathProcessor {

  /**
   * Creates list of paths recursively.
   *
   * @param path path to folder or file. It is start point in search.
   * @return list of paths
   */
  List<Path> getListOfPaths(String path);
}

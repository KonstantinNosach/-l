package com.nosach.ccl.output;

import java.util.Map;

public interface OutputProcessor {

  /**
   * Prints results out
   *
   * @param data results which should be printed out
   */
  void printOut(Map<String, Integer> data);
}

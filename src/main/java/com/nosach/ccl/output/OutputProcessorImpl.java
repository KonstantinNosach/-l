package com.nosach.ccl.output;

import java.util.Map;
import java.util.Map.Entry;

public class OutputProcessorImpl implements OutputProcessor {

  /**
   * {@inheritDoc}
   */
  @Override
  public void printOut(Map<String, Integer> data) {
    if (data.isEmpty()) {
      System.out.println("Did not find java files");
      return;
    }
    for (Entry<String, Integer> entry : data.entrySet()) {
      String key = entry.getKey();
      if (!key.endsWith(".java")) {
        System.out.println();
      }
      System.out.print(entry.getKey() + " : " + entry.getValue() + " ");
    }
    System.out.println();
  }

}

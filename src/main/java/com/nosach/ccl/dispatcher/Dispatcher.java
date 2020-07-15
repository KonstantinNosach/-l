package com.nosach.ccl.dispatcher;

/**
 * Defines interaction of processors which we use for application's work
 */
public interface Dispatcher {

  /**
   * Process business logic
   *
   * @param input path to folder or file which need to be processed
   */
  void processInputData(String input);
}

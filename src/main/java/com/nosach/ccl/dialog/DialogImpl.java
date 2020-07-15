package com.nosach.ccl.dialog;

import java.util.Scanner;

import com.nosach.ccl.dispatcher.Dispatcher;

public class DialogImpl implements Dialog {

  private static final String EXIT = "exit";
  private boolean stopped = false;
  private Dispatcher dispatcher;

  public DialogImpl(Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start() {
    System.out.println("Please enter path to file. For exit type exit");
    while (!stopped) {
      Scanner sc = new Scanner(System.in);
      String input = sc.next();
      if (input.equalsIgnoreCase(EXIT)) {
        stopped = true;
        sc.close();
      } else {
        dispatcher.processInputData(input);
      }
    }

  }

}

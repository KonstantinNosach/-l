package com.nosach.ccl;

import com.nosach.ccl.dialog.Dialog;
import com.nosach.ccl.dialog.DialogImpl;
import com.nosach.ccl.dispatcher.Dispatcher;
import com.nosach.ccl.dispatcher.DispatcherImpl;

/**
 * Entry point of the application
 */
public class Starter {

  public static void main(String[] args) {
    Dispatcher dispatcher = new DispatcherImpl();
    Dialog dialog = new DialogImpl(dispatcher);
    dialog.start();
  }
}

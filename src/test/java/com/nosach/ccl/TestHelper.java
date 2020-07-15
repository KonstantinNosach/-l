package com.nosach.ccl;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class TestHelper {

  public static String getPath(String resource) {
    URI uri;
    try {
      uri = TestHelper.class.getResource(resource).toURI();
    } catch (URISyntaxException e) {
      return null;
    }
    return Paths.get(uri).toString();
  }
}

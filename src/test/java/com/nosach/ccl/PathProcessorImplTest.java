package com.nosach.ccl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;
import org.junit.Test;

import com.nosach.ccl.path.PathProcessor;
import com.nosach.ccl.path.PathProcessorImpl;

/**
 * Unit test for PathProcessor methods implementation
 * 
 */
public class PathProcessorImplTest {

  private PathProcessor pathProcessor = new PathProcessorImpl();
  private String pathString = TestHelper.getPath("/folder1");
  private String notExistedPath = "notExistedPath";

  /**
   * Test method for {@link com.nosach.ccl.path.PathProcessorImpl#getListOfPaths(String)}
   * 
   */
  @Test
  public void testGetListOfPaths() throws URISyntaxException {
    List<Path> paths = pathProcessor.getListOfPaths(pathString);
    assertNotNull("List of paths is null", paths);
    assertEquals("List of paths is not equal 2", 2, paths.size());

    paths = pathProcessor.getListOfPaths(notExistedPath);
    assertNotNull("List of paths is null", paths);
    assertTrue("List of paths is not empty", paths.isEmpty());
  }
}

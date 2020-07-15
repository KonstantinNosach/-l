package com.nosach.ccl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.Test;

import com.nosach.ccl.core.CodeLineProcessor;
import com.nosach.ccl.core.CodeLineProcessorImpl;

/**
 * Unit test for CodeLineProcessor methods implementation
 * 
 */
public class CodeLineProcessorImplTest {

  private CodeLineProcessor codeLineProcessor = new CodeLineProcessorImpl();
  private String filePath1 = TestHelper.getPath("/folder1/Dave.java"); 
  private String filePath2 = TestHelper.getPath("/folder1/folder2/Hello.java");
  private String filePath3 = TestHelper.getPath("/Java.java");
  private String filePath4 = TestHelper.getPath("/Aloha.java");

  /**
   * Test method for {@link com.nosach.ccl.core.CodeLineProcessorImpl#processFile(Stream)}
   * 
   */
  @Test
  public void testProcessFile() {
    int result = 0;
    try {
      result = codeLineProcessor.processFile(Path.of(filePath1));
    } catch (IOException e) {
      fail();
    }
    assertEquals(3, result);
    try {
      result = codeLineProcessor.processFile(Path.of(filePath2));
    } catch (IOException e) {
      fail();
    }
    assertEquals(5, result);
    try {
      result = codeLineProcessor.processFile(Path.of(filePath3));
    } catch (IOException e) {
      fail();
    }
    assertEquals(9, result);
    try {
      result = codeLineProcessor.processFile(Path.of(filePath4));
    } catch (IOException e) {
      fail();
    }
    assertEquals(6, result);
  }
}
